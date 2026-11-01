package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes2.dex */
public class zzq {
    public final String zzba;
    public final DriveId zzbd;
    public final MetadataBundle zzde;
    public final Integer zzdk;
    public final int zzdl;

    protected zzq(MetadataBundle metadataBundle, Integer num, String str, DriveId driveId, int i) {
        this.zzde = metadataBundle;
        this.zzdk = num;
        this.zzba = str;
        this.zzbd = driveId;
        this.zzdl = i;
    }
}
