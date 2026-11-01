package com.google.android.gms.internal.firebase_auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzan {
    private final zzaf zza;
    private final boolean zzb;
    private final zzat zzc;
    private final int zzd;

    private zzan(zzat zzatVar) {
        this(zzatVar, false, zzaj.zza, Integer.MAX_VALUE);
    }

    private zzan(zzat zzatVar, boolean z, zzaf zzafVar, int i) {
        this.zzc = zzatVar;
        this.zzb = false;
        this.zza = zzafVar;
        this.zzd = Integer.MAX_VALUE;
    }

    public static zzan zza(char c) {
        zzah zzahVar = new zzah(c);
        zzao.zza(zzahVar);
        return new zzan(new zzaq(zzahVar));
    }

    public static zzan zza(String str) {
        zzao.zza(str.length() != 0, "The separator may not be the empty string.");
        if (str.length() == 1) {
            return zza(str.charAt(0));
        }
        return new zzan(new zzas(str));
    }

    public final List<String> zza(CharSequence charSequence) {
        zzao.zza(charSequence);
        Iterator<String> itZza = this.zzc.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (itZza.hasNext()) {
            arrayList.add(itZza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
