package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: NativeAppwallAdFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ex extends c<com.my.target.core.models.sections.e> {

    public interface b extends c.b {
    }

    @NonNull
    public static c<com.my.target.core.models.sections.e> newFactory(@NonNull com.my.target.b bVar) {
        return new ex(bVar);
    }

    private ex(@NonNull com.my.target.b bVar) {
        super(new a((byte) 0), bVar);
    }

    @Override // com.my.target.c
    @Nullable
    protected final String a(@NonNull af afVar, @NonNull at atVar, @NonNull Context context) {
        if (this.adConfig.getCachePeriod() > 0) {
            g.a("NativeAppwallAdFactory: check cached data");
            cd cdVarT = cd.t(context);
            String strA = cdVarT != null ? cdVarT.a(this.adConfig.getSlotId(), this.adConfig.getCachePeriod()) : null;
            if (strA != null) {
                g.a("NativeAppwallAdFactory: cached data loaded successfully");
                afVar.d(true);
                return strA;
            }
            g.a("NativeAppwallAdFactory: no cached data");
        }
        return super.a(afVar, atVar, context);
    }

    /* JADX INFO: compiled from: NativeAppwallAdFactory.java */
    private static class a implements c.a<com.my.target.core.models.sections.e> {
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
        public final d<com.my.target.core.models.sections.e> b() {
            return ey.newParser();
        }

        @Override // com.my.target.c.a
        @Nullable
        public final e<com.my.target.core.models.sections.e> c() {
            return ez.e();
        }

        @Override // com.my.target.c.a
        @NonNull
        public final f d() {
            return f.e();
        }
    }
}
