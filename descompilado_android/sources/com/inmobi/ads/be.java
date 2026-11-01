package com.inmobi.ads;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeVideoAsset.java */
/* JADX INFO: loaded from: classes2.dex */
public final class be extends ak {
    boolean A;
    boolean B;
    boolean C;
    boolean D;
    int E;
    int F;
    public Map<String, Object> G;
    private boolean H;
    List<ak> z;

    /* JADX INFO: compiled from: NativeVideoAsset.java */
    static class a extends al {
        public a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @Nullable ba baVar) {
            super(i, i2, i3, i4, i5, i6, i7, i8, "none", "straight", "#ff000000", "#00000000", baVar);
        }
    }

    public be(String str, String str2, al alVar, by byVar, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, List<NativeTracker> list, JSONObject jSONObject, boolean z6) {
        super(str, str2, ShareConstants.VIDEO_URL, alVar);
        this.e = byVar;
        this.i = 2;
        this.A = z;
        this.B = z2;
        this.C = z3;
        this.D = z4;
        this.z = new ArrayList();
        this.H = z6;
        if (byVar != null) {
            this.r = byVar.a();
            List<NativeTracker> listD = byVar.d();
            Map<String, String> map = null;
            if (list != null) {
                for (NativeTracker nativeTracker : list) {
                    if (NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS != nativeTracker.b) {
                        listD.add(nativeTracker);
                    } else {
                        map = nativeTracker.c;
                        if (!TextUtils.isEmpty(nativeTracker.a)) {
                            listD.add(nativeTracker);
                        }
                    }
                }
            }
            for (NativeTracker nativeTracker2 : listD) {
                if (NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS == nativeTracker2.b) {
                    nativeTracker2.c = map;
                }
            }
            if (!listD.isEmpty()) {
                a(listD);
            }
        }
        if (jSONObject != null) {
            this.f = jSONObject;
        }
        this.v.put("placementType", AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE);
        this.v.put("lastVisibleTimestamp", Integer.MIN_VALUE);
        this.v.put("visible", false);
        this.v.put("seekPosition", 0);
        this.v.put("didStartPlaying", false);
        this.v.put("didPause", false);
        this.v.put("didCompleteQ1", false);
        this.v.put("didCompleteQ2", false);
        this.v.put("didCompleteQ3", false);
        this.v.put("didCompleteQ4", false);
        this.v.put("didRequestFullScreen", false);
        this.v.put("isFullScreen", false);
        this.v.put("didImpressionFire", false);
        this.v.put("mapViewabilityParams", new HashMap());
        this.v.put("didSignalVideoCompleted", false);
        this.v.put("shouldAutoPlay", Boolean.valueOf(z5));
        this.v.put("lastMediaVolume", 0);
        this.v.put("currentMediaVolume", 0);
        this.v.put("didQ4Fire", false);
    }

    final boolean a() {
        if (this.H) {
            return this.A && !com.inmobi.commons.a.a.d();
        }
        return this.A;
    }

    public final void a(be beVar) {
        this.v.putAll(beVar.v);
        this.G.putAll(beVar.G);
        this.u = beVar.u;
    }

    public final by b() {
        if (this.e == null) {
            return null;
        }
        return (by) this.e;
    }
}
