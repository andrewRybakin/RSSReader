package com.mercdev.rybakin.rssreader.ui.feed;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercdev.rybakin.rssreader.Application;
import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.state.RSSManager;
import com.mercdev.rybakin.rssreader.ui.MainActivity;
import com.mercdev.rybakin.rssreader.ui.article.ArticleFragment;

public class FeedFragment extends Fragment {
	public static final String TAG = "FeedFragment";

	private final BroadcastReceiver channelUpdateReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			feedAdapter.setFeedItems(RSSManager.getInstance().getSelectedChannelFeed());
			refreshLayout.setRefreshing(false);
		}
	};

	private SwipeRefreshLayout refreshLayout;
	private FeedAdapter feedAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.f_feed, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		refreshLayout.setOnRefreshListener(this::refreshList);

		RecyclerView feedList = (RecyclerView) view.findViewById(R.id.feed_list);

		feedAdapter = new FeedAdapter(articleInfo -> {
			Bundle args = new Bundle();
			args.putInt(ArticleFragment.ARTICLE_ID_ARGUMENT, articleInfo.getId());
			ArticleFragment fragment = new ArticleFragment();
			fragment.setArguments(args);
			((MainActivity) getActivity()).addDetailFragment(fragment, ArticleFragment.TAG);
		});

		feedList.setLayoutManager(new LinearLayoutManager(getActivity()));
		feedList.setAdapter(feedAdapter);
		feedAdapter.setFeedItems(RSSManager.getInstance().getSelectedChannelFeed());
	}

	@Override
	public void onStart() {
		super.onStart();
		LocalBroadcastManager.getInstance(Application.getInstance()).registerReceiver(channelUpdateReceiver, new IntentFilter(RSSManager.CHANNEL_UPDATE_ACTION));
	}

	@Override
	public void onStop() {
		super.onStop();
		LocalBroadcastManager.getInstance(Application.getInstance()).unregisterReceiver(channelUpdateReceiver);
	}

	private void refreshList() {
		RSSManager.getInstance().refreshSelectedChannel();
		refreshLayout.setRefreshing(true);
	}
}
