package com.google.android.gms.drive;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.drive.events.ListenerToken;
import com.google.android.gms.drive.events.OnChangeListener;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.tasks.Task;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class DriveResourceClient extends GoogleApi<Drive.zza> {
    public DriveResourceClient(@NonNull Context context, @Nullable Drive.zza zzaVar) {
        super(context, Drive.zzw, zzaVar, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Deprecated
    public abstract Task<ListenerToken> addChangeListener(@NonNull DriveResource driveResource, @NonNull OnChangeListener onChangeListener);

    @Deprecated
    public abstract Task<Void> addChangeSubscription(@NonNull DriveResource driveResource);

    @Deprecated
    public abstract Task<Boolean> cancelOpenFileCallback(@NonNull ListenerToken listenerToken);

    @Deprecated
    public abstract Task<Void> commitContents(@NonNull DriveContents driveContents, @Nullable MetadataChangeSet metadataChangeSet);

    @Deprecated
    public abstract Task<Void> commitContents(@NonNull DriveContents driveContents, @Nullable MetadataChangeSet metadataChangeSet, @NonNull ExecutionOptions executionOptions);

    @Deprecated
    public abstract Task<DriveContents> createContents();

    @Deprecated
    public abstract Task<DriveFile> createFile(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents);

    @Deprecated
    public abstract Task<DriveFile> createFile(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet, @Nullable DriveContents driveContents, @NonNull ExecutionOptions executionOptions);

    @Deprecated
    public abstract Task<DriveFolder> createFolder(@NonNull DriveFolder driveFolder, @NonNull MetadataChangeSet metadataChangeSet);

    @Deprecated
    public abstract Task<Void> delete(@NonNull DriveResource driveResource);

    @Deprecated
    public abstract Task<Void> discardContents(@NonNull DriveContents driveContents);

    @Deprecated
    public abstract Task<DriveFolder> getAppFolder();

    @Deprecated
    public abstract Task<Metadata> getMetadata(@NonNull DriveResource driveResource);

    @Deprecated
    public abstract Task<DriveFolder> getRootFolder();

    @Deprecated
    public abstract Task<MetadataBuffer> listChildren(@NonNull DriveFolder driveFolder);

    @Deprecated
    public abstract Task<MetadataBuffer> listParents(@NonNull DriveResource driveResource);

    @Deprecated
    public abstract Task<DriveContents> openFile(@NonNull DriveFile driveFile, int i);

    @Deprecated
    public abstract Task<ListenerToken> openFile(@NonNull DriveFile driveFile, int i, @NonNull OpenFileCallback openFileCallback);

    @Deprecated
    public abstract Task<MetadataBuffer> query(@NonNull Query query);

    @Deprecated
    public abstract Task<MetadataBuffer> queryChildren(@NonNull DriveFolder driveFolder, @NonNull Query query);

    @Deprecated
    public abstract Task<Boolean> removeChangeListener(@NonNull ListenerToken listenerToken);

    @Deprecated
    public abstract Task<Void> removeChangeSubscription(@NonNull DriveResource driveResource);

    @Deprecated
    public abstract Task<DriveContents> reopenContentsForWrite(@NonNull DriveContents driveContents);

    @Deprecated
    public abstract Task<Void> setParents(@NonNull DriveResource driveResource, @NonNull Set<DriveId> set);

    @Deprecated
    public abstract Task<Void> trash(@NonNull DriveResource driveResource);

    @Deprecated
    public abstract Task<Void> untrash(@NonNull DriveResource driveResource);

    @Deprecated
    public abstract Task<Metadata> updateMetadata(@NonNull DriveResource driveResource, @NonNull MetadataChangeSet metadataChangeSet);

    public DriveResourceClient(@NonNull Activity activity, @Nullable Drive.zza zzaVar) {
        super(activity, Drive.zzw, zzaVar, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}
