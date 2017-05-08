package com.mercdev.rybakin.rssreader.ui.feed;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.mercdev.rybakin.rssreader.state.model.ArticleInfo;

class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
	private final List<ArticleInfo> feedItems = new ArrayList<>();
	private final OnFeedItemClickListener listener;

	FeedAdapter(OnFeedItemClickListener listener) {
		this.listener = listener;
	}

	@Override
	public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new FeedViewHolder(new FeedNewsView(parent.getContext()), listener);
	}

	@Override
	public void onBindViewHolder(FeedViewHolder holder, int position) {
		ArticleInfo item = feedItems.get(position);
		holder.bind(item);
	}

	@Override
	public int getItemCount() {
		return feedItems.size();
	}

	void setFeedItems(List<ArticleInfo> feedItems) {
		DiffUtil.DiffResult result = DiffUtil.calculateDiff(new FeedDiffCallback(this.feedItems, feedItems));
		this.feedItems.clear();
		this.feedItems.addAll(feedItems);
		result.dispatchUpdatesTo(this);
	}

	static class FeedViewHolder extends RecyclerView.ViewHolder {
		private final OnFeedItemClickListener listener;

		FeedViewHolder(View itemView, OnFeedItemClickListener listener) {
			super(itemView);
			this.listener = listener;
		}

		void bind(ArticleInfo info) {
			FeedNewsView view = ((FeedNewsView) itemView);
			view.setTitle(info.getTitle());
			view.setOnClickListener(view1 -> listener.onFeedItemClick(info));
		}
	}

	private static class FeedDiffCallback extends DiffUtil.Callback {
		private final List<ArticleInfo> oldList;
		private final List<ArticleInfo> newList;

		FeedDiffCallback(List<ArticleInfo> oldList, List<ArticleInfo> newList) {
			this.oldList = oldList;
			this.newList = newList;
		}

		@Override
		public int getOldListSize() {
			return oldList.size();
		}

		@Override
		public int getNewListSize() {
			return newList.size();
		}

		@Override
		public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
			return Objects.equals(oldList.get(oldItemPosition), newList.get(newItemPosition));
		}

		@Override
		public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
			return Objects.equals(oldList.get(oldItemPosition), newList.get(newItemPosition));
		}
	}

	interface OnFeedItemClickListener {
		void onFeedItemClick(ArticleInfo info);
	}
}
