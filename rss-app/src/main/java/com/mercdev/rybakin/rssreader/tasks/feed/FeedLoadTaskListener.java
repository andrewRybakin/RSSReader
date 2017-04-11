package com.mercdev.rybakin.rssreader.tasks.feed;

import com.mercdev.rybakin.rss.engine.Channel;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public abstract class FeedLoadTaskListener implements RequestListener<Channel> {
	@Override
	public void onRequestFailure(SpiceException spiceException) {
	}

	@Override
	public void onRequestSuccess(Channel feedItems) {
	}
}
