package com.appsflyer;

/* JADX INFO: loaded from: classes.dex */
final class o {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private boolean f187;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private a f188;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private String f189;

    o(a aVar, String str, boolean z) {
        this.f188 = aVar;
        this.f189 = str;
        this.f187 = z;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final String m168() {
        return this.f189;
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final boolean m169() {
        return this.f187;
    }

    public final String toString() {
        return String.format("%s,%s", this.f189, Boolean.valueOf(this.f187));
    }

    enum a {
        GOOGLE(0),
        AMAZON(1);


        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private int f193;

        a(int i) {
            this.f193 = i;
        }

        @Override // java.lang.Enum
        public final String toString() {
            return String.valueOf(this.f193);
        }
    }
}
