package com.my.target.core.models.sections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.al;

/* JADX INFO: compiled from: InterstitialAdSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c extends al {

    @Nullable
    private com.my.target.core.models.banners.e r;

    @NonNull
    public static c l() {
        return new c();
    }

    private c() {
    }

    @Nullable
    public final com.my.target.core.models.banners.e m() {
        return this.r;
    }

    public final void a(@Nullable com.my.target.core.models.banners.e eVar) {
        this.r = eVar;
    }

    @Override // com.my.target.al
    public final int getBannersCount() {
        return this.r == null ? 0 : 1;
    }
}
