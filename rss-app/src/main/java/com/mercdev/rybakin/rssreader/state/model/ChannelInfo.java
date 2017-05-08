package com.mercdev.rybakin.rssreader.state.model;

public class ChannelInfo {
	private final String name;
	private final String url;
	private final String imageUrl;

	public ChannelInfo(String name, String url, String imageUrl) {
		this.name = name;
		this.url = url;
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getImageUrl() {
		return imageUrl;
	}
}
