package com.chartboost.sdk.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public class ao {
    HttpURLConnection a(ad<?> adVar) throws IOException {
        return (HttpURLConnection) new URL(adVar.c).openConnection();
    }
}
