package com.chartboost.sdk.InPlay;

import android.graphics.Bitmap;
import android.os.Handler;
import com.applovin.mediation.AppLovinNativeAdapter;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Tracking.a;
import com.chartboost.sdk.b;
import com.chartboost.sdk.h;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.ak;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.bh;
import com.chartboost.sdk.impl.c;
import com.chartboost.sdk.impl.c.a;
import com.chartboost.sdk.impl.e;
import com.chartboost.sdk.impl.e.a;
import com.chartboost.sdk.impl.g;
import com.chartboost.sdk.impl.s;
import com.facebook.internal.AnalyticsEvents;
import java.io.File;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class CBInPlay {
    private final e a;
    public final String appName;
    private final ah b;
    private final ap c;
    private final a d;
    private final ak e;
    private final com.chartboost.sdk.Model.a f;
    private Bitmap g;
    public final File largeAppIconFile;
    public final String largeAppIconUrl;
    public final String location;

    private CBInPlay(e eVar, ah ahVar, ap apVar, a aVar, ak akVar, com.chartboost.sdk.Model.a aVar2, String str, File file, String str2) {
        this.a = eVar;
        this.b = ahVar;
        this.c = apVar;
        this.d = aVar;
        this.e = akVar;
        this.f = aVar2;
        this.appName = aVar2.q;
        this.largeAppIconUrl = str;
        this.largeAppIconFile = file;
        this.location = str2;
    }

    public void show() {
        aj ajVar = new aj("/inplay/show", this.c, this.d, 2, new g(this.a, this.location));
        ajVar.j = 1;
        ajVar.a("inplay-dictionary", this.f.a);
        ajVar.a("location", this.location);
        this.b.a(ajVar);
    }

    public void click() {
        String str = this.f.j;
        String str2 = this.f.i;
        String str3 = (str2.isEmpty() || !this.e.a(str2)) ? str : str2;
        aj ajVar = new aj("/api/click", this.c, this.d, 2, null);
        ajVar.a("location", this.location);
        ajVar.a("to", this.f.m);
        ajVar.a("cgn", this.f.g);
        ajVar.a("creative", this.f.h);
        ajVar.a(AppLovinNativeAdapter.KEY_EXTRA_AD_ID, this.f.f);
        ajVar.a("type", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
        if (str3 != null && !str3.isEmpty()) {
            this.e.a(null, str3, ajVar);
        } else {
            this.e.a(null, false, str3, CBError.CBClickError.URI_INVALID, ajVar);
        }
    }

    public String getLocation() {
        return this.location;
    }

    public Bitmap getAppIcon() throws Exception {
        if (this.g == null) {
            try {
                byte[] bArrB = bh.b(this.largeAppIconFile);
                if (bArrB != null) {
                    this.g = s.a().a(bArrB);
                }
                if (this.g == null) {
                    CBLogging.b("CBInPlay", "Error decoding inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                    if (!this.largeAppIconFile.delete()) {
                        CBLogging.b("CBInPlay", "Unable to delete corrupt inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                    }
                    throw new Exception("decodeByteArrayToBitmap returned null");
                }
            } catch (OutOfMemoryError e) {
                CBLogging.b("CBInPlay", "Out of memory decoding inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                throw new Exception(e);
            }
        }
        return this.g;
    }

    public String getAppName() {
        return this.appName;
    }

    public static void cacheInPlay(String str) {
        h hVarA = h.a();
        if (hVarA == null || !b.a()) {
            return;
        }
        if (s.a().a(str)) {
            CBLogging.b("CBInPlay", "cacheInPlay location cannot be empty");
            Handler handler = hVarA.p;
            c cVar = hVarA.e;
            cVar.getClass();
            handler.post(cVar.new a(4, str, CBError.CBImpressionError.INVALID_LOCATION));
            return;
        }
        com.chartboost.sdk.Model.e eVar = hVarA.m.get();
        if ((eVar.y && eVar.z) || (eVar.e && eVar.f)) {
            e eVar2 = hVarA.d;
            eVar2.getClass();
            hVarA.a.execute(eVar2.new a(3, str, null, null));
            return;
        }
        Handler handler2 = hVarA.p;
        c cVar2 = hVarA.e;
        cVar2.getClass();
        handler2.post(cVar2.new a(4, str, CBError.CBImpressionError.END_POINT_DISABLED));
    }

    public static boolean hasInPlay(String str) {
        h hVarA = h.a();
        if (hVarA == null || !b.a()) {
            return false;
        }
        if (!s.a().a(str)) {
            return hVarA.d.a(str) != null;
        }
        CBLogging.b("CBInPlay", "hasInPlay location cannot be empty");
        Handler handler = hVarA.p;
        c cVar = hVarA.e;
        cVar.getClass();
        handler.post(cVar.new a(4, str, CBError.CBImpressionError.INVALID_LOCATION));
        return false;
    }

    public static CBInPlay getInPlay(String str) {
        h hVarA = h.a();
        CBInPlay cBInPlay = null;
        if (hVarA != null && b.a()) {
            if (s.a().a(str)) {
                CBLogging.b("CBInPlay", "Inplay location cannot be empty");
                Handler handler = hVarA.p;
                c cVar = hVarA.e;
                cVar.getClass();
                handler.post(cVar.new a(4, str, CBError.CBImpressionError.INVALID_LOCATION));
                return null;
            }
            com.chartboost.sdk.Model.e eVar = hVarA.m.get();
            CBError.CBImpressionError cBImpressionError = CBError.CBImpressionError.NO_AD_FOUND;
            if ((eVar.y && eVar.z) || (eVar.e && eVar.f)) {
                com.chartboost.sdk.Model.a aVarA = hVarA.d.a(str);
                if (aVarA != null) {
                    com.chartboost.sdk.Model.b bVar = aVarA.c.get("lg");
                    if (bVar != null) {
                        File fileA = bVar.a(hVarA.d.b.d().a);
                        if (!fileA.exists()) {
                            cBImpressionError = CBError.CBImpressionError.ASSET_MISSING;
                        } else {
                            cBInPlay = new CBInPlay(hVarA.d, hVarA.h, hVarA.j, hVarA.o, hVarA.r, aVarA, bVar.c, fileA, str);
                        }
                    }
                    Executor executor = hVarA.a;
                    e eVar2 = hVarA.d;
                    eVar2.getClass();
                    executor.execute(eVar2.new a(8, str, null, null));
                }
                if (cBInPlay == null) {
                    Executor executor2 = hVarA.a;
                    e eVar3 = hVarA.d;
                    eVar3.getClass();
                    executor2.execute(eVar3.new a(3, str, null, null));
                }
            }
            if (cBInPlay == null) {
                Handler handler2 = hVarA.p;
                c cVar2 = hVarA.e;
                cVar2.getClass();
                handler2.post(cVar2.new a(4, str, cBImpressionError));
            }
        }
        return cBInPlay;
    }
}
