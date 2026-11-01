package com.inmobi.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: ViewableNativeV2VideoAd.java */
/* JADX INFO: loaded from: classes2.dex */
final class cd extends ca {

    @NonNull
    private final bd d;
    private boolean e;

    @Override // com.inmobi.ads.ca
    public final void a(int i) {
    }

    @Override // com.inmobi.ads.ca
    public final void a(Context context, int i) {
    }

    @Override // com.inmobi.ads.ca
    public final void a(@Nullable View... viewArr) {
    }

    @Override // com.inmobi.ads.ca
    public final void d() {
    }

    cd(@NonNull bd bdVar) {
        super(bdVar);
        this.e = false;
        this.d = bdVar;
    }

    @Override // com.inmobi.ads.ca
    @NonNull
    final c c() {
        return this.d.c;
    }

    @Override // com.inmobi.ads.ca
    @Nullable
    public final View a(View view, ViewGroup viewGroup, boolean z) {
        Context contextJ;
        if (this.e || (contextJ = this.d.j()) == null) {
            return null;
        }
        c cVar = this.d.c;
        bd bdVar = this.d;
        this.b = new at(contextJ, cVar, bdVar, bdVar.h());
        View viewA = this.b.a(view, viewGroup, false, null);
        a(viewA);
        this.d.t();
        return viewA;
    }

    @Override // com.inmobi.ads.ca
    public final void e() {
        if (this.e) {
            return;
        }
        this.e = true;
        if (this.b != null) {
            this.b.a();
        }
        super.e();
    }
}
