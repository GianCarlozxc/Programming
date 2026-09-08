package com.dsa.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.dsa.master.R;
import com.google.android.material.button.MaterialButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateChecker {

    // When the app is built, this is the current commit SHA
    public static final String CURRENT_INSTALLED_SHA = "54b04ed7b3d5e8b47763b736e3621c1022bacba1";
    private static final String GITHUB_API_URL = "https://api.github.com/repos/GianCarlozxc/Programming/commits?per_page=1";
    private static final String PREF_NAME = "app_update_prefs";
    private static final String KEY_DISMISSED_SHA = "dismissed_sha";

    private final Context context;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public UpdateChecker(Context context) {
        this.context = context;
    }

    public void checkForUpdates(
            CardView cardUpdateBanner,
            TextView tvCommitMsg,
            TextView tvDetails,
            MaterialButton btnViewGithub,
            ImageView btnClose) {

        executor.execute(() -> {
            try {
                URL url = new URL(GITHUB_API_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "DSA-Master-App");
                conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
                conn.setConnectTimeout(8000);
                conn.setReadTimeout(8000);

                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();

                    JSONArray commitsArray = new JSONArray(sb.toString());
                    if (commitsArray.length() > 0) {
                        JSONObject latestCommit = commitsArray.getJSONObject(0);
                        String sha = latestCommit.getString("sha");
                        String htmlUrl = latestCommit.optString("html_url", "https://github.com/GianCarlozxc/Programming");

                        JSONObject commitObj = latestCommit.getJSONObject("commit");
                        String message = commitObj.getString("message");
                        String date = "";
                        if (commitObj.has("committer") && commitObj.getJSONObject("committer").has("date")) {
                            date = commitObj.getJSONObject("committer").getString("date");
                            if (date.length() >= 10) {
                                date = date.substring(0, 10);
                            }
                        }

                        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                        String dismissedSha = prefs.getString(KEY_DISMISSED_SHA, "");

                        // If remote SHA is different from the currently installed SHA and hasn't been dismissed:
                        if (!sha.equalsIgnoreCase(CURRENT_INSTALLED_SHA) && !sha.equalsIgnoreCase(dismissedSha)) {
                            final String displaySha = sha.length() >= 7 ? sha.substring(0, 7) : sha;
                            final String displayMsg = message.contains("\n") ? message.substring(0, message.indexOf("\n")) : message;
                            final String finalDate = date;
                            final String repoUrl = htmlUrl;

                            mainHandler.post(() -> {
                                cardUpdateBanner.setVisibility(View.VISIBLE);
                                tvCommitMsg.setText(displayMsg);
                                tvDetails.setText("Commit: " + displaySha + (finalDate.isEmpty() ? "" : " • " + finalDate));

                                btnViewGithub.setOnClickListener(v -> {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repoUrl));
                                    context.startActivity(browserIntent);
                                });

                                btnClose.setOnClickListener(v -> {
                                    cardUpdateBanner.setVisibility(View.GONE);
                                    prefs.edit().putString(KEY_DISMISSED_SHA, sha).apply();
                                });
                            });
                        }
                    }
                }
            } catch (Exception e) {
                // Silently handle offline / no internet situations
                e.printStackTrace();
            }
        });
    }
}
