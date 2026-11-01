package com.my.target.core.presenters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ai;

/* JADX INFO: compiled from: StandardViewPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public interface c {

    /* JADX INFO: compiled from: StandardViewPresenter.java */
    public interface a {
        void a(@NonNull ai aiVar);

        void a(@NonNull ai aiVar, @Nullable String str);
    }

    void a(@Nullable a aVar);

    void destroy();

    void pause();

    void resume();

    void start();

    void stop();
}
