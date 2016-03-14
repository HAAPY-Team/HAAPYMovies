package com.haapyindustries.haapymovies.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.models.User;
import com.haapyindustries.haapymovies.models.UserManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin User List Activity
 * lists Users in a ListView, and allows Admins to change ban/unlock them
 *
 * @author pjztam
 * @version M8
 */
public class AdminUserListActivity extends AppCompatActivity {

    private UserAdapter userAdapter;

    /**
     * Sets up Activity
     * sets up and binds adapter to ListView
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<User> users = UserManager.getUsers();
        this.userAdapter = new UserAdapter(AdminUserListActivity.this, R.layout.activity_admin_user_list_row, new ArrayList<User>(users));
        ListView listView = (ListView) findViewById(R.id.admin_user_listview);
        listView.setAdapter(this.userAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                goToProfileView(UserManager.getUsers().get(position).getUsername());
                view.setEnabled(true);
            }
        });
    }

    /**
     * Handles Clicks on a User in the ListView
     * Opens an Admin User Profile View activity
     * for the User that was clicked on
     *
     * @param item username of User that was clicked on
     */
    public void goToProfileView(String item) {
        Intent intent = new Intent(this, AdminUserProfileViewActivity.class);
        intent.putExtra("username", item);
        startActivity(intent);
    }

    /**
     * Resumes Activity
     * refreshes Adapter
     */
    @Override
    protected void onResume() {
        super.onResume();
        this.userAdapter.notifyDataSetChanged();
    }
}
