package com.mercdev.rybakin.rssreader.repo.entities;

public class FeedItem {
	private final String title;
	private final String link;

	public FeedItem(String title, String link) {
		this.title = title;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}
}
