package com.mercdev.rybakin.rssreader.tasks.feed;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public abstract class TaskListener<T> implements RequestListener<T> {
	@Override
	public void onRequestFailure(SpiceException spiceException) {
	}

	@Override
	public void onRequestSuccess(T feedItems) {
	}
}
