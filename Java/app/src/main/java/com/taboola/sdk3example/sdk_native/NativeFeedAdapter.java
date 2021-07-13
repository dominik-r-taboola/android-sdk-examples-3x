package com.taboola.sdk3example.sdk_native;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taboola.android.tblnative.TBLImageView;
import com.taboola.android.tblnative.TBLRecommendationItem;
import com.taboola.android.tblnative.TBLTextView;
import com.taboola.sdk3example.R;

import java.util.ArrayList;

public class NativeFeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> mData;

    public NativeFeedAdapter(ArrayList<Object> data) {
        mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View linearLayout = inflater.inflate(R.layout.feed_taboola_item, parent, false);
        return new TaboolaItemViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (mData == null) {
            return;
        }

        TBLRecommendationItem item = (TBLRecommendationItem) mData.get(position);
        ViewGroup adContainer = ((TaboolaItemViewHolder) holder).mAdContainer;
        Context context = adContainer.getContext();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TBLImageView thumbnailView = item.getThumbnailView(context);
        TBLTextView titleView = item.getTitleView(context);
        TBLTextView brandingView = item.getBrandingView(context);
        TBLTextView descriptionView = item.getDescriptionView(context);
        adContainer.addView(thumbnailView);
        adContainer.addView(titleView, layoutParams);
        if (brandingView != null) {
            adContainer.addView(brandingView, layoutParams);
        }
        if (descriptionView != null) {
            adContainer.addView(descriptionView, layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }

        return mData.size();
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof TaboolaItemViewHolder) {
            ((TaboolaItemViewHolder) holder).mAdContainer.removeAllViews();
        }
    }

    class TaboolaItemViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout mAdContainer;

        public TaboolaItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mAdContainer = (LinearLayout) itemView;
        }
    }
}
