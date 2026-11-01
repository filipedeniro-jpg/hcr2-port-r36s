package com.my.target.core.presenters;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.my.target.dz;

/* JADX INFO: compiled from: InterstitialImagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements j {

    @Nullable
    j.a K;

    @NonNull
    private final dz L;

    @Override // com.my.target.core.presenters.j
    public final void destroy() {
    }

    @Override // com.my.target.core.presenters.j
    public final void pause() {
    }

    @Override // com.my.target.core.presenters.j
    public final void resume() {
    }

    @Override // com.my.target.core.presenters.j
    public final void stop() {
    }

    public static g c(Context context) {
        return new g(new dz(context));
    }

    @VisibleForTesting
    private g(@NonNull dz dzVar) {
        this.L = dzVar;
    }

    @Override // com.my.target.core.presenters.j
    @NonNull
    public final View v() {
        return this.L;
    }

    public final void e(@NonNull final com.my.target.core.models.banners.h hVar) {
        this.L.a(hVar.getOptimalLandscapeImage(), hVar.getOptimalPortraitImage(), hVar.getCloseIcon());
        this.L.setAgeRestrictions(hVar.getAgeRestrictions());
        this.L.getImageView().setOnClickListener(new View.OnClickListener() { // from class: com.my.target.core.presenters.g.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (g.this.K != null) {
                    g.this.K.a(hVar, null, view.getContext());
                }
            }
        });
        this.L.getCloseButton().setOnClickListener(new View.OnClickListener() { // from class: com.my.target.core.presenters.g.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (g.this.K != null) {
                    g.this.K.bf();
                }
            }
        });
        j.a aVar = this.K;
        if (aVar != null) {
            aVar.a(hVar, this.L.getContext());
        }
    }

    public final void a(@Nullable j.a aVar) {
        this.K = aVar;
    }
}
