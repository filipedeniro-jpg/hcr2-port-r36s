package com.my.target.core.models.banners;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ai;

/* JADX INFO: compiled from: StandardAdBanner.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends ai {

    @Nullable
    private String mraidJs;

    @Nullable
    private String mraidSource;
    private int timeout = 60;

    @NonNull
    private final d viewSettings = new d();

    @NonNull
    public static c newBanner() {
        return new c();
    }

    private c() {
    }

    @NonNull
    public final d getViewSettings() {
        return this.viewSettings;
    }

    public final void setMraidSource(@Nullable String str) {
        this.mraidSource = str;
    }

    @Nullable
    public final String getMraidSource() {
        return this.mraidSource;
    }

    public final void setMraidJs(@Nullable String str) {
        this.mraidJs = str;
    }

    @Nullable
    public final String getMraidJs() {
        return this.mraidJs;
    }

    public final int getTimeout() {
        return this.timeout;
    }

    public final void setTimeout(int i) {
        this.timeout = i;
    }
}
