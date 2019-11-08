package com.depromeet.android.main.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.depromeet.android.R;
import com.depromeet.android.data.Category;
import com.depromeet.android.data.CategorySet;

import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainGridViewHolder {

    private Context context;
    private OnItemDragEnterListener itemDragEnterListener;
    private OnItemClickListener itemClickListener;

    @BindView(R.id.category_item)
    ConstraintLayout categoryItem;
    @BindView(R.id.category_title)
    TextView title;
    @BindView(R.id.category_icon)
    ImageView icon;
    @BindView(R.id.category_expenditure)
    TextView expenditure;

    private CategorySet categorySet = new CategorySet();

    public MainGridViewHolder(View view, Context context,
                              OnItemDragEnterListener itemDragEnterListener,
                              OnItemClickListener itemClickListener) {
        ButterKnife.bind(this, view);
        this.context = context;
        this.itemDragEnterListener = itemDragEnterListener;
        this.itemClickListener = itemClickListener;
    }

    public void onBind(Category category, int position) {
        if(category.getCategory() != null) {
            title.setText(category.getCategory());

            int imageId = categorySet.getCategories(position);
            Drawable drawable = context.getResources().getDrawable(imageId);
            icon.setImageDrawable(drawable);

            expenditure.setText("110,400");

            categoryItem.setOnDragListener((v, event) -> {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DROP:
                        // Dropped, reassign View to ViewGroup
                        itemDragEnterListener.onItemDragResult();
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                    default:
                        break;
                }
                return true;
            });
        } else {
            title.setTextColor(context.getColor(R.color.categoryAddTitleTextColor));
            title.setText("추가");
            icon.setImageDrawable(context.getResources().getDrawable(R.drawable.blank_category_icon));

            categoryItem.setOnClickListener(v -> itemClickListener.onItemClick());
        }
    }
}
