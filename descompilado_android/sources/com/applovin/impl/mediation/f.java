package com.applovin.impl.mediation;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class f {
    private final Bundle a;

    public static class a {
        private final Bundle a = new Bundle();

        public a a(String str) {
            if (str == null) {
                throw new IllegalArgumentException("No key specified.");
            }
            this.a.remove(str);
            return this;
        }

        public a a(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("No key specified");
            }
            this.a.putString(str, str2);
            return this;
        }

        public f a() {
            return new f(this);
        }
    }

    private f(a aVar) {
        this.a = aVar.a;
    }

    public Bundle a() {
        return this.a;
    }
}
