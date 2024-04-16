package com.example.a2048;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
        holder.textBestCase.setTextColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_light_text));
        switch (score.getBestTile()){
            case 0:
                holder.textBestCase.setText("");
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_0));
                break;
            case 2:
                holder.textBestCase.setTextColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_text));
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_2));
                break;
            case 4:
                holder.textBestCase.setTextColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_text));
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_4));
                break;
            case 8:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_8));
                break;
            case 16:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_16));
                break;
            case 32:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_32));
                break;
            case 64:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_64));
                break;
            case 128:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_128));
                break;
            case 256:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_256));
                break;
            case 512:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_512));
                break;
            case 1024:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_1024));
                break;
            case 2048:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.color_tile_2048));
                break;
            default:
                holder.textBestCase.setBackgroundColor(ContextCompat.getColor(holder.textBestCase.getContext(), R.color.red));
                break;
        }
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
