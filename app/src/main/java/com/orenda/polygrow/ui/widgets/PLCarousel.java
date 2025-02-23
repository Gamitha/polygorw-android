package com.orenda.polygrow.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.orenda.polygrow.R;

import java.util.ArrayList;
import java.util.List;

public class PLCarousel extends FrameLayout {
    private ImageSlider imageSlider;
    private PLImageCardGroup carouselIndicator;
    private List<SlideModel> slideModels;

    public PLCarousel(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public PLCarousel(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PLCarousel(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.fragment_p_l_carousel, this, true);
        imageSlider = findViewById(R.id.image_slider);
        carouselIndicator = findViewById(R.id.carousel_indicator);

        carouselIndicator.setOnImageCardSelectedListener(new PLImageCardGroup.OnImageCardSelectedListener() {
            @Override
            public void onImageCardSelected(PLImageCardView imageCardView, Integer index) {
                Log.i("PLCarousel", "Image Card Selected: " + index);

            }
        });
        slideModels = new ArrayList<>();
    }

    public void setImages(List<String> imageResources) {
        slideModels.clear();
        for (String imageResource : imageResources) {
            slideModels.add(new SlideModel(imageResource, null, ScaleTypes.CENTER_CROP));
        }
        imageSlider.setImageList(slideModels);
        addIndicators(imageResources);
    }

    private void addIndicators(List<String> imageResources) {
        carouselIndicator.removeAllViews();
        for (int i = 0; i < imageResources.size(); i++) {
            String imageResource = imageResources.get(i);
            PLImageCardView imageCardView = new PLImageCardView(getContext());
            LayoutParams layoutParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT
            );
            layoutParams.setMargins(8, 0, 8, 0);
            imageCardView.setLayoutParams(layoutParams);
            imageCardView.setImageResource(imageResource);
            imageCardView.setCardCornerRadius(10);

            carouselIndicator.addView(imageCardView);
        }
    }


}