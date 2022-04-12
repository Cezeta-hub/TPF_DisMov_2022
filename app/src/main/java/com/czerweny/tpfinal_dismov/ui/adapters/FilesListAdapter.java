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
import com.czerweny.tpfinal_dismov.backend.models.Comment;
import com.czerweny.tpfinal_dismov.backend.models.File;

import java.util.List;
import java.util.function.Consumer;

public class FilesListAdapter extends RecyclerView.Adapter<FilesListAdapter.ViewHolder> {

    private List<File> files;
    private Consumer<String> accessFileLink;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fileTitle;
        public TextView fileAuthor;
        public TextView fileDescription;
        public TextView fileLink;
        public ImageButton fileGoToLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fileTitle = (TextView) itemView.findViewById(R.id.tv_cardFile_title);
            fileAuthor = (TextView) itemView.findViewById(R.id.tv_cardFile_author);
            fileDescription = (TextView) itemView.findViewById(R.id.tv_cardFile_description);
            fileLink = (TextView) itemView.findViewById(R.id.tv_cardFile_link);
            fileGoToLink = (ImageButton) itemView.findViewById(R.id.bt_cardFile_goToLink);
        }

        public void setClickListener(Runnable listener) {
            fileGoToLink.setOnClickListener(v -> listener.run());
        }
    }

    public FilesListAdapter(List<File> files, Consumer<String> accessFileLink) {
        this.files = files;
        this.accessFileLink = accessFileLink;
    }

    @NonNull
    @Override
    public FilesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_file, parent, false);
        return new FilesListAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull FilesListAdapter.ViewHolder holder, int position) {
        File file = files.get(position);

        holder.fileTitle.setText(file.getTitle());
        holder.fileAuthor.setText(file.getAuthor());
        holder.fileDescription.setText(file.getDescription());
        holder.fileLink.setText("Link: " + file.getLink());
        holder.setClickListener(() -> accessFileLink.accept(file.getLink()));
    }

    @Override
    public int getItemCount() { return files.size(); }
}
