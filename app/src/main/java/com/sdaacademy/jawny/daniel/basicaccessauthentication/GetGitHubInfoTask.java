package com.sdaacademy.jawny.daniel.basicaccessauthentication;

import android.os.AsyncTask;
import android.util.Base64;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetGitHubInfoTask extends AsyncTask<String, Integer, String> {

    private MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private boolean success;

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        String key = getKey(params[0], params[1]);
        try {
            response = sentRequest(key);
            success = true;
        } catch (IOException e) {
            if (mainActivity != null) {
                mainActivity.showError("Blad połączenia");
                success = false;
            }
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mainActivity != null && success) {
            mainActivity.displayResponse(s);
        }
    }

    private String sentRequest(String key) throws IOException {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Basic " + key)
                .url("https://api.github.com/user")
                .build();
        OkHttpClient client = new OkHttpClient();
        return client.newCall(request).execute().body().string();
    }

    private String getKey(String login, String password) {
        String credentials = login + ":" + password;
        byte[] credentialsBytes = credentials.getBytes();
        String encodedCredentials = Base64.encodeToString(credentialsBytes, Base64.NO_WRAP);
        return encodedCredentials;
    }
}
