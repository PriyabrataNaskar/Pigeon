package tech.hashincludebrain.pigeon.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.hashincludebrain.pigeon.Constants;
import tech.hashincludebrain.pigeon.R;
import tech.hashincludebrain.pigeon.adapters.NewsAdapter;
import tech.hashincludebrain.pigeon.model.Article;
import tech.hashincludebrain.pigeon.model.ResponseModel;
import tech.hashincludebrain.pigeon.rests.APIClient;
import tech.hashincludebrain.pigeon.rests.JSONPlaceHolderAPI;

public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private List<Article> mNewsData;
    private NewsAdapter mAdapter;

    private ChipGroup chipGroup;
    private Chip suggestedChip;
    private Chip businessChip;
    private Chip technologyChip;
    private Chip generalChip;
    private Chip economyChip;
    private Chip healthChip;
    private Chip scienceChip;
    private Chip sportsChip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recycler_view);

        suggestedChip = findViewById(R.id.suggested_chip);
        businessChip = findViewById(R.id.business_chip);
        technologyChip = findViewById(R.id.technology_chip);
        generalChip = findViewById(R.id.general_chip);
        economyChip = findViewById(R.id.entertainment_chip);
        healthChip = findViewById(R.id.health_chip);
        scienceChip = findViewById(R.id.science_chip);
        sportsChip = findViewById(R.id.sports_chip);

        chipGroup = findViewById(R.id.chip_group);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mNewsData = new ArrayList<>();

        final JSONPlaceHolderAPI jsonPlaceHolderAPI = APIClient.getRetrofit().create(JSONPlaceHolderAPI.class);

//        Call<ResponseModel> call = jsonPlaceHolderAPI.getNews(Constants.countryIndia,Constants.newsCategory,Constants.APIKey);
//
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                assert response.body() != null;
//                if (!response.body().getStatus().equals("ok")){
//                    Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_LONG).show();
//                }
//                List<Article> articleList = response.body().getArticles();
//                if (articleList.size()>0){
//                    mNewsData = articleList;
//                    mAdapter = new NewsAdapter(mNewsData,MainActivity.this);
//                    mRecyclerView.setAdapter(mAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
//            }
//        });
//        
        suggestedChip.setChecked(true);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                String category ="general";
                if (checkedId == R.id.suggested_chip){
                    category = "general";
                }else if (checkedId == R.id.business_chip){
                    category ="business";
                }else if (checkedId == R.id.technology_chip){
                    category = "technology";
                }else if (checkedId == R.id.general_chip){
                    category ="general";
                }else if (checkedId == R.id.entertainment_chip){
                    category = "entertainment";
                } else if (checkedId == R.id.health_chip){
                    category = "health";
                }else if (checkedId == R.id.science_chip){
                    category = "science";
                }else if (checkedId == R.id.sports_chip) {
                    category = "sports";
                }

                Call<ResponseModel> call = jsonPlaceHolderAPI.getNews(Constants.countryIndia,category,Constants.APIKey);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        assert response.body() != null;
                        if (!response.body().getStatus().equals("ok")){
                            Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_LONG).show();
                        }
                        List<Article> articleList = response.body().getArticles();
                        if (articleList.size()>0){
                            mNewsData = articleList;
                            mAdapter = new NewsAdapter(mNewsData,MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {

                    }
                });
            }
        });
    }
}