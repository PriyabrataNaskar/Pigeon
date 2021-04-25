package tech.hashincludebrain.pigeon.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import tech.hashincludebrain.pigeon.R;

public class SplashActivity extends AppCompatActivity {

    ImageView splashImage;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashImage = findViewById(R.id.pigeon_logo);

        //Loading gif using Glide
        Glide.with(this).load(R.raw.pigeon_splash_image).into(splashImage);

        handler=new Handler(Looper.getMainLooper());

        //managing background thread to start @MainActivity after 3 second
        handler.postDelayed(() -> {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);

            //clearing the backStack so that back button doesn't come back to splash screen
            finish();
        },3000);
    }
}