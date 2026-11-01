package com.vungle.warren;

import android.support.v4.os.ResultReceiver;

/* JADX INFO: loaded from: classes2.dex */
public interface PublisherDirectDownload {
    ResultReceiver getPublisherReceiver();

    void setSDKCallbackReceiver(ResultReceiver resultReceiver);
}
