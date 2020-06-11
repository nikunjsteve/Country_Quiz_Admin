package com.example.countryquizadmin;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class GridAdapter extends BaseAdapter {

    public List<String> sets;
    private String category;
    private GridListner listner;

    public GridAdapter(List<String> sets, String category, GridListner listner) {
        this.sets = sets;
        this.category = category;
        this.listner = listner;
    }

    @Override
    public int getCount() {
        return sets.size()+1;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View view1;

        if (view == null) {
            view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.set_item, viewGroup, false);
        } else {
            view1 = view;
        }
        if (i == 0) {
            ((TextView) view1.findViewById(R.id.textView)).setText("+");
        } else {
            ((TextView) view1.findViewById(R.id.textView)).setText(String.valueOf(i));
        }
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (i == 0) {
                    listner.addSet();
                } else {
                    Intent questionIntent = new Intent(view.getContext(), QuestionsActivity.class);
                    questionIntent.putExtra("category", category);
                    questionIntent.putExtra("setId", sets.get(i-1));
                    view.getContext().startActivity(questionIntent);
                }
            }
        });

        view1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (i != 0)
                    listner.onLongClick(sets.get(i-1),i);
                return false;
            }
        });

        return view1;
    }

    public interface GridListner {
        public void addSet();

        void onLongClick(String setId, int position);
    }
}
