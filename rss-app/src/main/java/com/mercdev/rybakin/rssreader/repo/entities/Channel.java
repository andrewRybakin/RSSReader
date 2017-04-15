package com.mercdev.rybakin.rssreader.repo.entities;

import java.util.Objects;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "channel")
public class Channel {
	public static final String LINK_COLUMN_NAME = "link";

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false)
	private String title;
	@DatabaseField(canBeNull = false, unique = true, columnName = LINK_COLUMN_NAME)
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

	@ForeignCollectionField
	private ForeignCollection<ChannelItem> items;

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

	public ForeignCollection<ChannelItem> getItems() {
		return items;
	}

	public void setItems(ForeignCollection<ChannelItem> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Channel channel = (Channel) o;
		return id == channel.id &&
				pubDate == channel.pubDate &&
				lastBuildDate == channel.lastBuildDate &&
				ttl == channel.ttl &&
				Objects.equals(title, channel.title) &&
				Objects.equals(link, channel.link) &&
				Objects.equals(description, channel.description) &&
				Objects.equals(language, channel.language) &&
				Objects.equals(copyright, channel.copyright) &&
				Objects.equals(imageUrl, channel.imageUrl) &&
				Objects.equals(imageTitle, channel.imageTitle) &&
				Objects.equals(imageLink, channel.imageLink) &&
				Objects.equals(rating, channel.rating) &&
				Objects.equals(items, channel.items);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, link, description, language, copyright, pubDate, lastBuildDate, ttl, imageUrl, imageTitle, imageLink, rating, items);
	}
}
