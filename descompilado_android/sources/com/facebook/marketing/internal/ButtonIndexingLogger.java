package com.facebook.marketing.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.codeless.ViewIndexer;
import com.facebook.appevents.codeless.internal.SensitiveUserDataUtils;
import com.facebook.appevents.codeless.internal.ViewHierarchy;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Utility;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ironsource.sdk.constants.Constants;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ButtonIndexingLogger {
    private static final String API_ENDPOINT = "%s/button_indexing";
    private static final String TAG = "com.facebook.marketing.internal.ButtonIndexingLogger";
    private static volatile Set<String> clickedKeySet = new HashSet();
    private static volatile Set<String> loadedKeySet = new HashSet();

    private static JSONObject generateButtonDetail(View view, String str, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            String[] strArrSplit = str.split("\\.", -1);
            int length = strArrSplit.length - 1;
            View parentOfView = view;
            while (parentOfView != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("classname", parentOfView.getClass().getCanonicalName());
                jSONObject2.put(FirebaseAnalytics.Param.INDEX, strArrSplit[length]);
                jSONObject2.put("id", parentOfView.getId());
                String strValueOf = "";
                jSONObject2.put(MimeTypes.BASE_TYPE_TEXT, SensitiveUserDataUtils.isSensitiveUserData(parentOfView) ? "" : ViewHierarchy.getTextOfView(parentOfView));
                jSONObject2.put("tag", parentOfView.getTag() == null ? "" : String.valueOf(parentOfView.getTag()));
                if (parentOfView.getContentDescription() != null) {
                    strValueOf = String.valueOf(parentOfView.getContentDescription());
                }
                jSONObject2.put("description", strValueOf);
                jSONArray.put(jSONObject2);
                parentOfView = ViewHierarchy.getParentOfView(parentOfView);
                length--;
            }
            JSONArray jSONArray2 = new JSONArray();
            for (int length2 = jSONArray.length() - 1; length2 >= 0; length2--) {
                jSONArray2.put(jSONArray.get(length2));
            }
            jSONObject.put("path", jSONArray2);
            jSONObject.put("is_from_click", z);
            if (view instanceof ImageView) {
                Bitmap bitmap = ((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                jSONObject.put(MessengerShareContentUtility.MEDIA_IMAGE, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
            }
            return jSONObject;
        } catch (Exception e) {
            Log.e(TAG, "Log button indexing error", e);
            return new JSONObject();
        }
    }

    private static void sendGraphAPIRequest(Context context, String str, String str2) {
        AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
        if (attributionIdentifiers == null || attributionIdentifiers.getAndroidAdvertiserId() == null) {
            return;
        }
        String appVersion = AppEventUtility.getAppVersion();
        Bundle bundle = new Bundle();
        try {
            bundle.putString("app_version", appVersion);
            bundle.putString("indexed_button_list", str);
            GraphRequest graphRequestNewPostRequest = GraphRequest.newPostRequest(null, String.format(Locale.US, API_ENDPOINT, str2), null, null);
            graphRequestNewPostRequest.setParameters(bundle);
            graphRequestNewPostRequest.executeAndWait();
        } catch (Exception e) {
            Log.e(TAG, "failed to send button indexing request", e);
        }
    }

    public static void logIndexing(String str, View view, String str2, Context context) {
        if (clickedKeySet.contains(str2)) {
            return;
        }
        clickedKeySet.add(str2);
        JSONObject jSONObjectGenerateButtonDetail = generateButtonDetail(view, str2, true);
        if (jSONObjectGenerateButtonDetail.length() > 0) {
            sendGraphAPIRequest(context, new JSONArray((Collection) Arrays.asList(jSONObjectGenerateButtonDetail.toString())).toString(), str);
        }
    }

    public static void logAllIndexing(final JSONObject jSONObject, final String str) {
        FacebookSdk.getExecutor().execute(new Runnable() { // from class: com.facebook.marketing.internal.ButtonIndexingLogger.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    String applicationId = FacebookSdk.getApplicationId();
                    AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
                    jSONObject2.put("screenname", str);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(jSONObject);
                    jSONObject2.put(Constants.ParametersKeys.VIEW, jSONArray);
                    GraphRequest graphRequestBuildAppIndexingRequest = ViewIndexer.buildAppIndexingRequest(jSONObject2.toString(), currentAccessToken, applicationId, com.facebook.appevents.codeless.internal.Constants.BUTTON_SAMPLING);
                    if (graphRequestBuildAppIndexingRequest != null) {
                        graphRequestBuildAppIndexingRequest.executeAndWait();
                    }
                } catch (JSONException e) {
                    Utility.logd(ButtonIndexingLogger.TAG, e);
                }
            }
        });
    }
}
