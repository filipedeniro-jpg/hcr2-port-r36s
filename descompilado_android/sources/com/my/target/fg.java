package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.AudioData;
import java.util.Iterator;

/* JADX INFO: compiled from: InstreamAudioAdResultProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fg extends e<com.my.target.core.models.sections.f> {
    @Override // com.my.target.e
    @Nullable
    public final /* synthetic */ al a(@NonNull al alVar, @NonNull b bVar, @NonNull Context context) {
        com.my.target.core.models.sections.f fVar = (com.my.target.core.models.sections.f) alVar;
        Iterator<am<AudioData>> it = fVar.i().iterator();
        while (it.hasNext()) {
            it.next().U();
        }
        return fVar;
    }

    @NonNull
    public static fg e() {
        return new fg();
    }

    private fg() {
    }
}
