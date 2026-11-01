package com.vungle.warren.network;

import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public interface Downloader {

    public interface Listener {
        void onComplete(File file);

        void onError(Throwable th);

        void onProgress(int i);
    }

    boolean download(@NonNull String str, @NonNull File file, @NonNull Listener listener) throws IllegalStateException, IOException, IllegalArgumentException;

    void pause();

    void resume();
}
