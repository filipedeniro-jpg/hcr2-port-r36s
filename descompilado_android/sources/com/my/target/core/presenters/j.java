package com.my.target.core.presenters;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ai;

/* JADX INFO: compiled from: InterstitialPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public interface j {

    /* JADX INFO: compiled from: InterstitialPresenter.java */
    public interface a {
        void a(@NonNull ai aiVar, @NonNull Context context);

        void a(@Nullable ai aiVar, @Nullable String str, @NonNull Context context);

        void bf();
    }

    void destroy();

    void pause();

    void resume();

    void stop();

    @NonNull
    View v();
}
