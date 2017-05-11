package com.mercdev.rybakin.rssreader.state.model;

import java.util.Date;

import com.mercdev.rybakin.rssreader.repo.entities.ArticleEntity;

public class Article {
	private final String title;
	private final String description;

	private String author;
	private String comments;
	private String guid;
	private Date pubDate;

	private Article(String title, String description) {
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	private void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public String getComments() {
		return comments;
	}

	private void setComments(String comments) {
		this.comments = comments;
	}

	public String getGuid() {
		return guid;
	}

	private void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getPubDate() {
		return pubDate;
	}

	private void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public static Article buildFromEntity(ArticleEntity entity) {
		Article result = new Article(entity.getTitle(), entity.getDescription());
		result.setPubDate(new Date(entity.getPubDate()));
		result.setAuthor(entity.getAuthor());
		result.setComments(entity.getComments());
		result.setGuid(entity.getGuid());
		return result;
	}
}
