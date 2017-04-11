package com.mercdev.rybakin.rssreader.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mercdev.rybakin.rssreader.repo.entities.ArticleItem;
import com.mercdev.rybakin.rssreader.repo.entities.FeedItem;

public class RSSRepository {
	private static RSSRepository instance;

	private final RSSDatabaseHelper databaseHelper;

	private RSSRepository(RSSDatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	public List<FeedItem> getFeedList() {
		Cursor cursor = databaseHelper.getReadableDatabase().query(
				RSSContract.FeedItem.TABLE_NAME,
				new String[] {
						RSSContract.FeedItem.COLUMN_TITLE, RSSContract.FeedItem.COLUMN_DESCRIPTION,
						RSSContract.FeedItem.COLUMN_DATE, RSSContract.FeedItem.COLUMN_URL
				},
				null, null, null, null, RSSContract.FeedItem.SORT_ORDER);
		List<FeedItem> result = new ArrayList<>();
		while (cursor.moveToNext()) {
			String title = cursor.getString(cursor.getColumnIndexOrThrow(RSSContract.FeedItem.COLUMN_TITLE));
			String description = cursor.getString(cursor.getColumnIndexOrThrow(RSSContract.FeedItem.COLUMN_DESCRIPTION));
			long dateInMills = cursor.getLong(cursor.getColumnIndexOrThrow(RSSContract.FeedItem.COLUMN_DATE));
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(dateInMills);
			String url = cursor.getString(cursor.getColumnIndexOrThrow(RSSContract.FeedItem.COLUMN_URL));
			result.add(new FeedItem(title, description, calendar.getTime(), url));
		}
		cursor.close();
		return result;
	}

	public void updateFeed(List<FeedItem> feedList) {
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		database.execSQL(RSSContract.FeedItem.SQL_DROP_QUERY);
		database.execSQL(RSSContract.FeedItem.SQL_CREATE_QUERY);
		for (FeedItem item : feedList) {
			ContentValues contentValues = new ContentValues();
			contentValues.put(RSSContract.FeedItem.COLUMN_TITLE, item.getTitle());
			contentValues.put(RSSContract.FeedItem.COLUMN_DESCRIPTION, item.getDescription());
			contentValues.put(RSSContract.FeedItem.COLUMN_DATE, item.getPubDate().getTime());
			contentValues.put(RSSContract.FeedItem.COLUMN_URL, item.getUrl());
			database.insertWithOnConflict(RSSContract.FeedItem.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
		}
	}

	@Nullable
	public ArticleItem getArticle(String articleUrl) {
		ArticleItem result = null;
		SQLiteDatabase database = databaseHelper.getReadableDatabase();
		Cursor cursor = database.query(
				RSSContract.ArticleItem.TABLE_NAME,
				new String[] { RSSContract.ArticleItem.COLUMN_TITLE, RSSContract.ArticleItem.COLUMN_BODY },
				RSSContract.ArticleItem.COLUMN_URL + " = ?",
				new String[] { articleUrl }, null, null, null);
		if (cursor.moveToFirst()) {
			String title = cursor.getString(cursor.getColumnIndexOrThrow(RSSContract.ArticleItem.COLUMN_TITLE));
			String body = cursor.getString(cursor.getColumnIndexOrThrow(RSSContract.ArticleItem.COLUMN_BODY));
			result = new ArticleItem(title, body, articleUrl);
		}
		cursor.close();
		return result;
	}

	public void updateArticle(ArticleItem article) {
		SQLiteDatabase database = databaseHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(RSSContract.ArticleItem.COLUMN_TITLE, article.getTitle());
		contentValues.put(RSSContract.ArticleItem.COLUMN_BODY, article.getBody());
		contentValues.put(RSSContract.ArticleItem.COLUMN_URL, article.getUrl());
		database.insertWithOnConflict(RSSContract.ArticleItem.TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
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
