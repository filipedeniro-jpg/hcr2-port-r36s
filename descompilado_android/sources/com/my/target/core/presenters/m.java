package com.my.target.core.presenters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ai;

/* JADX INFO: compiled from: InterstitialWebPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public interface m extends j {

    /* JADX INFO: compiled from: InterstitialWebPresenter.java */
    public interface a extends j.a {
        void a(@NonNull ai aiVar, float f, float f2, @NonNull Context context);

        void b(@NonNull ai aiVar, @NonNull String str, @NonNull Context context);

        void g();
    }

    void a(@NonNull com.my.target.core.models.banners.g gVar);

    void a(@Nullable a aVar);
}
