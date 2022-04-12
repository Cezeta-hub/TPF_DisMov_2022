package com.czerweny.tpfinal_dismov.ui.fragments.newsTab;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Article;

import java.util.Calendar;

public class ArticleDialogFragment extends DialogFragment {

    public static final String ARTICLE_ID = "ARTICLE_ID";
    public static final String ARTICLE_HEADLINE = "ARTICLE_HEADLINE";
    public static final String ARTICLE_DATETIME = "ARTICLE_DATETIME";
    public static final String ARTICLE_SECTION = "ARTICLE_SECTION";
    public static final String ARTICLE_DROPHEAD = "ARTICLE_DROPHEAD";

    private Article article;

    public ArticleDialogFragment () { }

    public static ArticleDialogFragment  newInstance() {
        ArticleDialogFragment  fragment = new ArticleDialogFragment ();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            article = new Article(getArguments().getString(ARTICLE_ID), getArguments().getString(ARTICLE_HEADLINE), getArguments().getString(ARTICLE_DATETIME), getArguments().getString(ARTICLE_SECTION), getArguments().getString(ARTICLE_DROPHEAD));
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_article_dialog, null);

        TextView tv_articleSection = (TextView) view.findViewById(R.id.tv_dialogArticle_section);
        TextView tv_articleHeadline = (TextView) view.findViewById(R.id.tv_dialogArticle_headline);
        TextView tv_articleDateTime = (TextView) view.findViewById(R.id.tv_dialogArticle_dateTime);
        TextView tv_articleDrophead = (TextView) view.findViewById(R.id.tv_dialogArticle_drophead);

        tv_articleSection.setText(article.getSection());
        tv_articleHeadline.setText(article.getHeadline());
        tv_articleDateTime.setText(article.getDateTime());
        tv_articleDrophead.setText(article.getDrophead());

        builder.setView(view);

        builder.setNegativeButton("Volver", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        return builder.create();
    }
}
