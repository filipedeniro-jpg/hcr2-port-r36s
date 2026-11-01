package com.fingersoft.hcr2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.io.File;
import java.io.FileWriter;

/* JADX INFO: loaded from: classes.dex */
public class InstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            try {
                String stringExtra = intent.getStringExtra("referrer");
                if (stringExtra != null) {
                    android.util.Log.d("hcr2", "InstallReceiver::onReceive(" + stringExtra + ")");
                    AppActivity appActivity = AppActivity.getInstance();
                    if (appActivity != null && appActivity.hasFinishedLaunching()) {
                        android.util.Log.d("hcr2", "InstallReceiver - forwarding link to activity...");
                        appActivity.checkForDeepLink(intent);
                        appActivity.handlePendingDeepLink();
                    } else {
                        FileWriter fileWriter = new FileWriter(new File(context.getFilesDir().getAbsolutePath() + "/install_receiver"));
                        fileWriter.write(stringExtra);
                        fileWriter.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
