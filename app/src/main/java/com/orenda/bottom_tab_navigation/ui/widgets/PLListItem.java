package com.orenda.bottom_tab_navigation.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orenda.bottom_tab_navigation.R;

public class PLListItem extends LinearLayout {
    public enum ListItemVariant { NORMAL, OUTLINED, TEXT_ONLY, CONTAINED }

    private ImageView itemImage;
    private TextView itemTitle;
    private TextView itemSecondaryText;
    private ImageButton itemAction;
    private LinearLayout rootLayout;

    public PLListItem(Context context) {
        super(context);
        init(null);
    }

    public PLListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PLListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.pl_list_item, this, true);

        rootLayout = findViewById(R.id.rootLayout);
        itemImage = findViewById(R.id.itemImage);
        itemTitle = findViewById(R.id.itemTitle);
        itemSecondaryText = findViewById(R.id.itemSecondaryText);
        itemAction = findViewById(R.id.itemAction);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PLListItem);
            int variantValue = a.getInt(R.styleable.PLListItem_list_item_variant, 0); // Default to DEFAULT variant


            applyVariant(ListItemVariant.values()[variantValue]);

            // Read custom attributes
            String titleText = a.getString(R.styleable.PLListItem_titleText);
            if (titleText != null) itemTitle.setText(titleText);

            String secondaryText = a.getString(R.styleable.PLListItem_secondaryText);
            if (secondaryText != null) itemSecondaryText.setText(secondaryText);
            else itemSecondaryText.setVisibility(GONE);

            int imageResId = a.getResourceId(R.styleable.PLListItem_imageSrc, 0);
            if (imageResId != 0) itemImage.setImageResource(imageResId);

            boolean showAction = a.getBoolean(R.styleable.PLListItem_showAction, true);
            itemAction.setVisibility(showAction ? VISIBLE : GONE);

            a.recycle();

        }
    }

    public void applyVariant(ListItemVariant variant) {
        switch (variant) {
            case NORMAL:
                rootLayout.setPadding(16, 16, 16, 16);
                itemSecondaryText.setVisibility(VISIBLE);
                break;
            case OUTLINED:
                rootLayout.setPadding(8, 8, 8, 8);
                break;
            case CONTAINED:
                rootLayout.setPadding(24, 24, 24, 24);
                break;
        }
        itemImage.requestLayout(); // Apply size changes
    }

    public void setTitle(String title) {
        itemTitle.setText(title);
    }

    public void setSecondaryText(String text) {
        itemSecondaryText.setText(text);
    }

    public void setImageResource(int resId) {
        itemImage.setImageResource(resId);
    }

    public void setOnActionClickListener(OnClickListener listener) {
        itemAction.setOnClickListener(listener);
    }
}
