package com.example.x5.a20180208_alvinjean_nyschools.views.highschoollist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.x5.a20180208_alvinjean_nyschools.R;
import com.example.x5.a20180208_alvinjean_nyschools.models.HighSchool;

import java.util.ArrayList;
import java.util.List;

public class HighSchoolListAdapter extends RecyclerView.Adapter<HighSchoolListAdapter.ViewHolder> {

    private static final String TAG = HighSchoolListAdapter.class.getSimpleName() + "_TAG";
    List<HighSchool> highSchoolList = new ArrayList<>();
    HighSchoolListItemListener listener;

    public HighSchoolListAdapter(Context context) {
        this.listener = (HighSchoolListItemListener) context;
    }

    public HighSchoolListAdapter(List<HighSchool> filmList) {
        this.highSchoolList = filmList;
    }

    @Override
    public HighSchoolListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_school_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HighSchoolListAdapter.ViewHolder holder, int position) {
        HighSchool highSchool = highSchoolList.get(position);
        holder.schoolName.setText(highSchool.getSchoolName());
        holder.schoolDesc.setText(highSchool.getOverviewParagraph());
        holder.schoolLocation.setText(highSchool.getLocation());
        holder.schoolGrades.setText(String.format("Grades: %s", highSchool.getGrades2018()));
        holder.schoolPriority.setText(highSchool.getAdmissionspriority11());
    }

    @Override
    public int getItemCount() {
        return highSchoolList.size();
    }

    public void updateDataSet(List<HighSchool> highSchools) {
        this.highSchoolList = highSchools;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView schoolName;
        TextView schoolDesc;
        TextView schoolLocation;
        TextView schoolGrades;
        TextView schoolPriority;

        public ViewHolder(View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.tv_high_school_name);
            schoolDesc = itemView.findViewById(R.id.tv_high_school_description);
            schoolLocation = itemView.findViewById(R.id.tv_high_school_location);
            schoolGrades = itemView.findViewById(R.id.tv_high_school_grades);
            schoolPriority = itemView.findViewById(R.id.tv_high_school_priority);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClicked(highSchoolList.get(getLayoutPosition()));
        }
    }

    interface HighSchoolListItemListener {
        void onItemClicked(HighSchool highSchool);
    }
}
