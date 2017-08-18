package com.myprog.zoop.ui.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    int space;
    boolean isHorizontalLayout;

    public DividerItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (isHorizontalLayout) {
            outRect.bottom = space;
            outRect.right = space;
            outRect.left = space;
            outRect.top = space;

        } else {
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0)
                outRect.top = space;
            else
                outRect.top = 0;

        }
    }
}