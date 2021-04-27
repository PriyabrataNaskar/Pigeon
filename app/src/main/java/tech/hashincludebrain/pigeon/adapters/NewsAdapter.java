package tech.hashincludebrain.pigeon.adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import tech.hashincludebrain.pigeon.R;
import tech.hashincludebrain.pigeon.model.Article;
import tech.hashincludebrain.pigeon.view.NewsDetailsActivity;

/**
 * Created by Priyabrata Naskar on 08-04-2021.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    // Member variables.
    private List<Article> mNewsData;
    private Context mContext;

    public NewsAdapter(List<Article> mNewsData, Context mContext) {
        this.mNewsData = mNewsData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.news_card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        // Get current news
        Article currentNews = mNewsData.get(position);

        // Populate the textViews with data.
        holder.bindTo(currentNews);
    }

    @Override
    public int getItemCount() {
        return mNewsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Member Variables for the TextViews
        private TextView mNewsTitleText;
        private ImageView mNewsImage;
        private TextView mAuthorTitle;
        private TextView mDescription;
        CheckBox shareButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views.
            mNewsTitleText = itemView.findViewById(R.id.news_title);
            mNewsImage = itemView.findViewById(R.id.news_image);
            mAuthorTitle = itemView.findViewById(R.id.author_name_text);
            mDescription = itemView.findViewById(R.id.description_text);
            shareButton = itemView.findViewById(R.id.share_button);
            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
            shareButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == itemView.getId()) {
                Article news = mNewsData.get(getAdapterPosition());

                //@SerializedName("author")
                String newsAuthorName = news.getNewsAuthorName();
                //@SerializedName("title")
                String newsTitle = news.getNewsTitle();
                //@SerializedName("description")
                String newsDescription = news.getNewsDescription();
                //@SerializedName("urlToImage")
                String newsImageResource = news.getNewsImageResource();
                //@SerializedName("publishedAt")
                String newsPublishTime = news.getNewsPublishTime();
                //@SerializedName("content")
                String content = news.getContent();

                Intent intent = new Intent(view.getContext(), NewsDetailsActivity.class);

                intent.putExtra("author", newsAuthorName);
                intent.putExtra("title", newsTitle);
                intent.putExtra("description", newsDescription);
                intent.putExtra("urlToImage", newsImageResource);
                intent.putExtra("publishedAt", newsPublishTime);
                intent.putExtra("content", content);

                view.getContext().startActivity(intent);
            }else if (view.getId() == R.id.share_button){
                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                //Intent shareIntent = new Intent();
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, mNewsData.get(getAdapterPosition()).getNewsTitle());
                //shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                view.getContext().startActivity(Intent.createChooser(shareIntent, "Share News With"));
            }
        }

        public void bindTo(Article currentNews) {
            mNewsTitleText.setText(currentNews.getNewsTitle());
            mAuthorTitle.setText(currentNews.getNewsAuthorName());
            mDescription.setText(Html.fromHtml(currentNews.getNewsDescription(),Html.FROM_HTML_MODE_LEGACY).toString());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentNews.getNewsImageResource()).into(mNewsImage);
        }
    }
}
