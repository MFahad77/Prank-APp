package com.example.prankapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prankapp.Model.Question;
import com.example.prankapp.R;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder> {

    private List<Question> questionList;
    private OnItemClickListener onItemClickListener;

    public QuestionsAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    public interface OnItemClickListener {
        void onItemClick(String question);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questionList.get(position);
        holder.bind(question);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void removeQuestion(int position) {
        if (position >= 0 && position < questionList.size()) {
            questionList.remove(position);
            notifyItemRemoved(position);
        }
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewQuestion;

        QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuestion = itemView.findViewById(R.id.textViewQuestion);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            String question = questionList.get(position).getQuestionText();
                            onItemClickListener.onItemClick(question);
                            removeQuestion(position); // Remove the question from the list
                        }
                    }
                }
            });
        }

        void bind(Question question) {
            textViewQuestion.setText(question.getQuestionText());
        }
    }
}
