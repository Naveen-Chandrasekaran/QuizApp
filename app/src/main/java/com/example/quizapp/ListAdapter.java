package com.example.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
   String[]questions;
   Context context;
   LayoutInflater inflater;

    public ListAdapter(String[] questions, Context context) {
        this.questions = questions;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return questions.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.list_design,null);
        TextView textView=(TextView) view.findViewById(R.id.list_design_question);

        textView.setText(questions[i]);

        return view;
    }
}
