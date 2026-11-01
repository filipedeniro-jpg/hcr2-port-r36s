package com.google.android.gms.internal.ads;

import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.UnsupportedEncodingException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzav extends zzq<String> {
    private final Object mLock;

    @Nullable
    @GuardedBy("mLock")
    private zzab<String> zzcm;

    public zzav(int i, String str, zzab<String> zzabVar, @Nullable zzy zzyVar) {
        super(i, str, zzyVar);
        this.mLock = new Object();
        this.zzcm = zzabVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzq
    /* JADX INFO: renamed from: zzh, reason: merged with bridge method [inline-methods] */
    public void zza(String str) {
        zzab<String> zzabVar;
        synchronized (this.mLock) {
            zzabVar = this.zzcm;
        }
        if (zzabVar != null) {
            zzabVar.zzb(str);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzq
    protected final zzz<String> zza(zzo zzoVar) {
        String str;
        try {
            byte[] bArr = zzoVar.data;
            String str2 = "ISO-8859-1";
            String str3 = zzoVar.zzab.get(HttpRequest.HEADER_CONTENT_TYPE);
            if (str3 != null) {
                String[] strArrSplit = str3.split(";", 0);
                for (int i = 1; i < strArrSplit.length; i++) {
                    String[] strArrSplit2 = strArrSplit[i].trim().split(Constants.RequestParameters.EQUAL, 0);
                    if (strArrSplit2.length == 2 && strArrSplit2[0].equals(HttpRequest.PARAM_CHARSET)) {
                        str2 = strArrSplit2[1];
                        break;
                    }
                }
            }
            str = new String(bArr, str2);
        } catch (UnsupportedEncodingException unused) {
            str = new String(zzoVar.data);
        }
        return zzz.zza(str, zzaq.zzb(zzoVar));
    }
}
