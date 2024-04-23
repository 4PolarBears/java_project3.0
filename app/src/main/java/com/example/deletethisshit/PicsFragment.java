package com.example.deletethisshit;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PicsFragment extends Fragment {
    private String cityChoice;
    private TextView textViewDescription;
    private TextView textViewTitle;

    public PicsFragment (String cityChoice) {
        super();
        this.cityChoice = cityChoice;
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pictures, container, false);

        textViewDescription = (TextView) view.findViewById(R.id.picturesText);
        textViewTitle = (TextView) view.findViewById(R.id.picturesName);

        Context context = getContext();
        MunicipalityDataRetriever municipalityDataRetriever = new MunicipalityDataRetriever();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
                            @Override
                            public void run() {
                                MunicipalityDataRetriever.getMunicipalityCodesMap();
                                MunicipalityData allData = municipalityDataRetriever.getData(context, cityChoice);


                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textViewTitle.setText(cityChoice);

                                        int f = allData.getHeadersCount();
                                        int h = allData.getYearsCount();

                                        String dataString = "";
                                        dataString = "Year" + "\t" + "\t";
                                        for (int a = 0; a < f; a++) {
                                            dataString = dataString + allData.getHeaders(a) + "\t";
                                        }
                                        dataString = dataString + "\n";

                                        for (int a = 0; a < h; a++) {
                                            dataString = dataString + allData.getYear(a) + "\t" + "\t";
                                            for (int c = 0; c < f; c++) {
                                                dataString = dataString + allData.getItem(a, c) + "\t" + "\t";
                                            }
                                            dataString = dataString + "\n";
                                        }

                                        textViewDescription.setText(dataString);
                                    }
                                });                            }
                        }
        );

        return view;
    }
}
