package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class zzax extends zzby implements Cloneable {
    private zzaw zzda;

    public final void zza(zzaw zzawVar) {
        this.zzda = zzawVar;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        zzaw zzawVar = this.zzda;
        if (zzawVar != null) {
            try {
                return zzawVar.toString(this);
            } catch (IOException e) {
                throw zzeb.zzb(e);
            }
        }
        return super.toString();
    }

    public final String zzaq() throws IOException {
        zzaw zzawVar = this.zzda;
        if (zzawVar != null) {
            return zzawVar.zzc(this);
        }
        return super.toString();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby, java.util.AbstractMap
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public zzax clone() {
        return (zzax) super.clone();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public zzax zzb(String str, Object obj) {
        return (zzax) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby
    /* JADX INFO: renamed from: zzb */
    public /* synthetic */ zzby clone() {
        return (zzax) clone();
    }
}
