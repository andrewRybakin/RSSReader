package com.mercdev.rybakin.rssreader.repo;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.Where;
import com.mercdev.rybakin.rss.engine.Item;
import com.mercdev.rybakin.rssreader.repo.entities.ArticleEntity;
import com.mercdev.rybakin.rssreader.repo.entities.ChannelEntity;
import com.mercdev.rybakin.rssreader.state.model.ChannelInfo;

public class RSSRepository {
	private static RSSRepository instance;

	private final RSSDatabaseHelper databaseHelper;

	private RSSRepository(RSSDatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	public void saveChannel(com.mercdev.rybakin.rss.engine.Channel channel, String url) {
		try {
			ChannelEntity data = ChannelEntity.build(channel, url);
			ChannelEntity existingChannel = databaseHelper.getChannelsDao().queryBuilder().where().eq(ChannelEntity.URL_COLUMN_NAME, url).queryForFirst();
			if (existingChannel != null) {
				data.setId(existingChannel.getId());
				databaseHelper.getChannelsDao().update(data);
				databaseHelper.getArticlesDao().deleteBuilder().where().eq(ArticleEntity.CHANNEL_ID_COLUMN_NAME, data.getId());
			} else {
				databaseHelper.getChannelsDao().create(data);
				data.setId(databaseHelper.getChannelsDao().extractId(data));
			}

			for (Item item : channel.getItems()) {
				ArticleEntity channelItem = ArticleEntity.build(item);
				channelItem.setChannel(data);
				Where<ArticleEntity, Integer> query = databaseHelper.getArticlesDao().queryBuilder()
						.where()
						.eq(ArticleEntity.GUID_COLUMN_NAME, channelItem.getGuid())
						.and()
						.eq(ArticleEntity.CHANNEL_ID_COLUMN_NAME, channelItem.getChannel().getId());
				if (query.countOf() != 0) {
					channelItem.setId(query.queryForFirst().getId());
					databaseHelper.getArticlesDao().update(channelItem);
				} else {
					databaseHelper.getArticlesDao().create(channelItem);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ChannelEntity getChannel(String url) {
		ChannelEntity result = null;
		List<ChannelEntity> queryResult = databaseHelper.getChannelsDao().queryForEq(ChannelEntity.URL_COLUMN_NAME, url);
		if (queryResult.size() == 1) {
			result = queryResult.get(0);
		}
		return result;
	}

	public ArticleEntity getArticle(int id) {
		ArticleEntity result = null;
		try {
			result = databaseHelper.getArticlesDao().queryBuilder().where().eq(ArticleEntity.ID_COLUMN_NAME, id).queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<ChannelInfo> getChannelList() {
		List<ChannelInfo> result = null;
		try {
			GenericRawResults<String[]> queryResult = databaseHelper.getChannelsDao().queryBuilder()
					.selectColumns(ChannelEntity.TITLE_COLUMN_NAME, ChannelEntity.URL_COLUMN_NAME, ChannelEntity.IMAGE_URL_COLUMN_NAME).queryRaw();
			result = new ArrayList<>();
			for (String[] oneResult : queryResult) {
				result.add(new ChannelInfo(oneResult[0], oneResult[1], oneResult[2]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void init(Context context) {
		if (instance != null) {
			throw new IllegalStateException("RSSRepository has been already initialized!");
		}
		instance = new RSSRepository(new RSSDatabaseHelper(context));
	}

	public static void destroy() {
		if (instance == null) {
			throw new IllegalStateException("RSSRepository wasn't initialized!");
		}
		instance.databaseHelper.close();
		instance = null;
	}

	public static RSSRepository getInstance() {
		if (instance == null) {
			throw new IllegalStateException("RSSRepository wasn't initialized!");
		}
		return instance;
	}
}
