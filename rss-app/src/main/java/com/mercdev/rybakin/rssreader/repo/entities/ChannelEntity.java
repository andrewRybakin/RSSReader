package com.mercdev.rybakin.rssreader.repo.entities;

import java.util.Objects;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@SuppressWarnings({ "unused", "WeakerAccess" })
@DatabaseTable(tableName = "channel")
public class ChannelEntity {
	public static final String ID_COLUMN_NAME = "id";
	public static final String TITLE_COLUMN_NAME = "title";
	public static final String URL_COLUMN_NAME = "url";
	public static final String IMAGE_URL_COLUMN_NAME = "image_url";

	@DatabaseField(generatedId = true, columnName = ID_COLUMN_NAME)
	private int id;

	@DatabaseField(unique = true, columnName = URL_COLUMN_NAME)
	private String url;

	@DatabaseField(canBeNull = false, columnName = TITLE_COLUMN_NAME)
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
	@DatabaseField(columnName = IMAGE_URL_COLUMN_NAME)
	private String imageUrl;
	@DatabaseField
	private String imageTitle;
	@DatabaseField
	private String imageLink;
	@DatabaseField
	private String rating;

	@ForeignCollectionField(orderColumnName = ArticleEntity.PUB_DATE_COLUMN_NAME, orderAscending = false)
	private ForeignCollection<ArticleEntity> items;

	public ChannelEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public ForeignCollection<ArticleEntity> getItems() {
		return items;
	}

	public void setItems(ForeignCollection<ArticleEntity> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ChannelEntity channel = (ChannelEntity) o;
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

	public static ChannelEntity build(com.mercdev.rybakin.rss.engine.Channel model, String url) {
		ChannelEntity entity = new ChannelEntity();
		entity.setUrl(url);
		entity.setTitle(model.getTitle());
		entity.setDescription(model.getDescription());
		entity.setLink(model.getLink());
		entity.setCopyright(model.getCopyright());
		entity.setLanguage(model.getLanguage());
		if (model.getLastBuildDate() != null) {
			entity.setLastBuildDate(model.getLastBuildDate().getTime());
		}
		if (model.getPubDate() != null) {
			entity.setPubDate(model.getPubDate().getTime());
		}
		entity.setRating(model.getRating());
		entity.setTtl(model.getTTL());
		entity.setImageLink(model.getImage().getLink());
		entity.setImageTitle(model.getImage().getTitle());
		entity.setImageUrl(model.getImage().getUrl());
		return entity;
	}
}
