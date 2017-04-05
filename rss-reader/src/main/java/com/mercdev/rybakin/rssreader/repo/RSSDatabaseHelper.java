package com.mercdev.rybakin.rssreader.repo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class RSSDatabaseHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "RSSReader.db";

	RSSDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(RSSContract.FeedItem.SQL_CREATE_QUERY);
		database.execSQL(RSSContract.ArticleItem.SQL_CREATE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL(RSSContract.FeedItem.SQL_DROP_QUERY);
		database.execSQL(RSSContract.ArticleItem.SQL_DROP_QUERY);
		onCreate(database);
	}
}
