package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdqn extends IOException {
    private zzdrn zzhla;

    public zzdqn(String str) {
        super(str);
        this.zzhla = null;
    }

    public final zzdqn zzo(zzdrn zzdrnVar) {
        this.zzhla = zzdrnVar;
        return this;
    }

    static zzdqn zzazx() {
        return new zzdqn("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzdqn zzazy() {
        return new zzdqn("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzdqn zzazz() {
        return new zzdqn("CodedInputStream encountered a malformed varint.");
    }

    static zzdqn zzbaa() {
        return new zzdqn("Protocol message contained an invalid tag (zero).");
    }

    static zzdqn zzbab() {
        return new zzdqn("Protocol message end-group tag did not match expected tag.");
    }

    static zzdqm zzbac() {
        return new zzdqm("Protocol message tag had invalid wire type.");
    }

    static zzdqn zzbad() {
        return new zzdqn("Failed to parse the message.");
    }

    static zzdqn zzbae() {
        return new zzdqn("Protocol message had invalid UTF-8.");
    }
}
