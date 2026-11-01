package com.facebook.ads.redexgen.X;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LS extends LP {
    public LS(String path, LW params) {
        super(path, null);
        this.D = LR.POST;
        this.E = path;
        this.C = "application/x-www-form-urlencoded;charset=UTF-8";
        if (params != null) {
            this.B = params.E();
        }
    }

    public LS(String path, LW params, String contentType, byte[] data) {
        super(path, params);
        this.D = LR.POST;
        this.C = contentType;
        this.B = data;
    }
}
