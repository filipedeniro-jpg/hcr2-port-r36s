package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

/* JADX INFO: loaded from: classes2.dex */
final class zzcf implements DrivePreferencesApi.FileUploadPreferencesResult {
    private final Status zzdy;
    private final FileUploadPreferences zzfm;

    private zzcf(zzcb zzcbVar, Status status, FileUploadPreferences fileUploadPreferences) {
        this.zzdy = status;
        this.zzfm = fileUploadPreferences;
    }

    @Override // com.google.android.gms.drive.DrivePreferencesApi.FileUploadPreferencesResult
    public final FileUploadPreferences getFileUploadPreferences() {
        return this.zzfm;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzdy;
    }

    /* synthetic */ zzcf(zzcb zzcbVar, Status status, FileUploadPreferences fileUploadPreferences, zzcc zzccVar) {
        this(zzcbVar, status, fileUploadPreferences);
    }
}
