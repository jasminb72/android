package com.monentreprise.exercice.activites;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.monentreprise.exercice.R;

/**
 * Created by Jas on 19/04/2017.
 */

public class CourseViewHolder extends RecyclerView.ViewHolder{

    private TextView textViewLibelleCourse=null;

    public CourseViewHolder(View itemView) {
        super(itemView);
        textViewLibelleCourse=(TextView) itemView.findViewById(R.id.libellecourse);
    }

    public TextView getTextViewLibelleCourse(){
        return textViewLibelleCourse;
    }
}
