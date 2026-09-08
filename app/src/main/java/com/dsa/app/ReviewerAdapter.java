package com.dsa.app;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.dsa.master.R;
import com.google.android.material.button.MaterialButton;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReviewerAdapter extends RecyclerView.Adapter<ReviewerAdapter.ReviewerViewHolder> {

    private final Context context;
    private final List<ReviewerItem> items;
    private final Set<Integer> revealedAnswers = new HashSet<>();

    public ReviewerAdapter(Context context, List<ReviewerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ReviewerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reviewer_card, parent, false);
        return new ReviewerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewerViewHolder holder, int position) {
        ReviewerItem item = items.get(position);

        holder.tvCardLessonTag.setText(item.getLessonTag());
        holder.tvCardTopic.setText(item.getTopic());
        holder.tvCardQuestion.setText(item.getQuestion());

        if (item.getType() == ReviewerItem.Type.FORMULA) {
            holder.tvCardTypeTag.setText("FORMULA");
            holder.tvCardTypeTag.setTextColor(ContextCompat.getColor(context, R.color.accent_orange));
        } else if (item.getType() == ReviewerItem.Type.QUIZ) {
            holder.tvCardTypeTag.setText("QUIZ");
            holder.tvCardTypeTag.setTextColor(ContextCompat.getColor(context, R.color.accent_purple));
        } else {
            holder.tvCardTypeTag.setText("CONCEPT");
            holder.tvCardTypeTag.setTextColor(ContextCompat.getColor(context, R.color.accent_green));
        }

        if (item.getType() == ReviewerItem.Type.QUIZ) {
            holder.layoutAnswerContainer.setVisibility(View.GONE);
            holder.layoutQuizContainer.setVisibility(View.VISIBLE);
            setupQuiz(holder, item, position);
        } else {
            holder.layoutQuizContainer.setVisibility(View.GONE);
            holder.layoutAnswerContainer.setVisibility(View.VISIBLE);
            setupFlashcard(holder, item, position);
        }
    }

    private void setupFlashcard(ReviewerViewHolder holder, ReviewerItem item, int position) {
        holder.tvCardAnswer.setText(item.getAnswer());
        holder.tvCardExplanation.setText(item.getExplanation());

        boolean isRevealed = revealedAnswers.contains(position);
        holder.layoutAnswerDetails.setVisibility(isRevealed ? View.VISIBLE : View.GONE);
        holder.btnToggleAnswer.setText(isRevealed ? "🙈 Hide Answer" : "👁 Reveal Answer");

        holder.btnToggleAnswer.setOnClickListener(v -> {
            if (revealedAnswers.contains(position)) {
                revealedAnswers.remove(position);
                holder.layoutAnswerDetails.setVisibility(View.GONE);
                holder.btnToggleAnswer.setText("👁 Reveal Answer");
            } else {
                revealedAnswers.add(position);
                holder.layoutAnswerDetails.setVisibility(View.VISIBLE);
                holder.btnToggleAnswer.setText("🙈 Hide Answer");
            }
        });
    }

    private void setupQuiz(ReviewerViewHolder holder, ReviewerItem item, int position) {
        holder.rgQuizOptions.removeAllViews();
        holder.tvQuizFeedback.setVisibility(View.GONE);
        holder.btnSubmitQuiz.setEnabled(true);

        String[] options = item.getQuizOptions();
        if (options != null) {
            for (int i = 0; i < options.length; i++) {
                RadioButton rb = new RadioButton(context);
                rb.setId(1000 + i);
                rb.setText(options[i]);
                rb.setTextColor(ContextCompat.getColor(context, R.color.text_primary));
                rb.setTextSize(13);
                rb.setPadding(12, 10, 12, 10);
                holder.rgQuizOptions.addView(rb);
            }
        }

        holder.btnSubmitQuiz.setOnClickListener(v -> {
            int selectedId = holder.rgQuizOptions.getCheckedRadioButtonId();
            if (selectedId == -1) {
                holder.tvQuizFeedback.setVisibility(View.VISIBLE);
                holder.tvQuizFeedback.setBackgroundColor(Color.parseColor("#44F59E0B"));
                holder.tvQuizFeedback.setTextColor(ContextCompat.getColor(context, R.color.accent_orange));
                holder.tvQuizFeedback.setText("⚠️ Please select an option first!");
                return;
            }

            int selectedIndex = selectedId - 1000;
            holder.tvQuizFeedback.setVisibility(View.VISIBLE);

            if (selectedIndex == item.getCorrectOptionIndex()) {
                holder.tvQuizFeedback.setBackgroundColor(Color.parseColor("#3310B981"));
                holder.tvQuizFeedback.setTextColor(ContextCompat.getColor(context, R.color.accent_green));
                holder.tvQuizFeedback.setText("✅ Correct!\n" + item.getExplanation());
            } else {
                holder.tvQuizFeedback.setBackgroundColor(Color.parseColor("#33EF4444"));
                holder.tvQuizFeedback.setTextColor(Color.parseColor("#FCA5A5"));
                String correctText = (options != null && item.getCorrectOptionIndex() >= 0 && item.getCorrectOptionIndex() < options.length) 
                        ? options[item.getCorrectOptionIndex()] : item.getAnswer();
                holder.tvQuizFeedback.setText("❌ Incorrect.\nCorrect Answer: " + correctText + "\n\n" + item.getExplanation());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ReviewerViewHolder extends RecyclerView.ViewHolder {
        TextView tvCardLessonTag;
        TextView tvCardTypeTag;
        TextView tvCardTopic;
        TextView tvCardQuestion;

        // Flashcard view elements
        LinearLayout layoutAnswerContainer;
        MaterialButton btnToggleAnswer;
        LinearLayout layoutAnswerDetails;
        TextView tvCardAnswer;
        TextView tvCardExplanation;

        // Quiz view elements
        LinearLayout layoutQuizContainer;
        RadioGroup rgQuizOptions;
        MaterialButton btnSubmitQuiz;
        TextView tvQuizFeedback;

        public ReviewerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCardLessonTag = itemView.findViewById(R.id.tvCardLessonTag);
            tvCardTypeTag = itemView.findViewById(R.id.tvCardTypeTag);
            tvCardTopic = itemView.findViewById(R.id.tvCardTopic);
            tvCardQuestion = itemView.findViewById(R.id.tvCardQuestion);

            layoutAnswerContainer = itemView.findViewById(R.id.layoutAnswerContainer);
            btnToggleAnswer = itemView.findViewById(R.id.btnToggleAnswer);
            layoutAnswerDetails = itemView.findViewById(R.id.layoutAnswerDetails);
            tvCardAnswer = itemView.findViewById(R.id.tvCardAnswer);
            tvCardExplanation = itemView.findViewById(R.id.tvCardExplanation);

            layoutQuizContainer = itemView.findViewById(R.id.layoutQuizContainer);
            rgQuizOptions = itemView.findViewById(R.id.rgQuizOptions);
            btnSubmitQuiz = itemView.findViewById(R.id.btnSubmitQuiz);
            tvQuizFeedback = itemView.findViewById(R.id.tvQuizFeedback);
        }
    }
}
