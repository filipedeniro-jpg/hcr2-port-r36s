package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: InstreamResearchFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fo extends c<fu> {

    public interface b extends c.b {
    }

    @NonNull
    public static c<fu> newFactory(@NonNull com.my.target.b bVar, int i) {
        return new fo(bVar, i);
    }

    private fo(com.my.target.b bVar, int i) {
        super(new a(i), bVar);
    }

    /* JADX INFO: compiled from: InstreamResearchFactory.java */
    private static class a implements c.a<fu> {
        private final int duration;

        @Override // com.my.target.c.a
        public final boolean a() {
            return false;
        }

        @Override // com.my.target.c.a
        @Nullable
        public final e<fu> c() {
            return null;
        }

        a(int i) {
            this.duration = i;
        }

        @Override // com.my.target.c.a
        @NonNull
        public final d<fu> b() {
            return fp.a(this.duration);
        }

        @Override // com.my.target.c.a
        @NonNull
        public final f d() {
            return fq.b(this.duration);
        }
    }
}
