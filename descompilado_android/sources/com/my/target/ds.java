package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: InterstitialSliderAdFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ds extends c<com.my.target.core.models.sections.d> {

    public interface b extends c.b {
    }

    @NonNull
    public static c<com.my.target.core.models.sections.d> newFactory(@NonNull com.my.target.b bVar) {
        return new ds(bVar);
    }

    private ds(@NonNull com.my.target.b bVar) {
        super(new a((byte) 0), bVar);
    }

    /* JADX INFO: compiled from: InterstitialSliderAdFactory.java */
    private static class a implements c.a<com.my.target.core.models.sections.d> {
        @Override // com.my.target.c.a
        public final boolean a() {
            return false;
        }

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final d<com.my.target.core.models.sections.d> b() {
            return dt.newParser();
        }

        @Override // com.my.target.c.a
        @Nullable
        public final e<com.my.target.core.models.sections.d> c() {
            return du.f();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final f d() {
            return f.e();
        }
    }
}
