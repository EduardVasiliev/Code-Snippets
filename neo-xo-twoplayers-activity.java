package com.eduard.xo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eduard.xo.dialogs.BackDialog;
import com.eduard.xo.R;
import com.eduard.xo.dialogs.WinnerDialogXWon;
import com.eduard.xo.dialogs.WinnerDialogOWon;
import com.eduard.xo.util.FacebookAds;
import com.eduard.xo.util.MyAnim;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.eduard.xo.util.MyAnim.accDecInterpolator;

public class PlayMultiplayerActivity extends AppCompatActivity {

    private ImageView background;

    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private TextView bar1;
    private TextView bar2;
    private TextView bar3;
    private TextView bar4;
    private TextView score1;
    private TextView score2;
    private TextView briefing;
    private TextView P1;
    private TextView P2;
    private TextView back;
    private TextView textViewX;
    private TextView textViewO;

    private Handler mHandler = new Handler();
    private float finValue;
    private int MatchLength;
    private boolean firstTurn = true;
    private boolean hasFocusFinished = false;

    List<Boolean> bClicked = Arrays.asList(false ,false ,false ,false ,false ,false,
                                            false, false, false);
    int match = 0;
    int turn = 0;
    int P1Score = 0;
    int P2Score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_multiplayer);

        background = (ImageView) findViewById(R.id.background);

        Intent intent = getIntent();
        finValue = intent.getFloatExtra(OptionsActivity.BACKGROUND_FINVALUE, 0);
        MatchLength = intent.getIntExtra(OptionsActivity.EXTRA, 0);

        background.setScaleX(finValue);
        background.setScaleY(finValue);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);

        bar1 = (TextView) findViewById(R.id.Bar1);
        bar2 = (TextView) findViewById(R.id.Bar2);
        bar3 = (TextView) findViewById(R.id.Bar3);
        bar4 = (TextView) findViewById(R.id.Bar4);

        score1 = (TextView) findViewById(R.id.score1);
        score2 = (TextView) findViewById(R.id.score2);
        briefing = (TextView) findViewById(R.id.Briefing);
        P1 = (TextView) findViewById(R.id.textViewX);
        P2 = (TextView) findViewById(R.id.textViewO);
        back = (Button) findViewById(R.id.back);
        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewO = (TextView) findViewById(R.id.textViewO);

        Stream.of(bar1, bar2, bar3, bar4)
                .forEach(x -> x.setVisibility(View.INVISIBLE));

        // Load Ads
        new Thread(() -> {
            FacebookAds.load(getApplicationContext());
        }).start();

        b1.setOnClickListener(v -> {
            if(bClicked.get(0) == false) {
                putSign(b1);
                bClicked.set(0, true);
                updateScore();
            }
        });

        b2.setOnClickListener(v -> {
            if(bClicked.get(1) == false) {
                putSign(b2);
                bClicked.set(1, true);
                updateScore();
            }
        });

        b3.setOnClickListener(v -> {
            if(bClicked.get(2) == false) {
                putSign(b3);
                bClicked.set(2, true);
                updateScore();
            }
        });

        b4.setOnClickListener(v -> {
            if(bClicked.get(3) == false) {
                putSign(b4);
                bClicked.set(3, true);
                updateScore();
            }
        });

        b5.setOnClickListener(v -> {
            if(bClicked.get(4) == false) {
                putSign(b5);
                bClicked.set(4, true);
                updateScore();
            }
        });

        b6.setOnClickListener(v -> {
            if(bClicked.get(5) == false) {
                putSign(b6);
                bClicked.set(5, true);
                updateScore();
            }
        });

        b7.setOnClickListener(v -> {
            if(bClicked.get(6) == false) {
                putSign(b7);
                bClicked.set(6, true);
                updateScore();
            }
        });

        b8.setOnClickListener(v -> {
            if(bClicked.get(7) == false) {
                putSign(b8);
                bClicked.set(7, true);
                updateScore();
            }
        });

        b9.setOnClickListener(v -> {
            if(bClicked.get(8) == false) {
                putSign(b9);
                bClicked.set(8, true);
                updateScore();
            }
        });

        back.setOnClickListener(v -> openDialogBack());

        mHandler.postDelayed(() -> {
            briefing.setTextColor(Color.parseColor("#B30000"));
            briefing.setText("X :: goes first");
            MyAnim.changeAlpha(briefing, 0.0f, 1.0f, 500, 0, accDecInterpolator);
        }, 1600);

    }

    public void putSign(Button b) {
        if(firstTurn) {
            MyAnim.changeAlpha(briefing, 1.0f, 0.0f, 500, 0, accDecInterpolator);
            firstTurn = false;
        }
        if(turn % 2 == 0) {
            b.setTextColor(Color.parseColor("#B30000"));
            b.setText("X");
            MyAnim.changeAlpha(b, 0.0f, 1.0f, 200, 0, accDecInterpolator);
            if(Stream.of(textViewX, textViewO).anyMatch(x-> x.getAlpha()!= 1.0f)) {
                MyAnim.changeAlpha(textViewO, 0.3f, 1.0f, 500, 0, accDecInterpolator);
            }
            MyAnim.changeAlpha(textViewX, 1.0f, 0.3f, 500, 0, accDecInterpolator);
        } else {
            b.setTextColor(Color.parseColor("#007fdf"));
            b.setText("O");
            MyAnim.changeAlpha(b, 0.0f, 1.0f, 200, 0, accDecInterpolator);
            if(Stream.of(textViewX, textViewO).anyMatch(x-> x.getAlpha()!= 1.0f)) {
                MyAnim.changeAlpha(textViewX, 0.3f, 1.0f, 500, 0, accDecInterpolator);
            }
            MyAnim.changeAlpha(textViewO, 1.0f, 0.3f, 500, 0, accDecInterpolator);
        }

        turn++;
    }

    public boolean hasWon() {
        String b1Sign = b1.getText().toString();
        String b2Sign = b2.getText().toString();
        String b3Sign = b3.getText().toString();
        String b4Sign = b4.getText().toString();
        String b5Sign = b5.getText().toString();
        String b6Sign = b6.getText().toString();
        String b7Sign = b7.getText().toString();
        String b8Sign = b8.getText().toString();
        String b9Sign = b9.getText().toString();

        if(b1Sign.equals(b2Sign) && b2Sign.equals(b3Sign) && !b1Sign.equals("")) {
            b1.setTextColor(Color.parseColor("#FFD700"));
            b2.setTextColor(Color.parseColor("#FFD700"));
            b3.setTextColor(Color.parseColor("#FFD700"));
            return true;
        } else if(b4Sign.equals(b5Sign) && b5Sign.equals(b6Sign) && !b4Sign.equals("")) {
            b4.setTextColor(Color.parseColor("#FFD700"));
            b5.setTextColor(Color.parseColor("#FFD700"));
            b6.setTextColor(Color.parseColor("#FFD700"));
            return true;
        } else if(b7Sign.equals(b8Sign) && b8Sign.equals(b9Sign) && !b7Sign.equals("")) {
            b7.setTextColor(Color.parseColor("#FFD700"));
            b8.setTextColor(Color.parseColor("#FFD700"));
            b9.setTextColor(Color.parseColor("#FFD700"));
            return true;
        } else if(b1Sign.equals(b4Sign) && b4Sign.equals(b7Sign) && !b1Sign.equals("")) {
            b1.setTextColor(Color.parseColor("#FFD700"));
            b4.setTextColor(Color.parseColor("#FFD700"));
            b7.setTextColor(Color.parseColor("#FFD700"));
            return true;
        } else if(b2Sign.equals(b5Sign) && b5Sign.equals(b8Sign) && !b2Sign.equals("")) {
            b2.setTextColor(Color.parseColor("#FFD700"));
            b5.setTextColor(Color.parseColor("#FFD700"));
            b8.setTextColor(Color.parseColor("#FFD700"));
            return true;
        } else if(b3Sign.equals(b6Sign) && b6Sign.equals(b9Sign) && !b3Sign.equals("")) {
            b3.setTextColor(Color.parseColor("#FFD700"));
            b6.setTextColor(Color.parseColor("#FFD700"));
            b9.setTextColor(Color.parseColor("#FFD700"));
            return true;
        } else if(b1Sign.equals(b5Sign) && b5Sign.equals(b9Sign) && !b1Sign.equals("")) {
            b1.setTextColor(Color.parseColor("#FFD700"));
            b5.setTextColor(Color.parseColor("#FFD700"));
            b9.setTextColor(Color.parseColor("#FFD700"));
            return true;
        } else if(b3Sign.equals(b5Sign) && b5Sign.equals(b7Sign) && !b3Sign.equals("")) {
            b3.setTextColor(Color.parseColor("#FFD700"));
            b5.setTextColor(Color.parseColor("#FFD700"));
            b7.setTextColor(Color.parseColor("#FFD700"));
            return true;
        } else {
            return false;
        }
    }

    public boolean hasTied() {
        String b1Sign = b1.getText().toString();
        String b2Sign = b2.getText().toString();
        String b3Sign = b3.getText().toString();
        String b4Sign = b4.getText().toString();
        String b5Sign = b5.getText().toString();
        String b6Sign = b6.getText().toString();
        String b7Sign = b7.getText().toString();
        String b8Sign = b8.getText().toString();
        String b9Sign = b9.getText().toString();

        if(Stream.of(b1Sign, b2Sign, b3Sign, b4Sign, b5Sign, b6Sign, b7Sign, b8Sign, b9Sign)
            .noneMatch(x -> x.equals(""))) {
            return true;
        } else {
            return false;
        }
    }

    public void updateScore() {


        if(hasWon()) {
            for(int i=0; i<bClicked.size(); i++) {
                bClicked.set(i, true);
            }

            if(turn % 2 == 0) {
                P2Score++;
                score2.setText(String.valueOf(P2Score));
                MyAnim.changeAlpha(score2, 0.0f, 1.0f, 500, 0, accDecInterpolator);
                if(P1Score < MatchLength && P2Score < MatchLength) {
                    briefing.setTextColor(Color.parseColor("#007fdf"));
                    briefing.setText("O :: Won!");
                    MyAnim.popAlpha(briefing);
                    match++;
                    resetBoard();
                    MyAnim.changeAlpha(textViewO, 0.3f, 1.0f, 1000, 0, accDecInterpolator);
                } else {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Bundle bundle = new Bundle();
                            bundle.putFloat(OptionsActivity.BACKGROUND_FINVALUE, finValue);
                            bundle.putInt(OptionsActivity.EXTRA, MatchLength);
                            openDialog2(bundle);
                        }
                    },1000);
                }
            } else {
                P1Score++;
                score1.setText(String.valueOf(P1Score));
                MyAnim.changeAlpha(score1, 0.0f, 1.0f, 500, 0, accDecInterpolator);
                if(P1Score < MatchLength && P2Score < MatchLength) {
                    briefing.setTextColor(Color.parseColor("#B30000"));
                    briefing.setText("X :: Won!");
                    MyAnim.popAlpha(briefing);
                    match++;
                    resetBoard();
                    MyAnim.changeAlpha(textViewX, 0.3f, 1.0f, 1000, 0, accDecInterpolator);
                } else {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Bundle bundle = new Bundle();
                            bundle.putFloat(OptionsActivity.BACKGROUND_FINVALUE, finValue);
                            bundle.putInt(OptionsActivity.EXTRA, MatchLength);
                            openDialog1(bundle);
                        }
                    }, 1000);
                }
            }
        }


        if(hasTied() && !hasWon()) {
            for(int i=0; i<bClicked.size(); i++) {
                bClicked.set(i, true);
            }
            briefing.setTextColor(Color.parseColor("#4FC1BE"));
            briefing.setText("That's a tie!");
            MyAnim.popAlpha(briefing);
            match++;
            resetBoard();
        }

    }

    public void resetBoard() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MyAnim.changeAlpha(b1, 1.0f, 0.0f, 300, 0, accDecInterpolator);
                MyAnim.changeAlpha(b2, 1.0f, 0.0f, 300, 0, accDecInterpolator);
                MyAnim.changeAlpha(b3, 1.0f, 0.0f, 300, 0, accDecInterpolator);
                MyAnim.changeAlpha(b4, 1.0f, 0.0f, 300, 0, accDecInterpolator);
                MyAnim.changeAlpha(b5, 1.0f, 0.0f, 300, 0, accDecInterpolator);
                MyAnim.changeAlpha(b6, 1.0f, 0.0f, 300, 0, accDecInterpolator);
                MyAnim.changeAlpha(b7, 1.0f, 0.0f, 300, 0, accDecInterpolator);
                MyAnim.changeAlpha(b8, 1.0f, 0.0f, 300, 0, accDecInterpolator);
                MyAnim.changeAlpha(b9, 1.0f, 0.0f, 300, 0, accDecInterpolator);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b1.setText("");
                        b2.setText("");
                        b3.setText("");
                        b4.setText("");
                        b5.setText("");
                        b6.setText("");
                        b7.setText("");
                        b8.setText("");
                        b9.setText("");
                        for(int i=0; i<bClicked.size(); i++) {
                            bClicked.set(i, false);
                        }

                        // Inceput al jocului alternativ
                        if(match%2!=0) {
                            firstTurn = true;
                            briefing.setTextColor(Color.parseColor("#007fdf"));
                            briefing.setText("O :: goes first");
                            MyAnim.changeAlpha(briefing, 0.0f, 1.0f, 500, 0, accDecInterpolator);
                            turn = 1;

                        } else {
                            firstTurn = true;
                            briefing.setTextColor(Color.parseColor("#B30000"));
                            briefing.setText("X :: goes first");
                            MyAnim.changeAlpha(briefing, 0.0f, 1.0f, 500, 0, accDecInterpolator);
                            turn = 0;
                        }

                    }
                }, 1000);
            }
        }, 2000);

    }

    public void openDialog1(Bundle bundle) {
        WinnerDialogXWon dialog = new WinnerDialogXWon();
        dialog.setArguments(bundle);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "dialog1");
    }

    public void openDialog2(Bundle bundle) {
        WinnerDialogOWon dialog = new WinnerDialogOWon();
        dialog.setCancelable(false);
        dialog.setArguments(bundle);
        dialog.show(getSupportFragmentManager(), "dialog2");
    }

    public void openDialogBack() {
        BackDialog dialog = new BackDialog();
        dialog.show(getSupportFragmentManager(), "dialogBack");
    }

    @Override
    public void onBackPressed() {
        BackDialog dialog = new BackDialog();
        dialog.show(getSupportFragmentManager(), "dialogBack");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus && !hasFocusFinished) {

            Stream.of(bar1, bar2, bar3, bar4)
                    .forEach(x -> x.setVisibility(View.VISIBLE));

            // Bar Animations
            runOnUiThread(() -> MyAnim.slideY(bar1, -500.0f, 1000, 0, accDecInterpolator));
            runOnUiThread(() -> MyAnim.slideY(bar2, 500.0f, 1000, 0, accDecInterpolator));
            runOnUiThread(() -> MyAnim.slideX(bar3, -200.0f, 1000, 0, accDecInterpolator));
            runOnUiThread(() -> MyAnim.slideX(bar4, 200.0f, 1000, 0, accDecInterpolator));
            hasFocusFinished = true;
        }
    }
}
