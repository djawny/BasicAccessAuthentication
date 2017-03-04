package com.sdaacademy.jawny.daniel.basicaccessauthentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login)
    EditText mLogin;

    @BindView(R.id.password)
    EditText mPassword;

    private GetGithubInfoTask getGithubInfoTask;

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
                getGithubInfoTask = new GetGithubInfoTask();
        getGithubInfoTask.setMainActivity(MainActivity.this);
        getGithubInfoTask.execute(login,password);
    }

    public void displayResponse(String text){

    }

}
