package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.os.AsyncTask;

/* JADX INFO: loaded from: assets/audience_network.dex */
public class HV<T> extends AsyncTask<Void, Void, T> {
    private final Context B;
    private final HO<T> C;
    private final HS<T> D;
    private HY E;

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HO != com.facebook.ads.internal.database.AdDatabaseCallback<T> */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HS != com.facebook.ads.internal.database.AdDatabaseQuery<T> */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HV != com.facebook.ads.internal.database.AdDatabaseHelper$DatabaseQueryAsyncTask<T> */
    public HV(Context applicationContext, HS<T> hs, HO<T> ho) {
        this.D = hs;
        this.C = ho;
        this.B = applicationContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HV != com.facebook.ads.internal.database.AdDatabaseHelper$DatabaseQueryAsyncTask<T> */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final T doInBackground(Void... params) {
        T tMo254B = null;
        try {
            tMo254B = this.D.mo254B();
            this.E = this.D.A();
            return tMo254B;
        } catch (Exception e) {
            C0585Hx.D(this.B, "database", C0584Hw.r, new C0583Hv(e));
            this.E = HY.UNKNOWN;
            return tMo254B;
        }
    }

    /* JADX WARN: Generic types in debug info not equals: com.facebook.ads.redexgen.X.HV != com.facebook.ads.internal.database.AdDatabaseHelper$DatabaseQueryAsyncTask<T> */
    @Override // android.os.AsyncTask
    public final void onPostExecute(T t) {
        if (this.E == null) {
            this.C.C(t);
        } else {
            this.C.A(this.E.A(), this.E.B());
        }
        this.C.B();
    }
}
