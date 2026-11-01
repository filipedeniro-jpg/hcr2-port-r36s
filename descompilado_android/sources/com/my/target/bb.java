package com.my.target;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.appsflyer.share.Constants;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: MyTargetCookieStore.java */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(9)
class bb implements CookieStore {

    @NonNull
    private static final String et = "mytarget_httpcookie_prefs";

    @NonNull
    private static final String eu = "|";

    @NonNull
    private static final String ev = "\\|";

    @NonNull
    private final SharedPreferences ew;

    @NonNull
    private final Map<URI, Set<HttpCookie>> ex = new HashMap();

    @NonNull
    private static URI a(@NonNull URI uri, @NonNull HttpCookie httpCookie) {
        if (httpCookie.getDomain() == null) {
            return uri;
        }
        String domain = httpCookie.getDomain();
        if (domain.charAt(0) == '.') {
            domain = domain.substring(1);
        }
        try {
            return new URI(uri.getScheme() == null ? "http" : uri.getScheme(), domain, httpCookie.getPath() == null ? Constants.URL_PATH_DELIMITER : httpCookie.getPath(), null);
        } catch (URISyntaxException e) {
            g.a(e.getMessage());
            return uri;
        }
    }

    bb(@NonNull Context context) {
        this.ew = context.getSharedPreferences(et, 0);
        ar();
    }

    @Override // java.net.CookieStore
    public synchronized void add(URI uri, HttpCookie httpCookie) {
        URI uriA = a(uri, httpCookie);
        Set<HttpCookie> hashSet = this.ex.get(uriA);
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.ex.put(uriA, hashSet);
        }
        hashSet.remove(httpCookie);
        hashSet.add(httpCookie);
        b(uriA, httpCookie);
    }

    @Override // java.net.CookieStore
    @NonNull
    public synchronized List<HttpCookie> get(URI uri) {
        return a(uri);
    }

    @Override // java.net.CookieStore
    @NonNull
    public synchronized List<HttpCookie> getCookies() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<URI> it = this.ex.keySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll(a(it.next()));
        }
        return arrayList;
    }

    @Override // java.net.CookieStore
    @NonNull
    public synchronized List<URI> getURIs() {
        return new ArrayList(this.ex.keySet());
    }

    @Override // java.net.CookieStore
    public synchronized boolean remove(URI uri, HttpCookie httpCookie) {
        boolean z;
        Set<HttpCookie> set = this.ex.get(uri);
        z = set != null && set.remove(httpCookie);
        if (z) {
            c(uri, httpCookie);
        }
        return z;
    }

    @Override // java.net.CookieStore
    public synchronized boolean removeAll() {
        this.ex.clear();
        as();
        return true;
    }

    private void ar() {
        for (Map.Entry<String, ?> entry : this.ew.getAll().entrySet()) {
            try {
                URI uri = new URI(entry.getKey().split(ev, 2)[0]);
                HttpCookie httpCookieE = new bc().E((String) entry.getValue());
                Set<HttpCookie> hashSet = this.ex.get(uri);
                if (hashSet == null) {
                    hashSet = new HashSet<>();
                    this.ex.put(uri, hashSet);
                }
                hashSet.add(httpCookieE);
            } catch (URISyntaxException e) {
                g.a(e.getMessage());
            }
        }
    }

    private void b(@NonNull URI uri, @NonNull HttpCookie httpCookie) {
        SharedPreferences.Editor editorEdit = this.ew.edit();
        editorEdit.putString(uri.toString() + eu + httpCookie.getName(), new bc().a(httpCookie));
        editorEdit.apply();
    }

    @NonNull
    private List<HttpCookie> a(@NonNull URI uri) {
        HashSet hashSet = new HashSet();
        for (Map.Entry<URI, Set<HttpCookie>> entry : this.ex.entrySet()) {
            URI key = entry.getKey();
            if (c(key.getHost(), uri.getHost()) && d(key.getPath(), uri.getPath())) {
                hashSet.addAll(entry.getValue());
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            HttpCookie httpCookie = (HttpCookie) it.next();
            if (httpCookie.hasExpired()) {
                arrayList.add(httpCookie);
                it.remove();
            }
        }
        if (!arrayList.isEmpty()) {
            a(uri, arrayList);
        }
        return new ArrayList(hashSet);
    }

    private boolean c(@NonNull String str, @NonNull String str2) {
        if (!str2.equals(str)) {
            if (!str2.endsWith("." + str)) {
                return false;
            }
        }
        return true;
    }

    private boolean d(@NonNull String str, @NonNull String str2) {
        return str2.equals(str) || (str2.startsWith(str) && str.charAt(str.length() - 1) == '/') || (str2.startsWith(str) && str2.substring(str.length()).charAt(0) == '/');
    }

    private void a(@NonNull URI uri, @NonNull List<HttpCookie> list) {
        SharedPreferences.Editor editorEdit = this.ew.edit();
        Iterator<HttpCookie> it = list.iterator();
        while (it.hasNext()) {
            editorEdit.remove(uri.toString() + eu + it.next().getName());
        }
        editorEdit.apply();
    }

    private void c(@NonNull URI uri, @NonNull HttpCookie httpCookie) {
        SharedPreferences.Editor editorEdit = this.ew.edit();
        editorEdit.remove(uri.toString() + eu + httpCookie.getName());
        editorEdit.apply();
    }

    private void as() {
        this.ew.edit().clear().apply();
    }
}
