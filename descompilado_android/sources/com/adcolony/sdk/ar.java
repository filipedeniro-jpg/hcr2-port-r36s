package com.adcolony.sdk;

import android.app.Activity;
import android.os.StatFs;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
class ar {
    private String a;
    private String b;
    private String c;
    private String d;
    private File e;
    private File f;
    private File g;

    ar() {
    }

    boolean a() {
        new aa.a().a("Configuring storage").a(aa.d);
        l lVarA = a.a();
        this.a = c() + "/adc3/";
        this.b = this.a + "media/";
        this.e = new File(this.b);
        if (!this.e.isDirectory()) {
            this.e.delete();
            this.e.mkdirs();
        }
        if (!this.e.isDirectory()) {
            lVarA.a(true);
            return false;
        }
        if (a(this.b) < 2.097152E7d) {
            new aa.a().a("Not enough memory available at media path, disabling AdColony.").a(aa.e);
            lVarA.a(true);
            return false;
        }
        this.c = c() + "/adc3/data/";
        this.f = new File(this.c);
        if (!this.f.isDirectory()) {
            this.f.delete();
        }
        this.f.mkdirs();
        this.d = this.a + "tmp/";
        this.g = new File(this.d);
        if (!this.g.isDirectory()) {
            this.g.delete();
            this.g.mkdirs();
        }
        return true;
    }

    boolean b() {
        File file = this.e;
        if (file == null || this.f == null || this.g == null) {
            return false;
        }
        if (!file.isDirectory()) {
            this.e.delete();
        }
        if (!this.f.isDirectory()) {
            this.f.delete();
        }
        if (!this.g.isDirectory()) {
            this.g.delete();
        }
        this.e.mkdirs();
        this.f.mkdirs();
        this.g.mkdirs();
        return true;
    }

    String c() {
        Activity activityC = a.c();
        return activityC == null ? "" : activityC.getFilesDir().getAbsolutePath();
    }

    double a(String str) {
        try {
            StatFs statFs = new StatFs(str);
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (RuntimeException unused) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    String d() {
        return this.b;
    }

    String e() {
        return this.c;
    }

    String f() {
        return this.d;
    }

    String g() {
        return this.a;
    }
}
