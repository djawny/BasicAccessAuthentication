package com.sdaacademy.jawny.daniel.basicaccessauthentication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login)
    EditText mLogin;

    @BindView(R.id.password)
    EditText mPassword;

    @BindView(R.id.user_id)
    TextView mUserId;

    @BindView(R.id.avatar)
    ImageView mAvatar;

    private GetGitHubInfoTask getGitHubInfoTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.logon)
    public void logon() {
        String login = mLogin.getText().toString();
        String password = mPassword.getText().toString();
        getGitHubInfoTask = new GetGitHubInfoTask();
        getGitHubInfoTask.setMainActivity(MainActivity.this);
        getGitHubInfoTask.execute(login, password);
    }

    public void displayResponse(String text) {
        try {
            JSONObject jsonObject = new JSONObject(text);
            mUserId.setText(jsonObject.optString("id"));
            Picasso.with(this).load(jsonObject.optString("avatar_url")).into(mAvatar);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showError(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("Ok", null)
                .show();
    }
}
