package com.mercdev.rybakin.rssreader.tasks.feed;

import java.util.List;

import com.mercdev.rybakin.rssreader.repo.entities.FeedItem;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public abstract class FeedLoadTaskListener implements RequestListener<List<FeedItem>> {
	@Override
	public void onRequestFailure(SpiceException spiceException) {
	}

	@Override
	public void onRequestSuccess(List<FeedItem> feedItems) {
	}
}
