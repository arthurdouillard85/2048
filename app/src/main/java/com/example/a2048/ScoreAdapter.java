package com.example.a2048;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private ArrayList<Score> scores;

    public ScoreAdapter(ArrayList<Score> scores) {
        this.scores = scores;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_score_joueur, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = scores.get(position);
        holder.textClassement.setText(String.valueOf(position + 1));
        holder.textName.setText(score.getPseudo());
        holder.textScore.setText(String.valueOf(score.getScore()));
        holder.textBestCase.setText(String.valueOf(score.getBestTile()));
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView textClassement, textName, textScore, textBestCase;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textClassement = itemView.findViewById(R.id.textClassement);
            textName = itemView.findViewById(R.id.textName);
            textScore = itemView.findViewById(R.id.textScore);
            textBestCase = itemView.findViewById(R.id.textBestCase);
        }
    }
}
