package com.mercdev.rybakin.rssreader.state;

import com.mercdev.rybakin.rssreader.state.model.Channel;
import com.mercdev.rybakin.rssreader.tasks.TaskManager;
import com.mercdev.rybakin.rssreader.tasks.feed.ChannelLoadTask;
import com.mercdev.rybakin.rssreader.tasks.feed.TaskListener;

public class RSSManager {
	public static final String CHANNEL_UPDATE_ACTION = "ChannelUpdateAction";
	private static RSSManager instance;

	private RSSManager() {
	}

	public void refreshFeed(String url) {
		ChannelLoadTask task = new ChannelLoadTask(url);
		TaskManager.getInstance().execute(task, new TaskListener<Channel>() {
			@Override
			public void onRequestSuccess(Channel feedItems) {
				super.onRequestSuccess(feedItems);

			}
		});
		// TODO Save to database
		// TODO Send broadcast
	}

	public static RSSManager getInstance() {
		if (instance == null) {
			instance = new RSSManager();
		}
		return instance;
	}
}
