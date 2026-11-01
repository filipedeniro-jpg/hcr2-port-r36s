package com.google.android.gms.ads.internal;

import android.os.AsyncTask;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzdf;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzn extends AsyncTask<Void, Void, String> {
    private final /* synthetic */ zzj zzblp;

    private zzn(zzj zzjVar) {
        this.zzblp = zzjVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final String doInBackground(Void... voidArr) {
        try {
            this.zzblp.zzbln = (zzdf) this.zzblp.zzblj.get(((Long) zzuo.zzoj().zzd(zzyt.zzcox)).longValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzawo.zzd("", e);
        }
        return this.zzblp.zzjs();
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(String str) {
        String str2 = str;
        if (this.zzblp.zzbll == null || str2 == null) {
            return;
        }
        this.zzblp.zzbll.loadUrl(str2);
    }

    /* synthetic */ zzn(zzj zzjVar, zzm zzmVar) {
        this(zzjVar);
    }
}
