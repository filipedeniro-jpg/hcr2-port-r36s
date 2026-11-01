package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.sdk.ISNAdView.ISNAdViewConstants;
import com.ironsource.sdk.constants.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgs implements zzaia<zzbgw> {
    private final zzpf zzfbg;
    private final Context zzlk;
    private final PowerManager zzzd;

    public zzbgs(Context context, zzpf zzpfVar) {
        this.zzlk = context;
        this.zzfbg = zzpfVar;
        this.zzzd = (PowerManager) context.getSystemService("power");
    }

    @Override // com.google.android.gms.internal.ads.zzaia
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final JSONObject zzj(zzbgw zzbgwVar) throws JSONException {
        boolean zIsScreenOn;
        JSONObject jSONObject;
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        if (zzbgwVar.zzfbz == null) {
            jSONObject = new JSONObject();
        } else {
            zzpi zzpiVar = zzbgwVar.zzfbz;
            if (this.zzfbg.zzlc() == null) {
                throw new JSONException("Active view Info cannot be null.");
            }
            boolean z = zzpiVar.zzbnv;
            JSONObject jSONObject3 = new JSONObject();
            JSONObject jSONObjectPut = jSONObject3.put("afmaVersion", this.zzfbg.zzlb()).put("activeViewJSON", this.zzfbg.zzlc()).put("timestamp", zzbgwVar.timestamp).put("adFormat", this.zzfbg.zzla()).put("hashCode", this.zzfbg.zzld());
            zzpf zzpfVar = this.zzfbg;
            JSONObject jSONObjectPut2 = jSONObjectPut.put("isMraid", false).put("isStopped", false).put("isPaused", zzbgwVar.zzfbw).put("isNative", this.zzfbg.zzle());
            if (Build.VERSION.SDK_INT >= 20) {
                zIsScreenOn = this.zzzd.isInteractive();
            } else {
                zIsScreenOn = this.zzzd.isScreenOn();
            }
            jSONObjectPut2.put("isScreenOn", zIsScreenOn).put("appMuted", com.google.android.gms.ads.internal.zzp.zzkd().zzop()).put("appVolume", com.google.android.gms.ads.internal.zzp.zzkd().zzoo()).put(Constants.RequestParameters.DEVICE_VOLUME, zzauk.zzbd(this.zzlk.getApplicationContext()));
            Rect rect = new Rect();
            Display defaultDisplay = ((WindowManager) this.zzlk.getSystemService("window")).getDefaultDisplay();
            rect.right = defaultDisplay.getWidth();
            rect.bottom = defaultDisplay.getHeight();
            jSONObject3.put("windowVisibility", zzpiVar.zzzk).put("isAttachedToWindow", z).put("viewBox", new JSONObject().put("top", zzpiVar.zzbnw.top).put("bottom", zzpiVar.zzbnw.bottom).put(TtmlNode.LEFT, zzpiVar.zzbnw.left).put(TtmlNode.RIGHT, zzpiVar.zzbnw.right)).put("adBox", new JSONObject().put("top", zzpiVar.zzbnx.top).put("bottom", zzpiVar.zzbnx.bottom).put(TtmlNode.LEFT, zzpiVar.zzbnx.left).put(TtmlNode.RIGHT, zzpiVar.zzbnx.right)).put("globalVisibleBox", new JSONObject().put("top", zzpiVar.zzbny.top).put("bottom", zzpiVar.zzbny.bottom).put(TtmlNode.LEFT, zzpiVar.zzbny.left).put(TtmlNode.RIGHT, zzpiVar.zzbny.right)).put("globalVisibleBoxVisible", zzpiVar.zzbnz).put("localVisibleBox", new JSONObject().put("top", zzpiVar.zzboa.top).put("bottom", zzpiVar.zzboa.bottom).put(TtmlNode.LEFT, zzpiVar.zzboa.left).put(TtmlNode.RIGHT, zzpiVar.zzboa.right)).put("localVisibleBoxVisible", zzpiVar.zzbob).put("hitBox", new JSONObject().put("top", zzpiVar.zzboc.top).put("bottom", zzpiVar.zzboc.bottom).put(TtmlNode.LEFT, zzpiVar.zzboc.left).put(TtmlNode.RIGHT, zzpiVar.zzboc.right)).put("screenDensity", this.zzlk.getResources().getDisplayMetrics().density);
            jSONObject3.put(ISNAdViewConstants.IS_VISIBLE_KEY, zzbgwVar.zzbnr);
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzclu)).booleanValue()) {
                JSONArray jSONArray2 = new JSONArray();
                if (zzpiVar.zzboe != null) {
                    for (Rect rect2 : zzpiVar.zzboe) {
                        jSONArray2.put(new JSONObject().put("top", rect2.top).put("bottom", rect2.bottom).put(TtmlNode.LEFT, rect2.left).put(TtmlNode.RIGHT, rect2.right));
                    }
                }
                jSONObject3.put("scrollableContainerBoxes", jSONArray2);
            }
            if (!TextUtils.isEmpty(zzbgwVar.zzfby)) {
                jSONObject3.put("doneReasonCode", "u");
            }
            jSONObject = jSONObject3;
        }
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }
}
