package com.adcolony.sdk;

import com.tonyodev.fetch.FetchService;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyAdSize {
    public static final AdColonyAdSize MEDIUM_RECTANGLE = new AdColonyAdSize(300, 250);
    static final AdColonyAdSize c = new AdColonyAdSize(FetchService.ACTION_LOGGING, 50);
    int a;
    int b;

    public AdColonyAdSize(int i, int i2) {
        this.a = i;
        this.b = i2;
    }
}
