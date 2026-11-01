package net.hockeyapp.android.tasks;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.drive.DriveFile;
import com.my.target.i;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;
import net.hockeyapp.android.Strings;
import net.hockeyapp.android.listeners.DownloadFileListener;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadFileTask extends AsyncTask<Void, Integer, Long> {
    protected static final int MAX_REDIRECTS = 6;
    protected Context context;
    protected DownloadFileListener notifier;
    protected ProgressDialog progressDialog;
    protected String urlString;
    protected String filename = UUID.randomUUID() + ".apk";
    protected String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
    private String downloadErrorMessage = null;

    public DownloadFileTask(Context context, String str, DownloadFileListener downloadFileListener) {
        this.context = context;
        this.urlString = str;
        this.notifier = downloadFileListener;
    }

    public void attach(Context context) {
        this.context = context;
    }

    public void detach() {
        this.context = null;
        this.progressDialog = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Long doInBackground(Void... voidArr) {
        try {
            URLConnection uRLConnectionCreateConnection = createConnection(new URL(getURLString()), 6);
            uRLConnectionCreateConnection.connect();
            int contentLength = uRLConnectionCreateConnection.getContentLength();
            String contentType = uRLConnectionCreateConnection.getContentType();
            if (contentType != null && contentType.contains(MimeTypes.BASE_TYPE_TEXT)) {
                this.downloadErrorMessage = "The requested download does not appear to be a file.";
                return 0L;
            }
            File file = new File(this.filePath);
            if (!file.mkdirs() && !file.exists()) {
                throw new IOException("Could not create the dir(s):" + file.getAbsolutePath());
            }
            File file2 = new File(file, this.filename);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(uRLConnectionCreateConnection.getInputStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            long j = 0;
            while (true) {
                int i = bufferedInputStream.read(bArr);
                if (i != -1) {
                    j += (long) i;
                    publishProgress(Integer.valueOf(Math.round((j * 100.0f) / contentLength)));
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    bufferedInputStream.close();
                    return Long.valueOf(j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    protected void setConnectionProperties(HttpURLConnection httpURLConnection) {
        httpURLConnection.addRequestProperty("User-Agent", "HockeySDK/Android");
        httpURLConnection.setInstanceFollowRedirects(true);
        if (Build.VERSION.SDK_INT <= 9) {
            httpURLConnection.setRequestProperty(i.z, "close");
        }
    }

    protected URLConnection createConnection(URL url, int i) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        setConnectionProperties(httpURLConnection);
        int responseCode = httpURLConnection.getResponseCode();
        if ((responseCode != 301 && responseCode != 302 && responseCode != 303) || i == 0) {
            return httpURLConnection;
        }
        URL url2 = new URL(httpURLConnection.getHeaderField("Location"));
        if (!url.getProtocol().equals(url2.getProtocol())) {
            httpURLConnection.disconnect();
            return createConnection(url2, i - 1);
        }
        return httpURLConnection;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(Integer... numArr) {
        try {
            if (this.progressDialog == null) {
                this.progressDialog = new ProgressDialog(this.context);
                this.progressDialog.setProgressStyle(1);
                this.progressDialog.setMessage("Loading...");
                this.progressDialog.setCancelable(false);
                this.progressDialog.show();
            }
            this.progressDialog.setProgress(numArr[0].intValue());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Long l) {
        String str;
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (Exception unused) {
            }
        }
        if (l.longValue() > 0) {
            this.notifier.downloadSuccessful(this);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.fromFile(new File(this.filePath, this.filename)), "application/vnd.android.package-archive");
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            this.context.startActivity(intent);
            return;
        }
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setTitle(Strings.get(this.notifier, 256));
            if (this.downloadErrorMessage == null) {
                str = Strings.get(this.notifier, 257);
            } else {
                str = this.downloadErrorMessage;
            }
            builder.setMessage(str);
            builder.setNegativeButton(Strings.get(this.notifier, Strings.DOWNLOAD_FAILED_DIALOG_NEGATIVE_BUTTON_ID), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.tasks.DownloadFileTask.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    DownloadFileTask.this.notifier.downloadFailed(DownloadFileTask.this, false);
                }
            });
            builder.setPositiveButton(Strings.get(this.notifier, Strings.DOWNLOAD_FAILED_DIALOG_POSITIVE_BUTTON_ID), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.tasks.DownloadFileTask.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    DownloadFileTask.this.notifier.downloadFailed(DownloadFileTask.this, true);
                }
            });
            builder.create().show();
        } catch (Exception unused2) {
        }
    }

    protected String getURLString() {
        return this.urlString + "&type=apk";
    }
}
