package com.mercdev.rybakin.rssreader.state;

import java.util.List;

import com.mercdev.rybakin.rssreader.tasks.feed.FeedLoadTaskListener;

public class RSSManager {
	private static RSSManager instance;

	private RSSManager() {
	}

	public List<Object> refreshFeed(String url, FeedLoadTaskListener listener) {
		// TODO Execute task
		// TODO Save to database
		// TODO return
		return null;
	}

	public static RSSManager getInstance() {
		if (instance == null) {
			instance = new RSSManager();
		}
		return instance;
	}
}
