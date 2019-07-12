package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AnimationExamplesActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnScale,btnFadein,btnFadeOut,btnZoomIn,btnZoomOut,btnRotate,btnBlink,btnMove,btnSlideUp,btnSlideDown,btnBounce;
    private  Animation scaleAnimation,fadeInAnimation,fadeOutAnimation,blinkAnimation,zoomInAnimation,zoomOutAnimation,
            rotateAnimation,moveAnimation,bounceAnimation,slideUpAnimation,slideDownAnimation;
    private TextView tvAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation_examples);

        btnScale = findViewById(R.id.btnScale);
        btnFadein = findViewById(R.id.btnFadein);
        btnFadeOut = findViewById(R.id.btnFadeOut);
        btnBlink = findViewById(R.id.btnBlink);
        btnZoomIn = findViewById(R.id.btnZoomIn);
        btnZoomOut = findViewById(R.id.btnZoomOut);
        btnRotate = findViewById(R.id.btnRotate);
        btnMove = findViewById(R.id.btnMove);
        btnSlideUp = findViewById(R.id.btnSlideUp);
        btnSlideDown = findViewById(R.id.btnSlideDown);
        btnBounce = findViewById(R.id.btnBounce);

        tvAnim = findViewById(R.id.tvAnim);
        setUpAnimation();

        btnScale.setOnClickListener(this);
        btnFadein.setOnClickListener(this);
        btnFadeOut.setOnClickListener(this);
        btnBlink.setOnClickListener(this);
        btnZoomIn.setOnClickListener(this);
        btnZoomOut.setOnClickListener(this);
        btnRotate.setOnClickListener(this);
        btnMove.setOnClickListener(this);
        btnSlideUp.setOnClickListener(this);
        btnSlideDown.setOnClickListener(this);
        btnBounce.setOnClickListener(this);



    }

    private void setUpAnimation() {
       scaleAnimation = AnimationUtils.loadAnimation(this,R.anim.ex_scale_anim);
        fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.ex_fade_in);
        fadeOutAnimation = AnimationUtils.loadAnimation(this,R.anim.ex_fade_out);
        blinkAnimation =  AnimationUtils.loadAnimation(this,R.anim.ex_blink_anim);
        zoomInAnimation =  AnimationUtils.loadAnimation(this,R.anim.ex_zoom_in_anim);
        zoomOutAnimation =  AnimationUtils.loadAnimation(this,R.anim.ex_zoom_out_anim);
        rotateAnimation =  AnimationUtils.loadAnimation(this,R.anim.ex_rotate_anim);
        moveAnimation =  AnimationUtils.loadAnimation(this,R.anim.ex_move_anim);
        slideUpAnimation =  AnimationUtils.loadAnimation(this,R.anim.ex_slide_up_anim);
        slideDownAnimation =  AnimationUtils.loadAnimation(this,R.anim.ex_slide_down_anim);
        bounceAnimation =  AnimationUtils.loadAnimation(this,R.anim.ex_bounce_anim);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnScale:

                tvAnim.startAnimation(scaleAnimation);
//                scaleAnimation.start();
                break;

            case R.id.btnFadein:
                tvAnim.startAnimation(fadeInAnimation);
                break;

            case R.id.btnFadeOut:
                tvAnim.startAnimation(fadeOutAnimation);
                break;

            case R.id.btnBlink:
                tvAnim.startAnimation(blinkAnimation);
                break;

            case R.id.btnZoomIn:
                tvAnim.startAnimation(zoomInAnimation);
                break;

            case R.id.btnZoomOut:
                tvAnim.startAnimation(zoomOutAnimation);
                break;

            case R.id.btnRotate:
                tvAnim.startAnimation(rotateAnimation);
                break;

            case R.id.btnMove:
                tvAnim.startAnimation(moveAnimation);
                break;
                case R.id.btnSlideUp:
            tvAnim.startAnimation(slideUpAnimation);
            break;
            case R.id.btnSlideDown:
            tvAnim.startAnimation(slideDownAnimation);
            break;

            case R.id.btnBounce:
                tvAnim.startAnimation(bounceAnimation);
                break;


            case R.id.bounce:
                tvAnim.startAnimation(bounceAnimation);
                break;
        }
    }
}
