package com.mercdev.rybakin.rssreader.ui.channels;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.List;

import com.mercdev.rybakin.rssreader.state.model.ChannelInfo;

public class ChannelsListView extends RecyclerView {
	private final ChannelsAdapter adapter;

	public ChannelsListView(Context context) {
		this(context, null);
	}

	public ChannelsListView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ChannelsListView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		adapter = new ChannelsAdapter();
		setAdapter(adapter);
		setLayoutManager(new LinearLayoutManager(context));
	}

	public void setChannelSelectListener(OnChannelsListItemClicked listener) {
		adapter.setChannelSelectionListener(listener);
	}

	public void setChannelsList(List<ChannelInfo> list) {
		adapter.setList(list);
	}

	public interface OnChannelsListItemClicked {
		void onChannelClick(ChannelInfo channel);

		void onAddChannelClick();
	}
}
