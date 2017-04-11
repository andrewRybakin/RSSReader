package com.mercdev.rybakin.rss.engine;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents RSS channel. All parameters in constructor are required.<br/>
 * All other fields in class that could be set by setters are optional.
 *
 * @see <a href=https://cyber.harvard.edu/rss/rss.html>RSS2.0 specifications</a><br/>
 * <a href=https://cyber.harvard.edu/rss/rss.html#requiredChannelElements>Required channel elements</a><br/>
 * <a href=https://cyber.harvard.edu/rss/rss.html#optionalChannelElements>Optional channel elements</a>
 */
@SuppressWarnings("unused")
public class Channel {
	/* Required fields */
	private final String title;
	private final URI link;
	private final String description;

	/* Optional fields */
	private final List<String> categories = new ArrayList<>();
	private final List<Integer> skipHours = new ArrayList<>();
	private final List<String> skipDays = new ArrayList<>();
	private final List<Item> items = new ArrayList<>();

	private String language;
	private String copyright;
	private String managingEditor;
	private String webMaster;
	private Date pubDate;
	private Date lastBuildDate;
	private String generator;
	private URI docs;
	private int ttl;
	private Image image;
	private Cloud cloud;
	private TextInput textInput;
	private String rating;

	Channel(String title, String link, String description) {
		this.title = title;
		this.link = URI.create(link);
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public URI getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getLanguage() {
		return language;
	}

	void setLanguage(String language) {
		this.language = language;
	}

	public String getCopyright() {
		return copyright;
	}

	void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getManagingEditor() {
		return managingEditor;
	}

	void setManagingEditor(String managingEditor) {
		this.managingEditor = managingEditor;
	}

	public String getWebMaster() {
		return webMaster;
	}

	void setWebMaster(String webMaster) {
		this.webMaster = webMaster;
	}

	public Date getPubDate() {
		return pubDate;
	}

	void setPubDate(String pubDate) {
		try {
			this.pubDate = RSSContract.DATE_FORMATTER.parse(pubDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getLastBuildDate() {
		return lastBuildDate;
	}

	void setLastBuildDate(String lastBuildDate) {
		try {
			this.lastBuildDate = RSSContract.DATE_FORMATTER.parse(lastBuildDate);
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	public List<String> getCategories() {
		return categories;
	}

	void addCategory(String category) {
		this.categories.add(category);
	}

	public String getGenerator() {
		return generator;
	}

	void setGenerator(String generator) {
		this.generator = generator;
	}

	public URI getDocs() {
		return docs;
	}

	void setDocs(String docs) {
		this.docs = URI.create(docs);
	}

	public int getTTL() {
		return ttl;
	}

	void setTTL(String ttl) {
		this.ttl = Integer.parseInt(ttl);
	}

	public List<Integer> getSkipHours() {
		return skipHours;
	}

	void setSkipHours(List<Integer> skipHours) {
		this.skipHours.clear();
		this.skipHours.addAll(skipHours);
	}

	public List<String> getSkipDays() {
		return skipDays;
	}

	void setSkipDays(List<String> skipDays) {
		this.skipDays.clear();
		this.skipDays.addAll(skipDays);
	}

	public Image getImage() {
		return image;
	}

	void setImage(Image image) {
		this.image = image;
	}

	public Cloud getCloud() {
		return cloud;
	}

	void setCloud(Cloud cloud) {
		this.cloud = cloud;
	}

	public TextInput getTextInput() {
		return textInput;
	}

	void setTextInput(TextInput textInput) {
		this.textInput = textInput;
	}

	public List<Item> getItems() {
		return items;
	}

	void addItem(Item items) {
		this.items.add(items);
	}

	public String getRating() {
		return rating;
	}

	void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Channel{" +
				"\n\ttitle = '" + title + '\'' +
				"\n\tlink = " + link +
				"\n\tdescription = '" + description + '\'' +
				"\n\tcategories = " + categories +
				"\n\tskipHours = " + skipHours +
				"\n\tskipDays = " + skipDays +
				"\n\titems = " + items.size() +
				"\n\tlanguage = '" + language + '\'' +
				"\n\tcopyright = '" + copyright + '\'' +
				"\n\tmanagingEditor = '" + managingEditor + '\'' +
				"\n\twebMaster = '" + webMaster + '\'' +
				"\n\tpubDate = " + pubDate +
				"\n\tlastBuildDate = " + lastBuildDate +
				"\n\tgenerator = '" + generator + '\'' +
				"\n\tdocs = " + docs +
				"\n\tttl = " + ttl +
				"\n\timage = " + image +
				"\n\tcloud = " + cloud +
				"\n\ttextInput = " + textInput +
				"\n\trating = '" + rating + '\'' +
				"\n}";
	}
}
