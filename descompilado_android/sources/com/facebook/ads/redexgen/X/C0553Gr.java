package com.facebook.ads.redexgen.X;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.appsflyer.AppsFlyerLib;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gr, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0553Gr {
    private C0553Gr() {
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x006c -> B:22:0x0077). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x006e -> B:22:0x0077). Please report as a decompilation issue!!! */
    public static C0552Gq B(ContentResolver contentResolver) {
        C0552Gq c0552Gq;
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = contentResolver.query(Uri.parse(AppsFlyerLib.ATTRIBUTION_ID_CONTENT_URI), new String[]{AppsFlyerLib.ATTRIBUTION_ID_COLUMN_NAME, "androidid", "limit_tracking"}, null, null, null);
                if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                    c0552Gq = new C0552Gq(null, null, false);
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } else {
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex(AppsFlyerLib.ATTRIBUTION_ID_COLUMN_NAME));
                    String advertisingId = cursorQuery.getString(cursorQuery.getColumnIndex("androidid"));
                    String attributionId = cursorQuery.getString(cursorQuery.getColumnIndex("limit_tracking"));
                    Boolean limitTrackingEnabled = Boolean.valueOf(attributionId);
                    c0552Gq = new C0552Gq(string, advertisingId, limitTrackingEnabled.booleanValue());
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Exception e) {
                c0552Gq = new C0552Gq(null, null, false);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
            return c0552Gq;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }
}
