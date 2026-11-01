package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.VideoData;
import java.util.Iterator;

/* JADX INFO: compiled from: InstreamAdResultProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fl extends e<com.my.target.core.models.sections.g> {
    @Override // com.my.target.e
    @Nullable
    public final /* synthetic */ al a(@NonNull al alVar, @NonNull b bVar, @NonNull Context context) {
        com.my.target.core.models.sections.g gVar = (com.my.target.core.models.sections.g) alVar;
        Iterator<am<VideoData>> it = gVar.j().iterator();
        while (it.hasNext()) {
            it.next().U();
        }
        return gVar;
    }

    @NonNull
    public static fl e() {
        return new fl();
    }

    private fl() {
    }
}
