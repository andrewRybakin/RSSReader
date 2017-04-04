package com.mercdev.rybakin.rssreader.ui.feed;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mercdev.rybakin.rssreader.R;

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
	}
}
