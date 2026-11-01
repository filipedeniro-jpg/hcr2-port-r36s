package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzckv {
    private final zzcgw zzfsj;
    private final ConcurrentHashMap<String, zzalj> zzgas = new ConcurrentHashMap<>();

    public zzckv(zzcgw zzcgwVar) {
        this.zzfsj = zzcgwVar;
    }

    public final void zzgb(String str) {
        try {
            this.zzgas.put(str, this.zzfsj.zzdb(str));
        } catch (RemoteException e) {
            zzatm.zzc("Couldn't create RTB adapter : ", e);
        }
    }

    @CheckForNull
    public final zzalj zzgc(String str) {
        if (this.zzgas.containsKey(str)) {
            return this.zzgas.get(str);
        }
        return null;
    }
}
