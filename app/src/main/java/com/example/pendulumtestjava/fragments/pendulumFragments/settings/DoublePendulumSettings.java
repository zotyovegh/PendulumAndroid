package com.example.pendulumtestjava.fragments.pendulumFragments.settings;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pendulumtestjava.R;
import com.example.pendulumtestjava.fragments.pendulumFragments.repositories.DoublePModelRepo;
import com.example.pendulumtestjava.fragments.pendulumFragments.viewModels.Settings.DoubleSettingsViewModel;

import java.util.Objects;

import yuku.ambilwarna.AmbilWarnaDialog;

public class DoublePendulumSettings extends AppCompatDialogFragment {
    private Button ballColor1, ballColor2, traceColor1, traceColor2;
    private int trace1DefaultColor, trace2DefaultColor, ball1DefaultColor, ball2DefaultColor;
    private DoublePModelRepo data = DoublePModelRepo.getInstance();
    private SeekBar a1b, a2b, r1b, r2b, gb, m1b, m2b, trace1b, trace2b;
    private TextView a1num, a2num, r1num, r2num, gnum, m1num, m2num, trace1num, trace2num;
    private static int TRACE1MAX = 101;
    private static int TRACE2MAX = 401;
    private DoubleSettingsViewModel viewModel;
    private boolean randomize;

