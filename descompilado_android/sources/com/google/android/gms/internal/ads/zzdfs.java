package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdfs {

    @Deprecated
    public static final zzdjs zzgso = (zzdjs) ((zzdqd) zzdjs.zzavf().zzhe("TINK_MAC_1_0_0").zzb(zzddl.zza("TinkMac", "Mac", "HmacKey", 0, true)).zzazm());

    @Deprecated
    private static final zzdjs zzgsp = (zzdjs) ((zzdqd) zzdjs.zzavf().zza(zzgso).zzhe("TINK_MAC_1_1_0").zzazm());
    public static final zzdjs zzgsq = (zzdjs) ((zzdqd) zzdjs.zzavf().zza(zzgso).zzhe("TINK_MAC").zzazm());

    public static void zzapu() throws GeneralSecurityException {
        zzdef.zza("TinkMac", new zzdfp());
        zzddl.zza(zzgsq);
    }

    static {
        try {
            zzapu();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
