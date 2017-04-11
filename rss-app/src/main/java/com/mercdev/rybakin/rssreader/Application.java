package com.mercdev.rybakin.rssreader;

import com.mercdev.rybakin.rssreader.repo.RSSRepository;

public class Application extends android.app.Application {
	@Override
	public void onCreate() {
		super.onCreate();
		RSSRepository.init(getApplicationContext());
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		RSSRepository.destroy();
	}
}
