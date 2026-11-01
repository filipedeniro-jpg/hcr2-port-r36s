package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.3z, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class C02243z implements C4M<String> {
    private final int B;

    @InterfaceC01641r
    private final File C;
    private final long D;
    private final long E;

    public C02243z(@InterfaceC01641r File file) {
        this.C = file;
        this.D = file != null ? file.lastModified() : 0L;
        this.E = file != null ? file.length() : 0L;
        this.B = B(file);
    }

    public final int A() {
        return "att".getBytes().length + "len".getBytes().length + "lmt".getBytes().length + 4 + 16;
    }

    private static int B(@InterfaceC01641r File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        int i = 0;
        if (file.isFile()) {
            i = 0 | 1;
        }
        if (file.isDirectory()) {
            i |= 2;
        }
        if (file.isAbsolute()) {
            i |= 4;
        }
        if (!file.isHidden()) {
            return i;
        }
        return i | 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.facebook.ads.redexgen.X.C4M
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final JSONObject uF(String key, JSONObject inputObject) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("att", this.B);
        jSONObject.put("len", this.E);
        jSONObject.put("lmt", this.D);
        inputObject.put(key, jSONObject);
        return inputObject;
    }

    @Override // com.facebook.ads.redexgen.X.C4M
    @SuppressLint({"BadMethodUse-java.io.File.getAbsolutePath"})
    public final boolean SC(Object newMapSignalValueDef) {
        C02243z c02243z = (C02243z) newMapSignalValueDef;
        return this.C != null && c02243z.C != null && this.C.getAbsolutePath().equals(c02243z.C.getAbsolutePath()) && this.B == c02243z.B && this.E == c02243z.E && this.D == c02243z.D;
    }
}
