package com.applovin.impl.mediation.a.a;

import android.text.SpannedString;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    protected SpannedString a;
    protected SpannedString b;

    /* JADX INFO: renamed from: com.applovin.impl.mediation.a.a.a$a, reason: collision with other inner class name */
    public enum EnumC0015a {
        SECTION(0),
        NETWORK(1),
        MISSING(2);

        private final int d;

        EnumC0015a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }
    }

    public static int a() {
        return 3;
    }

    public abstract int b();

    public abstract SpannedString c();

    public abstract SpannedString d();
}
