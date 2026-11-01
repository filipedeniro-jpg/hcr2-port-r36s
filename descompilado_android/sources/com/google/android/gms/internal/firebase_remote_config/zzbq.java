package com.google.android.gms.internal.firebase_remote_config;

import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
final class zzbq implements Comparator<String> {
    zzbq(zzbr zzbrVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        if (str3 == str4 || (str3 != null && str3.equals(str4))) {
            return 0;
        }
        if (str3 == null) {
            return -1;
        }
        if (str4 == null) {
            return 1;
        }
        return str3.compareTo(str4);
    }
}
