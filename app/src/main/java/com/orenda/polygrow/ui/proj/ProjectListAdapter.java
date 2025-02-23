package com.orenda.polygrow.ui.proj;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.orenda.polygrow.R;

import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHolder> {
    private List<ProjectItem> projectList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        public TextView date;
        public TextView time;
        public TextView location;
        public TextView status;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.projectTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            itemView.findViewById(R.id.viewDetails).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("ProjectListAdapter", "View Details Button Clicked");
                    Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_projectDetailsViewFragment);
                }
            });
        }
    }

    public ProjectListAdapter(List<ProjectItem> projectList) {
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_project_card, parent, false);
        ViewHolder vh = new ViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectItem currentItem = projectList.get(position);
        holder.title.setText(currentItem.getTitle());
//        holder.description.setText(currentItem.getDescription());
//        holder.date.setText(currentItem.getDate());
//        holder.time.setText(currentItem.getTime());
//        holder.location.setText(currentItem.getLocation());
//        holder.status.setText(currentItem.getStatus());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }
}
