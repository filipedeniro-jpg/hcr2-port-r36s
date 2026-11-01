package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzjg;
import java.io.InputStream;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzgj<MessageType extends zzjg> implements zzjq<MessageType> {
    private static final zzhk zza = zzhk.zza();

    private final MessageType zzb(InputStream inputStream, zzhk zzhkVar) throws zzig {
        zzgy zzhdVar;
        if (inputStream == null) {
            byte[] bArr = zzib.zzb;
            zzhdVar = zzgy.zza(bArr, 0, bArr.length, false);
        } else {
            zzhdVar = new zzhd(inputStream);
        }
        MessageType messagetypeZza = zza(zzhdVar, zzhkVar);
        try {
            zzhdVar.zza(0);
            return messagetypeZza;
        } catch (zzig e) {
            throw e.zza(messagetypeZza);
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjq
    public final /* synthetic */ Object zza(InputStream inputStream, zzhk zzhkVar) throws zzig {
        zzkq zzkqVar;
        zzjg zzjgVarZzb = zzb(inputStream, zzhkVar);
        if (zzjgVarZzb == null || zzjgVarZzb.zzaa()) {
            return zzjgVarZzb;
        }
        if (zzjgVarZzb instanceof zzge) {
            zzkqVar = new zzkq((zzge) zzjgVarZzb);
        } else if (zzjgVarZzb instanceof zzgg) {
            zzkqVar = new zzkq((zzgg) zzjgVarZzb);
        } else {
            zzkqVar = new zzkq(zzjgVarZzb);
        }
        throw new zzig(zzkqVar.getMessage()).zza(zzjgVarZzb);
    }
}
