package com.wadedwyane.www.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wadedwyane.www.R;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> {

    private Context mContext;
    private String[] titles;

    public MusicAdapter(Context context) {
        this.mContext = context;
        titles = mContext.getResources().getStringArray(R.array.music);
    }

    @NonNull
    @Override
    public MusicAdapter.MusicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_music, viewGroup, false);
        return new MusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.MusicHolder viewHolder, int i) {
        viewHolder.item_postion.setText(String.valueOf(i));
        viewHolder.music_name.setText(titles[i]);
    }

    @Override
    public int getItemCount() {
        return null == titles ? 0 : titles.length;
    }

    public class MusicHolder extends RecyclerView.ViewHolder {

        TextView music_name;
        TextView item_postion;
        MusicHolder(View view) {

            super(view);
            music_name = (TextView) view.findViewById(R.id.music_name);
            item_postion = view.findViewById(R.id.item_postion);
        }
    }
}
