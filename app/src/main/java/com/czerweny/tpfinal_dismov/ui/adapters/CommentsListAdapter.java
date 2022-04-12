package com.czerweny.tpfinal_dismov.ui.adapters;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.czerweny.tpfinal_dismov.R;
import com.czerweny.tpfinal_dismov.backend.models.Comment;
import com.czerweny.tpfinal_dismov.backend.models.Course;

import java.util.List;
import java.util.function.Consumer;

public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.ViewHolder> {

    private List<Comment> comments;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView commentAuthor;
        public TextView commentBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentAuthor = (TextView) itemView.findViewById(R.id.tv_cardComment_author);
            commentBody = (TextView) itemView.findViewById(R.id.tv_cardComment_body);
        }

        public void setClickListener(Runnable listener) {
            itemView.setOnClickListener(v -> listener.run());
        }
    }

    public CommentsListAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comment, parent, false);
        return new CommentsListAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull CommentsListAdapter.ViewHolder holder, int position) {
        Comment comment = comments.get(position);

        holder.commentAuthor.setText(comment.getAuthor());
        holder.commentBody.setText(comment.getBody());
    }

    @Override
    public int getItemCount() { return comments.size(); }
}
