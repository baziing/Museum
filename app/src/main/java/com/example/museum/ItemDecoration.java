package com.example.museum;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemDecoration extends RecyclerView.ItemDecoration{
    private int space;

    public ItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // Add top margin only for the no first item to avoid double space between items
        if (parent.getChildPosition(view) != 0) {
            outRect.left = space;
            outRect.right = 0;
            outRect.bottom = 0;
            outRect.top = 0;
        }
    }
}
