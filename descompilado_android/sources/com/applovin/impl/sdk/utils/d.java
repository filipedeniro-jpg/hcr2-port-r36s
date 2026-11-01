package com.applovin.impl.sdk.utils;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static String a(Collection<String> collection, int i) {
        return a(collection, ",", i);
    }

    static String a(Collection<String> collection, String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("No glue specified");
        }
        if (collection == null || collection.size() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (String str2 : collection) {
            if (i2 >= i) {
                break;
            }
            i2++;
            sb.append(str2);
            sb.append(str);
        }
        if (sb.length() > str.length()) {
            sb.setLength(sb.length() - str.length());
        }
        return sb.toString();
    }

    public static <T> List<T> a(int i) {
        return Collections.synchronizedList(new ArrayList(i));
    }

    public static List<String> a(String str) {
        return a(str, ",\\s*");
    }

    public static List<String> a(String str, String str2) {
        return TextUtils.isEmpty(str) ? Collections.emptyList() : Arrays.asList(str.split(str2));
    }

    public static List<String> a(List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String strTrim = it.next().trim();
            if (!TextUtils.isEmpty(strTrim)) {
                arrayList.add(strTrim);
            }
        }
        return arrayList;
    }
}
