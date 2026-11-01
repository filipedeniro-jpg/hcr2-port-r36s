package com.google.android.gms.internal.drive;

import com.google.android.gms.internal.drive.zzit;
import com.google.android.gms.internal.drive.zziu;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzit<MessageType extends zzit<MessageType, BuilderType>, BuilderType extends zziu<MessageType, BuilderType>> implements zzlq {
    private static boolean zznf = false;
    protected int zzne = 0;

    @Override // com.google.android.gms.internal.drive.zzlq
    public final zzjc zzbl() {
        try {
            zzjk zzjkVarZzu = zzjc.zzu(zzcx());
            zzb(zzjkVarZzu.zzby());
            return zzjkVarZzu.zzbx();
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

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzcx()];
            zzjr zzjrVarZzb = zzjr.zzb(bArr);
            zzb(zzjrVarZzb);
            zzjrVarZzb.zzcb();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "byte array".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    int zzbm() {
        throw new UnsupportedOperationException();
    }

    void zzo(int i) {
        throw new UnsupportedOperationException();
    }
}
