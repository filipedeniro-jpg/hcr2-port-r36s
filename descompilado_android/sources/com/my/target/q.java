package com.my.target;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: AbstractJsEvent.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class q implements v {

    @NonNull
    private final String type;

    public q(@NonNull String str) {
        this.type = str;
    }

    @Override // com.my.target.v
    @NonNull
    public String getType() {
        return this.type;
    }
}
