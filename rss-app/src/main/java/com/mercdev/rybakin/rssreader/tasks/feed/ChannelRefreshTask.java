package com.mercdev.rybakin.rssreader.tasks.feed;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.mercdev.rybakin.rss.engine.RSSDocumentParser;
import com.mercdev.rybakin.rssreader.repo.RSSRepository;
import com.octo.android.robospice.request.SpiceRequest;

public class ChannelRefreshTask extends SpiceRequest<String> {
	private static final String TAG = "ChannelRefreshTask";
	private final String targetUrl;

	public ChannelRefreshTask(String url) {
		super(String.class);
		targetUrl = url;
	}

	@Override
	public String loadDataFromNetwork() throws Exception {
		com.mercdev.rybakin.rss.engine.Channel channel = null;
		URL url = new URL(targetUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = conn.getInputStream();
			channel = RSSDocumentParser.parse(inputStream);
			inputStream.close();
		}
		conn.disconnect();

		RSSRepository.getInstance().saveChannel(channel, targetUrl);
		return targetUrl;
	}
}
