package com.mercdev.rybakin.rssreader.repo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.repo.entities.ChannelEntity;
import com.mercdev.rybakin.rssreader.repo.entities.ArticleEntity;

class RSSDatabaseHelper extends OrmLiteSqliteOpenHelper {
	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "RSSReader.db";

	private RuntimeExceptionDao<ChannelEntity, Integer> channelsDao;
	private RuntimeExceptionDao<ArticleEntity, Integer> articlesDao;

	RSSDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTableIfNotExists(connectionSource, ChannelEntity.class);
			TableUtils.createTableIfNotExists(connectionSource, ArticleEntity.class);
		} catch (SQLException e) {
			throw new RuntimeException("Couldn't initialize database!", e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, ChannelEntity.class, true);
			TableUtils.dropTable(connectionSource, ArticleEntity.class, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		onCreate(database, connectionSource);
	}

	RuntimeExceptionDao<ChannelEntity, Integer> getChannelsDao() {
		if (channelsDao == null) {
			try {
				channelsDao = new RuntimeExceptionDao<>(DaoManager.<Dao<ChannelEntity, Integer>, ChannelEntity> createDao(connectionSource, ChannelEntity.class));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return channelsDao;
	}

	RuntimeExceptionDao<ArticleEntity, Integer> getArticlesDao() {
		if (articlesDao == null) {
			try {
				articlesDao = new RuntimeExceptionDao<>(DaoManager.<Dao<ArticleEntity, Integer>, ArticleEntity> createDao(connectionSource, ArticleEntity.class));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return articlesDao;
	}
}
