package net.hockeyapp.android.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.my.target.i;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Queue;
import net.hockeyapp.android.Constants;
import net.hockeyapp.android.objects.FeedbackAttachment;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.ImageUtils;
import net.hockeyapp.android.views.AttachmentView;

/* JADX INFO: loaded from: classes2.dex */
public class AttachmentDownloader {
    private boolean downloadRunning;
    private Queue<DownloadJob> queue;

    private static class AttachmentDownloaderHolder {
        public static final AttachmentDownloader INSTANCE = new AttachmentDownloader();

        private AttachmentDownloaderHolder() {
        }
    }

    public static AttachmentDownloader getInstance() {
        return AttachmentDownloaderHolder.INSTANCE;
    }

    private AttachmentDownloader() {
        this.queue = new LinkedList();
        this.downloadRunning = false;
    }

    public void download(FeedbackAttachment feedbackAttachment, AttachmentView attachmentView) {
        this.queue.add(new DownloadJob(feedbackAttachment, attachmentView));
        downloadNext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadNext() {
        DownloadJob downloadJobPeek;
        if (this.downloadRunning || (downloadJobPeek = this.queue.peek()) == null) {
            return;
        }
        DownloadTask downloadTask = new DownloadTask(downloadJobPeek, new Handler() { // from class: net.hockeyapp.android.tasks.AttachmentDownloader.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                final DownloadJob downloadJob = (DownloadJob) AttachmentDownloader.this.queue.poll();
                if (!downloadJob.isSuccess() && downloadJob.consumeRetry()) {
                    postDelayed(new Runnable() { // from class: net.hockeyapp.android.tasks.AttachmentDownloader.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AttachmentDownloader.this.queue.add(downloadJob);
                            AttachmentDownloader.this.downloadNext();
                        }
                    }, 3000L);
                }
                AttachmentDownloader.this.downloadRunning = false;
                AttachmentDownloader.this.downloadNext();
            }
        });
        this.downloadRunning = true;
        AsyncTaskUtils.execute(downloadTask);
    }

    private static class DownloadJob {
        private final AttachmentView attachmentView;
        private final FeedbackAttachment feedbackAttachment;
        private int remainingRetries;
        private boolean success;

        private DownloadJob(FeedbackAttachment feedbackAttachment, AttachmentView attachmentView) {
            this.feedbackAttachment = feedbackAttachment;
            this.attachmentView = attachmentView;
            this.success = false;
            this.remainingRetries = 2;
        }

        public FeedbackAttachment getFeedbackAttachment() {
            return this.feedbackAttachment;
        }

        public AttachmentView getAttachmentView() {
            return this.attachmentView;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(boolean z) {
            this.success = z;
        }

        public boolean hasRetry() {
            return this.remainingRetries > 0;
        }

        public boolean consumeRetry() {
            int i = this.remainingRetries - 1;
            this.remainingRetries = i;
            return i >= 0;
        }
    }

    private static class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
        private final DownloadJob downloadJob;
        private final Handler handler;
        private File dropFolder = Constants.getHockeyAppStorageDir();
        private Bitmap bitmap = null;
        private int bitmapOrientation = 0;

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onProgressUpdate(Integer... numArr) {
        }

        public DownloadTask(DownloadJob downloadJob, Handler handler) {
            this.downloadJob = downloadJob;
            this.handler = handler;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            FeedbackAttachment feedbackAttachment = this.downloadJob.getFeedbackAttachment();
            if (feedbackAttachment.isAvailableInCache()) {
                Log.e("HockeyApp", "Cached...");
                loadImageThumbnail();
                return true;
            }
            Log.e("HockeyApp", "Downloading...");
            boolean zDownloadAttachment = downloadAttachment(feedbackAttachment.getUrl(), feedbackAttachment.getCacheId());
            if (zDownloadAttachment) {
                loadImageThumbnail();
            }
            return Boolean.valueOf(zDownloadAttachment);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            AttachmentView attachmentView = this.downloadJob.getAttachmentView();
            this.downloadJob.setSuccess(bool.booleanValue());
            if (bool.booleanValue()) {
                attachmentView.setImage(this.bitmap, this.bitmapOrientation);
            } else if (!this.downloadJob.hasRetry()) {
                attachmentView.signalImageLoadingError();
            }
            this.handler.sendEmptyMessage(0);
        }

        private void loadImageThumbnail() {
            try {
                String cacheId = this.downloadJob.getFeedbackAttachment().getCacheId();
                AttachmentView attachmentView = this.downloadJob.getAttachmentView();
                this.bitmapOrientation = ImageUtils.determineOrientation(new File(this.dropFolder, cacheId));
                this.bitmap = ImageUtils.decodeSampledBitmap(new File(this.dropFolder, cacheId), this.bitmapOrientation == 1 ? attachmentView.getWidthLandscape() : attachmentView.getWidthPortrait(), this.bitmapOrientation == 1 ? attachmentView.getMaxHeightLandscape() : attachmentView.getMaxHeightPortrait());
            } catch (IOException e) {
                e.printStackTrace();
                this.bitmap = null;
            }
        }

        private boolean downloadAttachment(String str, String str2) {
            try {
                URLConnection uRLConnectionCreateConnection = createConnection(new URL(str));
                uRLConnectionCreateConnection.connect();
                int contentLength = uRLConnectionCreateConnection.getContentLength();
                String headerField = uRLConnectionCreateConnection.getHeaderField("Status");
                if (headerField != null && !headerField.startsWith("200")) {
                    return false;
                }
                File file = new File(this.dropFolder, str2);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(uRLConnectionCreateConnection.getInputStream());
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int i = bufferedInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    j += (long) i;
                    publishProgress(Integer.valueOf((int) ((100 * j) / ((long) contentLength))));
                    fileOutputStream.write(bArr, 0, i);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
                return j > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        private URLConnection createConnection(URL url) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "HockeySDK/Android");
            httpURLConnection.setInstanceFollowRedirects(true);
            if (Build.VERSION.SDK_INT <= 9) {
                httpURLConnection.setRequestProperty(i.z, "close");
            }
            return httpURLConnection;
        }
    }
}
