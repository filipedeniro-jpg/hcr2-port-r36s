package com.my.target.core.models.sections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.al;
import com.my.target.am;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: InstreamAdSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends al {

    @NonNull
    private final HashMap<String, am<VideoData>> E = new HashMap<>();

    @NonNull
    public static g i() {
        return new g();
    }

    private g() {
        this.E.put(am.a.dm, am.r(am.a.dm));
        this.E.put(am.a.f703do, am.r(am.a.f703do));
        this.E.put(am.a.dp, am.r(am.a.dp));
        this.E.put("postroll", am.r("postroll"));
    }

    @Nullable
    public final am<VideoData> a(@NonNull String str) {
        return this.E.get(str);
    }

    @NonNull
    public final ArrayList<am<VideoData>> j() {
        return new ArrayList<>(this.E.values());
    }

    @Override // com.my.target.al
    public final int getBannersCount() {
        Iterator<am<VideoData>> it = this.E.values().iterator();
        int bannersCount = 0;
        while (it.hasNext()) {
            bannersCount += it.next().getBannersCount();
        }
        return bannersCount;
    }

    public final boolean hasContent() {
        for (am<VideoData> amVar : this.E.values()) {
            if (amVar.getBannersCount() > 0 || amVar.V()) {
                return true;
            }
        }
        return false;
    }
}
