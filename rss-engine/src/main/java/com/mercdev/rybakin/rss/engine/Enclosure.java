package com.mercdev.rybakin.rss.engine;

import java.net.URI;

@SuppressWarnings("unused")
public class Enclosure {
	private final URI url;
	private final long length;
	private final String type;

	Enclosure(String url, long length, String type) {
		this.url = URI.create(url);
		this.length = length;
		this.type = type;
	}

	public URI getUrl() {
		return url;
	}

	public long getLength() {
		return length;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Enclosure{" +
				"url=" + url +
				", length=" + length +
				", type='" + type + '\'' +
				'}';
	}
}