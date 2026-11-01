package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: MyTargetCookieManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ba {

    @Nullable
    private static volatile ba er;

    @NonNull
    private final CookieHandler es;

    @NonNull
    public static ba f(@NonNull Context context) {
        ba baVar = er;
        if (baVar == null) {
            synchronized (ba.class) {
                baVar = er;
                if (baVar == null) {
                    baVar = new ba(context);
                    er = baVar;
                }
            }
        }
        return baVar;
    }

    public ba(@NonNull Context context) {
        this.es = new CookieManager(new bb(context), null);
    }

    public void a(@NonNull URLConnection uRLConnection) throws IOException {
        Map<String, List<String>> headerFields = uRLConnection.getHeaderFields();
        this.es.put(URI.create(uRLConnection.getURL().toString()), headerFields);
    }

    public void b(@NonNull URLConnection uRLConnection) throws IOException {
        HashMap map = new HashMap();
        Iterator<Map.Entry<String, List<String>>> it = this.es.get(URI.create(uRLConnection.getURL().toString()), map).entrySet().iterator();
        for (boolean zHasNext = it.hasNext(); zHasNext; zHasNext = it.hasNext()) {
            Map.Entry<String, List<String>> next = it.next();
            String key = next.getKey();
            Iterator<String> it2 = next.getValue().iterator();
            for (boolean zHasNext2 = it2.hasNext(); zHasNext2; zHasNext2 = it2.hasNext()) {
                uRLConnection.addRequestProperty(key, it2.next());
            }
        }
    }
}
