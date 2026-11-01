package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzge;
import com.google.android.gms.internal.firebase_auth.zzgh;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzgh<MessageType extends zzge<MessageType, BuilderType>, BuilderType extends zzgh<MessageType, BuilderType>> implements zzjj {
    @Override // 
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public abstract BuilderType clone();

    protected abstract BuilderType zza(MessageType messagetype);

    @Override // com.google.android.gms.internal.firebase_auth.zzjj
    public final /* synthetic */ zzjj zza(zzjg zzjgVar) {
        if (!zzag().getClass().isInstance(zzjgVar)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return zza((zzge) zzjgVar);
    }
}
