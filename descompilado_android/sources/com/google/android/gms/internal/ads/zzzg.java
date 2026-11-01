package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzzg extends zzzb {
    zzzg() {
    }

    @Override // com.google.android.gms.internal.ads.zzzb
    public final String zzi(@Nullable String str, String str2) {
        String strZzcn = zzcn(str);
        String strZzcn2 = zzcn(str2);
        if (TextUtils.isEmpty(strZzcn)) {
            return strZzcn2;
        }
        if (TextUtils.isEmpty(strZzcn2)) {
            return strZzcn;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(strZzcn).length() + 1 + String.valueOf(strZzcn2).length());
        sb.append(strZzcn);
        sb.append(",");
        sb.append(strZzcn2);
        return sb.toString();
    }

    @Nullable
    private static String zzcn(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = 0;
        int length = str.length();
        while (i < str.length() && str.charAt(i) == ',') {
            i++;
        }
        while (length > 0 && str.charAt(length - 1) == ',') {
            length--;
        }
        if (length < i) {
            return null;
        }
        return (i == 0 && length == str.length()) ? str : str.substring(i, length);
    }
}
