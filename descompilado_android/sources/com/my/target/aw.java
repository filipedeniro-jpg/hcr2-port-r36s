package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: HttpRequest.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class aw<T> {
    protected boolean ck;
    protected boolean dY;

    @Nullable
    protected T dZ;

    @Nullable
    protected String o;
    protected int responseCode = -1;

    @Nullable
    protected abstract T c(@NonNull String str, @NonNull Context context);

    public boolean al() {
        return this.dY;
    }

    public boolean D() {
        return this.ck;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    @Nullable
    public T am() {
        return this.dZ;
    }

    @Nullable
    public String j() {
        return this.o;
    }

    @Nullable
    public final T f(@NonNull String str, @NonNull Context context) {
        this.dY = true;
        this.ck = false;
        this.responseCode = -1;
        this.dZ = null;
        this.o = null;
        return c(str, context);
    }
}
