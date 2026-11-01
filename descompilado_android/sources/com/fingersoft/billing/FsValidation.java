package com.fingersoft.billing;

import com.fingersoft.billing.util.Purchase;
import com.fingersoft.hcr2.AppActivity;
import com.fingersoft.hcr2.Log;
import com.google.android.gms.games.GamesStatusCodes;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.SocketTimeoutException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: classes.dex */
public class FsValidation {
    private static final String TAG = "hcr2 - FsValidation";

    public static String generatePayload(String str) {
        return String.format("%s:%d", str, Long.valueOf(System.currentTimeMillis() / 1000));
    }

    public static void validate(final Purchase purchase, final ValidationCallback validationCallback) {
        new Thread() { // from class: com.fingersoft.billing.FsValidation.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    Log.d(FsValidation.TAG, "FS validating purchase: " + purchase.getSku());
                    String str = String.format("{\"packageName\":\"com.fingersoft.hcr2\",\"userId\":\"%s\",\"productId\":\"%s\",\"purchaseData\":\"%s\",\"signature\":\"%s\"}", AppActivity.getInstance().getAnalyticsId(), purchase.getSku(), purchase.getOriginalJson().replace("\"", "\\\""), purchase.getSignature());
                    Log.d(FsValidation.TAG, "Request JSON: " + str);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("https://fs-iap.eastus.cloudapp.azure.com:30303/api/validate/android").openConnection();
                    httpsURLConnection.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, "application/json");
                    httpsURLConnection.setReadTimeout(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED);
                    httpsURLConnection.setConnectTimeout(GamesStatusCodes.STATUS_MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED);
                    httpsURLConnection.setRequestMethod(HttpRequest.METHOD_POST);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpsURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(str);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    httpsURLConnection.connect();
                    Log.d(FsValidation.TAG, "FS validate response: " + httpsURLConnection.getResponseCode() + " - " + httpsURLConnection.getResponseMessage());
                    if (httpsURLConnection.getResponseCode() == 200) {
                        validationCallback.onValidateSuccess(purchase);
                    } else if (httpsURLConnection.getResponseCode() == 400) {
                        validationCallback.onValidateFailed(purchase, httpsURLConnection.getResponseMessage());
                    } else {
                        validationCallback.onValidateError(purchase, httpsURLConnection.getResponseMessage());
                    }
                } catch (SocketTimeoutException e) {
                    validationCallback.onValidateError(purchase, e.getMessage());
                } catch (Exception e2) {
                    validationCallback.onValidateError(purchase, e2.getMessage());
                }
            }
        }.start();
    }
}
