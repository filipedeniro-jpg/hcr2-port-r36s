package com.adcolony.sdk;

/* JADX INFO: loaded from: classes.dex */
class aa {
    static aa a = new aa(3, false);
    static aa b = new aa(3, true);
    static aa c = new aa(2, false);
    static aa d = new aa(2, true);
    static aa e = new aa(1, false);
    static aa f = new aa(1, true);
    static aa g = new aa(0, false);
    static aa h = new aa(0, true);
    private int i;
    private boolean j;

    aa(int i, boolean z) {
        this.i = i;
        this.j = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        ac.a(this.i, str, this.j);
    }

    static class a {
        StringBuilder a = new StringBuilder();

        a() {
        }

        a a(char c) {
            if (c != '\n') {
                this.a.append(c);
            }
            return this;
        }

        a a(String str) {
            this.a.append(str);
            return this;
        }

        a a(Object obj) {
            if (obj != null) {
                this.a.append(obj.toString());
            } else {
                this.a.append("null");
            }
            return this;
        }

        a a(double d) {
            aw.a(d, 2, this.a);
            return this;
        }

        a a(int i) {
            this.a.append(i);
            return this;
        }

        a a(boolean z) {
            this.a.append(z);
            return this;
        }

        void a(aa aaVar) {
            aaVar.a(this.a.toString());
        }
    }
}
