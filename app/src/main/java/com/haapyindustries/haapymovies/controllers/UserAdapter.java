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

import java.util.List;

/**
 * User Adaptor
 * Converts a User to a View that can be used by ListView
 *
 * @author pjztam
 * @version M8
 */
public class UserAdapter extends ArrayAdapter<User> {

    /**
     * Context to access database
     */
    private final Context context;
    /**
     * List of users returned from database
     */
    private final List<User> data;
    /**
     * Id of layout resource
     */
    private final int layoutResourceId;

    /**
     * Constructs the UserAdapter
     *
     * @param contextParam The current context
     * @param layoutResourceIdParam The resource ID for a layout file containing a layout to use when instantiating views.
     * @param dataParam The Data to be displayed
     */
    public UserAdapter(Context contextParam, int layoutResourceIdParam, List<User> dataParam) {
        super(contextParam, layoutResourceIdParam, dataParam);
        this.context = contextParam;
        this.data = dataParam;
        this.layoutResourceId = layoutResourceIdParam;
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

        if(row == null) {
            final LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.textView1 = (TextView)row.findViewById(R.id.userlist_text1);
            holder.textView2 = (TextView)row.findViewById(R.id.userlist_text2);
            holder.textView3 = (TextView)row.findViewById(R.id.userlist_text3);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        final User user = data.get(position);

        holder.textView1.setText(user.getUsername().toString());
        holder.textView2.setText(user.getUserType().toString());
        holder.textView3.setText(user.getUserStatus().toString());

        return row;
    }

    /**
     * Holds Data for each View
     */
    static class ViewHolder {
        /**
         * Textview displaying user information
         */
        private TextView textView1;
        /**
         * Textview displaying user information
         */
        private TextView textView2;
        /**
         * Textview displaying user information
         */
        private TextView textView3;
    }
}

