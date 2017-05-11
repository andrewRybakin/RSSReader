package com.mercdev.rybakin.rssreader.ui.article;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebView;

import java.text.SimpleDateFormat;

import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.state.RSSManager;
import com.mercdev.rybakin.rssreader.state.model.Article;

public class ArticleFragment extends Fragment {
	public static final String ARTICLE_ID_ARGUMENT = "ArticleId";
	public static final String TAG = "ArticleFragment";

	public static final String HTML_STYLE = "<style>" +
			"img { display: inline, height: auto; max-width: 100%; }" +
			"small { width: 100%; height: auto; font-size: 10px; font-color: grey; text-align: center }" +
			"</style>";

	private Article article;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		article = RSSManager.getInstance().getArticle(getArguments().getInt(ARTICLE_ID_ARGUMENT));
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.f_article, container, false);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		String header = String.format("<h2>%s</h2>", article.getTitle());
		String pubDate = "";
		if (article.getPubDate() != null) {
			pubDate = String.format("<small>%s</small><br>", SimpleDateFormat.getInstance().format(article.getPubDate()));
		}
		String footer = "";
		if (!article.getDescription().contains(article.getGuid()) && URLUtil.isValidUrl(article.getGuid())) {
			footer = String.format("<br><br><a href='%s'>%s</a>", article.getGuid(), getString(R.string.article_read_more));
		}
		WebView articleView = (WebView) view.findViewById(R.id.article_view);
		articleView.getSettings().setJavaScriptEnabled(true);
		articleView.loadData(HTML_STYLE + header + pubDate + article.getDescription() + footer, "text/html; charset=utf-8", "UTF-8");
		articleView.computeScroll();
	}
}
