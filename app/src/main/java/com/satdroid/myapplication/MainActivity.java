package com.satdroid.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView coinImage,diceImage;
    AppCompatButton coinBtn,diceBtn;
    TextView Coin_text,Dice_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coinBtn=findViewById(R.id.coin_btn);
        diceImage=findViewById(R.id.dice_img);
        diceBtn=findViewById(R.id.dice_btn);
        coinImage=findViewById(R.id.coin_head);
        Coin_text=findViewById(R.id.coin_text);
        Dice_text=findViewById(R.id.dice_text);

        coinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coinBtn.setEnabled(false);
                diceBtn.setEnabled(false);
                MediaPlayer coinSound=MediaPlayer.create(MainActivity.this,R.raw.coin_flip);
               coinSound.start(); //coin sound
                coinImage.animate().rotationXBy(3600f).setDuration(1600); //flip animation

              Animation flip= AnimationUtils.loadAnimation(MainActivity.this,R.anim.flip_coin);
                coinImage.startAnimation(flip); //up and down animation

                Handler handler=new Handler(); //generate head tell after few seconds of animation
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        HeadorTail(); //head or tell code || user defined method
                        diceBtn.setEnabled(true);
                        coinBtn.setEnabled(true);
                    }
                },1200);
            }
        }); //delay time

        diceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                diceBtn.setEnabled(false);
                coinBtn.setEnabled(false);
                MediaPlayer diceMp=MediaPlayer.create(MainActivity.this,R.raw.dice_roll);
                diceMp.start(); //dice sound
                diceImage.animate().rotationYBy(3600).setDuration(1500);
                Animation roll=AnimationUtils.loadAnimation(MainActivity.this,R.anim.dice_roll);
                diceImage.startAnimation(roll);
                diceImage.animate().rotationXBy(3600).setDuration(1500);

                Handler dhandler=new Handler();
                dhandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DiceNumber();
                        coinBtn.setEnabled(true);
                        diceBtn.setEnabled(true);

                    }
                },1000);
            }
        });

    }
    void HeadorTail()
    {
        Random rand=new Random();
        int decs=rand.nextInt(2);
        if(decs==1) {
            coinImage.setImageResource(R.mipmap.coin_head_foreground);
            Coin_text.setText("Head");
        }
        else {
            coinImage.setImageResource(R.mipmap.coin_tails_foreground);
            Coin_text.setText("Tails");
        }
    }

    void DiceNumber()
    {
        Random Drand=new Random();
        int dicen=Drand.nextInt(6);
        if(dicen==0)
        {
            diceImage.setImageResource(R.drawable.diceone);
            Dice_text.setText("1");
        }
        else if(dicen==1)
        {
            diceImage.setImageResource(R.drawable.dicetwo);
            Dice_text.setText("2");
        }
        else if(dicen==2)
        {
            diceImage.setImageResource(R.drawable.dicethree);
            Dice_text.setText("3");
        }
        else if(dicen==3)
        {
            diceImage.setImageResource(R.drawable.dicefour);
            Dice_text.setText("4");
        }
        else if(dicen==4)
        {
            diceImage.setImageResource(R.drawable.dicefive);
            Dice_text.setText("5");
        }
        else
        {
            diceImage.setImageResource(R.drawable.dicesix);
            Dice_text.setText("6");
        }
    }
}