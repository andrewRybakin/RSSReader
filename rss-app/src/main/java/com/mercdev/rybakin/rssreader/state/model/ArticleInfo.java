package com.mercdev.rybakin.rssreader.state.model;

import java.util.Objects;

import com.mercdev.rybakin.rssreader.repo.entities.ArticleEntity;

public class ArticleInfo {
	private final int id;
	private final String title;
	private final String guid;

	private ArticleInfo(int id, String title, String guid) {
		this.id = id;
		this.title = title;
		this.guid = guid;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getGuid() {
		return guid;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ArticleInfo that = (ArticleInfo) o;
		return id == that.id &&
				Objects.equals(title, that.title) &&
				Objects.equals(guid, that.guid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, guid);
	}

	public static ArticleInfo buildFromEntity(ArticleEntity item) {
		ArticleInfo result = null;
		if (item != null) {
			result = new ArticleInfo(item.getId(), item.getTitle(), item.getGuid());
		}
		return result;
	}
}
