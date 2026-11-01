package com.google.android.gms.internal.ads;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzadf implements zzadx<Object> {
    private final zzadi zzcwr;

    public zzadf(zzadi zzadiVar) {
        this.zzcwr = zzadiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("name");
        if (str == null) {
            zzatm.zzeu("App event with no name parameter.");
        } else {
            this.zzcwr.onAppEvent(str, map.get("info"));
        }
    }
}
