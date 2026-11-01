package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdol;
import com.google.android.gms.internal.ads.zzdom;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdol<MessageType extends zzdom<MessageType, BuilderType>, BuilderType extends zzdol<MessageType, BuilderType>> implements zzdrm {
    protected abstract BuilderType zza(MessageType messagetype);

    public abstract BuilderType zza(zzdpf zzdpfVar, zzdpq zzdpqVar) throws IOException;

    @Override // 
    /* JADX INFO: renamed from: zzaxa, reason: merged with bridge method [inline-methods] */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i, int i2, zzdpq zzdpqVar) throws zzdqn {
        try {
            zzdpf zzdpfVarZzc = zzdpf.zzc(bArr, 0, i2, false);
            zza(zzdpfVarZzc, zzdpqVar);
            zzdpfVarZzc.zzfq(0);
            return this;
        } catch (zzdqn e) {
            throw e;
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 60 + "byte array".length());
            sb.append("Reading ");
            sb.append(name);
            sb.append(" from a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdrm
    public final /* synthetic */ zzdrm zzi(zzdrn zzdrnVar) {
        if (!zzazn().getClass().isInstance(zzdrnVar)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return zza((zzdom) zzdrnVar);
    }
}
