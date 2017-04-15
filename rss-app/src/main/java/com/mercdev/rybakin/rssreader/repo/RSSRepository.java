package com.mercdev.rybakin.rssreader.repo;

import android.content.Context;

import com.mercdev.rybakin.rssreader.repo.entities.Channel;
import com.mercdev.rybakin.rssreader.repo.entities.ChannelItem;
import com.mercdev.rybakin.rssreader.state.model.Article;

public class RSSRepository {
	private static RSSRepository instance;

	private final RSSDatabaseHelper databaseHelper;

	private RSSRepository(RSSDatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	public void saveChannel(com.mercdev.rybakin.rssreader.state.model.Channel channel) {
		Channel data = new Channel();
		data.setTitle(channel.getTitle());
		data.setDescription(channel.getDescription());
		data.setLink(channel.getLink());
		data.setCopyright(channel.getCopyright());
		data.setLanguage(channel.getLanguage());
		data.setLastBuildDate(channel.getLastBuildDate().getTime());
		data.setPubDate(channel.getPubDate().getTime());
		data.setRating(channel.getRating());
		data.setTtl(channel.getTtl());
		data.setImageLink(channel.getImageLink());
		data.setImageTitle(channel.getImageTitle());
		data.setImageUrl(channel.getImageUrl());
		databaseHelper.getChannelsDao().createOrUpdate(data);

		data.setId(databaseHelper.getChannelsDao().extractId(data));
		for (Article article : channel.getArticles()) {
			ChannelItem item = new ChannelItem();
			item.setChannel(data);
			item.setAuthor(article.getAuthor());
			item.setTitle(article.getTitle());
			item.setPubDate(article.getPubDate().getTime());
			item.setComments(article.getComments());
			item.setDescription(article.getDescription());
			item.setGuid(article.getGuid());
			databaseHelper.getChannelItemDao().createOrUpdate(item);
		}
	}

	public static void init(Context context) {
		if (instance != null) {
			throw new IllegalStateException("RSSRepository has already been initialized!");
		}
		instance = new RSSRepository(new RSSDatabaseHelper(context));
	}

	public static void destroy() {
		if (instance == null) {
			throw new IllegalStateException("RSSRepository wasn't initialized!");
		}
		instance.databaseHelper.close();
	}

	public static RSSRepository getInstance() {
		if (instance == null) {
			throw new IllegalStateException("RSSRepository wasn't initialized!");
		}
		return instance;
	}
}
