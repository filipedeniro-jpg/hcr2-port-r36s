package com.chartboost.sdk.impl;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.chartboost.sdk.Libraries.CBLogging;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class bg implements Runnable {
    private final bd a;
    private final bf b;
    private final int c;
    private final JSONObject d;
    private final String e;

    bg(bd bdVar, bf bfVar, int i, String str, JSONObject jSONObject) {
        this.a = bdVar;
        this.b = bfVar;
        this.c = i;
        this.e = str;
        this.d = jSONObject;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        try {
            switch (this.c) {
                case 0:
                    this.b.b((JSONObject) null);
                    return;
                case 1:
                    this.b.h();
                    return;
                case 2:
                    try {
                        float f = (float) this.d.getDouble("duration");
                        StringBuilder sb = new StringBuilder();
                        sb.append("######### JS->Native Video current player duration");
                        float f2 = f * 1000.0f;
                        sb.append(f2);
                        CBLogging.a("NativeBridgeCommand", sb.toString());
                        this.b.a(f2);
                        return;
                    } catch (Exception unused) {
                        this.b.e("Parsing exception unknown field for current player duration");
                        CBLogging.b("NativeBridgeCommand", "Cannot find duration parameter for the video");
                        return;
                    }
                case 3:
                    try {
                        String string = this.d.getString("message");
                        Log.d(be.class.getName(), "JS->Native Debug message: " + string);
                        this.b.c(string);
                        return;
                    } catch (Exception unused2) {
                        CBLogging.b("NativeBridgeCommand", "Exception occured while parsing the message for webview debug track event");
                        this.b.c("Exception occured while parsing the message for webview debug track event");
                        return;
                    }
                case 4:
                    try {
                        String string2 = this.d.getString("message");
                        Log.d(be.class.getName(), "JS->Native Error message: " + string2);
                        this.b.d(string2);
                        return;
                    } catch (Exception unused3) {
                        CBLogging.b("NativeBridgeCommand", "Error message is empty");
                        this.b.d("");
                        return;
                    }
                case 5:
                    try {
                        String string3 = this.d.getString("url");
                        if (!string3.startsWith("http://") && !string3.startsWith("https://")) {
                            string3 = "http://" + string3;
                        }
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(string3));
                        bf.b bVarE = this.b.e();
                        if (bVarE == null || (context = bVarE.getContext()) == null || intent.resolveActivity(context.getPackageManager()) == null) {
                            return;
                        }
                        context.startActivity(intent);
                        CBLogging.a(be.class.getName(), "JS->Native Track MRAID openUrl: " + string3);
                        return;
                    } catch (ActivityNotFoundException e) {
                        com.chartboost.sdk.Tracking.a.a(getClass(), "ActivityNotFoundException occured when opening a url in a browser", e);
                        CBLogging.b("NativeBridgeCommand", "ActivityNotFoundException occured when opening a url in a browser");
                        return;
                    } catch (Exception e2) {
                        com.chartboost.sdk.Tracking.a.a(getClass(), "Exception while opening a browser view with MRAID url", e2);
                        CBLogging.b("NativeBridgeCommand", "Exception while opening a browser view with MRAID url");
                        return;
                    }
                case 6:
                    this.b.z();
                    return;
                case 7:
                    try {
                        float f3 = (float) this.d.getDouble("duration");
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("######### JS->Native Video total player duration");
                        float f4 = f3 * 1000.0f;
                        sb2.append(f4);
                        CBLogging.a("NativeBridgeCommand", sb2.toString());
                        this.b.b(f4);
                        return;
                    } catch (Exception unused4) {
                        this.b.e("Parsing exception unknown field for total player duration");
                        CBLogging.b("NativeBridgeCommand", "Cannot find duration parameter for the video");
                        return;
                    }
                case 8:
                    try {
                        String string4 = this.d.getString("event");
                        this.b.b(string4);
                        Log.d(be.class.getName(), "JS->Native Track VAST event message: " + string4);
                        return;
                    } catch (Exception unused5) {
                        CBLogging.b("NativeBridgeCommand", "Exception occured while parsing the message for webview tracking VAST events");
                        return;
                    }
                case 9:
                    this.a.onHideCustomView();
                    this.b.b(1);
                    this.b.w();
                    return;
                case 10:
                    try {
                        String string5 = this.d.getString("name");
                        if (!s.a().a(string5)) {
                            this.b.m = string5;
                        }
                        break;
                    } catch (Exception unused6) {
                        CBLogging.b("NativeBridgeCommand", "Cannot find video file name");
                        this.b.e("Parsing exception unknown field for video pause");
                    }
                    this.b.b(3);
                    return;
                case 11:
                    try {
                        String string6 = this.d.getString("name");
                        if (!s.a().a(string6)) {
                            this.b.m = string6;
                        }
                        break;
                    } catch (Exception unused7) {
                        CBLogging.b("NativeBridgeCommand", "Cannot find video file name");
                        this.b.e("Parsing exception unknown field for video play");
                    }
                    this.b.b(2);
                    return;
                case 12:
                    try {
                        String string7 = this.d.getString("name");
                        if (!s.a().a(string7)) {
                            this.b.m = string7;
                        }
                        this.b.x();
                        return;
                    } catch (Exception unused8) {
                        CBLogging.b("NativeBridgeCommand", "Cannot find video file name");
                        this.b.e("Parsing exception unknown field for video replay");
                        return;
                    }
                case 13:
                    try {
                        String string8 = this.d.getString("message");
                        Log.d(be.class.getName(), "JS->Native Warning message: " + string8);
                        this.b.e(string8);
                        return;
                    } catch (Exception unused9) {
                        CBLogging.b("NativeBridgeCommand", "Warning message is empty");
                        this.b.e("");
                        return;
                    }
                case 14:
                    try {
                        this.b.c(this.d);
                        return;
                    } catch (Exception unused10) {
                        CBLogging.b("NativeBridgeCommand", "Invalid set orientation command");
                        return;
                    }
                default:
                    return;
            }
        } catch (Exception e3) {
            com.chartboost.sdk.Tracking.a.a(getClass(), "run(" + this.e + ")", e3);
        }
        com.chartboost.sdk.Tracking.a.a(getClass(), "run(" + this.e + ")", e3);
    }
}
