package com.mercdev.rybakin.rssreader.tasks.feed;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.mercdev.rybakin.rssreader.repo.entities.FeedItem;
import com.octo.android.robospice.request.SpiceRequest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FeedLoadTask extends SpiceRequest<List<FeedItem>> {
	private static final String TAG = "FeedLoadTask";

	@SuppressWarnings("unchecked")
	private static final Class<List<FeedItem>> LIST_CLASS_WORKAROUND = (Class<List<FeedItem>>) (Class<?>) List.class;
	private final String targetUrl;

	public FeedLoadTask(String url) {
		super(LIST_CLASS_WORKAROUND);
		targetUrl = url;
	}

	@Override
	public List<FeedItem> loadDataFromNetwork() throws Exception {
		ArrayList<FeedItem> feedItems = null;

		URL url = new URL(targetUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream is = conn.getInputStream();

			DocumentBuilderFactory dbf = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document document = db.parse(is);
			Element element = document.getDocumentElement();

			NodeList nodeList = element.getElementsByTagName("item");
			if (nodeList.getLength() > 0) {
				feedItems = new ArrayList<>();
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element entry = (Element) nodeList.item(i);

					Element titleElement = (Element) entry.getElementsByTagName(
							"title").item(0);
					Element descriptionElement = (Element) entry
							.getElementsByTagName("description").item(0);
					Element pubDateElement = (Element) entry
							.getElementsByTagName("pubDate").item(0);
					Element linkElement = (Element) entry.getElementsByTagName(
							"link").item(0);

					String title = titleElement.getFirstChild().getNodeValue();
					String description = descriptionElement.getFirstChild().getNodeValue();
					Date pubDate = DateFormat.getDateInstance().parse(pubDateElement.getFirstChild().getNodeValue());
					String link = linkElement.getFirstChild().getNodeValue();

					FeedItem rssItem = new FeedItem(title, description, pubDate, link);

					feedItems.add(rssItem);
				}
			}
		}
		return feedItems;
	}
}
