package com.mercdev.rybakin.rssreader.state.model;

import com.mercdev.rybakin.rssreader.repo.entities.ArticleEntity;

public class ArticleInfo {
	private final int id;
	private final String title;
	private final String guid;

	public ArticleInfo(int id, String title, String guid) {
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

	public static ArticleInfo buildFromEntity(ArticleEntity item) {
		ArticleInfo result = null;
		if (item != null) {
			result = new ArticleInfo(item.getId(), item.getTitle(), item.getGuid());
		}
		return result;
	}
}
