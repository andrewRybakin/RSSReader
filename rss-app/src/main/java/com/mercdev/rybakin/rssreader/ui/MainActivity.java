package com.mercdev.rybakin.rssreader.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.state.RSSManager;
import com.mercdev.rybakin.rssreader.ui.channels.ChannelsListView;
import com.mercdev.rybakin.rssreader.ui.feed.FeedFragment;

public class MainActivity extends AppCompatActivity {
	public static final String BACK_STACK = "BackStack";
	private Toolbar toolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_main);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setActionBar(toolbar);

		FeedFragment fragment = new FeedFragment();
		if (getFragmentManager().findFragmentByTag(FeedFragment.TAG) == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.master_fragment_host, fragment, FeedFragment.TAG)
					.show(fragment)
					.commit();
		}
		ChannelsListView channelsList = (ChannelsListView) findViewById(R.id.channels_list);
		channelsList.setChannelsList(RSSManager.getInstance().getChannelList());
		channelsList.setChannelSelectListener(channel -> RSSManager.getInstance().setSelectedChannel(channel.getUrl()));
	}
}
