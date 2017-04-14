package com.mercdev.rybakin.rssreader.ui.feed;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class FeedAdapter<T> extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
	private final List<T> feedItems = new ArrayList<>();

	@Override
	public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new FeedViewHolder(new FeedNewsView(parent.getContext()));
	}

	@Override
	public void onBindViewHolder(FeedViewHolder holder, int position) {
		T item = feedItems.get(position);
		/*holder.bind(item.getTitle(), item.getUrl());*/
	}

	@Override
	public int getItemCount() {
		return 0;
	}

	void setFeedItems(List<T> feedItems) {
		DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback<>(this.feedItems, feedItems));
		this.feedItems.clear();
		this.feedItems.addAll(feedItems);
		result.dispatchUpdatesTo(this);
	}

	static class FeedViewHolder extends RecyclerView.ViewHolder {
		private static final String TAG = "FeedViewHolder";

		FeedViewHolder(View itemView) {
			super(itemView);
		}

		void bind(String title, final String link) {
			FeedNewsView view = ((FeedNewsView) itemView);
			view.setTitle(title);
			view.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.d(TAG, String.format("onClick: open link: %s", link));
				}
			});
		}
	}

	private static class DiffCallback<T> extends DiffUtil.Callback {
		private final List<T> oldList;
		private final List<T> newList;

		DiffCallback(List<T> oldList, List<T> newList) {
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
}
