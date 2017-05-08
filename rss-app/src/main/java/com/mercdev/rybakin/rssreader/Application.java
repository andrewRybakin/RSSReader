package com.mercdev.rybakin.rssreader;

import com.mercdev.rybakin.rssreader.repo.RSSRepository;
import com.mercdev.rybakin.rssreader.state.RSSManager;
import com.mercdev.rybakin.rssreader.tasks.TaskManager;

public class Application extends android.app.Application {
	private static Application instance;

	public Application() {
		instance = this;
	}

	public static Application getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		RSSRepository.init(getApplicationContext());
		RSSManager.init(getApplicationContext());
		TaskManager.getInstance().start(getApplicationContext());
	}
}
