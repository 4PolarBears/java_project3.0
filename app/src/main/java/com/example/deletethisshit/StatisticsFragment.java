package com.example.deletethisshit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StatisticsFragment extends Fragment {
    private String cityChoice;

    public StatisticsFragment (String cityChoice) {
        super();
        this.cityChoice = cityChoice;
    }

    String trash = "something";
    private TextView textViewDescription;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        textViewDescription = (TextView)view.findViewById(R.id.statisticsText);
        textViewDescription.setText(trash);

        return view;
    }
}
