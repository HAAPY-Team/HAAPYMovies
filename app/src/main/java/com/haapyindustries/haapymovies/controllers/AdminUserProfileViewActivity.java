package com.haapyindustries.haapymovies.controllers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.haapyindustries.haapymovies.R;
import com.haapyindustries.haapymovies.enums.UserStatus;
import com.haapyindustries.haapymovies.models.User;
import com.haapyindustries.haapymovies.models.UserManager;

import org.w3c.dom.Text;

/**
 * Admin User Profile View Activity
 * Opens up User info page
 * allows Admin to ban/unban/unlock a User
 *
 * @author pjztam
 * @version M8
 */
public class AdminUserProfileViewActivity extends AppCompatActivity {

    private User user;
    private Button ban;
    private Button unlock;
    private TextView type;
    private TextView status;

    /**
     * Sets up Activity
     * Displays information about User
     * changes text/enables/disables buttons depending on User state
     *
     * @param savedInstanceState Bundle with info about Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_profile_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.user = UserManager.getUser(getIntent().getStringExtra("username"));

        TextView username = (TextView) findViewById(R.id.adminuserprofileview_name);
        this.type = (TextView) findViewById(R.id.adminuserprofileview_type);
        this.status = (TextView) findViewById(R.id.adminuserprofileview_status);
        this.ban = (Button) findViewById(R.id.adminuserprofileview_ban);
        this.unlock = (Button) findViewById(R.id.adminuserprofileview_unlock);

        username.setText(user.getUsername());
        type.setText(user.getUserType().toString());
        status.setText(user.getUserStatus().toString());
        ban.setText(user.getUserStatus() != UserStatus.BANNED ? "Ban" : "Unban");
        unlock.setEnabled(user.getUserStatus() == UserStatus.LOCKED);
    }

    /**
     * Handles Ban Button clicks
     * bans/unbans a User and resets text on button and info page
     *
     * @param w View that was clicked
     */
    public void onBanButtonClick(View w) {
        if(user.getUserStatus() != UserStatus.BANNED) {
            user.setStatus(UserStatus.BANNED);
            ban.setText("Unban");
            status.setText("Banned");
            if(user.getUserStatus() == UserStatus.LOCKED) {
                unlock.setEnabled(false);
            }
        } else {
            user.setStatus(UserStatus.ACTIVE);
            ban.setText("Ban");
            status.setText("Active");
        }
    }

    /**
     * Handles Unlock Button clicks
     * unlocks a User and resets text on button and info page
     *
     * @param w View that was clicked
     */
    public void onUnlockButtonClick(View w) {
        user.setStatus(UserStatus.ACTIVE);
        status.setText("Active");
        unlock.setEnabled(false);
    }

}