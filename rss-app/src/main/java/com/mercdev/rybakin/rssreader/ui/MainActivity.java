package com.mercdev.rybakin.rssreader.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.ui.feed.FeedFragment;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_main);

		getFragmentManager().beginTransaction().add(new FeedFragment(), FeedFragment.TAG).commit();
	}
}
