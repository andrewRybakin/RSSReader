package com.mercdev.rybakin.rssreader.ui.feed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.repo.entities.FeedItem;
import com.mercdev.rybakin.rssreader.state.RSSManager;
import com.mercdev.rybakin.rssreader.tasks.feed.FeedLoadTaskListener;
import com.octo.android.robospice.persistence.exception.SpiceException;

public class FeedFragment extends Fragment {
	public static final String TAG = "FeedFragment";

	private RecyclerView feedList;
	private FeedAdapter feedAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.f_feed, container);
		feedList = (RecyclerView) view.findViewById(R.id.feed_list);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		feedAdapter = new FeedAdapter();
		feedList.setAdapter(feedAdapter);
		RSSManager.getInstance().refreshFeed(getString(R.string.rss_feed_url), new FeedLoadTaskListener() {
			@Override
			public void onRequestFailure(SpiceException spiceException) {
				super.onRequestFailure(spiceException);
				// TODO show error message
			}

			@Override
			public void onRequestSuccess(List<FeedItem> feedItems) {
				super.onRequestSuccess(feedItems);
				// TODO show list
			}
		});
	}
}
