package net.hockeyapp.android;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.drive.DriveFile;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.io.FileOutputStream;
import net.hockeyapp.android.objects.FeedbackUserDataElement;
import net.hockeyapp.android.tasks.ParseFeedbackTask;
import net.hockeyapp.android.tasks.SendFeedbackTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.PrefsUtil;
import net.hockeyapp.android.utils.Util;

/* JADX INFO: loaded from: classes2.dex */
public class FeedbackManager {
    private static final String BROADCAST_ACTION = "net.hockeyapp.android.SCREENSHOT";
    private static final int BROADCAST_REQUEST_CODE = 1;
    private static final int SCREENSHOT_NOTIFICATION_ID = 1;
    private static Activity currentActivity = null;
    private static String identifier = null;
    private static FeedbackManagerListener lastListener = null;
    private static boolean notificationActive = false;
    private static BroadcastReceiver receiver;
    private static FeedbackUserDataElement requireUserEmail;
    private static FeedbackUserDataElement requireUserName;
    private static String urlString;

    public static void register(Context context, String str) {
        register(context, str, null);
    }

    public static void register(Context context, String str, FeedbackManagerListener feedbackManagerListener) {
        register(context, Constants.BASE_URL, str, feedbackManagerListener);
    }

    public static void register(Context context, String str, String str2, FeedbackManagerListener feedbackManagerListener) {
        if (context != null) {
            identifier = Util.sanitizeAppIdentifier(str2);
            urlString = str;
            lastListener = feedbackManagerListener;
            Constants.loadFromContext(context);
        }
    }

    public static void unregister() {
        lastListener = null;
    }

    public static void showFeedbackActivity(Context context, Uri... uriArr) {
        showFeedbackActivity(context, null, uriArr);
    }

    public static void showFeedbackActivity(Context context, Bundle bundle, Uri... uriArr) {
        if (context != null) {
            FeedbackManagerListener feedbackManagerListener = lastListener;
            Class<? extends FeedbackActivity> feedbackActivityClass = feedbackManagerListener != null ? feedbackManagerListener.getFeedbackActivityClass() : null;
            if (feedbackActivityClass == null) {
                feedbackActivityClass = FeedbackActivity.class;
            }
            Intent intent = new Intent();
            if (bundle != null && !bundle.isEmpty()) {
                intent.putExtras(bundle);
            }
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            intent.setClass(context, feedbackActivityClass);
            intent.putExtra("url", getURLString(context));
            intent.putExtra("initialAttachments", uriArr);
            context.startActivity(intent);
        }
    }

