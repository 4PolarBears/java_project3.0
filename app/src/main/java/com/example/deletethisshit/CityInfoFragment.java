package com.example.deletethisshit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CityInfoFragment extends Fragment {
    private static final String ARG_ITEM = "item";
    private int population;
    private String cityChoice;
    private TextView textViewTitle;
    private TextView textViewDescription;

    public CityInfoFragment (String cityChoice) {
        super();
        this.cityChoice = cityChoice;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        textViewDescription = (TextView) view.findViewById(R.id.cityInfo);
        textViewTitle = (TextView) view.findViewById(R.id.cityName);
//        vziat source zdes i tak otdelnij v kazdom tabe atdelno

        Context context = getContext();
        MunicipalityDataRetriever municipalityDataRetriever = new MunicipalityDataRetriever();
        WeatherDataRetriever weatherDataRetriever = new WeatherDataRetriever();

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
                            @Override
                            public void run() {
                                MunicipalityDataRetriever.getMunicipalityCodesMap();
                                MunicipalityData allData = municipalityDataRetriever.getData(context, cityChoice);

                                WeatherData weatherData = weatherDataRetriever.getData(cityChoice);

                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        String weatherDataAsString = weatherData.getName() + "\n" +
                                                "Weather now: " + weatherData.getMain() + "(" + weatherData.getDescription() + ")\n" +
                                                "Temperature: " + weatherData.getTemperature() + "\n" +
                                                "Wind speed: " + weatherData.getWindSpeed() + "\n";
                                        textViewTitle.setText(weatherDataAsString);

                                        int f = allData.getHeadersCount();
                                        int h = allData.getYearsCount();

                                        String dataString = "";
                                        dataString = "Year" + "\t";
                                        for (int a = 0; a < f; a++) {
                                            dataString = dataString + allData.getHeaders(a) + "\t";
                                        }
                                        dataString = dataString + "\n";

                                        for (int a = 0; a < h; a++) {
                                            dataString = dataString + allData.getYear(a) + "\t";
                                            for (int c = 0; c < f; c++) {
                                                dataString = dataString + allData.getItem(a, c) + "\t";
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


