package com.inmobi.ads.b;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.inmobi.ads.InMobiAdRequest;
import com.inmobi.ads.d;
import com.inmobi.ads.h;
import com.inmobi.ads.i;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;

/* JADX INFO: compiled from: GMAManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    @NonNull
    public i a;

    @NonNull
    public String b;
    public long c;

    @Nullable
    public b d;

    public a(@NonNull i iVar, @NonNull String str) {
        this.a = iVar;
        this.b = str;
    }

    public final byte[] a() throws com.inmobi.ads.a.b {
        List<com.inmobi.ads.a> listC;
        this.a.d("AdCacheImpressionRequested");
        this.a.i();
        h.a();
        h hVarI = this.a.i();
        long j = this.a.d;
        this.a.b();
        String strC = this.a.c();
        InMobiAdRequest.MonetizationContext monetizationContextL = this.a.l();
        String str = this.b;
        h.c();
        if (hVarI.d.e) {
            listC = hVarI.b.d(j, strC, monetizationContextL, str);
        } else {
            listC = hVarI.b.c(j, strC, monetizationContextL, str);
        }
        com.inmobi.ads.a aVar = listC.size() == 0 ? null : listC.get(0);
        this.d = new b(this.a.t(), aVar == null ? null : Collections.singletonList(aVar));
        if (aVar != null) {
            HashMap map = new HashMap();
            map.put("impId", aVar.g);
            this.a.c("AdCacheImpressionOffered", map);
        }
        if (aVar != null) {
            h hVarI2 = this.a.i();
            String str2 = aVar.g;
            d dVar = hVarI2.b;
            d.b(str2);
        }
        this.a.i().a(this.a.u());
        this.c = System.currentTimeMillis();
        try {
            return this.d.a();
        } catch (JSONException unused) {
            return null;
        }
    }
}
