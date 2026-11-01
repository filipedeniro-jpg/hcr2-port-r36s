package com.google.android.gms.drive;

import android.content.IntentSender;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.drive.zzbi;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class CreateFileActivityBuilder {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    private final com.google.android.gms.internal.drive.zzt zzn = new com.google.android.gms.internal.drive.zzt(0);

    @Nullable
    private DriveContents zzo;
    private boolean zzp;

    public CreateFileActivityBuilder setInitialDriveContents(@Nullable DriveContents driveContents) {
        if (driveContents != null) {
            if (!(driveContents instanceof zzbi)) {
                throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
            }
            if (driveContents.getDriveId() != null) {
                throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
            }
            if (driveContents.zzk()) {
                throw new IllegalArgumentException("DriveContents are already closed.");
            }
            this.zzn.zzd(driveContents.zzi().zzj);
            this.zzo = driveContents;
        } else {
            this.zzn.zzd(1);
        }
        this.zzp = true;
        return this;
    }

    final int getRequestId() {
        return this.zzn.getRequestId();
    }

    public CreateFileActivityBuilder setInitialMetadata(MetadataChangeSet metadataChangeSet) {
        this.zzn.zza(metadataChangeSet);
        return this;
    }

    final MetadataChangeSet zzc() {
        return this.zzn.zzc();
    }

    public CreateFileActivityBuilder setActivityStartFolder(DriveId driveId) {
        this.zzn.zza(driveId);
        return this;
    }

    final DriveId zzd() {
        return this.zzn.zzd();
    }

    public CreateFileActivityBuilder setActivityTitle(String str) {
        this.zzn.zzc(str);
        return this;
    }

    final String zze() {
        return this.zzn.zze();
    }

    final int zzf() {
        com.google.android.gms.internal.drive.zzt zztVar = this.zzn;
        return 0;
    }

    final void zzg() {
        Preconditions.checkState(this.zzp, "Must call setInitialDriveContents.");
        DriveContents driveContents = this.zzo;
        if (driveContents != null) {
            driveContents.zzj();
        }
        this.zzn.zzg();
    }

    public IntentSender build(GoogleApiClient googleApiClient) {
        Preconditions.checkState(googleApiClient.isConnected(), "Client must be connected");
        zzg();
        return this.zzn.build(googleApiClient);
    }
}
