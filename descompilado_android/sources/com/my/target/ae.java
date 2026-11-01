package com.my.target;

import androidx.annotation.NonNull;
import com.my.target.common.models.ImageData;

/* JADX INFO: compiled from: AdChoices.java */
/* JADX INFO: loaded from: classes2.dex */
public class ae {

    @NonNull
    private final String cb;

    @NonNull
    private final ImageData icon;

    @NonNull
    public static ae a(@NonNull ImageData imageData, @NonNull String str) {
        return new ae(imageData, str);
    }

    private ae(@NonNull ImageData imageData, @NonNull String str) {
        this.icon = imageData;
        this.cb = str;
    }

    @NonNull
    public ImageData getIcon() {
        return this.icon;
    }

    @NonNull
    public String y() {
        return this.cb;
    }
}
