package com.ironsource.eventsmodule;

import android.os.AsyncTask;
import com.google.android.exoplayer2.DefaultLoadControl;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class EventsSender extends AsyncTask<Object, Void, Boolean> {
    private ArrayList extraData;
    private IEventsSenderResultListener mResultListener;
    private final int SERVER_REQUEST_TIMEOUT = DefaultLoadControl.DEFAULT_MIN_BUFFER_MS;
    private final String SERVER_REQUEST_METHOD = HttpRequest.METHOD_POST;
    private final String SERVER_REQUEST_ENCODING = "UTF-8";
    private final String CONTENT_TYPE_FIELD = HttpRequest.HEADER_CONTENT_TYPE;
    private final String APPLICATION_JSON = "application/json";

    public EventsSender() {
    }

    public EventsSender(IEventsSenderResultListener iEventsSenderResultListener) {
        this.mResultListener = iEventsSenderResultListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.AsyncTask
    public Boolean doInBackground(Object... objArr) {
        try {
            boolean z = true;
            URL url = new URL((String) objArr[1]);
            this.extraData = (ArrayList) objArr[2];
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
            httpURLConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_MIN_BUFFER_MS);
            httpURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
            httpURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write((String) objArr[0]);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            int responseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            if (responseCode != 200) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        IEventsSenderResultListener iEventsSenderResultListener = this.mResultListener;
        if (iEventsSenderResultListener != null) {
            iEventsSenderResultListener.onEventsSenderResult(this.extraData, bool.booleanValue());
        }
    }
}
