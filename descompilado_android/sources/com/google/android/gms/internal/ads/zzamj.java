package com.google.android.gms.internal.ads;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzamj implements DialogInterface.OnClickListener {
    private final /* synthetic */ String zzdfs;
    private final /* synthetic */ String zzdft;
    private final /* synthetic */ zzamk zzdfu;

    zzamj(zzamk zzamkVar, String str, String str2) {
        this.zzdfu = zzamkVar;
        this.zzdfs = str;
        this.zzdft = str2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        DownloadManager downloadManager = (DownloadManager) this.zzdfu.zzlk.getSystemService("download");
        try {
            String str = this.zzdfs;
            String str2 = this.zzdft;
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
            com.google.android.gms.ads.internal.zzp.zzka();
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
            downloadManager.enqueue(request);
        } catch (IllegalStateException unused) {
            this.zzdfu.zzdn("Could not store picture.");
        }
    }
}
