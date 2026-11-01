package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class HU extends HT<String> {
    public final /* synthetic */ HW B;
    public final /* synthetic */ Map C;
    public final /* synthetic */ int D;
    public final /* synthetic */ String E;
    public final /* synthetic */ double F;
    public final /* synthetic */ double G;
    public final /* synthetic */ String H;
    public final /* synthetic */ String I;

    public HU(HW this$0, String str, int i, String str2, double d, double d2, String str3, Map map) {
        this.B = this$0;
        this.H = str;
        this.D = i;
        this.I = str2;
        this.G = d;
        this.F = d2;
        this.E = str3;
        this.C = map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // com.facebook.ads.redexgen.X.HS
    @Nullable
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final String mo254B() {
        String strL;
        HU hu = this;
        if (TextUtils.isEmpty(hu.H)) {
            return null;
        }
        HW.I.lock();
        SQLiteDatabase sQLiteDatabaseH = null;
        try {
            try {
                sQLiteDatabaseH = hu.B.H();
                sQLiteDatabaseH.beginTransaction();
                strL = hu.B.D.L(hu.B.E.J(hu.H), hu.D, hu.I, hu.G, hu.F, hu.E, hu.C);
                sQLiteDatabaseH.setTransactionSuccessful();
                ?? r1 = hu;
                if (sQLiteDatabaseH != null && sQLiteDatabaseH.isOpen()) {
                    try {
                        r1 = hu;
                        r1 = hu;
                        if (sQLiteDatabaseH.inTransaction()) {
                            sQLiteDatabaseH.endTransaction();
                            r1 = hu;
                        }
                    } catch (Exception e) {
                        Context context = hu.B.B;
                        int i = C0584Hw.u;
                        C0585Hx.D(context, "database", i, new C0583Hv(e));
                        r1 = i;
                    }
                }
                r1 = hu;
                HW.I.unlock();
                hu = r1;
            } catch (Exception e2) {
                hu.C(HY.DATABASE_INSERT);
                C0585Hx.D(hu.B.B, "database", C0584Hw.t, new C0583Hv(e2));
                ?? r2 = hu;
                if (sQLiteDatabaseH != null && sQLiteDatabaseH.isOpen()) {
                    try {
                        r2 = hu;
                        r2 = hu;
                        if (sQLiteDatabaseH.inTransaction()) {
                            sQLiteDatabaseH.endTransaction();
                            r2 = hu;
                        }
                    } catch (Exception e3) {
                        Context context2 = hu.B.B;
                        int i2 = C0584Hw.u;
                        C0585Hx.D(context2, "database", i2, new C0583Hv(e3));
                        r2 = i2;
                    }
                }
                r2 = hu;
                HW.I.unlock();
                strL = null;
                hu = r2;
            }
            return strL;
        } catch (Throwable th) {
            if (sQLiteDatabaseH != null && sQLiteDatabaseH.isOpen()) {
                try {
                    if (sQLiteDatabaseH.inTransaction()) {
                        sQLiteDatabaseH.endTransaction();
                    }
                } catch (Exception e4) {
                    C0585Hx.D(hu.B.B, "database", C0584Hw.u, new C0583Hv(e4));
                }
            }
            HW.I.unlock();
            throw th;
        }
    }
}
