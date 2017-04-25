package com.monentreprise.exercice.activites;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monentreprise.exercice.R;

import java.util.List;

/**
 * Created by Jas on 19/04/2017.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseViewHolder> {
    private List<Course> listCourse=null;

    public CourseAdapter(List<Course> listCourse){
        this.listCourse=listCourse;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCourse = LayoutInflater.from(parent.getContext()).inflate(R.layout.liste_course,parent,false);
        return new CourseViewHolder(viewCourse);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        holder.getTextViewLibelleCourse().setText(listCourse.get(position).getIntitule());
    }

    @Override
    public int getItemCount() {
        return listCourse.size();
    }
}