    @SuppressLint({"SetTextI18n", "DefaultLocale", "InflateParams"})
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.settings_doublep, null);
        viewModel = new ViewModelProvider(this).get(DoubleSettingsViewModel.class);

        a1b = view.findViewById(R.id.a1b);
        a2b = view.findViewById(R.id.a2b);
        r1b = view.findViewById(R.id.r1b);
        r2b = view.findViewById(R.id.r2b);
        gb = view.findViewById(R.id.gb);
        m1b = view.findViewById(R.id.m1b);
        m2b = view.findViewById(R.id.m2b);
        trace1b = view.findViewById(R.id.trace1b);
        trace2b = view.findViewById(R.id.trace2b);
        a1num = view.findViewById(R.id.a1num);
        a2num = view.findViewById(R.id.a2num);
        r1num = view.findViewById(R.id.r1num);
        r2num = view.findViewById(R.id.r2num);
        gnum = view.findViewById(R.id.gnum);
        m1num = view.findViewById(R.id.m1num);
        m2num = view.findViewById(R.id.m2num);
        trace1num = view.findViewById(R.id.trace1num);
        trace2num = view.findViewById(R.id.trace2num);
        ballColor1 = view.findViewById(R.id.ballColor1);
        ballColor2 = view.findViewById(R.id.ballColor2);
        traceColor1 = view.findViewById(R.id.traceColor1);
        traceColor2 = view.findViewById(R.id.traceColor2);

        r1b.setProgress((int) data.getR1());
        r2b.setProgress((int) data.getR2());
        gb.setProgress((int) (data.getG() * 1000));
        m1b.setProgress((int) data.getM1());
        m2b.setProgress((int) data.getM2());
        trace1b.setProgress(data.getTrace1());
        trace2b.setProgress(data.getTrace2());

        r1num.setText(String.format("%.0f", data.getR1()));
        r2num.setText(String.format("%.0f", data.getR2()));
        gnum.setText(String.format("%.2f", data.getG() * 10));
        m1num.setText(String.format("%.0f", data.getM1()));
        m2num.setText(String.format("%.0f", data.getM2()));

        if(Math.toDegrees(data.getA1()) < 0)
        {
            a1b.setProgress((int) Math.toDegrees(data.getA1())+360);
            a1num.setText(String.format("%.0f", Math.toDegrees(data.getA1())+360));
        }else{
            a1b.setProgress((int) Math.toDegrees(data.getA1()));
            a1num.setText(String.format("%.0f", Math.toDegrees(data.getA1())));
        }

        if(Math.toDegrees(data.getA2()) < 0)
        {
            a2b.setProgress((int) Math.toDegrees(data.getA2())+360);
            a2num.setText(String.format("%.0f", Math.toDegrees(data.getA2())+360));
        }else{
            a2b.setProgress((int) Math.toDegrees(data.getA2()));
            a2num.setText(String.format("%.0f", Math.toDegrees(data.getA2())));
        }

        if (!data.isTrace1On()) {
            trace1num.setText("Off");
            trace1b.setProgress(30);
        } else if (data.isEndlessTrace1()) {
            trace1num.setText("Endless");
            trace1b.setProgress(TRACE1MAX);
        } else {
            trace1num.setText(String.valueOf(data.getTrace1()));
        }

        if (!data.isTrace2On()) {
            trace2num.setText("Off");
            trace2b.setProgress(30);
        } else if (data.isEndlessTrace2()) {
            trace2num.setText("Endless");
            trace2b.setProgress(TRACE2MAX);
        } else {
            trace2num.setText(String.valueOf(data.getTrace2()));
        }

        a1b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                a1num.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        a2b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                a2num.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        r1b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r1num.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        r2b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                r2num.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        gb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gnum.setText(Float.toString((float) (progress * 0.01)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        m1b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                m1num.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        m2b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                m2num.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        trace1b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 30) {
                    trace1num.setText("Off");
                } else if (progress == TRACE1MAX) {
                    trace1num.setText("Endless");
                } else {
                    trace1num.setText(String.valueOf(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        trace2b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 30) {
                    trace2num.setText("Off");
                } else if (progress == TRACE2MAX) {
                    trace2num.setText("Endless");
                } else {
                    trace2num.setText(String.valueOf(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        trace1DefaultColor = data.getTrace1Color();
        trace2DefaultColor = data.getTrace2Color();
        ball1DefaultColor = data.getBall1Color();
        ball2DefaultColor = data.getBall2Color();

        ballColor1.setBackgroundColor(data.getBall1Color());
        ballColor2.setBackgroundColor(data.getBall2Color());
        traceColor1.setBackgroundColor(data.getTrace1Color());
        traceColor2.setBackgroundColor(data.getTrace2Color());

        ballColor1.setOnClickListener(v -> ball1ColorPicker());
        ballColor2.setOnClickListener(v -> ball2ColorPicker());
        traceColor1.setOnClickListener(v -> trace1ColorPicker());
        traceColor2.setOnClickListener(v -> trace2ColorPicker());

        viewModel.getDoublePRandom().observe(this, doublePRandom -> {
            if(randomize)
            {
                a1num.setText(String.format("%.0f", Math.toDegrees(doublePRandom.getA1())));
                a2num.setText(String.format("%.0f", Math.toDegrees(doublePRandom.getA2())));
                r1num.setText(String.format("%.0f", doublePRandom.getR1()));
                r2num.setText(String.format("%.0f", doublePRandom.getR2()));
                gnum.setText(String.format("%.2f", doublePRandom.getG()));
                m1num.setText(String.format("%.0f", doublePRandom.getM1()));
                m2num.setText(String.format("%.0f", doublePRandom.getM2()));

                a1b.setProgress((int) Math.toDegrees(doublePRandom.getA1()) / 100);
                a2b.setProgress((int) Math.toDegrees(doublePRandom.getA2()) / 100);
                r1b.setProgress((int) doublePRandom.getR1());
                r2b.setProgress((int) doublePRandom.getR2());
                gb.setProgress((int) (doublePRandom.getG() * 100));
                m1b.setProgress((int) doublePRandom.getM1());
                m2b.setProgress((int) doublePRandom.getM2());
                trace1b.setProgress(doublePRandom.getTrace1());
                trace2b.setProgress(doublePRandom.getTrace2());
            }
            randomize = false;
        });

        viewModel.getErrorMessage().observe(this, s -> Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show());

        builder.setView(view)
                .setTitle("Settings")
                .setPositiveButton("OK",
                        (dialog, whichButton) -> {
                            data.setA1(a1b.getProgress());
                            data.setA2(a2b.getProgress());
                            data.setR1(r1b.getProgress());
                            data.setR2(r2b.getProgress());

                            data.setG((float) (gb.getProgress() * 0.01));
                            data.setM1(m1b.getProgress());
                            data.setM2(m2b.getProgress());

                            if (trace1b.getProgress() == 30) {
                                data.setTrace1On(false);
                                data.setEndlessTrace1(false);
                            } else if (trace1b.getProgress() == TRACE1MAX) {
                                data.setTrace1On(true);
                                data.setEndlessTrace1(true);
                            } else {
                                data.setTrace1(trace1b.getProgress());
                                data.setTrace1On(true);
                                data.setEndlessTrace1(false);
                            }

                            if (trace2b.getProgress() == 30) {
                                data.setTrace2On(false);
                                data.setEndlessTrace2(false);

                            } else if (trace2b.getProgress() == TRACE2MAX) {
                                data.setTrace2On(true);
                                data.setEndlessTrace2(true);

                            } else {
                                data.setTrace2(trace2b.getProgress());
                                data.setTrace2On(true);
                                data.setEndlessTrace2(false);
                            }
                            data.setBall1Color(ball1DefaultColor);
                            data.setBall2Color(ball2DefaultColor);
                            data.setTrace1Color(trace1DefaultColor);
                            data.setTrace2Color(trace2DefaultColor);
                        }
                )
                .setNeutralButton("Randomize", (dialog, which) -> {
                })
                .setNegativeButton("Cancel", (dialog, whichButton) -> dialog.dismiss()
                );
        return builder.create();
    }

    private void ball1ColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), ball1DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ball1DefaultColor = color;
                ballColor1.setBackgroundColor(ball1DefaultColor);
            }
        });
        colorPicker.show();
    }

    private void ball2ColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), ball2DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                ball2DefaultColor = color;
                ballColor2.setBackgroundColor(ball2DefaultColor);
            }
        });
        colorPicker.show();
    }

    private void trace1ColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), trace1DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                trace1DefaultColor = color;
                traceColor1.setBackgroundColor(trace1DefaultColor);
            }
        });
        colorPicker.show();
    }

    private void trace2ColorPicker() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), trace2DefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                trace2DefaultColor = color;
                traceColor2.setBackgroundColor(trace2DefaultColor);
            }
        });
        colorPicker.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        final AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = d.getButton(Dialog.BUTTON_NEUTRAL);
            positiveButton.setOnClickListener(v -> {
                randomize = true;
                viewModel.requestDoubleRandom();
            });
        }
    }
}
