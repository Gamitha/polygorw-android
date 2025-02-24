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

        carouselWithIndicator = view.findViewById(R.id.carousel_with_indicator);
        List<String> imageResources = Arrays.asList(
                "https://picsum.photos/id/237/200/300",
                "https://picsum.photos/seed/picsum/200/300",
                "https://picsum.photos/200/300?grayscale"
        );
        carouselWithIndicator.setImages(imageResources);
        return this.view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("ProjectDetailsViewFragment", "onDestroyView");
        getActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
    }
}