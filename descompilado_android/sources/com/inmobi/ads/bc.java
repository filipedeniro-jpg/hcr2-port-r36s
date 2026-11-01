package com.inmobi.ads;

import android.content.ContentValues;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import net.nend.android.NendAdNativeMediaView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeVideoAd.java */
/* JADX INFO: loaded from: classes2.dex */
class bc extends a {
    private static final String o = "bc";
    final String l;
    final String m;
    final String n;
    private final String p;
    private final String q;

    bc(JSONObject jSONObject, String str, long j, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, InMobiAdRequest.MonetizationContext monetizationContext, boolean z, long j2, float f, @Nullable String str11) {
        super(jSONObject, str, j, str2, str3, str4, str5, monetizationContext, z, j2, f, str11);
        this.l = str6;
        this.m = str7;
        this.n = str8;
        this.p = str9;
        this.q = str10;
    }

    bc(ContentValues contentValues) {
        super(contentValues);
        this.l = contentValues.getAsString("video_url");
        this.m = contentValues.getAsString("video_track_duration");
        this.n = contentValues.getAsString(NendAdNativeMediaView.RESULT_CLICK_URL);
        this.p = contentValues.getAsString("video_trackers");
        this.q = contentValues.getAsString("companion_ads");
    }

    @Override // com.inmobi.ads.a
    public final ContentValues a() {
        ContentValues contentValuesA = super.a();
        contentValuesA.put("video_url", this.l);
        contentValuesA.put("video_track_duration", this.m);
        contentValuesA.put(NendAdNativeMediaView.RESULT_CLICK_URL, this.n);
        contentValuesA.put("video_trackers", this.p);
        contentValuesA.put("companion_ads", this.q);
        return contentValuesA;
    }

    @NonNull
    public final List<NativeTracker> h() {
        ArrayList arrayList = new ArrayList();
        String str = this.p;
        if (str != null && str.length() != 0) {
            try {
                JSONArray jSONArray = new JSONArray(this.p);
                if (jSONArray.length() == 0) {
                    return arrayList;
                }
                for (int i = 0; i < jSONArray.length(); i++) {
                    NativeTracker nativeTrackerA = NativeTracker.a(new JSONObject(jSONArray.getString(i)));
                    if (nativeTrackerA != null) {
                        arrayList.add(nativeTrackerA);
                    }
                }
                return arrayList;
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
        return arrayList;
    }

    @NonNull
    final List<bt> i() {
        ArrayList arrayList = new ArrayList();
        String str = this.q;
        if (str != null && str.length() != 0) {
            try {
                JSONArray jSONArray = new JSONArray(this.q);
                if (jSONArray.length() == 0) {
                    return arrayList;
                }
                for (int i = 0; i < jSONArray.length(); i++) {
                    bt btVarA = bt.a(new JSONObject(jSONArray.getString(i)));
                    if (btVarA != null) {
                        arrayList.add(btVarA);
                    }
                }
                return arrayList;
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
        return arrayList;
    }
}
