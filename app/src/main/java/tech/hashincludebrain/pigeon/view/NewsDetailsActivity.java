package tech.hashincludebrain.pigeon.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import tech.hashincludebrain.pigeon.R;

public class NewsDetailsActivity extends AppCompatActivity {
    CheckBox backButton;
    ImageView newsImage;
    TextView titleTextView;
    TextView contentDescriptionView;
    TextView newsMetaDataTextView;
    TextView newsArticleTextView;
    CheckBox shareButton;

    Intent intent;

    //@SerializedName("author")
    private String newsAuthorName;
    //@SerializedName("title")
    private String newsTitle;
    //@SerializedName("description")
    private String newsDescription;
    //@SerializedName("urlToImage")
    private String newsImageResource;
    //@SerializedName("publishedAt")
    private String newsPublishTime;
    //@SerializedName("content")
    private String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        backButton = findViewById(R.id.back_button);
        newsImage = findViewById(R.id.news_image_detail);
        titleTextView = findViewById(R.id.title_text);
        contentDescriptionView = findViewById(R.id.content_description);
        newsMetaDataTextView = findViewById(R.id.news_meta_data);
        newsArticleTextView = findViewById(R.id.news_article_text);
        shareButton = findViewById(R.id.share_button);

        intent = getIntent();

        newsAuthorName = intent.getStringExtra("author");
        newsTitle = intent.getStringExtra("title");
        newsDescription = intent.getStringExtra("description");
        newsImageResource = intent.getStringExtra("urlToImage");
        newsPublishTime = intent.getStringExtra("publishedAt");
        content = intent.getStringExtra("content");

        //Loading image
        Glide.with(NewsDetailsActivity.this).load(newsImageResource).centerCrop().into(newsImage);

        titleTextView.setText(newsTitle);
        contentDescriptionView.setText(newsDescription);
        newsMetaDataTextView.setText("by " + newsAuthorName + " " + newsPublishTime);
        //newsArticleTextView.setText(content);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * Get Bitmap Image
                 */

                Glide.with(getApplicationContext()).asBitmap().load(newsImageResource).into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {

                    }
                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                    }
                });
                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                //Intent shareIntent = new Intent();
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, newsTitle);
                //shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Share News With"));
            }
        });

    }

    /**
     * Saves the image as PNG to the app's private external storage folder.
     * @param image Bitmap to save.
     * @return Uri of the saved file or null
     */
    private Uri saveImageExternal(Bitmap image) {
        //TODO - Should be processed in another thread
        Uri uri = null;
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "to-share.png");
            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, stream);
            stream.close();
            uri = Uri.fromFile(file);
        } catch (IOException e) {
        }
        return uri;
    }
}