package com.example.pendulumtestjava.fragments.pendulumFragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.pendulumFragments.views.infoActivity.InfoView;
import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.DoublePModelRepo;
import com.example.pendulumtestjava.fragments.pendulumFragments.views.DoublePendulumView;
import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.SinglePModelRepo;
import com.example.pendulumtestjava.fragments.pendulumFragments.views.SinglePendulumView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PendulumsFragment extends Fragment {

    private SinglePModelRepo dataS = SinglePModelRepo.getInstance();
    private DoublePModelRepo dataD = DoublePModelRepo.getInstance();
    private SharedPreferences preferences;
    private TextView lastPlayedSingle, lastPlayedDouble;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_pendulums, container, false);

        CardView singleCard = v.findViewById(R.id.singleCard);
        singleCard.setOnClickListener(v1 -> openSinglePendulumActivity());
        CardView doubleCard = v.findViewById(R.id.doubleCard);
        doubleCard.setOnClickListener(v12 -> openDoublePendulumActivity());

        lastPlayedSingle = v.findViewById(R.id.lastPlayedSingle);
        lastPlayedDouble = v.findViewById(R.id.lastPlayedDouble);
        Button singleInfo = v.findViewById(R.id.singleInfo);
        Button doubleInfo = v.findViewById(R.id.doubleInfo);
        singleInfo.setOnClickListener(v14 -> openInfoFragment("single"));
        doubleInfo.setOnClickListener(v13 -> openInfoFragment("double"));

        preferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editor = preferences.edit();

        lastPlayedSingle.setText(preferences.getString("single", "-"));
        lastPlayedDouble.setText(preferences.getString("double", "-"));

        return v;
    }

    private void openInfoFragment(String type) {
        Intent intent = new Intent(getActivity(), InfoView.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }


    private void openSinglePendulumActivity() {
        Intent intent = new Intent(getActivity(), SinglePendulumView.class);
        dataS.resetValues();
        editor.putString("single", getCurrentTime());
        editor.apply();

        startActivity(intent);
    }

    private void openDoublePendulumActivity() {
        Intent intent = new Intent(getActivity(), DoublePendulumView.class);
        dataD.resetValues();
        editor.putString("double", getCurrentTime());
        editor.apply();

        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        lastPlayedSingle.setText(preferences.getString("single", "-"));
        lastPlayedDouble.setText(preferences.getString("double", "-"));
    }

    private String getCurrentTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
