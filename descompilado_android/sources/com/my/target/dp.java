package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: InterstitialAdFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class dp extends c<com.my.target.core.models.sections.c> {

    public interface b extends c.b {
    }

    @NonNull
    public static c<com.my.target.core.models.sections.c> newFactory(@NonNull com.my.target.b bVar) {
        return new dp(bVar);
    }

    private dp(@NonNull com.my.target.b bVar) {
        super(new a((byte) 0), bVar);
    }

    /* JADX INFO: compiled from: InterstitialAdFactory.java */
    private static class a implements c.a<com.my.target.core.models.sections.c> {
        @Override // com.my.target.c.a
        public final boolean a() {
            return true;
        }

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final d<com.my.target.core.models.sections.c> b() {
            return dq.newParser();
        }

        @Override // com.my.target.c.a
        @Nullable
        public final e<com.my.target.core.models.sections.c> c() {
            return dr.e();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final f d() {
            return f.e();
        }
    }
}
