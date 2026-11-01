package com.google.android.gms.internal.ads;

import com.my.target.i;
import com.my.target.v;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzamp {
    private final zzbbc zzcyx;
    private final String zzdga;

    public zzamp(zzbbc zzbbcVar) {
        this(zzbbcVar, "");
    }

    public zzamp(zzbbc zzbbcVar, String str) {
        this.zzcyx = zzbbcVar;
        this.zzdga = str;
    }

    public final void zzdn(String str) {
        try {
            JSONObject jSONObjectPut = new JSONObject().put("message", str).put("action", this.zzdga);
            if (this.zzcyx != null) {
                this.zzcyx.zza(v.aC, jSONObjectPut);
            }
        } catch (JSONException e) {
            zzatm.zzc("Error occurred while dispatching error event.", e);
        }
    }

    public final void zzdo(String str) {
        try {
            this.zzcyx.zza("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (JSONException e) {
            zzatm.zzc("Error occurred while dispatching ready Event.", e);
        }
    }

    public final void zza(int i, int i2, int i3, int i4) {
        try {
            this.zzcyx.zza("onSizeChanged", new JSONObject().put("x", i).put("y", i2).put("width", i3).put("height", i4));
        } catch (JSONException e) {
            zzatm.zzc("Error occurred while dispatching size change.", e);
        }
    }

    public final void zzb(int i, int i2, int i3, int i4) {
        try {
            this.zzcyx.zza("onDefaultPositionReceived", new JSONObject().put("x", i).put("y", i2).put("width", i3).put("height", i4));
        } catch (JSONException e) {
            zzatm.zzc("Error occurred while dispatching default position.", e);
        }
    }

    public final void zzdp(String str) {
        try {
            this.zzcyx.zza("onStateChanged", new JSONObject().put("state", str));
        } catch (JSONException e) {
            zzatm.zzc("Error occurred while dispatching state change.", e);
        }
    }

    public final void zza(int i, int i2, int i3, int i4, float f, int i5) {
        try {
            this.zzcyx.zza("onScreenInfoChanged", new JSONObject().put("width", i).put("height", i2).put("maxSizeWidth", i3).put("maxSizeHeight", i4).put(i.O, f).put("rotation", i5));
        } catch (JSONException e) {
            zzatm.zzc("Error occurred while obtaining screen information.", e);
        }
    }
}
