package com.mercdev.rybakin.rssreader.ui.feed;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mercdev.rybakin.rssreader.R;

public class FeedNewsView extends LinearLayout {
	private final TextView titleView;

	public FeedNewsView(Context context) {
		this(context, null);
	}

	public FeedNewsView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FeedNewsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs, defStyleAttr, 0);
	}

	public FeedNewsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		inflate(context, R.layout.v_news, this);
		titleView = (TextView) findViewById(R.id.news_title);
	}

	public void setTitle(String title) {
		titleView.setText(title);
	}
}
