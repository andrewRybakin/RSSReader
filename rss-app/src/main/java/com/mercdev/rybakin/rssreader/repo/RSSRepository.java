package com.mercdev.rybakin.rssreader.repo;

import android.content.Context;

public class RSSRepository {
	private static RSSRepository instance;

	private final RSSDatabaseHelper databaseHelper;

	private RSSRepository(RSSDatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
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
