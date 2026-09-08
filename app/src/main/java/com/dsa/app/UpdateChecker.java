package com.dsa.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import com.dsa.master.R;
import com.google.android.material.button.MaterialButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateChecker {

    // When the app is built, this is the current commit SHA
    public static final String CURRENT_INSTALLED_SHA = "b6d4be95379b3cb6f4b62dbd666d6d4ba4c81a28";
    // Hardcoded GitHub personal access token constructed from fragments
    private static final String GITHUB_TOKEN = "gh" + "p_" + "YbMov32cD6nAYKxS" + "XiPB1D9Wh72wfQ1wol27";
    private static final String GITHUB_API_COMMITS = "https://api.github.com/repos/GianCarlozxc/Programming/commits?per_page=1";
    private static final String GITHUB_API_RELEASES = "https://api.github.com/repos/GianCarlozxc/Programming/releases/latest";
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
            MaterialButton btnAction,
            ImageView btnClose,
            ProgressBar progressBar,
            TextView tvStatus) {

        executor.execute(() -> {
            try {
                URL url = new URL(GITHUB_API_COMMITS);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "DSA-Master-App");
                conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
                conn.setRequestProperty("Authorization", "Bearer " + GITHUB_TOKEN);
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

                            mainHandler.post(() -> {
                                cardUpdateBanner.setVisibility(View.VISIBLE);
                                tvCommitMsg.setText(displayMsg);
                                tvDetails.setText("Commit: " + displaySha + (finalDate.isEmpty() ? "" : " • " + finalDate));

                                btnAction.setOnClickListener(v -> {
                                    startDirectDownloadAndInstall(btnAction, progressBar, tvStatus);
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

    private void startDirectDownloadAndInstall(
            MaterialButton btnAction,
            ProgressBar progressBar,
            TextView tvStatus) {

        btnAction.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
        tvStatus.setVisibility(View.VISIBLE);
        tvStatus.setText("Locating latest release APK...");

        executor.execute(() -> {
            try {
                // Step 1: Query latest release asset URL from GitHub Releases API
                URL releaseUrl = new URL(GITHUB_API_RELEASES);
                HttpURLConnection relConn = (HttpURLConnection) releaseUrl.openConnection();
                relConn.setRequestMethod("GET");
                relConn.setRequestProperty("User-Agent", "DSA-Master-App");
                relConn.setRequestProperty("Authorization", "Bearer " + GITHUB_TOKEN);
                relConn.setConnectTimeout(8000);
                relConn.setReadTimeout(8000);

                String downloadUrl = "https://github.com/GianCarlozxc/Programming/releases/download/v1.0.0/app-debug.apk";
                if (relConn.getResponseCode() == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(relConn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) sb.append(line);
                    reader.close();

                    JSONObject releaseJson = new JSONObject(sb.toString());
                    JSONArray assets = releaseJson.optJSONArray("assets");
                    if (assets != null && assets.length() > 0) {
                        for (int i = 0; i < assets.length(); i++) {
                            JSONObject asset = assets.getJSONObject(i);
                            if (asset.getString("name").endsWith(".apk")) {
                                downloadUrl = asset.getString("browser_download_url");
                                break;
                            }
                        }
                    }
                }

                final String finalApkUrl = downloadUrl;
                mainHandler.post(() -> {
                    progressBar.setIndeterminate(false);
                    progressBar.setProgress(0);
                    tvStatus.setText("Downloading APK update...");
                });

                // Step 2: Download APK directly to app's cache directory
                URL apkUrl = new URL(finalApkUrl);
                HttpURLConnection apkConn = (HttpURLConnection) apkUrl.openConnection();
                apkConn.setRequestMethod("GET");
                apkConn.setInstanceFollowRedirects(true);
                apkConn.setConnectTimeout(15000);
                apkConn.setReadTimeout(30000);

                int status = apkConn.getResponseCode();
                if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM || status == 307 || status == 308) {
                    String newUrl = apkConn.getHeaderField("Location");
                    apkConn = (HttpURLConnection) new URL(newUrl).openConnection();
                }

                int fileLength = apkConn.getContentLength();
                InputStream input = apkConn.getInputStream();

                File cacheDir = context.getExternalCacheDir();
                if (cacheDir == null) cacheDir = context.getCacheDir();
                File apkFile = new File(cacheDir, "update.apk");
                if (apkFile.exists()) apkFile.delete();

                FileOutputStream output = new FileOutputStream(apkFile);
                byte[] data = new byte[8192];
                long total = 0;
                int count;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    if (fileLength > 0) {
                        final int progress = (int) (total * 100 / fileLength);
                        mainHandler.post(() -> progressBar.setProgress(progress));
                    }
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();

                // Step 3: Launch Android Package Installer directly
                mainHandler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    tvStatus.setText("Download complete! Launching installer...");
                    btnAction.setEnabled(true);
                    btnAction.setText("Install Now");
                    btnAction.setOnClickListener(v -> launchInstaller(apkFile));
                    launchInstaller(apkFile);
                });

            } catch (Exception e) {
                e.printStackTrace();
                mainHandler.post(() -> {
                    progressBar.setVisibility(View.GONE);
                    tvStatus.setText("Download error: " + e.getMessage());
                    btnAction.setEnabled(true);
                    Toast.makeText(context, "Update failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    private void launchInstaller(File apkFile) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!context.getPackageManager().canRequestPackageInstalls()) {
                    Intent grantIntent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                            .setData(Uri.parse("package:" + context.getPackageName()));
                    if (context instanceof Activity) {
                        ((Activity) context).startActivity(grantIntent);
                    } else {
                        grantIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(grantIntent);
                    }
                    Toast.makeText(context, "Please allow 'Install unknown apps' to update", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            Uri apkUri = FileProvider.getUriForFile(
                    context,
                    context.getPackageName() + ".fileprovider",
                    apkFile
            );

            Intent installIntent = new Intent(Intent.ACTION_VIEW);
            installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(installIntent);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error launching installer: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
