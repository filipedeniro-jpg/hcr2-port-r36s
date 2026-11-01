package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: StandardAdFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class df extends c<com.my.target.core.models.sections.b> {

    public interface a extends c.b {
    }

    @NonNull
    public static c<com.my.target.core.models.sections.b> newFactory(@NonNull com.my.target.b bVar) {
        return new df(bVar);
    }

    private df(@NonNull com.my.target.b bVar) {
        super(new b((byte) 0), bVar);
    }

    /* JADX INFO: compiled from: StandardAdFactory.java */
    private static class b implements c.a<com.my.target.core.models.sections.b> {
        @Override // com.my.target.c.a
        public final boolean a() {
            return false;
        }

        private b() {
        }

        /* synthetic */ b(byte b) {
            this();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final d<com.my.target.core.models.sections.b> b() {
            return dg.newParser();
        }

        @Override // com.my.target.c.a
        @Nullable
        public final e<com.my.target.core.models.sections.b> c() {
            return dh.e();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final f d() {
            return f.e();
        }
    }
}
