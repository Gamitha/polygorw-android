package com.orenda.polygrow.ui.proj;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.orenda.polygrow.R;
import com.orenda.polygrow.ui.widgets.PLCarousel;
import com.orenda.polygrow.ui.widgets.PLImageCardGroup;
import com.orenda.polygrow.ui.widgets.PLImageCardView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProjectDetailsViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProjectDetailsViewFragment extends Fragment {


    private View view;
    private PLCarousel carouselWithIndicator;

    public ProjectDetailsViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_project_details_view, container, false);

        getActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
//
//
//        List<SlideModel> slideModels = List.of(
//                new SlideModel("https://picsum.photos/id/237/200/300", null, ScaleTypes.CENTER_CROP),
//                new SlideModel("https://picsum.photos/seed/picsum/200/300", null, ScaleTypes.CENTER_CROP),
//                new SlideModel("https://picsum.photos/200/300?grayscale", null, ScaleTypes.CENTER_CROP)
//        );
//
//        ImageSlider imageSlider = this.view.findViewById(R.id.image_slider);
//        imageSlider.setImageList(slideModels);

        carouselWithIndicator = view.findViewById(R.id.carousel_with_indicator);
        List<String> imageResources = Arrays.asList(
                "https://picsum.photos/id/237/200/300",
                "https://picsum.photos/seed/picsum/200/300",
                "https://picsum.photos/200/300?grayscale"
        );
        carouselWithIndicator.setImages(imageResources);
        return this.view;
    }

    public void goToNextSlide(ImageSlider imageSlider) {
        try {
            Field field = ImageSlider.class.getDeclaredField("viewPager");
            field.setAccessible(true);
            Object sliderViewPager = field.get(imageSlider);

            if (sliderViewPager instanceof androidx.viewpager.widget.ViewPager) {
                androidx.viewpager.widget.ViewPager viewPager = (androidx.viewpager.widget.ViewPager) sliderViewPager;

                int nextItem = viewPager.getCurrentItem() + 1;
                int itemCount = viewPager.getAdapter().getCount();

                if (nextItem < itemCount) {
                    viewPager.setCurrentItem(nextItem, true);
                } else {
                    viewPager.setCurrentItem(0, true); // Loop back to the first slide
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("ProjectDetailsViewFragment", "onDestroyView");
        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
    }
}