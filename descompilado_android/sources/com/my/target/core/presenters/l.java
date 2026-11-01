package com.my.target.core.presenters;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.er;
import com.my.target.eu;

/* JADX INFO: compiled from: InterstitialSliderPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l {

    @NonNull
    private final er av;

    @Nullable
    private a aw;

    /* JADX INFO: compiled from: InterstitialSliderPresenter.java */
    public interface a {
        void a(@NonNull com.my.target.core.models.banners.h hVar);

        void b(@NonNull com.my.target.core.models.banners.h hVar);

        void bf();
    }

    @NonNull
    public static l g(@NonNull Context context) {
        return new l(context);
    }

    private l(@NonNull Context context) {
        this.av = new er(context);
        this.av.setFSSliderCardListener(new eu.c() { // from class: com.my.target.core.presenters.l.1
            @Override // com.my.target.eu.c
            public final void f(@NonNull com.my.target.core.models.banners.h hVar) {
                if (l.this.aw != null) {
                    l.this.aw.a(hVar);
                }
            }

            @Override // com.my.target.eu.c
            public final void a(int i, @NonNull com.my.target.core.models.banners.h hVar) {
                if (l.this.aw != null) {
                    l.this.aw.b(hVar);
                }
                l.this.av.g(i);
            }
        });
        this.av.setCloseClickListener(new View.OnClickListener() { // from class: com.my.target.core.presenters.l.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (l.this.aw != null) {
                    l.this.aw.bf();
                }
            }
        });
    }

    public final void a(@NonNull com.my.target.core.models.sections.d dVar) {
        this.av.a(dVar, dVar.R());
    }

    @NonNull
    public final View getView() {
        return this.av;
    }

    public final void a(@Nullable a aVar) {
        this.aw = aVar;
    }
}
