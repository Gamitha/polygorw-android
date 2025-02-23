package com.orenda.polygrow.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class PLImageCardGroup extends LinearLayout {

    private final List<PLImageCardView> imageCardViews = new ArrayList<>();
    private PLImageCardView selectedImageCardView = null;
    private OnImageCardSelectedListener onImageCardSelectedListener;

    public PLImageCardGroup(Context context) {
        super(context);
        init();
    }

    public PLImageCardGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PLImageCardGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child instanceof PLImageCardView) {
            PLImageCardView imageCardView = (PLImageCardView) child;
            imageCardViews.add(imageCardView);
            imageCardView.setOnImageCardClickListener(new PLImageCardView.OnImageCardClickListener() {
                @Override
                public void onImageCardClick(PLImageCardView clickedImageCardView) {
                    selectImageCardView(clickedImageCardView, imageCardViews.indexOf(imageCardView));
                }
            });
        }
    }

    private void selectImageCardView(PLImageCardView selected, Integer index) {
        if (selectedImageCardView != selected) {
            if (selectedImageCardView != null) {
                selectedImageCardView.setActive(false);
            }
            selectedImageCardView = selected;
            selectedImageCardView.setActive(true);
            if (onImageCardSelectedListener != null) {
                onImageCardSelectedListener.onImageCardSelected(selectedImageCardView, index);
            }
        }
    }

    public void setOnImageCardSelectedListener(OnImageCardSelectedListener listener) {
        this.onImageCardSelectedListener = listener;
    }

    public interface OnImageCardSelectedListener {
        void onImageCardSelected(PLImageCardView imageCardView, Integer index);
    }

    public void addImageCardView(PLImageCardView imageCardView) {
        addView(imageCardView);
    }
}