package com.mercdev.rybakin.rss.engine;

import java.net.URI;

public class Image {
	private final URI url;
	private final String title;
	private final String link;

	private int width;
	private int height;
	private String description;

	public Image(String url, String title, String link) {
		this.url = URI.create(url);
		this.title = title;
		this.link = link;
	}

	public Image(URI url, String title, String link) {
		this.url = url;
		this.title = title;
		this.link = link;
	}

	public URI getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Image{" +
				"url=" + url +
				", title='" + title + '\'' +
				", link='" + link + '\'' +
				", width=" + width +
				", height=" + height +
				", description='" + description + '\'' +
				'}';
	}
}