package com.mercdev.rybakin.rssreader.ui;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.state.RSSManager;
import com.mercdev.rybakin.rssreader.state.model.ChannelInfo;
import com.mercdev.rybakin.rssreader.ui.article.ArticleFragment;
import com.mercdev.rybakin.rssreader.ui.channels.ChannelsListView;
import com.mercdev.rybakin.rssreader.ui.feed.FeedFragment;

public class MainActivity extends AppCompatActivity {
	public static final String BACK_STACK = "BackStack";

	private final BroadcastReceiver channelUpdateReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			channelsList.setChannelsList(RSSManager.getInstance().getChannelList());
			hideProgress();
		}
	};

	private ChannelsListView channelsList;

	private DrawerLayout drawer;
	private ProgressBar progress;

	private boolean hasDetailFragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setActionBar(toolbar);

		hasDetailFragment = (findViewById(R.id.detail_fragment_host) != null);
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		progress = (ProgressBar) findViewById(R.id.main_progress);

		FeedFragment fragment = new FeedFragment();
		if (getFragmentManager().findFragmentByTag(FeedFragment.TAG) == null) {
			getFragmentManager().beginTransaction()
					.replace(R.id.master_fragment_host, fragment, FeedFragment.TAG)
					.show(fragment)
					.commit();
		}
		if (getFragmentManager().findFragmentByTag(ArticleFragment.TAG) != null && getFragmentManager().getBackStackEntryCount() > 0) {
			getFragmentManager().popBackStackImmediate();
		}
		channelsList = (ChannelsListView) findViewById(R.id.channels_list);
		channelsList.setChannelsList(RSSManager.getInstance().getChannelList());
		channelsList.setChannelSelectListener(new ChannelsListView.OnChannelsListItemClicked() {
			@Override
			public void onChannelClick(ChannelInfo channel) {
				RSSManager.getInstance().setSelectedChannel(channel.getUrl());
				drawer.closeDrawer(GravityCompat.START);
			}

			@Override
			public void onAddChannelClick() {
				EditText dialogTextView = (EditText) View.inflate(MainActivity.this, R.layout.v_add_channel_dialog, null);
				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
				dialogBuilder
						.setTitle(R.string.add_channel_dialog_title)
						.setView(dialogTextView)
						.setPositiveButton(R.string.add_channel_button,
								(dialogInterface, i) -> {
									RSSManager.getInstance().addChannel(dialogTextView.getText().toString());
									dialogInterface.dismiss();
									drawer.closeDrawer(GravityCompat.START);
								})
						.setNegativeButton(R.string.common_cancel, (dialogInterface, i) -> dialogInterface.dismiss())
						.create().show();
			}
		});
	}

	public void addDetailFragment(Fragment fragment, String tag) {
		if (hasDetailFragment) {
			getFragmentManager().beginTransaction()
					.replace(R.id.detail_fragment_host, fragment, tag)
					.commit();
		} else {
			getFragmentManager().beginTransaction()
					.replace(R.id.master_fragment_host, fragment, tag)
					.addToBackStack(MainActivity.BACK_STACK)
					.commit();
		}
	}

	private void showProgress() {
		drawer.animate().cancel();
		drawer.animate().alpha(0).withEndAction(() -> drawer.setVisibility(View.INVISIBLE));
		progress.setAlpha(0);
		progress.animate().cancel();
		progress.animate().alpha(1).withStartAction(() -> progress.setVisibility(View.VISIBLE));
	}

	private void hideProgress() {
		progress.animate().cancel();
		progress.animate().alpha(0).withEndAction(() -> drawer.setVisibility(View.GONE));
		drawer.setAlpha(0);
		drawer.animate().cancel();
		drawer.animate().alpha(1).withStartAction(() -> drawer.setVisibility(View.VISIBLE));
	}
}
