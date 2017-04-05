package com.mercdev.rybakin.rssreader.repo;

import android.provider.BaseColumns;

final class RSSContract {
	private RSSContract() {
	}

	static class FeedItem implements BaseColumns {
		static final String TABLE_NAME = "news";

		static final String COLUMN_TITLE = "title";
		static final String COLUMN_DESCRIPTION = "description";
		static final String COLUMN_DATE = "date";
		static final String COLUMN_URL = "url";
		static final String SORT_ORDER = RSSContract.FeedItem.COLUMN_DATE + " DESC";

		static final String SQL_CREATE_QUERY =
				"CREATE TABLE " + FeedItem.TABLE_NAME + " (" +
						FeedItem._ID + " INTEGER PRIMARY KEY," +
						FeedItem.COLUMN_TITLE + " TEXT," +
						FeedItem.COLUMN_DESCRIPTION + " TEXT," +
						FeedItem.COLUMN_DATE + " BIGINT," +
						FeedItem.COLUMN_URL + " TEXT UNIQUE)";

		static final String SQL_DROP_QUERY =
				"DROP TABLE IF EXISTS " + FeedItem.TABLE_NAME;
	}

	static class ArticleItem implements BaseColumns {
		static final String TABLE_NAME = "news";
		
		static final String COLUMN_TITLE = "title";
		static final String COLUMN_BODY = "body";
		static final String COLUMN_URL = "url";

		static final String SQL_CREATE_QUERY =
				"CREATE TABLE " + ArticleItem.TABLE_NAME + " (" +
						ArticleItem._ID + " INTEGER PRIMARY KEY," +
						ArticleItem.COLUMN_TITLE + " TEXT," +
						ArticleItem.COLUMN_BODY + " TEXT," +
						ArticleItem.COLUMN_URL + " TEXT UNIQUE)";

		static final String SQL_DROP_QUERY =
				"DROP TABLE IF EXISTS " + ArticleItem.TABLE_NAME;
	}
}
