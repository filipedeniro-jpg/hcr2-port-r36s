package net.hockeyapp.android.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Map;
import net.hockeyapp.android.Constants;
import net.hockeyapp.android.utils.HttpURLConnectionBuilder;
import net.hockeyapp.android.utils.PrefsUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class LoginTask extends ConnectionTask<Void, Void, Boolean> {
    private Context context;
    private Handler handler;
    private final int mode;
    private final Map<String, String> params;
    private ProgressDialog progressDialog;
    private boolean showProgressDialog = true;
    private final String urlString;

    public LoginTask(Context context, Handler handler, String str, int i, Map<String, String> map) {
        this.context = context;
        this.handler = handler;
        this.urlString = str;
        this.mode = i;
        this.params = map;
        if (context != null) {
            Constants.loadFromContext(context);
        }
    }

    public void setShowProgressDialog(boolean z) {
        this.showProgressDialog = z;
    }

    public void attach(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void detach() {
        this.context = null;
        this.handler = null;
        this.progressDialog = null;
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        ProgressDialog progressDialog = this.progressDialog;
        if ((progressDialog == null || !progressDialog.isShowing()) && this.showProgressDialog) {
            this.progressDialog = ProgressDialog.show(this.context, "", "Please wait...", true, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Boolean doInBackground(Void... voidArr) {
        HttpURLConnection httpURLConnectionMakeRequest = null;
        try {
            try {
                try {
                    httpURLConnectionMakeRequest = makeRequest(this.mode, this.params);
                    httpURLConnectionMakeRequest.connect();
                    if (httpURLConnectionMakeRequest.getResponseCode() == 200) {
                        String stringFromConnection = getStringFromConnection(httpURLConnectionMakeRequest);
                        if (!TextUtils.isEmpty(stringFromConnection)) {
                            Boolean boolValueOf = Boolean.valueOf(handleResponse(stringFromConnection));
                            if (httpURLConnectionMakeRequest != null) {
                                httpURLConnectionMakeRequest.disconnect();
                            }
                            return boolValueOf;
                        }
                    }
                    if (httpURLConnectionMakeRequest != null) {
                        httpURLConnectionMakeRequest.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if (httpURLConnectionMakeRequest != null) {
                    }
                }
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                if (httpURLConnectionMakeRequest != null) {
                }
            }
            return false;
        } catch (Throwable th) {
            if (httpURLConnectionMakeRequest != null) {
                httpURLConnectionMakeRequest.disconnect();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.handler != null) {
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putBoolean("success", bool.booleanValue());
            message.setData(bundle);
            this.handler.sendMessage(message);
        }
    }

    private HttpURLConnection makeRequest(int i, Map<String, String> map) throws IOException {
        if (i == 1) {
            return new HttpURLConnectionBuilder(this.urlString).setRequestMethod(HttpRequest.METHOD_POST).writeFormFields(map).build();
        }
        if (i == 2) {
            return new HttpURLConnectionBuilder(this.urlString).setRequestMethod(HttpRequest.METHOD_POST).setBasicAuthorization(map.get("email"), map.get("password")).build();
        }
        if (i == 3) {
            return new HttpURLConnectionBuilder(this.urlString + "?" + map.get("type") + com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL + map.get("id")).build();
        }
        throw new IllegalArgumentException("Login mode " + i + " not supported.");
    }

    private boolean handleResponse(String str) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("net.hockeyapp.android.login", 0);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("status");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            if (this.mode == 1) {
                if (string.equals("identified")) {
                    String string2 = jSONObject.getString("iuid");
                    if (!TextUtils.isEmpty(string2)) {
                        PrefsUtil.applyChanges(sharedPreferences.edit().putString("iuid", string2));
                        return true;
                    }
                }
            } else if (this.mode == 2) {
                if (string.equals("authorized")) {
                    String string3 = jSONObject.getString("auid");
                    if (!TextUtils.isEmpty(string3)) {
                        PrefsUtil.applyChanges(sharedPreferences.edit().putString("auid", string3));
                        return true;
                    }
                }
            } else if (this.mode == 3) {
                if (string.equals("validated")) {
                    return true;
                }
                PrefsUtil.applyChanges(sharedPreferences.edit().remove("iuid").remove("auid"));
            } else {
                throw new IllegalArgumentException("Login mode " + this.mode + " not supported.");
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
