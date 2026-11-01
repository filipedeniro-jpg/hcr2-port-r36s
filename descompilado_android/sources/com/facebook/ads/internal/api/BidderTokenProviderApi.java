package com.facebook.ads.internal.api;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.WorkerThread;

/* JADX INFO: loaded from: classes.dex */
@Keep
public interface BidderTokenProviderApi {
    @WorkerThread
    String getBidderToken(Context context);
}
