package com.example.pendulumtestjava;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import yuku.ambilwarna.AmbilWarnaDialog;

public class SinglePSettings extends AppCompatDialogFragment {
    private Button colorButton;
    private int defaultColor;
    SinglePData data = SinglePData.getInstance();
    private EditText a, r, g, damp, trace;
    private Switch switch1;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_singlepsettings, null);

        a = (EditText) view.findViewById(R.id.a);
        double temp = Math.toDegrees(data.getA());
        a.setText(String.format("%.0f", temp));
        r = (EditText) view.findViewById(R.id.r);
        r.setText(String.format("%.0f", data.getR()));
        g = (EditText) view.findViewById(R.id.g);
        g.setText(String.format("%.1f", data.getGravity()));
        damp = (EditText) view.findViewById(R.id.damp);
        damp.setText(String.format("%.3f", data.getDamping()));
        trace = (EditText) view.findViewById(R.id.trace);
        trace.setText(String.valueOf(data.getTrace()));
        switch1 = (Switch) view.findViewById(R.id.switch1);

        if(data.getTrace() == 0)
        {
            switch1.setChecked(false);
        } else {
            switch1.setChecked(true);
        }





        defaultColor = data.getDrawColor();

        colorButton = (Button) view.findViewById(R.id.colorButton);
        colorButton.setBackgroundColor(data.getDrawColor());
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", "hali");
                openColorPicker();
            }
        });

        builder.setView(view)
                .setTitle("Settings")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                data.setA(Double.parseDouble(a.getText().toString()));
                                data.setR(Double.parseDouble(r.getText().toString()));
                                data.setGravity((float)Double.parseDouble(g.getText().toString()));
                                data.setDamping((float)Double.parseDouble(damp.getText().toString()));
                                if(switch1.isChecked()) {
                                    data.setTrace((int) Double.parseDouble(trace.getText().toString()));
                                }else {
                                    data.setTrace(0);
                                }
                                data.setDrawColor(defaultColor);
                            }
                        }
                )

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                )
        ;

        return builder.create();
    }
    public void openColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                colorButton.setBackgroundColor(defaultColor);
            }
        });
        colorPicker.show();
    }
}