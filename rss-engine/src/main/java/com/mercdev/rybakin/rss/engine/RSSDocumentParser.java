package com.mercdev.rybakin.rss.engine;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static com.mercdev.rybakin.rss.engine.RSSContract.RSS_ROOT;
import static com.mercdev.rybakin.rss.engine.RSSContract.RSS_SUPPORTED_VERSION;
import static com.mercdev.rybakin.rss.engine.RSSContract.RSS_VERSION_ATTR;

public final class RSSDocumentParser {
	public static Channel parse(InputStream streamToParse) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document document = builder.parse(streamToParse);
		document.normalizeDocument();

		System.out.println("Strat parsing...");

		// Is it rss?
		NodeList mainTag = document.getElementsByTagName(RSS_ROOT);
		if (mainTag.getLength() != 0 && !Objects.equals(mainTag.item(0).getAttributes().getNamedItem(RSS_VERSION_ATTR).getNodeValue(), RSS_SUPPORTED_VERSION)) {
			throw new Exception(String.format("Sorry, this parser works only with rss version %s", RSS_SUPPORTED_VERSION));
		}

		NodeList channelNodes = document.getElementsByTagName(RSSContract.Channel.ROOT_TAG);

		// Does this file contains only one channel tag?
		if (channelNodes.getLength() != 1) {
			throw new Exception("There's an error in the source rss file!");
		}
		Element channelElement = (Element) channelNodes.item(0);

		// Extract required fields for channel
		String title = extractValueByTag(channelElement, RSSContract.Channel.TITLE_TAG);
		String description = extractValueByTag(channelElement, RSSContract.Channel.DESCRIPTION_TAG);
		String link = extractValueByTag(channelElement, RSSContract.Channel.LINK_TAG);

		Channel channel = new Channel(title, link, description);

