package net.hockeyapp.android.objects;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorObject implements Serializable {
    private static final long serialVersionUID = 1508110658372169868L;
    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
