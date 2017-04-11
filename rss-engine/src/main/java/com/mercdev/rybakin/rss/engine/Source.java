package com.mercdev.rybakin.rss.engine;

import java.net.URI;

public class Source {
	private final URI url;
	private final String name;

	Source(String url, String name) {
		this.url = URI.create(url);
		this.name = name;
	}

	public URI getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Source{" +
				"url=" + url +
				", name='" + name + '\'' +
				'}';
	}
}