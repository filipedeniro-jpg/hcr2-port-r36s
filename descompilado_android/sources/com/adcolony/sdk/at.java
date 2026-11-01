package com.adcolony.sdk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.Vibrator;
import android.view.View;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import com.amazon.device.ads.WebRequest;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class at {
    at() {
    }

    void a() {
        a.a("System.open_store", new ah() { // from class: com.adcolony.sdk.at.1
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.a(afVar);
            }
        });
        a.a("System.save_screenshot", new ah() { // from class: com.adcolony.sdk.at.8
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.b(afVar);
            }
        });
        a.a("System.telephone", new ah() { // from class: com.adcolony.sdk.at.9
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.c(afVar);
            }
        });
        a.a("System.sms", new ah() { // from class: com.adcolony.sdk.at.10
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.d(afVar);
            }
        });
        a.a("System.vibrate", new ah() { // from class: com.adcolony.sdk.at.11
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.e(afVar);
            }
        });
        a.a("System.open_browser", new ah() { // from class: com.adcolony.sdk.at.12
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.f(afVar);
            }
        });
        a.a("System.mail", new ah() { // from class: com.adcolony.sdk.at.13
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.g(afVar);
            }
        });
        a.a("System.launch_app", new ah() { // from class: com.adcolony.sdk.at.14
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.h(afVar);
            }
        });
        a.a("System.create_calendar_event", new ah() { // from class: com.adcolony.sdk.at.15
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.i(afVar);
            }
        });
        a.a("System.check_app_presence", new ah() { // from class: com.adcolony.sdk.at.2
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.j(afVar);
            }
        });
        a.a("System.check_social_presence", new ah() { // from class: com.adcolony.sdk.at.3
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.k(afVar);
            }
        });
        a.a("System.social_post", new ah() { // from class: com.adcolony.sdk.at.4
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.l(afVar);
            }
        });
        a.a("System.make_in_app_purchase", new ah() { // from class: com.adcolony.sdk.at.5
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.n(afVar);
            }
        });
        a.a("System.close", new ah() { // from class: com.adcolony.sdk.at.6
            @Override // com.adcolony.sdk.ah
            public void a(af afVar) {
                at.this.m(afVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean m(af afVar) {
        String strB = y.b(afVar.c(), "ad_session_id");
        Activity activityC = a.c();
        if (activityC == null || !(activityC instanceof b)) {
            return false;
        }
        JSONObject jSONObjectA = y.a();
        y.a(jSONObjectA, "id", strB);
        new af("AdSession.on_request_close", ((b) activityC).f, jSONObjectA).b();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        d dVarM = a.a().m();
        String strB = y.b(jSONObjectC, "ad_session_id");
        AdColonyInterstitial adColonyInterstitial = dVarM.c().get(strB);
        az azVar = dVarM.f().get(strB);
        if ((adColonyInterstitial == null || adColonyInterstitial.getListener() == null || adColonyInterstitial.d() == null) && (azVar == null || azVar.getListener() == null || azVar.getExpandedContainer() == null)) {
            return false;
        }
        if (azVar == null) {
            new af("AdUnit.make_in_app_purchase", adColonyInterstitial.d().c()).b();
        } else {
            new af("AdUnit.make_in_app_purchase", azVar.getExpandedContainer().c()).b();
        }
        b(y.b(jSONObjectC, "ad_session_id"));
        return true;
    }

    boolean a(af afVar) {
        JSONObject jSONObjectA = y.a();
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "product_id");
        if (strB.equals("")) {
            strB = y.b(jSONObjectC, "handle");
        }
        if (aw.a(new Intent("android.intent.action.VIEW", Uri.parse(strB)))) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            a(y.b(jSONObjectC, "ad_session_id"));
            b(y.b(jSONObjectC, "ad_session_id"));
            return true;
        }
        aw.a("Unable to open.", 0);
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    boolean b(final af afVar) {
        Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        try {
            if (ActivityCompat.checkSelfPermission(activityC, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                b(y.b(afVar.c(), "ad_session_id"));
                final JSONObject jSONObjectA = y.a();
                String str = Environment.getExternalStorageDirectory().toString() + "/Pictures/AdColony_Screenshots/AdColony_Screenshot_" + System.currentTimeMillis() + ".jpg";
                View rootView = activityC.getWindow().getDecorView().getRootView();
                rootView.setDrawingCacheEnabled(true);
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                rootView.setDrawingCacheEnabled(false);
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures");
                    File file2 = new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/AdColony_Screenshots");
                    file.mkdirs();
                    file2.mkdirs();
                } catch (Exception unused) {
                }
                try {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
                        bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        MediaScannerConnection.scanFile(activityC, new String[]{str}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.adcolony.sdk.at.7
                            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
                            public void onScanCompleted(String str2, Uri uri) {
                                aw.a("Screenshot saved to Gallery!", 0);
                                y.a(jSONObjectA, "success", true);
                                afVar.a(jSONObjectA).b();
                            }
                        });
                        return true;
                    } catch (FileNotFoundException unused2) {
                        aw.a("Error saving screenshot.", 0);
                        y.a(jSONObjectA, "success", false);
                        afVar.a(jSONObjectA).b();
                        return false;
                    }
                } catch (IOException unused3) {
                    aw.a("Error saving screenshot.", 0);
                    y.a(jSONObjectA, "success", false);
                    afVar.a(jSONObjectA).b();
                    return false;
                }
            }
            aw.a("Error saving screenshot.", 0);
            JSONObject jSONObjectC = afVar.c();
            y.a(jSONObjectC, "success", false);
            afVar.a(jSONObjectC).b();
            return false;
        } catch (NoClassDefFoundError unused4) {
            aw.a("Error saving screenshot.", 0);
            JSONObject jSONObjectC2 = afVar.c();
            y.a(jSONObjectC2, "success", false);
            afVar.a(jSONObjectC2).b();
            return false;
        }
    }

    boolean c(af afVar) {
        JSONObject jSONObjectA = y.a();
        JSONObject jSONObjectC = afVar.c();
        if (aw.a(new Intent("android.intent.action.DIAL").setData(Uri.parse("tel:" + y.b(jSONObjectC, "phone_number"))))) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            a(y.b(jSONObjectC, "ad_session_id"));
            b(y.b(jSONObjectC, "ad_session_id"));
            return true;
        }
        aw.a("Failed to dial number.", 0);
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    boolean d(af afVar) {
        JSONObject jSONObjectC = afVar.c();
        JSONObject jSONObjectA = y.a();
        JSONArray jSONArrayG = y.g(jSONObjectC, "recipients");
        String str = "";
        for (int i = 0; i < jSONArrayG.length(); i++) {
            if (i != 0) {
                str = str + ";";
            }
            str = str + y.c(jSONArrayG, i);
        }
        if (aw.a(new Intent("android.intent.action.VIEW", Uri.parse("smsto:" + str)).putExtra("sms_body", y.b(jSONObjectC, TtmlNode.TAG_BODY)))) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            a(y.b(jSONObjectC, "ad_session_id"));
            b(y.b(jSONObjectC, "ad_session_id"));
            return true;
        }
        aw.a("Failed to create sms.", 0);
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    boolean e(af afVar) {
        Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        int iA = y.a(afVar.c(), "length_ms", 500);
        JSONObject jSONObjectA = y.a();
        JSONArray jSONArrayZ = a.a().n().z();
        boolean z = false;
        for (int i = 0; i < jSONArrayZ.length(); i++) {
            if (y.c(jSONArrayZ, i).equals("android.permission.VIBRATE")) {
                z = true;
            }
        }
        if (!z) {
            new aa.a().a("No vibrate permission detected.").a(aa.e);
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
        try {
            ((Vibrator) activityC.getSystemService("vibrator")).vibrate(iA);
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return true;
        } catch (Exception unused) {
            new aa.a().a("Vibrate command failed.").a(aa.e);
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
    }

    boolean f(af afVar) {
        JSONObject jSONObjectA = y.a();
        JSONObject jSONObjectC = afVar.c();
        String strB = y.b(jSONObjectC, "url");
        if (strB.startsWith("browser")) {
            strB = strB.replaceFirst("browser", "http");
        }
        if (strB.startsWith("safari")) {
            strB = strB.replaceFirst("safari", "http");
        }
        if (aw.a(new Intent("android.intent.action.VIEW", Uri.parse(strB)))) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            a(y.b(jSONObjectC, "ad_session_id"));
            b(y.b(jSONObjectC, "ad_session_id"));
            return true;
        }
        aw.a("Failed to launch browser.", 0);
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    boolean g(af afVar) {
        JSONObject jSONObjectA = y.a();
        JSONObject jSONObjectC = afVar.c();
        JSONArray jSONArrayG = y.g(jSONObjectC, "recipients");
        boolean zD = y.d(jSONObjectC, com.my.target.ai.a.da);
        String strB = y.b(jSONObjectC, "subject");
        String strB2 = y.b(jSONObjectC, TtmlNode.TAG_BODY);
        String[] strArr = new String[jSONArrayG.length()];
        for (int i = 0; i < jSONArrayG.length(); i++) {
            strArr[i] = y.c(jSONArrayG, i);
        }
        Intent intent = new Intent("android.intent.action.SEND");
        if (!zD) {
            intent.setType("plain/text");
        }
        intent.putExtra("android.intent.extra.SUBJECT", strB).putExtra("android.intent.extra.TEXT", strB2).putExtra("android.intent.extra.EMAIL", strArr);
        if (aw.a(intent)) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            a(y.b(jSONObjectC, "ad_session_id"));
            b(y.b(jSONObjectC, "ad_session_id"));
            return true;
        }
        aw.a("Failed to send email.", 0);
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    boolean h(af afVar) {
        JSONObject jSONObjectA = y.a();
        JSONObject jSONObjectC = afVar.c();
        if (y.d(jSONObjectC, "deep_link")) {
            return a(afVar);
        }
        Activity activityC = a.c();
        if (activityC == null) {
            return false;
        }
        if (aw.a(activityC.getPackageManager().getLaunchIntentForPackage(y.b(jSONObjectC, "handle")))) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            a(y.b(jSONObjectC, "ad_session_id"));
            b(y.b(jSONObjectC, "ad_session_id"));
            return true;
        }
        aw.a("Failed to launch external application.", 0);
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x019d  */
    /* JADX WARN: Code duplicated, block: B:51:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:53:0x01e7  */
    boolean i(af afVar) {
        JSONArray jSONArrayG;
        JSONArray jSONArrayG2;
        String strB;
        JSONArray jSONArrayG3;
        String strB2;
        long j;
        Intent intentPutExtra;
        JSONObject jSONObjectA = y.a();
        JSONObject jSONObjectC = afVar.c();
        JSONObject jSONObjectF = y.f(jSONObjectC, "params");
        JSONObject jSONObjectF2 = y.f(jSONObjectF, "recurrence");
        JSONArray jSONArrayB = y.b();
        JSONArray jSONArrayB2 = y.b();
        JSONArray jSONArrayB3 = y.b();
        String strB3 = y.b(jSONObjectF, "description");
        y.b(jSONObjectF, "location");
        String strB4 = y.b(jSONObjectF, "start");
        String strB5 = y.b(jSONObjectF, TtmlNode.END);
        String strB6 = y.b(jSONObjectF, "summary");
        if (jSONObjectF2 == null || jSONObjectF2.length() <= 0) {
            jSONArrayG = jSONArrayB2;
            jSONArrayG2 = jSONArrayB3;
            strB = "";
            jSONArrayG3 = jSONArrayB;
            strB2 = strB;
        } else {
            strB2 = y.b(jSONObjectF2, "expires");
            strB = y.b(jSONObjectF2, "frequency");
            jSONArrayG3 = y.g(jSONObjectF2, "daysInWeek");
            jSONArrayG = y.g(jSONObjectF2, "daysInMonth");
            jSONArrayG2 = y.g(jSONObjectF2, "daysInYear");
        }
        if (strB6.equals("")) {
            strB6 = strB3;
        }
        Date dateH = aw.h(strB4);
        Date dateH2 = aw.h(strB5);
        Date dateH3 = aw.h(strB2);
        if (dateH == null || dateH2 == null) {
            aw.a("Unable to create Calendar Event", 0);
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
        long time = dateH.getTime();
        long time2 = dateH2.getTime();
        long j2 = 0;
        long time3 = dateH3 != null ? (dateH3.getTime() - dateH.getTime()) / 1000 : 0L;
        if (strB.equals("DAILY")) {
            j = time3 / 86400;
        } else if (strB.equals("WEEKLY")) {
            j = time3 / 604800;
        } else if (strB.equals("MONTHLY")) {
            j = time3 / 2629800;
        } else {
            if (strB.equals("YEARLY")) {
                j = time3 / 31557600;
            }
            long j3 = j2;
            if (jSONObjectF2 == null && jSONObjectF2.length() > 0) {
                String str = "FREQ=" + strB + ";COUNT=" + j3;
                try {
                    if (jSONArrayG3.length() != 0) {
                        str = str + ";BYDAY=" + aw.a(jSONArrayG3);
                    }
                    if (jSONArrayG.length() != 0) {
                        str = str + ";BYMONTHDAY=" + aw.b(jSONArrayG);
                    }
                    if (jSONArrayG2.length() != 0) {
                        str = str + ";BYYEARDAY=" + aw.b(jSONArrayG2);
                    }
                } catch (JSONException unused) {
                }
                intentPutExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra("title", strB6).putExtra("description", strB3).putExtra("beginTime", time).putExtra("endTime", time2).putExtra("rrule", str);
            } else {
                intentPutExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra("title", strB6).putExtra("description", strB3).putExtra("beginTime", time).putExtra("endTime", time2);
            }
            if (aw.a(intentPutExtra)) {
                y.a(jSONObjectA, "success", true);
                afVar.a(jSONObjectA).b();
                a(y.b(jSONObjectC, "ad_session_id"));
                b(y.b(jSONObjectC, "ad_session_id"));
                return true;
            }
            aw.a("Unable to create Calendar Event.", 0);
            y.a(jSONObjectA, "success", false);
            afVar.a(jSONObjectA).b();
            return false;
        }
        j2 = j + 1;
        long j4 = j2;
        if (jSONObjectF2 == null) {
            intentPutExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra("title", strB6).putExtra("description", strB3).putExtra("beginTime", time).putExtra("endTime", time2);
        } else {
            intentPutExtra = new Intent("android.intent.action.EDIT").setType("vnd.android.cursor.item/event").putExtra("title", strB6).putExtra("description", strB3).putExtra("beginTime", time).putExtra("endTime", time2);
        }
        if (aw.a(intentPutExtra)) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            a(y.b(jSONObjectC, "ad_session_id"));
            b(y.b(jSONObjectC, "ad_session_id"));
            return true;
        }
        aw.a("Unable to create Calendar Event.", 0);
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    boolean j(af afVar) {
        JSONObject jSONObjectA = y.a();
        String strB = y.b(afVar.c(), "name");
        boolean zA = aw.a(strB);
        y.a(jSONObjectA, "success", true);
        y.a(jSONObjectA, IronSourceConstants.EVENTS_RESULT, zA);
        y.a(jSONObjectA, "name", strB);
        y.a(jSONObjectA, NotificationCompat.CATEGORY_SERVICE, strB);
        afVar.a(jSONObjectA).b();
        return true;
    }

    boolean k(af afVar) {
        return j(afVar);
    }

    boolean l(af afVar) {
        JSONObject jSONObjectA = y.a();
        JSONObject jSONObjectC = afVar.c();
        if (aw.a(new Intent("android.intent.action.SEND").setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT).putExtra("android.intent.extra.TEXT", y.b(jSONObjectC, MimeTypes.BASE_TYPE_TEXT) + " " + y.b(jSONObjectC, "url")), true)) {
            y.a(jSONObjectA, "success", true);
            afVar.a(jSONObjectA).b();
            a(y.b(jSONObjectC, "ad_session_id"));
            b(y.b(jSONObjectC, "ad_session_id"));
            return true;
        }
        aw.a("Unable to create social post.", 0);
        y.a(jSONObjectA, "success", false);
        afVar.a(jSONObjectA).b();
        return false;
    }

    void a(String str) {
        d dVarM = a.a().m();
        AdColonyInterstitial adColonyInterstitial = dVarM.c().get(str);
        if (adColonyInterstitial != null && adColonyInterstitial.getListener() != null) {
            adColonyInterstitial.getListener().onLeftApplication(adColonyInterstitial);
            return;
        }
        az azVar = dVarM.f().get(str);
        e listener = azVar != null ? azVar.getListener() : null;
        if (azVar == null || listener == null || !(listener instanceof AdColonyNativeAdViewListener)) {
            return;
        }
        ((AdColonyNativeAdViewListener) listener).onLeftApplication((AdColonyNativeAdView) azVar);
    }

    void b(String str) {
        d dVarM = a.a().m();
        AdColonyInterstitial adColonyInterstitial = dVarM.c().get(str);
        if (adColonyInterstitial != null && adColonyInterstitial.getListener() != null) {
            adColonyInterstitial.getListener().onClicked(adColonyInterstitial);
            return;
        }
        az azVar = dVarM.f().get(str);
        e listener = azVar != null ? azVar.getListener() : null;
        if (azVar == null || listener == null || !(listener instanceof AdColonyNativeAdViewListener)) {
            return;
        }
        ((AdColonyNativeAdViewListener) listener).onClicked((AdColonyNativeAdView) azVar);
    }
}
