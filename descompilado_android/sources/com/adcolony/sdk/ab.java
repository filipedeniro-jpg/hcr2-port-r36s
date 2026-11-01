package com.adcolony.sdk;

import com.appsflyer.share.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
class ab {
    static final SimpleDateFormat l = new SimpleDateFormat("yyyyMMdd'T'HHmmss.SSSZ");
    static final String m = "message";
    static final String n = "timestamp";
    private Date a;
    private int b;
    private x c;
    protected String o;

    ab() {
    }

    void a(x xVar) {
        this.c = xVar;
    }

    void a(int i) {
        this.b = i;
    }

    String b() {
        int i = this.b;
        if (i == -1) {
            return "Fatal";
        }
        if (i == 0) {
            return "Error";
        }
        if (i == 1) {
            return "Warn";
        }
        if (i != 2) {
            return i != 3 ? "UNKNOWN LOG LEVEL" : "Debug";
        }
        return "Info";
    }

    int c() {
        return this.b;
    }

    String d() {
        return this.o;
    }

    String e() {
        return l.format(this.a);
    }

    x f() {
        return this.c;
    }

    public String toString() {
        return e() + " " + b() + Constants.URL_PATH_DELIMITER + f().d() + ": " + d();
    }

    static class a {
        protected ab b = new ab();

        a() {
        }

        a a(int i) {
            this.b.b = i;
            return this;
        }

        a a(x xVar) {
            this.b.c = xVar;
            return this;
        }

        a a(String str) {
            this.b.o = str;
            return this;
        }

        a a(Date date) {
            this.b.a = date;
            return this;
        }

        ab a() {
            if (this.b.a == null) {
                this.b.a = new Date(System.currentTimeMillis());
            }
            return this.b;
        }
    }
}
