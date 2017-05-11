package com.mercdev.rybakin.rssreader.repo.entities;

import java.util.Objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.mercdev.rybakin.rss.engine.Item;

@SuppressWarnings({ "unused", "WeakerAccess" })
@DatabaseTable(tableName = "channel-items")
public class ArticleEntity {
	public static final String ID_COLUMN_NAME = "id";
	public static final String CHANNEL_ID_COLUMN_NAME = "channel_id";
	public static final String GUID_COLUMN_NAME = "guid";
	public static final String PUB_DATE_COLUMN_NAME = "pub_date";

	@DatabaseField(generatedId = true, columnName = ID_COLUMN_NAME)
	private int id;

	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = CHANNEL_ID_COLUMN_NAME)
	private ChannelEntity channel;

	@DatabaseField
	private String title;
	@DatabaseField
	private String description;

	@DatabaseField
	private String author;
	@DatabaseField
	private String comments;
	@DatabaseField(columnName = GUID_COLUMN_NAME)
	private String guid;
	@DatabaseField(columnName = PUB_DATE_COLUMN_NAME)
	private long pubDate;

	public ArticleEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ChannelEntity getChannel() {
		return channel;
	}

	public void setChannel(ChannelEntity channel) {
		this.channel = channel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public long getPubDate() {
		return pubDate;
	}

	public void setPubDate(long pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ArticleEntity that = (ArticleEntity) o;
		return pubDate == that.pubDate &&
				Objects.equals(channel, that.channel) &&
				Objects.equals(title, that.title) &&
				Objects.equals(description, that.description) &&
				Objects.equals(author, that.author) &&
				Objects.equals(comments, that.comments) &&
				Objects.equals(guid, that.guid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(channel, title, description, author, comments, guid, pubDate);
	}

	public static ArticleEntity build(Item model) {
		ArticleEntity entity = new ArticleEntity();
		entity.setAuthor(model.getAuthor());
		entity.setTitle(model.getTitle());
		if (model.getPubDate() != null) {
			entity.setPubDate(model.getPubDate().getTime());
		}
		entity.setComments(model.getComments());
		entity.setDescription(model.getDescription());
		entity.setGuid(model.getGuid());
		return entity;
	}
}
