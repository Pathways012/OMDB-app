package com.example.mobilefinalproject;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

class CustomAdapter implements ListAdapter {
    ArrayList<SubjectData> arrayList;
    Context context;
    public CustomAdapter(Context context, ArrayList<SubjectData> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) { return position; }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SubjectData subjectData=arrayList.get(position);              //made final for onClick method
        if(convertView==null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.list_row, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //do a search using the title of the item that was selected
                    //Toast.makeText(context, "Listview item clicked", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, SelectedResultActivity.class);
                    String title = subjectData.SubjectName;   //made this work by making subjectData final above
                    intent.putExtra("title", title);
                    String posterUrl = subjectData.Image;
                    intent.putExtra("posterUrl", posterUrl);
                    context.startActivity(intent);
                }
            });
            TextView tittle=convertView.findViewById(R.id.title);
            ImageView imag=convertView.findViewById(R.id.list_image);
            tittle.setText(subjectData.SubjectName);
            Glide.with(context)
                    .load(subjectData.Image)
                    .into(imag);
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}