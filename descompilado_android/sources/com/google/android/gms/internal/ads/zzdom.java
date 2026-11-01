package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdol;
import com.google.android.gms.internal.ads.zzdom;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdom<MessageType extends zzdom<MessageType, BuilderType>, BuilderType extends zzdol<MessageType, BuilderType>> implements zzdrn {
    protected int zzhfc = 0;

    @Override // com.google.android.gms.internal.ads.zzdrn
    public final zzdot zzaxb() {
        try {
            zzdpb zzdpbVarZzfp = zzdot.zzfp(zzazp());
            zzb(zzdpbVarZzfp.zzaxo());
            return zzdpbVarZzfp.zzaxn();
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

    @Override // com.google.android.gms.internal.ads.zzdrn
    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzazp()];
            zzdpm zzdpmVarZzaa = zzdpm.zzaa(bArr);
            zzb(zzdpmVarZzaa);
            zzdpmVarZzaa.zzayq();
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

    int zzaxc() {
        throw new UnsupportedOperationException();
    }

    void zzfj(int i) {
        throw new UnsupportedOperationException();
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzdqe.checkNotNull(iterable);
        if (iterable instanceof zzdqu) {
            List<?> listZzbai = ((zzdqu) iterable).zzbai();
            zzdqu zzdquVar = (zzdqu) list;
            int size = list.size();
            for (Object obj : listZzbai) {
                if (obj == null) {
                    int size2 = zzdquVar.size() - size;
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(size2);
                    sb.append(" is null.");
                    String string = sb.toString();
                    for (int size3 = zzdquVar.size() - 1; size3 >= size; size3--) {
                        zzdquVar.remove(size3);
                    }
                    throw new NullPointerException(string);
                }
                if (obj instanceof zzdot) {
                    zzdquVar.zzdb((zzdot) obj);
                } else {
                    zzdquVar.add((String) obj);
                }
            }
            return;
        }
        if (iterable instanceof zzdrz) {
            list.addAll((Collection) iterable);
            return;
        }
        if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
            ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
        }
        int size4 = list.size();
        for (T t : iterable) {
            if (t == null) {
                int size5 = list.size() - size4;
                StringBuilder sb2 = new StringBuilder(37);
                sb2.append("Element at index ");
                sb2.append(size5);
                sb2.append(" is null.");
                String string2 = sb2.toString();
                for (int size6 = list.size() - 1; size6 >= size4; size6--) {
                    list.remove(size6);
                }
                throw new NullPointerException(string2);
            }
            list.add(t);
        }
    }
}
