package com.czerweny.tpfinal_dismov.ui.fragments.newsTab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Article;
import com.czerweny.tpfinal_dismov.backend.models.NoticiasUNLResponse;
import com.czerweny.tpfinal_dismov.backend.retrofit.NoticiasUNLWebService;
import com.czerweny.tpfinal_dismov.backend.retrofit.RetrofitClientUNL;
import com.czerweny.tpfinal_dismov.ui.adapters.NewsListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout noNewsLayout;
    private NoticiasUNLWebService service;

    public NewsFragment() {
        Retrofit retrofit = RetrofitClientUNL.getInstance();
        service = retrofit.create(NoticiasUNLWebService.class);
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_news_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noNewsLayout = (LinearLayout) view.findViewById(R.id.ll_news_noNews);

        this.updateView();
        return view;
    }

    private void updateView() {
        Call<List<NoticiasUNLResponse>> noticiasUNLResponseCall = service.getNews();

        noticiasUNLResponseCall.enqueue(new Callback<List<NoticiasUNLResponse>>() {
            @Override
            public void onResponse(Call<List<NoticiasUNLResponse>> call, Response<List<NoticiasUNLResponse>> response) {
                if (response.isSuccessful()) {
                    List<NoticiasUNLResponse> noticiasUNLResponseList = response.body();

                    List<Article> articles = new ArrayList<>();

                    if (noticiasUNLResponseList != null) {
                        for (NoticiasUNLResponse noticiasUNLResponse : noticiasUNLResponseList) {
                            String id = noticiasUNLResponse.getNews().get(0).getId();
                            String titulo = noticiasUNLResponse.getNews().get(0).getTitle();
                            String fechaHora = noticiasUNLResponse.getNews().get(0).getModified().substring(0,noticiasUNLResponse.getNews().get(0).getModified().length()-3);
                            String volanta = noticiasUNLResponse.getNews().get(0).getVolanta();
                            String copete = noticiasUNLResponse.getNews().get(0).getCopete();

                            Article article = new Article(id,titulo,fechaHora,volanta,copete);

                            articles.add(article);
                        }
                    }

                    NewsListAdapter newsListAdapter = new NewsListAdapter(articles, articleDisplay);
                    recyclerView.setAdapter(newsListAdapter);

                    boolean empty = newsListAdapter.getItemCount() == 0;
                    recyclerView.setVisibility(empty ? View.GONE : View.VISIBLE);
                    noNewsLayout.setVisibility(empty ? View.VISIBLE : View.GONE);
                } else {
                    Log.e("NOTICIASUNL", " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<NoticiasUNLResponse>> call, Throwable t) {
                Log.e("NOTICIASUNL", " onFailure: " + t.getMessage());
            }
        });
    }

    private Consumer<Article> articleDisplay = article -> {
        Bundle args = new Bundle();
        args.putString(ArticleDialogFragment.ARTICLE_ID, article.getId());
        args.putString(ArticleDialogFragment.ARTICLE_HEADLINE, article.getHeadline());
        args.putString(ArticleDialogFragment.ARTICLE_DATETIME, article.getDateTime());
        args.putString(ArticleDialogFragment.ARTICLE_SECTION, article.getSection());
        args.putString(ArticleDialogFragment.ARTICLE_DROPHEAD, article.getDrophead());

        DialogFragment dialog = new ArticleDialogFragment();
        dialog.setArguments(args);
        dialog.show(getParentFragmentManager(), "MostrarNoticiaDialogFragment");
    };
}