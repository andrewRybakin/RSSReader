package com.mercdev.rybakin.rssreader.repo.entities;

import java.util.Objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@SuppressWarnings("unused")
@DatabaseTable(tableName = "channel-items")
public class ChannelItem {
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "channel_id")
	private Channel channel;

	@DatabaseField
	private String title;
	@DatabaseField
	private String description;

	@DatabaseField
	private String author;
	@DatabaseField
	private String comments;
	@DatabaseField
	private String guid;
	@DatabaseField
	private long pubDate;

	public ChannelItem() {
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
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
		ChannelItem that = (ChannelItem) o;
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
}
