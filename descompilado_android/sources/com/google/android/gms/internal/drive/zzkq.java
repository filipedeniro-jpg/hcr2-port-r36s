package com.google.android.gms.internal.drive;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class zzkq extends IOException {
    private zzlq zzsq;

    public zzkq(String str) {
        super(str);
        this.zzsq = null;
    }

    public final zzkq zzg(zzlq zzlqVar) {
        this.zzsq = zzlqVar;
        return this;
    }

    static zzkq zzdi() {
        return new zzkq("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzkq zzdj() {
        return new zzkq("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzkq zzdk() {
        return new zzkq("Protocol message contained an invalid tag (zero).");
    }

    static zzkr zzdl() {
        return new zzkr("Protocol message tag had invalid wire type.");
    }

    static zzkq zzdm() {
        return new zzkq("Failed to parse the message.");
    }

    static zzkq zzdn() {
        return new zzkq("Protocol message had invalid UTF-8.");
    }
}
