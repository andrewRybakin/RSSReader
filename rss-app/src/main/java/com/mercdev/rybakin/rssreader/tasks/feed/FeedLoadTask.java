package com.mercdev.rybakin.rssreader.tasks.feed;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.mercdev.rybakin.rss.engine.Channel;
import com.mercdev.rybakin.rss.engine.RSSDocumentParser;
import com.octo.android.robospice.request.SpiceRequest;

public class FeedLoadTask extends SpiceRequest<Channel> {
	private final String targetUrl;

	public FeedLoadTask(String url) {
		super(Channel.class);
		targetUrl = url;
	}

	@Override
	public Channel loadDataFromNetwork() throws Exception {
		Channel channel = null;
		URL url = new URL(targetUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = conn.getInputStream();
			channel = RSSDocumentParser.parse(inputStream);
			inputStream.close();
		}
		conn.disconnect();
		return channel;
	}
}
