package com.facebook.ads;

import androidx.annotation.Keep;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
@Keep
public enum CacheFlag {
    NONE,
    ICON,
    IMAGE,
    VIDEO;

    public static final EnumSet<CacheFlag> ALL = EnumSet.allOf(CacheFlag.class);
}
