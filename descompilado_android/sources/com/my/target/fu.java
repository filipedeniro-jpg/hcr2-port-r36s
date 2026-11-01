package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: InstreamResearchSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fu extends al {

    @Nullable
    private ft banner;

    @NonNull
    public static fu e() {
        return new fu();
    }

    @Nullable
    public final ft f() {
        return this.banner;
    }

    public final void a(@Nullable ft ftVar) {
        this.banner = ftVar;
    }

    @Override // com.my.target.al
    public final int getBannersCount() {
        return this.banner != null ? 1 : 0;
    }
}
