package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.Map;

/* JADX INFO: compiled from: InstreamResearchServiceBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fq extends f.a {
    private final int duration;

    @NonNull
    public static f b(int i) {
        return new fq(i);
    }

    private fq(int i) {
        this.duration = i;
    }

    @Override // com.my.target.f.a
    @NonNull
    protected final Map<String, String> b(@NonNull b bVar, @NonNull Context context) {
        Map<String, String> mapB = super.b(bVar, context);
        mapB.put("duration", Integer.toString(this.duration));
        return mapB;
    }
}
