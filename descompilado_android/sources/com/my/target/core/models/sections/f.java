package com.my.target.core.models.sections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.al;
import com.my.target.am;
import com.my.target.common.models.AudioData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: InstreamAudioAdSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends al {

    @NonNull
    private final HashMap<String, am<AudioData>> E = new HashMap<>();

    @NonNull
    public static f h() {
        return new f();
    }

    private f() {
        this.E.put(am.a.dm, am.s(am.a.dm));
        this.E.put(am.a.f703do, am.s(am.a.f703do));
        this.E.put(am.a.dp, am.s(am.a.dp));
        this.E.put("postroll", am.s("postroll"));
    }

    @Nullable
    public final am<AudioData> a(@NonNull String str) {
        return this.E.get(str);
    }

    @NonNull
    public final ArrayList<am<AudioData>> i() {
        return new ArrayList<>(this.E.values());
    }

    @Override // com.my.target.al
    public final int getBannersCount() {
        Iterator<am<AudioData>> it = this.E.values().iterator();
        int bannersCount = 0;
        while (it.hasNext()) {
            bannersCount += it.next().getBannersCount();
        }
        return bannersCount;
    }

    public final boolean hasContent() {
        for (am<AudioData> amVar : this.E.values()) {
            if (amVar.getBannersCount() > 0 || amVar.V()) {
                return true;
            }
        }
        return false;
    }
}
