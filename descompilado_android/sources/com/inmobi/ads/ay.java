package com.inmobi.ads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.inmobi.commons.core.utilities.Logger;

/* JADX INFO: compiled from: NativeScrollableDataSourceFactory.java */
/* JADX INFO: loaded from: classes2.dex */
final class ay {
    @Nullable
    public static ax a(int i, @NonNull ao aoVar, @NonNull au auVar) {
        if (i == 0) {
            return new ap(aoVar, auVar);
        }
        if (i != 1) {
            return null;
        }
        try {
            return new NativeRecyclerViewAdapter(aoVar, auVar);
        } catch (NoClassDefFoundError e) {
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Error rendering ad! RecyclerView not found. Please check if the recyclerview support library was included");
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return null;
        }
    }
}
