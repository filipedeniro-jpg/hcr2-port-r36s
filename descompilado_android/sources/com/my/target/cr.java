package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: NativeAdFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cr extends c<com.my.target.core.models.sections.a> {

    public interface b extends c.b {
    }

    @NonNull
    public static c<com.my.target.core.models.sections.a> newFactory(@NonNull com.my.target.b bVar) {
        return new cr(bVar);
    }

    private cr(@NonNull com.my.target.b bVar) {
        super(new a((byte) 0), bVar);
    }

    /* JADX INFO: compiled from: NativeAdFactory.java */
    private static class a implements c.a<com.my.target.core.models.sections.a> {
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
        public final d<com.my.target.core.models.sections.a> b() {
            return cs.newParser();
        }

        @Override // com.my.target.c.a
        @Nullable
        public final e<com.my.target.core.models.sections.a> c() {
            return ct.e();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final f d() {
            return cu.f();
        }
    }
}
