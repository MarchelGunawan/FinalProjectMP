package com.example.mobileprogrammingfinalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class StageFourth extends AppCompatActivity {

    ProgressBar hp1, hp2;
    LinearLayout t1, t2, t3, t4, t5, t6, t7, t8, t9, btn_atk, potion, atkbuff, defbuff;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, coin;
    int amountC = 1800;
    int hpenemy = 1000;
    int hpplayer = 1000;
    int totalatkplayer = 0;
    int totaldefplayers = 0;
    int totalatkenemy = 745;
    int totaldefenemy = 1040;
    Champion warrior = new Champion(100, 70, 100);
    Champion archer = new Champion(200, 60, 150);
    Champion wizard = new Champion(300, 10, 200);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_fourth);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        hp1 = findViewById(R.id.progressBar);
        hp2 = findViewById(R.id.progressBar2);

        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t9 = findViewById(R.id.t9);
        btn_atk = findViewById(R.id.btn_atk);
        potion = findViewById(R.id.potion);
        atkbuff = findViewById(R.id.atkbuff);
        defbuff = findViewById(R.id.defbuff);
        coin = findViewById(R.id.coin);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);

        hp1.setMax(hpenemy);
        hp1.setProgress(hpenemy);

        hp2.setMax(hpplayer);
        hp2.setProgress(hpplayer);

        coin.setText(""+amountC);
    }

    public void check(int hpplayer, int hpenemy) {
        if(hpplayer <= 0){
            btn_atk.setEnabled(false);
        }
        if(hpenemy <= 0){
            Toast.makeText(StageFourth.this, "You win", Toast.LENGTH_SHORT).show();
            btn_atk.setEnabled(false);
        }
    }

    public void clickBuff(View v){
        int percent = 0;
        switch (v.getId()){
            case R.id.potion:
                if (amountC < 200){
                    Toast.makeText(StageFourth.this, "Didn't have enough Money", Toast.LENGTH_SHORT);
                }else{
                    amountC -= 200;
                    coin.setText(""+amountC);
                    percent = (int) (1000 * 0.25);
                    hpplayer += percent;
                    hp2.setProgress(hpplayer);
                }
                break;
            case R.id.atkbuff:
                if (amountC < 100){
                    Toast.makeText(StageFourth.this, "Didn't have enough Money", Toast.LENGTH_SHORT);
                }else{
                    amountC -= 100;
                    coin.setText(""+amountC);
                    percent = (int) (totalatkplayer * 0.1);
                    totalatkplayer += percent;
                    atkbuff.setEnabled(false);
                }
                break;
            case R.id.defbuff:
                if (amountC < 100){
                    Toast.makeText(StageFourth.this, "Didn't have enough Money", Toast.LENGTH_SHORT);
                }else {
                    amountC -= 100;
                    coin.setText("" + amountC);
                    percent = (int) (totaldefenemy * 0.15);
                    totaldefplayers += percent;
                    defbuff.setEnabled(false);
                }
                break;
        }
    }

    public void attack(View v){
        int calatkplayer = totalatkplayer - totaldefenemy;
        hpenemy -= calatkplayer;
        hp1.setProgress(hpenemy);
        int calatkenemy = totalatkenemy - totaldefplayers;
        hpplayer -= calatkenemy;
        hp2.setProgress(hpplayer);
        if(hpenemy <= 500 && hpenemy > 250){
            hp1.getProgressDrawable().setColorFilter(
                    Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        }else if(hpenemy <= 250){
            hp1.getProgressDrawable().setColorFilter(
                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        if(hpplayer <= 500 && hpplayer > 250){
            hp2.getProgressDrawable().setColorFilter(
                    Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        }else if(hpplayer <= 250){
            hp2.getProgressDrawable().setColorFilter(
                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        }
        check(hpplayer, hpenemy);
    }

    public void clickChampion(View v){
        switch (v.getId()) {
            case R.id.t1:
                chooseChampion(t1);
                break;
            case R.id.t2:
                chooseChampion(t2);
                break;
            case R.id.t3:
                chooseChampion(t3);
                break;
            case R.id.t4:
                chooseChampion(t4);
                break;
            case R.id.t5:
                chooseChampion(t5);
                break;
            case R.id.t6:
                chooseChampion(t6);
                break;
            case R.id.t7:
                chooseChampion(t7);
                break;
            case R.id.t8:
                chooseChampion(t8);
                break;
            case R.id.t9:
                chooseChampion(t9);
                break;
        }
    }

    public void chooseChampion(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(StageFourth.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(StageFourth.this).inflate(
                R.layout.layout_warning_dialog,(ConstraintLayout)findViewById(R.id.layoutDialogContainer)
        );
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        if(amountC <= 0 || amountC < 100){
            Toast.makeText(StageFourth.this, "You don't have enough money", Toast.LENGTH_SHORT).show();
        }
        else{
            view.findViewById(R.id.btn_warrior).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (v.getId()){
                        case R.id.t1:
                            img1.setBackgroundResource(R.drawable.knight);
                            tv1.setText("Warrior");
                            tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                        case R.id.t2:
                            img2.setBackgroundResource(R.drawable.knight);
                            tv2.setText("Warrior");
                            tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                        case R.id.t3:
                            img3.setBackgroundResource(R.drawable.knight);
                            tv3.setText("Warrior");
                            tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                        case R.id.t4:
                            img4.setBackgroundResource(R.drawable.knight);
                            tv4.setText("Warrior");
                            tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                        case R.id.t5:
                            img5.setBackgroundResource(R.drawable.knight);
                            tv5.setText("Warrior");
                            tv5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                        case R.id.t6:
                            img6.setBackgroundResource(R.drawable.knight);
                            tv6.setText("Warrior");
                            tv6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                        case R.id.t7:
                            img7.setBackgroundResource(R.drawable.knight);
                            tv7.setText("Warrior");
                            tv7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                        case R.id.t8:
                            img8.setBackgroundResource(R.drawable.knight);
                            tv8.setText("Warrior");
                            tv8.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                        case R.id.t9:
                            img9.setBackgroundResource(R.drawable.knight);
                            tv9.setText("Warrior");
                            tv9.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                            alertDialog.dismiss();
                            amountC -= warrior.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += warrior.getAtk();
                            totaldefplayers += warrior.getDef();
                            break;
                    }
                }
            });
        }
        if (amountC <= 0 || amountC < 150){
            Toast.makeText(StageFourth.this, "You don't have enough money", Toast.LENGTH_SHORT).show();
        }
        else{
            view.findViewById(R.id.btn_archer).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (v.getId()){
                        case R.id.t1:
                            img1.setBackgroundResource(R.drawable.archer);
                            tv1.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                        case R.id.t2:
                            img2.setBackgroundResource(R.drawable.archer);
                            tv2.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                        case R.id.t3:
                            img3.setBackgroundResource(R.drawable.archer);
                            tv3.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                        case R.id.t4:
                            img4.setBackgroundResource(R.drawable.archer);
                            tv4.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                        case R.id.t5:
                            img5.setBackgroundResource(R.drawable.archer);
                            tv5.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                        case R.id.t6:
                            img6.setBackgroundResource(R.drawable.archer);
                            tv6.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                        case R.id.t7:
                            img7.setBackgroundResource(R.drawable.archer);
                            tv7.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                        case R.id.t8:
                            img8.setBackgroundResource(R.drawable.archer);
                            tv8.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                        case R.id.t9:
                            img9.setBackgroundResource(R.drawable.archer);
                            tv9.setText("Archer");
                            alertDialog.dismiss();
                            amountC -= archer.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += archer.getAtk();
                            totaldefplayers += archer.getDef();
                            break;
                    }
                }
            });
        }
        if (amountC <= 0 || amountC < 200){
            Toast.makeText(StageFourth.this, "You don't have enough money", Toast.LENGTH_SHORT).show();
        }
        else{
            view.findViewById(R.id.btn_wizard).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (v.getId()) {
                        case R.id.t1:
                            img1.setBackgroundResource(R.drawable.wizard);
                            tv1.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                        case R.id.t2:
                            img2.setBackgroundResource(R.drawable.wizard);
                            tv2.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                        case R.id.t3:
                            img3.setBackgroundResource(R.drawable.wizard);
                            tv3.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                        case R.id.t4:
                            img4.setBackgroundResource(R.drawable.wizard);
                            tv4.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                        case R.id.t5:
                            img5.setBackgroundResource(R.drawable.wizard);
                            tv5.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                        case R.id.t6:
                            img6.setBackgroundResource(R.drawable.wizard);
                            tv6.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                        case R.id.t7:
                            img7.setBackgroundResource(R.drawable.wizard);
                            tv7.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                        case R.id.t8:
                            img8.setBackgroundResource(R.drawable.wizard);
                            tv8.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                        case R.id.t9:
                            img9.setBackgroundResource(R.drawable.wizard);
                            tv9.setText("Wizard");
                            alertDialog.dismiss();
                            amountC -= wizard.getCost();
                            coin.setText(""+amountC);
                            totalatkplayer += wizard.getAtk();
                            totaldefplayers += wizard.getDef();
                            break;
                    }
                }
            });
        }
        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }
}