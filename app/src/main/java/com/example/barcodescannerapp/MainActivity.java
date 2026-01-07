package com.example.barcodescannerapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button getStartedButton = findViewById(R.id.button);

        //nak buat bila user touch button , dia scale up
        getStartedButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        animateButtonScale(v, 1.0f, 1.1f);
                        return true;

                    case MotionEvent.ACTION_UP:
                        animateButtonScale(v, 1.1f, 1.0f);
                        v.performClick();
                        return true;
                }
                return false;
            }
        });
    }
    public void openHomepage(View view) {
        Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
        startActivity(intent);
    }

    private void animateButtonScale(View view, float startScale, float endScale) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", startScale, endScale);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", startScale, endScale);

        scaleX.setDuration(150);
        scaleY.setDuration(150);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleX).with(scaleY);
        animatorSet.start();
    }
}
