package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zze implements zzs {
    private final Map<String, List<zzq<?>>> zzn = new HashMap();
    private final zzc zzo;

    zze(zzc zzcVar) {
        this.zzo = zzcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzs
    public final void zza(zzq<?> zzqVar, zzz<?> zzzVar) {
        List<zzq<?>> listRemove;
        if (zzzVar.zzbh == null || zzzVar.zzbh.isExpired()) {
            zza(zzqVar);
            return;
        }
        String strZzd = zzqVar.zzd();
        synchronized (this) {
            listRemove = this.zzn.remove(strZzd);
        }
        if (listRemove != null) {
            if (zzag.DEBUG) {
                zzag.v("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(listRemove.size()), strZzd);
            }
            Iterator<zzq<?>> it = listRemove.iterator();
            while (it.hasNext()) {
                this.zzo.zzd.zzb(it.next(), zzzVar);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzs
    public final synchronized void zza(zzq<?> zzqVar) {
        try {
            String strZzd = zzqVar.zzd();
            List<zzq<?>> listRemove = this.zzn.remove(strZzd);
            if (listRemove != null && !listRemove.isEmpty()) {
                if (zzag.DEBUG) {
                    zzag.v("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(listRemove.size()), strZzd);
                }
                zzq<?> zzqVarRemove = listRemove.remove(0);
                this.zzn.put(strZzd, listRemove);
                zzqVarRemove.zza((zzs) this);
                try {
                    this.zzo.zzb.put(zzqVarRemove);
                } catch (InterruptedException e) {
                    zzag.e("Couldn't add request to queue. %s", e.toString());
                    Thread.currentThread().interrupt();
                    this.zzo.quit();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized boolean zzb(zzq<?> zzqVar) {
        String strZzd = zzqVar.zzd();
        if (this.zzn.containsKey(strZzd)) {
            List<zzq<?>> arrayList = this.zzn.get(strZzd);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            zzqVar.zzb("waiting-for-response");
            arrayList.add(zzqVar);
            this.zzn.put(strZzd, arrayList);
            if (zzag.DEBUG) {
                zzag.d("Request for cacheKey=%s is in flight, putting on hold.", strZzd);
            }
            return true;
        }
        this.zzn.put(strZzd, null);
        zzqVar.zza((zzs) this);
        if (zzag.DEBUG) {
            zzag.d("new request, sending to network %s", strZzd);
        }
        return false;
    }
}
