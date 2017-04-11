package com.mercdev.rybakin.rss.engine;

import java.net.URI;

public class TextInput {
	private final String title;
	private final String description;
	private final String name;
	private final URI link;

	public TextInput(String title, String description, String name, String link) {
		this.title = title;
		this.description = description;
		this.name = name;
		this.link = URI.create(link);
	}

	public TextInput(String title, String description, String name, URI link) {
		this.title = title;
		this.description = description;
		this.name = name;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public URI getLink() {
		return link;
	}

	@Override
	public String toString() {
		return "TextInput{" +
				"title='" + title + '\'' +
				", description='" + description + '\'' +
				", name='" + name + '\'' +
				", link=" + link +
				'}';
	}
}
