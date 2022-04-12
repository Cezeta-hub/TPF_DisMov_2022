package com.czerweny.tpfinal_dismov.ui.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Article;
import com.czerweny.tpfinal_dismov.backend.models.File;

import java.util.List;
import java.util.function.Consumer;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private List<Article> articles;
    private Consumer<Article> articleDisplay;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView articleHeadline;
        public TextView articleDateTime;
        public TextView articleSection;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            articleHeadline = (TextView) itemView.findViewById(R.id.tv_cardArticle_headline);
            articleDateTime = (TextView) itemView.findViewById(R.id.tv_cardArticle_dateTime);
            articleSection = (TextView) itemView.findViewById(R.id.tv_cardArticle_section);
        }

        public void setClickListener(Runnable listener) {
            itemView.setOnClickListener(v -> listener.run());
        }
    }

    public NewsListAdapter(List<Article> articles, Consumer<Article> articleDisplay) {
        this.articles = articles;
        this.articleDisplay = articleDisplay;
    }

    @NonNull
    @Override
    public NewsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_article, parent, false);
        return new NewsListAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.ViewHolder holder, int position) {
        Article article = articles.get(position);

        holder.articleHeadline.setText(article.getHeadline());
        holder.articleDateTime.setText(article.getDateTime());
        holder.articleSection.setText(article.getSection());
        holder.setClickListener(() -> articleDisplay.accept(article));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
