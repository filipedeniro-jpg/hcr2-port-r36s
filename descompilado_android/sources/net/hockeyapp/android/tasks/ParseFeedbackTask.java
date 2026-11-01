package net.hockeyapp.android.tasks;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.drive.DriveFile;
import java.util.ArrayList;
import net.hockeyapp.android.FeedbackActivity;
import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.FeedbackManagerListener;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.objects.FeedbackResponse;
import net.hockeyapp.android.utils.FeedbackParser;
import net.hockeyapp.android.utils.PrefsUtil;
import net.hockeyapp.android.utils.Util;

/* JADX INFO: loaded from: classes2.dex */
public class ParseFeedbackTask extends AsyncTask<Void, Void, FeedbackResponse> {
    public static final String ID_LAST_MESSAGE_PROCESSED = "idLastMessageProcessed";
    public static final String ID_LAST_MESSAGE_SEND = "idLastMessageSend";
    public static final int NEW_ANSWER_NOTIFICATION_ID = 2;
    public static final String PREFERENCES_NAME = "net.hockeyapp.android.feedback";
    private Context context;
    private String feedbackResponse;
    private Handler handler;
    private String requestType;
    private String urlString = null;

    public ParseFeedbackTask(Context context, String str, Handler handler, String str2) {
        this.context = context;
        this.feedbackResponse = str;
        this.handler = handler;
        this.requestType = str2;
    }

    public void setUrlString(String str) {
        this.urlString = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public FeedbackResponse doInBackground(Void... voidArr) {
        ArrayList<FeedbackMessage> messages;
        if (this.context == null || this.feedbackResponse == null) {
            return null;
        }
        FeedbackResponse feedbackResponse = FeedbackParser.getInstance().parseFeedbackResponse(this.feedbackResponse);
        if (feedbackResponse != null && feedbackResponse.getFeedback() != null && (messages = feedbackResponse.getFeedback().getMessages()) != null && !messages.isEmpty()) {
            checkForNewAnswers(messages);
        }
        return feedbackResponse;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(FeedbackResponse feedbackResponse) {
        if (feedbackResponse == null || this.handler == null) {
            return;
        }
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putSerializable("parse_feedback_response", feedbackResponse);
        message.setData(bundle);
        this.handler.sendMessage(message);
    }

    private void checkForNewAnswers(ArrayList<FeedbackMessage> arrayList) {
        FeedbackMessage feedbackMessage = arrayList.get(arrayList.size() - 1);
        int id = feedbackMessage.getId();
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(PREFERENCES_NAME, 0);
        if (this.requestType.equals("send")) {
            PrefsUtil.applyChanges(sharedPreferences.edit().putInt(ID_LAST_MESSAGE_SEND, id).putInt(ID_LAST_MESSAGE_PROCESSED, id));
            return;
        }
        if (this.requestType.equals("fetch")) {
            int i = sharedPreferences.getInt(ID_LAST_MESSAGE_SEND, -1);
            int i2 = sharedPreferences.getInt(ID_LAST_MESSAGE_PROCESSED, -1);
            if (id == i || id == i2) {
                return;
            }
            PrefsUtil.applyChanges(sharedPreferences.edit().putInt(ID_LAST_MESSAGE_PROCESSED, id));
            FeedbackManagerListener lastListener = FeedbackManager.getLastListener();
            if (lastListener != null ? lastListener.feedbackAnswered(feedbackMessage) : false) {
                return;
            }
            startNotification(this.context);
        }
    }

    private void startNotification(Context context) {
        if (this.urlString == null) {
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        int identifier = context.getResources().getIdentifier("ic_menu_refresh", "drawable", "android");
        Class<? extends FeedbackActivity> feedbackActivityClass = FeedbackManager.getLastListener() != null ? FeedbackManager.getLastListener().getFeedbackActivityClass() : null;
        if (feedbackActivityClass == null) {
            feedbackActivityClass = FeedbackActivity.class;
        }
        Intent intent = new Intent();
        intent.setFlags(DriveFile.MODE_READ_WRITE);
        intent.setClass(context, feedbackActivityClass);
        intent.putExtra("url", this.urlString);
        Notification notificationCreateNotification = Util.createNotification(context, PendingIntent.getActivity(context, 0, intent, 1073741824), "HockeyApp Feedback", "A new answer to your feedback is available.", identifier);
        if (notificationCreateNotification != null) {
            notificationManager.notify(2, notificationCreateNotification);
        }
    }
}
