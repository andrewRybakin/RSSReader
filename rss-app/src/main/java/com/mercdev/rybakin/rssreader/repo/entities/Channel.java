package com.mercdev.rybakin.rssreader.repo.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "channel")
public class Channel {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false)
	private String title;
	@DatabaseField(canBeNull = false)
	private String link;
	@DatabaseField(canBeNull = false)
	private String description;

	@DatabaseField
	private String language;
	@DatabaseField
	private String copyright;
	@DatabaseField
	private long pubDate;
	@DatabaseField
	private long lastBuildDate;
	@DatabaseField
	private int ttl;
	@DatabaseField
	private String imageUrl;
	@DatabaseField
	private String imageTitle;
	@DatabaseField
	private String imageLink;
	@DatabaseField
	private String rating;

	public Channel() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public long getPubDate() {
		return pubDate;
	}

	public void setPubDate(long pubDate) {
		this.pubDate = pubDate;
	}

	public long getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(long lastBuildDate) {
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
}
