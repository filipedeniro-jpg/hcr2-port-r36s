package com.vungle.warren.persistence;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public interface Memorable {
    @NonNull
    String getId();

    byte[] toByteArray();
}
