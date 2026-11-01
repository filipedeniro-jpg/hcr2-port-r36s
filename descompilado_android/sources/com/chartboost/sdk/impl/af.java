package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;

/* JADX INFO: loaded from: classes.dex */
public class af<T> {
    public final T a;
    public final CBError b;

    public static <T> af<T> a(T t) {
        return new af<>(t, null);
    }

    public static <T> af<T> a(CBError cBError) {
        return new af<>(null, cBError);
    }

    private af(T t, CBError cBError) {
        this.a = t;
        this.b = cBError;
    }
}
