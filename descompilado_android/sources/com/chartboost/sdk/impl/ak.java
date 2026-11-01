package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.google.android.gms.drive.DriveFile;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class ak {
    final ai a;
    final Handler b;
    private final Executor c;
    private final ah d;

    public ak(Executor executor, ah ahVar, ai aiVar, Handler handler) {
        this.c = executor;
        this.d = ahVar;
        this.a = aiVar;
        this.b = handler;
    }

    public void a(com.chartboost.sdk.Model.c cVar, boolean z, String str, CBError.CBClickError cBClickError, aj ajVar) {
        if (cVar != null) {
            cVar.x = false;
            if (cVar.b()) {
                cVar.l = 4;
            }
        }
        if (!z) {
            if (com.chartboost.sdk.i.c != null) {
                com.chartboost.sdk.i.c.didFailToRecordClick(str, cBClickError);
            }
        } else if (cVar != null && cVar.w != null) {
            this.d.a(cVar.w);
        } else if (ajVar != null) {
            this.d.a(ajVar);
        }
    }

    public void a(final com.chartboost.sdk.Model.c cVar, final String str, final Activity activity, final aj ajVar) {
        try {
            String scheme = new URI(str).getScheme();
            if (scheme == null) {
                a(cVar, false, str, CBError.CBClickError.URI_INVALID, ajVar);
            } else if (!scheme.equals("http") && !scheme.equals("https")) {
                a(cVar, str, (Context) activity, ajVar);
            } else {
                this.c.execute(new Runnable() { // from class: com.chartboost.sdk.impl.ak.1
                    /* JADX WARN: Code duplicated, block: B:24:0x004b A[Catch: Exception -> 0x0053, TRY_ENTER, TryCatch #4 {Exception -> 0x0053, blocks: (B:2:0x0000, B:11:0x0031, B:24:0x004b, B:25:0x004e, B:26:0x004f), top: B:36:0x0000 }] */
                    @Override // java.lang.Runnable
                    public void run() throws Throwable {
                        HttpURLConnection httpURLConnection;
                        Throwable th;
                        Exception e;
                        try {
                            String str2 = str;
                            if (ak.this.a.b()) {
                                try {
                                    try {
                                        httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                                        try {
                                            httpURLConnection.setInstanceFollowRedirects(false);
                                            httpURLConnection.setConnectTimeout(10000);
                                            httpURLConnection.setReadTimeout(10000);
                                            String headerField = httpURLConnection.getHeaderField("Location");
                                            if (headerField != null) {
                                                str2 = headerField;
                                            }
                                            if (httpURLConnection != null) {
                                                httpURLConnection.disconnect();
                                            }
                                        } catch (Exception e2) {
                                            e = e2;
                                            CBLogging.a("CBURLOpener", "Exception raised while opening a HTTP Conection", e);
                                            if (httpURLConnection != null) {
                                            }
                                            a(str2);
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                        }
                                        throw th;
                                    }
                                } catch (Exception e3) {
                                    httpURLConnection = null;
                                    e = e3;
                                } catch (Throwable th3) {
                                    th = th3;
                                    httpURLConnection = null;
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    throw th;
                                }
                            }
                            a(str2);
                        } catch (Exception e4) {
                            com.chartboost.sdk.Tracking.a.a(ak.class, "open followTask", e4);
                        }
                    }

                    private void a(final String str2) {
                        Runnable runnable = new Runnable() { // from class: com.chartboost.sdk.impl.ak.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    ak.this.a(cVar, str2, (Context) activity, ajVar);
                                } catch (Exception e) {
                                    com.chartboost.sdk.Tracking.a.a(ak.class, "open openOnUiThread Runnable.run", e);
                                }
                            }
                        };
                        Activity activity2 = activity;
                        if (activity2 != null) {
                            activity2.runOnUiThread(runnable);
                        } else {
                            ak.this.b.post(runnable);
                        }
                    }
                });
            }
        } catch (URISyntaxException unused) {
            a(cVar, false, str, CBError.CBClickError.URI_INVALID, ajVar);
        }
    }

    void a(com.chartboost.sdk.Model.c cVar, String str, Context context, aj ajVar) {
        if (cVar != null && cVar.b()) {
            cVar.l = 5;
        }
        if (context == null) {
            context = com.chartboost.sdk.i.m;
        }
        if (context == null) {
            a(cVar, false, str, CBError.CBClickError.NO_HOST_ACTIVITY, ajVar);
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!(context instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        } catch (Exception unused) {
            if (str.startsWith("market://")) {
                try {
                    str = "http://market.android.com/" + str.substring(9);
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    if (!(context instanceof Activity)) {
                        intent2.addFlags(DriveFile.MODE_READ_ONLY);
                    }
                    intent2.setData(Uri.parse(str));
                    context.startActivity(intent2);
                } catch (Exception e) {
                    CBLogging.a("CBURLOpener", "Exception raised openeing an inavld playstore URL", e);
                    a(cVar, false, str, CBError.CBClickError.URI_UNRECOGNIZED, ajVar);
                    return;
                }
            } else {
                a(cVar, false, str, CBError.CBClickError.URI_UNRECOGNIZED, ajVar);
            }
        }
        a(cVar, true, str, null, ajVar);
    }

    public boolean a(String str) {
        try {
            Context context = com.chartboost.sdk.i.m;
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!(context instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            intent.setData(Uri.parse(str));
            return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
        } catch (Exception e) {
            CBLogging.a("CBURLOpener", "Cannot open URL", e);
            com.chartboost.sdk.Tracking.a.a(ak.class, "canOpenURL", e);
            return false;
        }
    }

    public void a(com.chartboost.sdk.Model.c cVar, String str, aj ajVar) {
        a(cVar, str, cVar != null ? cVar.g.a() : null, ajVar);
    }
}
