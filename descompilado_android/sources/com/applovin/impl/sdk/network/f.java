package com.applovin.impl.sdk.network;

import com.applovin.impl.sdk.h;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class f<T> extends b {

    public static class a<T> extends b.a<T> {
        public a(h hVar) {
            super(hVar);
            this.g = false;
            this.h = ((Integer) hVar.a(com.applovin.impl.sdk.b.b.dz)).intValue();
            this.i = ((Integer) hVar.a(com.applovin.impl.sdk.b.b.dy)).intValue();
            this.j = ((Integer) hVar.a(com.applovin.impl.sdk.b.b.dE)).intValue();
        }

        @Override // com.applovin.impl.sdk.network.b.a
        public /* synthetic */ b.a a(Map map) {
            return b((Map<String, String>) map);
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(T t) {
            this.f = t;
            return this;
        }

        public a b(Map<String, String> map) {
            this.d = map;
            return this;
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(JSONObject jSONObject) {
            this.e = jSONObject;
            return this;
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a a(boolean z) {
            this.k = z;
            return this;
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public f<T> a() {
            return new f<>(this);
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public a a(int i) {
            this.h = i;
            return this;
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public a a(String str) {
            this.b = str;
            return this;
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a b(int i) {
            this.i = i;
            return this;
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public a b(String str) {
            this.a = str;
            return this;
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public a c(int i) {
            this.j = i;
            return this;
        }

        @Override // com.applovin.impl.sdk.network.b.a
        /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
        public a c(String str) {
            this.c = str;
            return this;
        }
    }

    protected f(a aVar) {
        super(aVar);
    }

    public static a b(h hVar) {
        return new a(hVar);
    }
}