		parseChannelElement(channel, channelElement);
		return channel;
	}

	private static void parseChannelElement(Channel channel, Element channelElement) {
		NodeList nodes = channelElement.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (!Objects.equals(node.getNodeName(), "#text")) {
				Element element = (Element) node;
				RSSContract.TagInfo tagInfo = RSSContract.Channel.TAGS.get(element.getTagName());
				if (tagInfo != null) {
					if (tagInfo.isHasChildren()) {
						switch (element.getTagName()) {
							case RSSContract.Cloud.ROOT_TAG:
								channel.setCloud(parseCloudElement(element));
								break;
							case RSSContract.Image.ROOT_TAG:
								channel.setImage(parseImageElement(element));
								break;
							case RSSContract.TextInput.ROOT_TAG:
								channel.setTextInput(parseTextInput(element));
								break;
							case RSSContract.SkipDays.ROOT_TAG:
								channel.setSkipDays(parseSkipDays(element));
								break;
							case RSSContract.SkipHours.ROOT_TAG:
								channel.setSkipHours(parseSkipHours(element));
								break;
							case RSSContract.Item.ROOT_TAG:
								channel.addItem(parseItemElement(element));
								break;
							default:
						}
					} else {
						try {
							channel.getClass().getDeclaredMethod(tagInfo.getSetter(), new Class[] { String.class }).invoke(channel, element.getTextContent());
						} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
							System.err.println("Unexpected error occurred. Nothing fatal.");
						}
					}
				}
			}
		}
	}

	private static Item parseItemElement(Element itemElement) {
		String title = extractValueByTag(itemElement, RSSContract.Item.TITLE);
		String description = extractValueByTag(itemElement, RSSContract.Item.DESCRIPTION);

		Item item = new Item(title, description);
		NodeList nodes = itemElement.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (!Objects.equals(node.getNodeName(), "#text")) {
				Element element = (Element) node;
				RSSContract.TagInfo tagInfo = RSSContract.Item.TAGS.get(element.getTagName());
				if (tagInfo != null) {
					if (tagInfo.isHasChildren()) {
						switch (element.getTagName()) {
							case RSSContract.Item.Enclosure.ROOT_TAG:
								item.setEnclosure(parseEnclosure(element));
								break;
							case RSSContract.Item.Source.ROOT_TAG:
								item.setSource(parseSource(element));
								break;
							default:
						}
					} else {
						try {
							item.getClass().getDeclaredMethod(tagInfo.getSetter(), new Class[] { String.class }).invoke(item, element.getTextContent());
						} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return item;
	}

	private static Source parseSource(Element element) {
		Source result = null;
		try {
			NamedNodeMap attributes = element.getAttributes();
			String url = attributes.getNamedItem(RSSContract.Item.Source.URL_ATTR).getNodeValue();
			String name = element.getTextContent();
			result = new Source(url, name);
		} catch (Exception exception) {
			System.err.println(String.format("There's an error in <%s> tag!", element.getTagName()));
		}
		return result;
	}

	private static Enclosure parseEnclosure(Element element) {
		Enclosure result = null;
		try {
			NamedNodeMap attributes = element.getAttributes();
			String url = attributes.getNamedItem(RSSContract.Item.Enclosure.URL_ATTR).getNodeValue();
			long length = Long.parseLong(attributes.getNamedItem(RSSContract.Item.Enclosure.LENGTH_ATTR).getNodeValue());
			String type = attributes.getNamedItem(RSSContract.Item.Enclosure.TYPE_ATTR).getNodeValue();
			result = new Enclosure(url, length, type);
		} catch (Exception exception) {
			System.err.println(String.format("There's an error in <%s> tag!", element.getTagName()));
		}
		return result;
	}

	private static List<Integer> parseSkipHours(Element element) {
		List<Integer> result = new ArrayList<>();
		NodeList nodes = element.getElementsByTagName(RSSContract.SkipHours.HOUR);
		for (int i = 0; i < nodes.getLength(); i++) {
			try {
				result.add(Integer.parseInt(nodes.item(i).getTextContent()));
			} catch (Exception exception) {
				System.err.println(String.format("There's an error in <%s> tag!", element.getTagName()));
			}
		}
		return result;
	}

	private static List<String> parseSkipDays(Element element) {
		List<String> result = new ArrayList<>();
		NodeList nodes = element.getElementsByTagName(RSSContract.SkipDays.DAY);
		for (int i = 0; i < nodes.getLength(); i++) {
			try {
				result.add(nodes.item(i).getTextContent());
			} catch (Exception exception) {
				System.err.println(String.format("There's an error in <%s> tag!", element.getTagName()));
			}
		}
		return result;
	}

	private static TextInput parseTextInput(Element element) {
		TextInput result = null;
		try {
			String title = extractValueByTag(element, RSSContract.TextInput.TITLE);
			String description = extractValueByTag(element, RSSContract.TextInput.DESCRIPTION);
			String name = extractValueByTag(element, RSSContract.TextInput.NAME);
			String link = extractValueByTag(element, RSSContract.TextInput.LINK);
			result = new TextInput(title, description, name, link);
		} catch (Exception exception) {
			System.err.println(String.format("There's an error in <%s> tag!", element.getTagName()));
		}
		return result;
	}

	private static Image parseImageElement(Element element) {
		Image result = null;
		try {
			String url = extractValueByTag(element, RSSContract.Image.URL);
			String title = extractValueByTag(element, RSSContract.Image.TITLE);
			String link = extractValueByTag(element, RSSContract.Image.LINK);
			result = new Image(url, title, link);
			String width = extractValueByTag(element, RSSContract.Image.WIDTH);
			String height = extractValueByTag(element, RSSContract.Image.HEIGHT);
			String description = extractValueByTag(element, RSSContract.Image.DESCRIPTION);
			if (description != null) {
				result.setDescription(description);
			}
			if (height != null) {
				result.setHeight(Integer.parseInt(height));
			}
			if (width != null) {
				result.setWidth(Integer.parseInt(width));
			}
		} catch (Exception exception) {
			System.err.println(String.format("There's an error in <%s> tag!", element.getTagName()));
		}
		return result;
	}

	private static Cloud parseCloudElement(Element rootElement) {
		Cloud result = null;
		try {
			NamedNodeMap attributes = rootElement.getAttributes();
			String domain = attributes.getNamedItem(RSSContract.Cloud.DOMAIN_ATTR).getNodeValue();
			String port = attributes.getNamedItem(RSSContract.Cloud.PORT_ATTR).getNodeValue();
			String path = attributes.getNamedItem(RSSContract.Cloud.PATH_ATTR).getNodeValue();
			String registerProcedure = attributes.getNamedItem(RSSContract.Cloud.REGISTER_PROCEDURE_ATTR).getNodeValue();
			String protocol = attributes.getNamedItem(RSSContract.Cloud.PROTOCOL_ATTR).getNodeValue();
			result = new Cloud(domain, port, path, registerProcedure, protocol);
		} catch (Exception exception) {
			System.err.println(String.format("There's an error in <%s> tag!", rootElement.getTagName()));
		}
		return result;
	}

	private static String extractValueByTag(Element rootElement, String tag) {
		Node node = rootElement.getElementsByTagName(tag).item(0);
		String result = null;
		if (node != null) {
			result = node.getTextContent();
		}
		return result;
	}
}
