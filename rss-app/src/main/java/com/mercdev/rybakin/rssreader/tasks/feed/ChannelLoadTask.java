package com.mercdev.rybakin.rssreader.tasks.feed;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.mercdev.rybakin.rss.engine.Item;
import com.mercdev.rybakin.rss.engine.RSSDocumentParser;
import com.mercdev.rybakin.rssreader.repo.RSSRepository;
import com.mercdev.rybakin.rssreader.state.model.Article;
import com.mercdev.rybakin.rssreader.state.model.Channel;
import com.octo.android.robospice.request.SpiceRequest;

public class ChannelLoadTask extends SpiceRequest<Channel> {
	private final String targetUrl;

	public ChannelLoadTask(String url) {
		super(Channel.class);
		targetUrl = url;
	}

	@Override
	public Channel loadDataFromNetwork() throws Exception {
		com.mercdev.rybakin.rss.engine.Channel channel = null;
		URL url = new URL(targetUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			InputStream inputStream = conn.getInputStream();
			channel = RSSDocumentParser.parse(inputStream);
			inputStream.close();
		}
		conn.disconnect();

		Channel result = null;
		if (channel != null) {
			result = new Channel(channel.getTitle(), channel.getLink(), channel.getDescription());
			result.setCopyright(channel.getCopyright());
			result.setLanguage(channel.getLanguage());
			result.setLastBuildDate(channel.getLastBuildDate());
			result.setPubDate(channel.getPubDate());
			result.setRating(channel.getRating());
			result.setTtl(channel.getTTL());
			if (channel.getImage() != null) {
				result.setImageUrl(channel.getImage().getUrl());
				result.setImageTitle(channel.getImage().getTitle());
				result.setImageLink(channel.getImage().getLink());
			}

			for (Item item : channel.getItems()) {
				Article article = new Article(item.getTitle(), item.getDescription());
				article.setPubDate(item.getPubDate());
				article.setAuthor(item.getAuthor());
				article.setComments(item.getComments());
				article.setGuid(item.getGuid());

				result.addArticle(article);
			}
		}

		RSSRepository.getInstance().saveChannel(result);
		return result;
	}
}
