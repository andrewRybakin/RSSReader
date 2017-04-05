package com.mercdev.rybakin.rssreader.repo.entities;

import java.util.Date;

public class FeedItem {
	private final String title;
	private final String description;
	private final Date pubDate;
	private final String url;

	public FeedItem(String title, String description, Date pubDate, String url) {
		this.title = title;
		this.description = description;
		this.pubDate = pubDate;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public String getUrl() {
		return url;
	}
}
