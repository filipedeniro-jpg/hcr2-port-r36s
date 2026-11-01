package com.inmobi.ads;

import androidx.annotation.Nullable;

/* JADX INFO: compiled from: AdUnitTRCCollectorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class k implements j {
    private final i a;

    public k(@Nullable i iVar) {
        this.a = iVar;
    }

    @Override // com.inmobi.ads.j
    public final void a(i.b bVar, String str, String str2) {
        i iVar = this.a;
        if (iVar != null) {
            iVar.a(bVar, str, str2);
        } else {
            k.class.getName();
        }
    }
}
