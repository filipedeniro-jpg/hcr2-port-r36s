package com.my.target.core.models.sections;

import android.util.LruCache;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.al;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: NativeAdSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends al {

    @NonNull
    private static final LruCache<String, String> Q = new LruCache<>(10);

    @NonNull
    private final ArrayList<com.my.target.core.models.banners.a> R = new ArrayList<>();

    @NonNull
    public static LruCache<String, String> G() {
        return Q;
    }

    @NonNull
    public static a H() {
        return new a();
    }

    private a() {
    }

    @Nullable
    public final com.my.target.core.models.banners.a I() {
        if (this.R.size() > 0) {
            return this.R.get(0);
        }
        return null;
    }

    public final void a(@NonNull com.my.target.core.models.banners.a aVar) {
        this.R.add(aVar);
        Q.put(aVar.getId(), aVar.getId());
    }

    @NonNull
    public final List<com.my.target.core.models.banners.a> R() {
        return new ArrayList(this.R);
    }

    @Override // com.my.target.al
    public final int getBannersCount() {
        return this.R.size();
    }
}
