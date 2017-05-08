package com.mercdev.rybakin.rssreader.state;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.List;

import com.mercdev.rybakin.rssreader.Application;
import com.mercdev.rybakin.rssreader.repo.RSSRepository;
import com.mercdev.rybakin.rssreader.repo.entities.ArticleEntity;
import com.mercdev.rybakin.rssreader.repo.entities.ChannelEntity;
import com.mercdev.rybakin.rssreader.state.model.Article;
import com.mercdev.rybakin.rssreader.state.model.ArticleInfo;
import com.mercdev.rybakin.rssreader.state.model.Channel;
import com.mercdev.rybakin.rssreader.state.model.ChannelInfo;
import com.mercdev.rybakin.rssreader.tasks.TaskManager;
import com.mercdev.rybakin.rssreader.tasks.feed.ChannelRefreshTask;
import com.mercdev.rybakin.rssreader.tasks.feed.TaskListener;

public class RSSManager {
	public static final String CHANNEL_UPDATE_ACTION = "ChannelUpdateAction";

	private static final String PREFERENCES_NAME = "RSSManager.Preferences";
	private static final String SELECTED_CHANNEL_KEY = "RSSManager.Preferences.selectedChannelUrl";

	private static RSSManager instance;

	private String selectedChannelUrl;
	private Channel selectedChannel;
	private final List<ArticleInfo> selectedChannelFeed = new ArrayList<>();

	private RSSManager(String initialSelectedChannel) {
		selectedChannelUrl = initialSelectedChannel;
	}

	public void addChannel(String url) {
		refreshChannel(url);
		selectedChannelUrl = url;
	}

	public List<ChannelInfo> getChannelList() {
		return RSSRepository.getInstance().getChannelList();
	}

	public void setSelectedChannel(String url) {
		ChannelEntity channelEntity = RSSRepository.getInstance().getChannel(url);
		selectedChannelUrl = url;
		selectedChannel = Channel.createFromEntity(channelEntity);
		selectedChannelFeed.clear();
		for (ArticleEntity item : channelEntity.getItems()) {
			selectedChannelFeed.add(ArticleInfo.buildFromEntity(item));
		}
	}

	public Channel getSelectedChannel() {
		return selectedChannel;
	}

	public List<ArticleInfo> getSelectedChannelFeed() {
		return selectedChannelFeed;
	}

	public Article getArticle(int id) {
		return Article.bulidFromEntity(RSSRepository.getInstance().getArticle(id));
	}

	public void refreshSelectedChannel() {
		refreshChannel(selectedChannelUrl);
	}

	private void refreshChannel(final String url) {
		TaskManager.getInstance().execute(new ChannelRefreshTask(url), new TaskListener<String>() {
			@Override
			public void onRequestSuccess(String url) {
				setSelectedChannel(url);
				LocalBroadcastManager.getInstance(Application.getInstance()).sendBroadcast(new Intent(CHANNEL_UPDATE_ACTION));
			}
		});
	}

	public static void init(Context context) {
		if (instance == null) {
			SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
			instance = new RSSManager(preferences.getString(SELECTED_CHANNEL_KEY, null));
		}
	}

	public static RSSManager getInstance() {
		if (instance == null) {
			throw new IllegalStateException("RSSManager must be initialized on application startup!");
		}
		return instance;
	}
}
