package com.integralads.avid.library.inmobi.walking.async;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.ThreadPoolExecutor;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AvidAsyncTask extends AsyncTask<Object, Void, String> {
    private AvidAsyncTaskListener listener;
    protected final StateProvider stateProvider;

    public interface AvidAsyncTaskListener {
        void onTaskCompleted(AvidAsyncTask avidAsyncTask);
    }

    public interface StateProvider {
        JSONObject getPreviousState();

        void setPreviousState(JSONObject jSONObject);
    }

    public AvidAsyncTask(StateProvider stateProvider) {
        this.stateProvider = stateProvider;
    }

    public void setListener(AvidAsyncTaskListener avidAsyncTaskListener) {
        this.listener = avidAsyncTaskListener;
    }

    public AvidAsyncTaskListener getListener() {
        return this.listener;
    }

    public StateProvider getStateProvider() {
        return this.stateProvider;
    }

    public void start(ThreadPoolExecutor threadPoolExecutor) {
        if (Build.VERSION.SDK_INT > 11) {
            executeOnExecutor(threadPoolExecutor, new Object[0]);
        } else {
            execute(new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        AvidAsyncTaskListener avidAsyncTaskListener = this.listener;
        if (avidAsyncTaskListener != null) {
            avidAsyncTaskListener.onTaskCompleted(this);
        }
    }

    @VisibleForTesting
    String invokeDoInBackground() {
        return doInBackground(new Object[0]);
    }

    @VisibleForTesting
    void invokeOnPostExecute(String str) {
        onPostExecute(str);
    }
}
