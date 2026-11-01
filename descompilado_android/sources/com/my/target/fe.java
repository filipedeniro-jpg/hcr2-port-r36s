package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: InstreamAudioAdFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fe extends c<com.my.target.core.models.sections.f> {

    @Nullable
    private final List<af> a;

    @NonNull
    private final ci b;

    @Nullable
    private Runnable c;

    public interface b extends c.b {
    }

    @NonNull
    public static c<com.my.target.core.models.sections.f> newFactory(@NonNull com.my.target.b bVar, int i) {
        return new fe(bVar, i);
    }

    @NonNull
    public static c<com.my.target.core.models.sections.f> newFactoryForAdService(@NonNull af afVar, @NonNull com.my.target.b bVar, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(afVar);
        return new fe(arrayList, bVar, i);
    }

    @NonNull
    public static c<com.my.target.core.models.sections.f> newFactoryForAdServices(@NonNull List<af> list, @NonNull com.my.target.b bVar, int i) {
        return new fe(list, bVar, i);
    }

    private fe(@NonNull com.my.target.b bVar, int i) {
        this(null, bVar, i);
    }

    private fe(@Nullable List<af> list, @NonNull com.my.target.b bVar, int i) {
        super(new a((byte) 0), bVar);
        this.a = list;
        this.b = ci.i(i * 1000);
    }

    @Override // com.my.target.c
    @NonNull
    public final c<com.my.target.core.models.sections.f> a(@NonNull Context context) {
        if (this.c == null) {
            this.c = new Runnable() { // from class: com.my.target.fe.1
                @Override // java.lang.Runnable
                public final void run() {
                    fe.this.b.e(fe.this.c);
                    fe.this.a((al) null, "ad loading timeout");
                }
            };
        }
        this.b.d(this.c);
        return super.a(context);
    }

    /* JADX INFO: compiled from: InstreamAudioAdFactory.java */
    private static class a implements c.a<com.my.target.core.models.sections.f> {
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
        public final d<com.my.target.core.models.sections.f> b() {
            return ff.newParser();
        }

        @Override // com.my.target.c.a
        @Nullable
        public final e<com.my.target.core.models.sections.f> c() {
            return fg.e();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final f d() {
            return f.e();
        }
    }

    @Override // com.my.target.c
    @Nullable
    protected final /* synthetic */ al c(@NonNull Context context) {
        if (this.a != null) {
            return a(a(this.a, (al) null, (d<al>) this.n.b(), at.ah(), context), context);
        }
        return (com.my.target.core.models.sections.f) super.c(context);
    }
}
