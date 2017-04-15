package com.mercdev.rybakin.rssreader.repo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.repo.entities.Channel;
import com.mercdev.rybakin.rssreader.repo.entities.ChannelItem;

class RSSDatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "RSSReader.db";

	private RuntimeExceptionDao<Channel, Integer> channelsDao;
	private RuntimeExceptionDao<ChannelItem, Void> channelItemDao;

	RSSDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTableIfNotExists(connectionSource, Channel.class);
			TableUtils.createTableIfNotExists(connectionSource, ChannelItem.class);
			channelsDao = DaoManager.createDao(connectionSource, Channel.class);
			channelItemDao = DaoManager.createDao(connectionSource, ChannelItem.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Channel.class, true);
			TableUtils.dropTable(connectionSource, ChannelItem.class, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		onCreate(database, connectionSource);
	}

	RuntimeExceptionDao<Channel, Integer> getChannelsDao() {
		return channelsDao;
	}

	RuntimeExceptionDao<ChannelItem, Void> getChannelItemDao() {
		return channelItemDao;
	}
}
