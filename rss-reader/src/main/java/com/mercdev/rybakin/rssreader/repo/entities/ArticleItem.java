package com.mercdev.rybakin.rssreader.repo.entities;

public class ArticleItem {
	private final String title;
	private final String body;
	private final String url;

	public ArticleItem(String title, String body, String url) {
		this.title = title;
		this.body = body;
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public String getUrl() {
		return url;
	}
}
