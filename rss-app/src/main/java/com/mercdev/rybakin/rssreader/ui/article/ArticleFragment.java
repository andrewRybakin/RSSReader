package com.mercdev.rybakin.rssreader.ui.article;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.state.RSSManager;
import com.mercdev.rybakin.rssreader.state.model.Article;

public class ArticleFragment extends Fragment {
	public static final String ARTICLE_ID_ARGUMENT = "ArticleId";
	public static final String TAG = "ArticleFragment";

	public static final String HTML_STYLE =
			"<style>img { display: inline, height: auto; max-width: 100%; }</style>";

	private Article article;
	private WebView articleView;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		article = RSSManager.getInstance().getArticle(getArguments().getInt(ARTICLE_ID_ARGUMENT));
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.f_article, container, false);
		articleView = (WebView) view.findViewById(R.id.article_view);
		articleView.clearCache(true);
		articleView.getSettings().setJavaScriptEnabled(true);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		articleView.loadData(HTML_STYLE + article.getDescription(), "text/html; charset=utf-8", "UTF-8");
		articleView.computeScroll();
	}
}
