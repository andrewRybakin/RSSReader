package com.mercdev.rybakin.rss.engine;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class Item {
	private final String title;
	private final String description;

	private final List<String> category = new ArrayList<>();

	private String author;
	private String comments;
	private Enclosure enclosure;
	private String guid;
	private Date pubDate;
	private Source source;

	Item(String title, String description) {
		if (title != null || description != null) {
			this.title = title;
			this.description = description;
		} else {
			throw new IllegalArgumentException("Title or description mes be specified!");
		}
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getAuthor() {
		return author;
	}

	void setAuthor(String author) {
		this.author = author;
	}

	public List<String> getCategory() {
		return category;
	}

	void addCategory(String category) {
		this.category.add(category);
	}

	public String getComments() {
		return comments;
	}

	void setComments(String comments) {
		this.comments = comments;
	}

	public Enclosure getEnclosure() {
		return enclosure;
	}

	void setEnclosure(Enclosure enclosure) {
		this.enclosure = enclosure;
	}

	public String getGuid() {
		return guid;
	}

	void setGuid(String guid) {
		this.guid = guid;
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

	public Source getSource() {
		return source;
	}

	void setSource(Source source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Item{" +
				"\n\ttitle='" + title + '\'' +
				"\n\tdescription='" + ellipsize(description, 50) + '\'' +
				"\n\tcategory=" + category +
				"\n\tauthor='" + author + '\'' +
				"\n\tcomments=" + comments +
				"\n\tenclosure=" + enclosure +
				"\n\tguid='" + guid + '\'' +
				"\n\tpubDate=" + pubDate +
				"\n\tsource=" + source +
				"\n}";
	}

	private static String ellipsize(String string, int maxLength) {
		String result = string;
		if (maxLength > 3 && string.length() > maxLength - 3) {
			result = result.substring(0, maxLength - 3) + "...";
		}
		return result;
	}
}
