package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: MediaData.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ah<T> {
    protected int height;

    @Nullable
    private T t;

    @NonNull
    protected final String url;
    protected int width;

    @NonNull
    public String getUrl() {
        return this.url;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setData(@Nullable T t) {
        this.t = t;
    }

    @Nullable
    public T getData() {
        return this.t;
    }

    protected ah(@NonNull String str) {
        this.url = str;
    }
}