    public static void checkForAnswersAndNotify(final Context context) {
        String feedbackTokenFromPrefs = PrefsUtil.getInstance().getFeedbackTokenFromPrefs(context);
        if (feedbackTokenFromPrefs == null) {
            return;
        }
        int i = context.getSharedPreferences(ParseFeedbackTask.PREFERENCES_NAME, 0).getInt(ParseFeedbackTask.ID_LAST_MESSAGE_SEND, -1);
        SendFeedbackTask sendFeedbackTask = new SendFeedbackTask(context, getURLString(context), null, null, null, null, null, feedbackTokenFromPrefs, new Handler() { // from class: net.hockeyapp.android.FeedbackManager.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                String string = message.getData().getString("feedback_response");
                if (string != null) {
                    ParseFeedbackTask parseFeedbackTask = new ParseFeedbackTask(context, string, null, "fetch");
                    parseFeedbackTask.setUrlString(FeedbackManager.getURLString(context));
                    AsyncTaskUtils.execute(parseFeedbackTask);
                }
            }
        }, true);
        sendFeedbackTask.setShowProgressDialog(false);
        sendFeedbackTask.setLastMessageId(i);
        AsyncTaskUtils.execute(sendFeedbackTask);
    }

    public static FeedbackManagerListener getLastListener() {
        return lastListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getURLString(Context context) {
        return urlString + "api/2/apps/" + identifier + "/feedback/";
    }

    public static FeedbackUserDataElement getRequireUserName() {
        return requireUserName;
    }

    public static void setRequireUserName(FeedbackUserDataElement feedbackUserDataElement) {
        requireUserName = feedbackUserDataElement;
    }

    public static FeedbackUserDataElement getRequireUserEmail() {
        return requireUserEmail;
    }

    public static void setRequireUserEmail(FeedbackUserDataElement feedbackUserDataElement) {
        requireUserEmail = feedbackUserDataElement;
    }

    public static void setActivityForScreenshot(Activity activity) {
        currentActivity = activity;
        if (notificationActive) {
            return;
        }
        startNotification();
    }

    public static void unsetCurrentActivityForScreenshot(Activity activity) {
        Activity activity2 = currentActivity;
        if (activity2 == null || activity2 != activity) {
            return;
        }
        endNotification();
        currentActivity = null;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [net.hockeyapp.android.FeedbackManager$2] */
    public static void takeScreenshot(final Context context) {
        View decorView = currentActivity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        final Bitmap drawingCache = decorView.getDrawingCache();
        String localClassName = currentActivity.getLocalClassName();
        File hockeyAppStorageDir = Constants.getHockeyAppStorageDir();
        File file = new File(hockeyAppStorageDir, localClassName + ".jpg");
        int i = 1;
        while (file.exists()) {
            file = new File(hockeyAppStorageDir, localClassName + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + i + ".jpg");
            i++;
        }
        new AsyncTask<File, Void, Boolean>() { // from class: net.hockeyapp.android.FeedbackManager.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(File... fileArr) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileArr[0]);
                    drawingCache.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                    return true;
                } catch (Exception e) {
                    Log.e("HockeyApp", "Could not save screenshot.", e);
                    return false;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    return;
                }
                Toast.makeText(context, "Screenshot could not be created. Sorry.", 2000).show();
            }
        }.execute(file);
        MediaScannerClient mediaScannerClient = new MediaScannerClient(file.getAbsolutePath());
        MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(currentActivity, mediaScannerClient);
        mediaScannerClient.setConnection(mediaScannerConnection);
        mediaScannerConnection.connect();
        Toast.makeText(context, "Screenshot '" + file.getName() + "' is available in gallery.", 2000).show();
    }

    private static void startNotification() {
        notificationActive = true;
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService("notification");
        int identifier2 = currentActivity.getResources().getIdentifier("ic_menu_camera", "drawable", "android");
        Intent intent = new Intent();
        intent.setAction(BROADCAST_ACTION);
        notificationManager.notify(1, Util.createNotification(currentActivity, PendingIntent.getBroadcast(currentActivity, 1, intent, 1073741824), "HockeyApp Feedback", "Take a screenshot for your feedback.", identifier2));
        if (receiver == null) {
            receiver = new BroadcastReceiver() { // from class: net.hockeyapp.android.FeedbackManager.3
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent2) {
                    FeedbackManager.takeScreenshot(context);
                }
            };
        }
        currentActivity.registerReceiver(receiver, new IntentFilter(BROADCAST_ACTION));
    }

    private static void endNotification() {
        notificationActive = false;
        currentActivity.unregisterReceiver(receiver);
        ((NotificationManager) currentActivity.getSystemService("notification")).cancel(1);
    }

    private static class MediaScannerClient implements MediaScannerConnection.MediaScannerConnectionClient {
        private MediaScannerConnection connection;
        private String path;

        private MediaScannerClient(String str) {
            this.connection = null;
            this.path = str;
        }

        public void setConnection(MediaScannerConnection mediaScannerConnection) {
            this.connection = mediaScannerConnection;
        }

        @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
        public void onMediaScannerConnected() {
            MediaScannerConnection mediaScannerConnection = this.connection;
            if (mediaScannerConnection != null) {
                mediaScannerConnection.scanFile(this.path, null);
            }
        }

        @Override // android.media.MediaScannerConnection.OnScanCompletedListener
        public void onScanCompleted(String str, Uri uri) {
            Log.i("HockeyApp", String.format("Scanned path %s -> URI = %s", str, uri.toString()));
            this.connection.disconnect();
        }
    }
}
