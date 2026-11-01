package com.my.target.core.presenters;

import androidx.annotation.NonNull;
import com.my.target.aq;
import com.my.target.cj;
import com.my.target.eh;
import com.my.target.ep;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: InterstitialCarouselPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e {
    private k.b A;

    @NonNull
    private final eh y;

    @NonNull
    private final ArrayList<com.my.target.core.models.banners.f> z = new ArrayList<>();

    public static e a(@NonNull List<com.my.target.core.models.banners.f> list, @NonNull ep epVar) {
        return new e(list, epVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private e(@NonNull List<com.my.target.core.models.banners.f> list, @NonNull ep epVar) {
        this.y = epVar;
        epVar.setCarouselListener(new a(this, 0 == true ? 1 : 0));
        for (int i : epVar.getNumbersOfCurrentShowingCards()) {
            if (i < list.size() && i >= 0) {
                com.my.target.core.models.banners.f fVar = list.get(i);
                this.z.add(fVar);
                cj.a(fVar.getStatHolder().x(aq.a.du), epVar.getView().getContext());
            }
        }
    }

    public final void a(k.b bVar) {
        this.A = bVar;
    }

    /* JADX INFO: compiled from: InterstitialCarouselPresenter.java */
    private class a implements ep.a {
        private a() {
        }

        /* synthetic */ a(e eVar, byte b) {
            this();
        }

        @Override // com.my.target.ep.a
        public final void a(@NonNull com.my.target.core.models.banners.f fVar) {
            if (e.this.A != null) {
                e.this.A.a(fVar, null, e.this.y.getView().getContext());
            }
        }

        @Override // com.my.target.ep.a
        public final void a(@NonNull List<com.my.target.core.models.banners.f> list) {
            for (com.my.target.core.models.banners.f fVar : list) {
                if (!e.this.z.contains(fVar)) {
                    e.this.z.add(fVar);
                    cj.a(fVar.getStatHolder().x(aq.a.du), e.this.y.getView().getContext());
                }
            }
        }
    }
}
