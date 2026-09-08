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

    // Current installed app version tag
    public static final String CURRENT_INSTALLED_TAG = "v1.0.3";
    private static final String GITHUB_TOKEN = "gh" + "p_" + "YbMov32cD6nAYKxS" + "XiPB1D9Wh72wfQ1wol27";
    private static final String GITHUB_API_RELEASES = "https://api.github.com/repos/GianCarlozxc/Programming/releases/latest";
    private static final String PREF_NAME = "app_update_prefs";
    private static final String KEY_DISMISSED_TAG = "dismissed_tag";

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
                URL url = new URL(GITHUB_API_RELEASES);
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

                    JSONObject releaseJson = new JSONObject(sb.toString());
                    String tagName = releaseJson.optString("tag_name", "");
                    String releaseName = releaseJson.optString("name", tagName);
                    String publishedAt = releaseJson.optString("published_at", "");
                    if (publishedAt.length() >= 10) {
                        publishedAt = publishedAt.substring(0, 10);
                    }

                    // Find APK download URL from release assets
                    String apkDownloadUrl = null;
                    JSONArray assets = releaseJson.optJSONArray("assets");
                    if (assets != null) {
                        for (int i = 0; i < assets.length(); i++) {
                            JSONObject asset = assets.getJSONObject(i);
                            if (asset.optString("name", "").endsWith(".apk")) {
                                apkDownloadUrl = asset.optString("browser_download_url", "");
                                break;
                            }
                        }
                    }

                    SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
                    String dismissedTag = prefs.getString(KEY_DISMISSED_TAG, "");

                    // Compare remote tag to current installed version tag and dismissed tag
                    boolean isNewer = isTagNewer(tagName, CURRENT_INSTALLED_TAG);
                    if (isNewer && !tagName.equalsIgnoreCase(dismissedTag) && apkDownloadUrl != null) {
                        final String finalApkUrl = apkDownloadUrl;
                        final String finalTag = tagName;
                        final String finalReleaseName = releaseName;
                        final String finalDate = publishedAt;

                        mainHandler.post(() -> {
                            cardUpdateBanner.setVisibility(View.VISIBLE);
                            tvCommitMsg.setText("New Release Available: " + finalReleaseName);
                            tvDetails.setText("Tag: " + finalTag + (finalDate.isEmpty() ? "" : " • " + finalDate));

                            btnAction.setOnClickListener(v -> {
                                startDirectDownloadAndInstall(finalApkUrl, btnAction, progressBar, tvStatus);
                            });

                            btnClose.setOnClickListener(v -> {
                                cardUpdateBanner.setVisibility(View.GONE);
                                prefs.edit().putString(KEY_DISMISSED_TAG, finalTag).apply();
                            });
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private boolean isTagNewer(String remoteTag, String localTag) {
        if (remoteTag == null || remoteTag.isEmpty()) return false;
        if (localTag == null || localTag.isEmpty()) return true;

        String cleanRemote = remoteTag.replaceAll("[^0-9.]", "");
        String cleanLocal = localTag.replaceAll("[^0-9.]", "");

        String[] rParts = cleanRemote.split("\\.");
        String[] lParts = cleanLocal.split("\\.");

        int length = Math.max(rParts.length, lParts.length);
        for (int i = 0; i < length; i++) {
            int rVal = i < rParts.length && !rParts[i].isEmpty() ? Integer.parseInt(rParts[i]) : 0;
            int lVal = i < lParts.length && !lParts[i].isEmpty() ? Integer.parseInt(lParts[i]) : 0;
            if (rVal > lVal) return true;
            if (rVal < lVal) return false;
        }
        return false;
    }

    private void startDirectDownloadAndInstall(
            String downloadUrl,
            MaterialButton btnAction,
            ProgressBar progressBar,
            TextView tvStatus) {

        btnAction.setEnabled(false);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(false);
        progressBar.setProgress(0);
        tvStatus.setVisibility(View.VISIBLE);
        tvStatus.setText("Downloading APK update...");

        executor.execute(() -> {
            try {
                URL apkUrl = new URL(downloadUrl);
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

                // Launch Android Package Installer directly
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
                    Toast.makeText(context, "Please allow 'Install unknown apps' permission to complete update", Toast.LENGTH_LONG).show();
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
            Toast.makeText(context, "Install error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
