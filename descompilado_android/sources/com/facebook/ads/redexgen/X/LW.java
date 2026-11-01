package com.facebook.ads.redexgen.X;

import com.ironsource.sdk.constants.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LW implements Map<String, String> {
    private Map<String, String> B = new HashMap();

    public final LW A(String name, String value) {
        this.B.put(name, value);
        return this;
    }

    public final LW B(Map<? extends String, ? extends String> arg) {
        putAll(arg);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Map
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final String get(Object key) {
        return this.B.get(key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.util.Map
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final String remove(Object key) {
        return this.B.remove(key);
    }

    @Override // java.util.Map
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final String put(String key, String value) {
        return this.B.put(key, value);
    }

    public final String D() {
        StringBuilder sb = new StringBuilder();
        for (String str : this.B.keySet()) {
            if (sb.length() > 0) {
                sb.append(Constants.RequestParameters.AMPERSAND);
            }
            sb.append(str);
            String str2 = this.B.get(str);
            if (str2 != null) {
                sb.append(Constants.RequestParameters.EQUAL);
                try {
                    String value = URLEncoder.encode(str2, "UTF-8");
                    sb.append(value);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        String value2 = sb.toString();
        return value2;
    }

    public final byte[] E() {
        byte[] bytes = null;
        try {
            bytes = D().getBytes("UTF-8");
            return bytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return bytes;
        }
    }

    @Override // java.util.Map
    public final void clear() {
        this.B.clear();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object key) {
        return this.B.containsKey(key);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object value) {
        return this.B.containsValue(value);
    }

    @Override // java.util.Map
    public final Set<Map.Entry<String, String>> entrySet() {
        return this.B.entrySet();
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.B.isEmpty();
    }

    @Override // java.util.Map
    public final Set<String> keySet() {
        return this.B.keySet();
    }

    @Override // java.util.Map
    public final void putAll(Map<? extends String, ? extends String> map) {
        Map<? extends String, ? extends String> arg = this.B;
        arg.putAll(map);
    }

    @Override // java.util.Map
    public final int size() {
        return this.B.size();
    }

    @Override // java.util.Map
    public final Collection<String> values() {
        return this.B.values();
    }
}
