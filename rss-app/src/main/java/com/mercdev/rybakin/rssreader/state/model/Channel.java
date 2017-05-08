package com.mercdev.rybakin.rssreader.state.model;

import java.util.Date;

import com.mercdev.rybakin.rssreader.repo.entities.ChannelEntity;

@SuppressWarnings({ "WeakerAccess", "unused" })
public class Channel {
	private final String title;
	private final String link;
	private final String description;
	private final String url;

	private String language;
	private String copyright;
	private Date pubDate;
	private Date lastBuildDate;
	private int ttl;
	private String imageUrl;
	private String imageTitle;
	private String imageLink;
	private String rating;

	public Channel(String url, String title, String link, String description) {
		this.url = url;
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public Date getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public void setImageTitle(String imageTitle) {
		this.imageTitle = imageTitle;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public static Channel createFromEntity(ChannelEntity entity) {
		Channel result = null;
		if (entity != null) {
			result = new Channel(entity.getUrl(), entity.getTitle(), entity.getLink(), entity.getDescription());
			result.setPubDate(new Date(entity.getPubDate()));
			result.setTtl(entity.getTtl());
			result.setRating(entity.getRating());
			result.setCopyright(entity.getCopyright());
			result.setImageLink(entity.getImageLink());
			result.setImageTitle(entity.getImageTitle());
			result.setImageUrl(entity.getImageUrl());
			result.setLanguage(entity.getLanguage());
			result.setLastBuildDate(new Date(entity.getLastBuildDate()));
		}
		return result;
	}
}
