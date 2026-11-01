package com.google.android.gms.internal.firebase_remote_config;

import com.google.android.gms.internal.firebase_remote_config.zzfp;
import com.google.android.gms.internal.firebase_remote_config.zzfq;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzfq<MessageType extends zzfq<MessageType, BuilderType>, BuilderType extends zzfp<MessageType, BuilderType>> implements zziq {
    private static boolean zzok = false;
    protected int zzoj = 0;

    @Override // com.google.android.gms.internal.firebase_remote_config.zziq
    public final zzfx zzep() {
        try {
            zzgf zzgfVarZzx = zzfx.zzx(zzgs());
            zzb(zzgfVarZzx.zzfa());
            return zzgfVarZzx.zzez();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    int zzeq() {
        throw new UnsupportedOperationException();
    }

    void zzr(int i) {
        throw new UnsupportedOperationException();
    }
}
