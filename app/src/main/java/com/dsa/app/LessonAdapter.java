package com.dsa.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dsa.master.R;
import com.google.android.material.button.MaterialButton;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_LESSON = 0;
    private static final int VIEW_TYPE_IDE = 1;

    private final Context context;
    private final List<LessonItem> lessons;

    public LessonAdapter(Context context, List<LessonItem> lessons) {
        this.context = context;
        this.lessons = lessons;
    }

    @Override
    public int getItemViewType(int position) {
        return position < lessons.size() ? VIEW_TYPE_LESSON : VIEW_TYPE_IDE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_IDE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_ide_card, parent, false);
            return new IdeViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_lesson_card, parent, false);
            return new LessonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LessonViewHolder) {
            LessonItem item = lessons.get(position);
            LessonViewHolder lvh = (LessonViewHolder) holder;
            lvh.tvTag.setText(item.getTag());
            lvh.tvSlideRef.setText(item.getSlideRef());
            lvh.tvTitle.setText(item.getTitle());
            lvh.tvDescription.setText(item.getDescription());

            View.OnClickListener launchListener = v -> {
                Intent intent = new Intent(context, LessonDetailActivity.class);
                intent.putExtra(LessonDetailActivity.EXTRA_LESSON_ID, item.getId());
                context.startActivity(intent);
            };

            lvh.itemView.setOnClickListener(launchListener);
            lvh.btnOpen.setOnClickListener(launchListener);
        } else if (holder instanceof IdeViewHolder) {
            IdeViewHolder ivh = (IdeViewHolder) holder;
            View.OnClickListener launchIde = v -> {
                Intent intent = new Intent(context, JavaIdeActivity.class);
                context.startActivity(intent);
            };
            ivh.itemView.setOnClickListener(launchIde);
            ivh.btnLaunchIde.setOnClickListener(launchIde);
        }
    }

    @Override
    public int getItemCount() {
        // All 8 lessons + Java IDE Card directly below Graph & Dijkstra (Lesson 8)
        return lessons.size() + 1;
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView tvTag;
        TextView tvSlideRef;
        TextView tvTitle;
        TextView tvDescription;
        MaterialButton btnOpen;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tvTag);
            tvSlideRef = itemView.findViewById(R.id.tvSlideRef);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnOpen = itemView.findViewById(R.id.btnOpen);
        }
    }

    public static class IdeViewHolder extends RecyclerView.ViewHolder {
        MaterialButton btnLaunchIde;

        public IdeViewHolder(@NonNull View itemView) {
            super(itemView);
            btnLaunchIde = itemView.findViewById(R.id.btnLaunchIde);
        }
    }
}
