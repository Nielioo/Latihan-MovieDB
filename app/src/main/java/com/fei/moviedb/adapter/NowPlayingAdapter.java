package com.fei.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fei.moviedb.R;
import com.fei.moviedb.helper.Const;
import com.fei.moviedb.model.NowPlaying;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder> {

    private Context context;
    private List<NowPlaying.Results> nowPlayingList;

    public NowPlayingAdapter(Context context) {
        this.context = context;
    }

    private List<NowPlaying.Results> getNowPlayingList() {
        return nowPlayingList;
    }

    public void setNowPlayingList(List<NowPlaying.Results> nowPlayingList) {
        this.nowPlayingList = nowPlayingList;
    }

    @NonNull
    @Override
    public NowPlayingAdapter.NowPlayingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
        return new NowPlayingAdapter.NowPlayingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowPlayingAdapter.NowPlayingViewHolder holder, int position) {
        final NowPlaying.Results results = getNowPlayingList().get(position);
        holder.card_title_textView.setText(results.getTitle());
        holder.card_overview_textView.setText(results.getOverview());
        holder.card_releasedDate_textView.setText(results.getRelease_date());
        Glide.with(context)
                .load(Const.IMG_URL+results.getPoster_path())
                .into(holder.card_poster_imageView);
    }

    @Override
    public int getItemCount() {
        return getNowPlayingList().size();
    }

    public class NowPlayingViewHolder extends RecyclerView.ViewHolder {

        ImageView card_poster_imageView;
        TextView card_title_textView, card_overview_textView, card_releasedDate_textView;

        public NowPlayingViewHolder(@NonNull View itemView) {
            super(itemView);

            card_poster_imageView = itemView.findViewById(R.id.card_poster_imageView);
            card_title_textView = itemView.findViewById(R.id.card_title_textView);
            card_overview_textView = itemView.findViewById(R.id.card_overview_textView);
            card_releasedDate_textView = itemView.findViewById(R.id.card_releasedDate_textView);
        }
    }
}
