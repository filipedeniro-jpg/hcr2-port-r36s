package com.chartboost.sdk.Libraries;

import com.my.target.ai;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class g {
    public final File a;
    public final File b;
    public final File c;
    public final File d;
    public final File e;
    public final File f;
    public final File g;

    g(File file) {
        this.a = new File(file, ".chartboost");
        if (!this.a.exists()) {
            this.a.mkdirs();
        }
        this.b = a(this.a, "css");
        this.c = a(this.a, ai.a.da);
        this.d = a(this.a, "images");
        this.e = a(this.a, "js");
        this.f = a(this.a, "templates");
        this.g = a(this.a, "videos");
    }

    private static File a(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file2;
    }
}
