package net.nend.android.internal.a;

import android.text.TextUtils;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: FailedRequestException.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends Exception {
    private final int a;
    private final String b;

    public a(NendVideoAdClientError nendVideoAdClientError) {
        this(nendVideoAdClientError.getCode(), nendVideoAdClientError.getMessage());
    }

    public a(int i, String str) {
        super(str);
        this.a = i;
        this.b = a(i, str);
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "Status = " + this.a + ": " + this.b;
    }

    private String a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return i != 204 ? "Unknown error. Response body is empty." : "No fill ad.";
        }
        try {
            return new JSONObject(str).getString("message");
        } catch (JSONException unused) {
            return str;
        }
    }
}
