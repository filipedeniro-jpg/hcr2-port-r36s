package com.google.android.gms.drive.metadata.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.DriveFolder;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class zzk {
    private String zzji;

    @Nullable
    public static zzk zzg(@Nullable String str) {
        Preconditions.checkArgument(str == null || !str.isEmpty());
        if (str == null) {
            return null;
        }
        return new zzk(str);
    }

    private zzk(String str) {
        this.zzji = str.toLowerCase(Locale.US);
    }

    public final boolean isFolder() {
        return this.zzji.equals(DriveFolder.MIME_TYPE);
    }

    public final boolean zzbh() {
        return this.zzji.startsWith("application/vnd.google-apps");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.zzji.equals(((zzk) obj).zzji);
    }

    public final int hashCode() {
        return this.zzji.hashCode();
    }

    public final String toString() {
        return this.zzji;
    }
}
