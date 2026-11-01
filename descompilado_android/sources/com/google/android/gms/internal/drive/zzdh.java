package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes2.dex */
final class zzdh extends TaskApiCall<zzaw, DriveFile> {
    private final DriveFolder zzfj;
    private final MetadataChangeSet zzgc;
    private ExecutionOptions zzgd;
    private String zzge = null;
    private com.google.android.gms.drive.metadata.internal.zzk zzgf;
    private final DriveContents zzo;

    zzdh(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents, @NonNull ExecutionOptions executionOptions, @Nullable String str) {
        this.zzfj = driveFolder;
        this.zzgc = metadataChangeSet;
        this.zzo = driveContents;
        this.zzgd = executionOptions;
        Preconditions.checkNotNull(driveFolder, "DriveFolder must not be null");
        Preconditions.checkNotNull(driveFolder.getDriveId(), "Folder's DriveId must not be null");
        Preconditions.checkNotNull(metadataChangeSet, "MetadataChangeSet must not be null");
        Preconditions.checkNotNull(executionOptions, "ExecutionOptions must not be null");
        this.zzgf = com.google.android.gms.drive.metadata.internal.zzk.zzg(metadataChangeSet.getMimeType());
        com.google.android.gms.drive.metadata.internal.zzk zzkVar = this.zzgf;
        if (zzkVar != null && zzkVar.isFolder()) {
            throw new IllegalArgumentException("May not create folders using this method. Use DriveFolderManagerClient#createFolder() instead of mime type application/vnd.google-apps.folder");
        }
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
        }
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<DriveFile> taskCompletionSource) throws RemoteException {
        zzaw zzawVar = (zzaw) anyClient;
        this.zzgd.zza(zzawVar);
        MetadataChangeSet metadataChangeSet = this.zzgc;
        metadataChangeSet.zzq().zza(zzawVar.getContext());
        int iZza = zzbs.zza(this.zzo, this.zzgf);
        com.google.android.gms.drive.metadata.internal.zzk zzkVar = this.zzgf;
        ((zzeo) zzawVar.getService()).zza(new zzw(this.zzfj.getDriveId(), metadataChangeSet.zzq(), iZza, (zzkVar == null || !zzkVar.zzbh()) ? 0 : 1, this.zzgd), new zzhj(taskCompletionSource));
    }
}
