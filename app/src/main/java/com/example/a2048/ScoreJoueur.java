package com.example.a2048;

import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2048.databinding.FragmentScoreJoueurBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScoreJoueur#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreJoueur extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    private FragmentScoreJoueurBinding binding;
    private String classement;
    private String name;
    private int score;
    private int bestCase;

    public ScoreJoueur() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param classement
     * @param name
     * @param score
     * @param bestCase
     * @return A new instance of fragment score_joueur.
     */
    // TODO: Rename and change types and number of parameters
    public static ScoreJoueur newInstance(String classement, String name, int score, int bestCase) {
        ScoreJoueur fragment = new ScoreJoueur();
        Bundle args = new Bundle();
        args.putString("CLASSEMENT", classement);
        args.putString("NAME", name);
        args.putInt("SCORE", score);
        args.putInt("BESTCASE",bestCase);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classement = getArguments().getString("CLASSEMENT", "");
        name = getArguments().getString("NAME", "");
        score = getArguments().getInt("SCORE", 0);
        bestCase = getArguments().getInt("BESTCASE", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScoreJoueurBinding.inflate(inflater, container, false);
        binding.textClassement.setText(String.valueOf(classement));
        binding.textName.setText(name);
        binding.textScore.setText(String.valueOf(score));
        binding.textBestCase.setText(String.valueOf(bestCase));
        return binding.getRoot();
    }
}