package com.haapyindustries.haapymovies.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.User;

import java.util.ArrayList;

/**
 * User Adaptor
 * Converts a User to a View that can be used by ListView
 *
 * @author pjztam
 * @version M8
 */
public class UserAdapter extends ArrayAdapter<User> {

    private final Context context;
    private final ArrayList<User> data;
    private final int layoutResourceId;

    /**
     * Constructs the UserAdapter
     *
     * @param context The current context
     * @param layoutResourceId The resource ID for a layout file containing a layout to use when instantiating views.
     * @param data The Data to be displayed
     */
    public UserAdapter(Context context, int layoutResourceId, ArrayList<User> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    /**
     *  Makes a View for the given data item
     *
     * @param position The position of the item within the adapter's data set of the item whose view we want.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.textView1 = (TextView)row.findViewById(R.id.userlist_text1);
            holder.textView2 = (TextView)row.findViewById(R.id.userlist_text2);
            holder.textView3 = (TextView)row.findViewById(R.id.userlist_text3);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        User user = data.get(position);

        holder.textView1.setText(user.getUsername().toString());
        holder.textView2.setText(user.getUserType().toString());
        holder.textView3.setText(user.getUserStatus().toString());

        return row;
    }

    /**
     * Holds Data for each View
     */
    static class ViewHolder
    {
        TextView textView1;
        TextView textView2;
        TextView textView3;
    }
}

