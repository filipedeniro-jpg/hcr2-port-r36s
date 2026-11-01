package com.facebook.ads.redexgen.X;

import android.os.AsyncTask;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LO extends AsyncTask<LP, Void, LU> implements LJ {
    private static Executor E = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    private AbstractC0650Kl B;
    private LI C;
    private Exception D;

    public LO(LI httpClient, AbstractC0650Kl callback) {
        this.C = httpClient;
        this.B = callback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final LU doInBackground(LP... params) {
        if (params != null) {
            try {
                if (params.length > 0) {
                    return this.C.L(params[0]);
                }
            } catch (Exception e) {
                this.D = e;
                cancel(true);
                return null;
            }
        }
        throw new IllegalArgumentException("DoHttpRequestTask takes exactly one argument of type HttpRequest");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final void onPostExecute(LU result) {
        this.B.A(result);
    }

    @Override // com.facebook.ads.redexgen.X.LJ
    public final void nB(LP httpRequest) {
        super.executeOnExecutor(E, httpRequest);
    }

    @Override // android.os.AsyncTask
    public final void onCancelled() {
        this.B.B(this.D);
    }
}
