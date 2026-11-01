package com.google.firebase.abt;

import com.google.android.gms.common.annotation.KeepForSdk;

/* JADX INFO: loaded from: classes2.dex */
@KeepForSdk
public class AbtException extends Exception {
    public AbtException(String str) {
        super(str);
    }

    public AbtException(String str, Exception exc) {
        super(str, exc);
    }
}
