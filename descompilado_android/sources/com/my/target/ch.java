package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: PrefsCache.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ApplySharedPref"})
public class ch {

    @NonNull
    private static final String et = "mytarget_prefs";

    @NonNull
    private static final String ka = "mrgsDeviceId";

    @Nullable
    private static volatile ch kb;

    @NonNull
    private final SharedPreferences kc;

    @NonNull
    public static ch w(@NonNull Context context) {
        ch chVar = kb;
        if (chVar == null) {
            synchronized (ch.class) {
                chVar = kb;
                if (chVar == null) {
                    chVar = new ch(context.getSharedPreferences(et, 0));
                    kb = chVar;
                }
            }
        }
        return chVar;
    }

    private ch(@NonNull SharedPreferences sharedPreferences) {
        this.kc = sharedPreferences;
    }

    @Nullable
    public String bn() {
        return getString(ka);
    }

    public void Q(@Nullable String str) {
        putString(ka, str);
    }

    @NonNull
    private synchronized String getString(@NonNull String str) {
        try {
        } catch (Throwable th) {
            g.c("PrefsCache exception: " + th);
            return "";
        }
        return this.kc.getString(str, "");
    }

    private synchronized void putString(@NonNull String str, @Nullable String str2) {
        try {
            SharedPreferences.Editor editorEdit = this.kc.edit();
            editorEdit.putString(str, str2);
            editorEdit.commit();
        } catch (Throwable th) {
            g.c("PrefsCache exception: " + th);
        }
    }
}
