package com.mercdev.rybakin.rssreader.ui.channels;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.mercdev.rybakin.rssreader.R;
import com.mercdev.rybakin.rssreader.state.model.ChannelInfo;
import com.squareup.picasso.Picasso;

class ChannelsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private final List<ChannelInfo> channels = new ArrayList<>();

	private static final int ADD_CHANNEL_ITEM = 0;
	private static final int CHANNEL_ITEM = 1;

	private ChannelsListView.OnChannelsListItemClicked listener;

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == ADD_CHANNEL_ITEM) {
			return new AddChannelViewHolder(View.inflate(parent.getContext(), R.layout.v_add_channel, null), listener);
		} else {
			return new ChannelViewHolder(View.inflate(parent.getContext(), R.layout.v_channel, null), listener);
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (position != ADD_CHANNEL_ITEM) {
			((ChannelViewHolder) holder).bind(channels.get(position - 1));
		}
	}

	@Override
	public int getItemCount() {
		return channels.size() + 1;
	}

	@Override
	public int getItemViewType(int position) {
		return position == ADD_CHANNEL_ITEM ? ADD_CHANNEL_ITEM : CHANNEL_ITEM;
	}

	void setList(List<ChannelInfo> list) {
		channels.clear();
		channels.addAll(list);
		notifyDataSetChanged();
	}

	void setChannelSelectionListener(ChannelsListView.OnChannelsListItemClicked listener) {
		this.listener = listener;
	}

	private static class AddChannelViewHolder extends RecyclerView.ViewHolder {
		AddChannelViewHolder(View itemView, ChannelsListView.OnChannelsListItemClicked listener) {
			super(itemView);
			itemView.setOnClickListener(view -> listener.onAddChannelClick());
		}
	}

	private static class ChannelViewHolder extends RecyclerView.ViewHolder {
		private final ChannelsListView.OnChannelsListItemClicked listener;

		private final TextView channelTitle;
		private final ImageView channelImage;

		ChannelViewHolder(View itemView, ChannelsListView.OnChannelsListItemClicked listener) {
			super(itemView);
			this.listener = listener;
			channelTitle = (TextView) itemView.findViewById(R.id.channel_name);
			channelImage = (ImageView) itemView.findViewById(R.id.channel_image);
		}

		void bind(final ChannelInfo channel) {
			channelTitle.setText(channel.getName());
			Picasso.with(itemView.getContext())
					.load(channel.getImageUrl())
					.into(channelImage);
			itemView.setOnClickListener(view -> {
				if (listener != null) {
					listener.onChannelClick(channel);
				}
			});
		}
	}
}
