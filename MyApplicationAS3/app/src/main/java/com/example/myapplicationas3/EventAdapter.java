package com.example.myapplicationas3;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter {

    Context context;
    Cursor results;
    LayoutInflater inflater;

    class ViewHolder
    {
        TextView eventName;
        TextView eventDate;
    }

    public EventAdapter(Context applicationContext, Cursor results)
    {
        this.context = applicationContext;
        this.results = results;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount()
    {
        return results.getCount();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder viewHolder;
        if(view == null)
        {
            view = inflater.inflate(R.layout.events, viewGroup, false);
        }

        results.moveToPosition(i);

        ((TextView)view.findViewById(R.id.eventNameText)).setText(results.getString(0));
        ((TextView)view.findViewById(R.id.eventDateText)).setText(results.getString(0));

        return view;
    }
}
