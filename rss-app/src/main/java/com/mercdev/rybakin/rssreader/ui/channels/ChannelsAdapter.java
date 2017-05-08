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

class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelViewHolder> {
	private final List<ChannelInfo> channels = new ArrayList<>();

	private ChannelsListView.ChannelSelectListener listener;

	@Override
	public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ChannelViewHolder(View.inflate(parent.getContext(), R.layout.v_channel, null), listener);
	}

	@Override
	public void onBindViewHolder(ChannelViewHolder holder, int position) {
		holder.bind(channels.get(position));
	}

	@Override
	public int getItemCount() {
		return channels.size();
	}

	void setList(List<ChannelInfo> list) {
		channels.clear();
		channels.addAll(list);
		notifyDataSetChanged();
	}

	void setChannelSelectionListener(ChannelsListView.ChannelSelectListener listener) {
		this.listener = listener;
	}

	static class ChannelViewHolder extends RecyclerView.ViewHolder {
		private final ChannelsListView.ChannelSelectListener listener;

		private final TextView channelTitle;
		private final ImageView channelImage;

		ChannelViewHolder(View itemView, ChannelsListView.ChannelSelectListener listener) {
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
					listener.onChannelSelected(channel);
				}
			});
		}
	}
}
