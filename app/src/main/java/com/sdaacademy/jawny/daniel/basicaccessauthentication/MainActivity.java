package com.sdaacademy.jawny.daniel.basicaccessauthentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login)
    EditText mLogin;

    @BindView(R.id.password)
    EditText mPassword;

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
        String key = getKey(login, password);


    }

    private String getKey(String login, String password) {
        String credentials = login + ":" + password;
        byte[] credentialsBytes = credentials.getBytes();
        String encodedCredentials = Base64.encodeToString(credentialsBytes, Base64.NO_WRAP);
        return encodedCredentials;
    }
}
