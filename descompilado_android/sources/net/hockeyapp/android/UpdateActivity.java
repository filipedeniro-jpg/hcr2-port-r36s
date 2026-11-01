package net.hockeyapp.android;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.device.ads.WebRequest;
import net.hockeyapp.android.listeners.DownloadFileListener;
import net.hockeyapp.android.objects.ErrorObject;
import net.hockeyapp.android.tasks.DownloadFileTask;
import net.hockeyapp.android.tasks.GetFileSizeTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.VersionHelper;
import net.hockeyapp.android.views.UpdateView;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateActivity extends Activity implements UpdateActivityInterface, UpdateInfoListener, View.OnClickListener {
    private final int DIALOG_ERROR_ID = 0;
    private Context context;
    protected DownloadFileTask downloadTask;
    private ErrorObject error;
    protected VersionHelper versionHelper;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle("App Update");
        setContentView(getLayoutView());
        this.context = this;
        this.versionHelper = new VersionHelper(this, getIntent().getStringExtra("json"), this);
        configureView();
        this.downloadTask = (DownloadFileTask) getLastNonConfigurationInstance();
        DownloadFileTask downloadFileTask = this.downloadTask;
        if (downloadFileTask != null) {
            downloadFileTask.attach(this);
        }
    }

    protected void configureView() {
        String str;
        ((TextView) findViewById(4098)).setText(getAppName());
        final TextView textView = (TextView) findViewById(4099);
        final String str2 = "Version " + this.versionHelper.getVersionString();
        final String fileDateString = this.versionHelper.getFileDateString();
        long fileSizeBytes = this.versionHelper.getFileSizeBytes();
        if (fileSizeBytes >= 0) {
            str = String.format("%.2f", Float.valueOf(fileSizeBytes / 1048576.0f)) + " MB";
        } else {
            AsyncTaskUtils.execute(new GetFileSizeTask(this, getIntent().getStringExtra("url"), new DownloadFileListener() { // from class: net.hockeyapp.android.UpdateActivity.1
                @Override // net.hockeyapp.android.listeners.DownloadFileListener
                public void downloadSuccessful(DownloadFileTask downloadFileTask) {
                    if (downloadFileTask instanceof GetFileSizeTask) {
                        String str3 = String.format("%.2f", Float.valueOf(((GetFileSizeTask) downloadFileTask).getSize() / 1048576.0f)) + " MB";
                        textView.setText(str2 + "\n" + fileDateString + " - " + str3);
                    }
                }
            }));
            str = "Unknown size";
        }
        textView.setText(str2 + "\n" + fileDateString + " - " + str);
        ((Button) findViewById(UpdateView.UPDATE_BUTTON_ID)).setOnClickListener(this);
        WebView webView = (WebView) findViewById(UpdateView.WEB_VIEW_ID);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL(Constants.BASE_URL, getReleaseNotes(), WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
    }

    protected String getReleaseNotes() {
        return this.versionHelper.getReleaseNotes(false);
    }

    @Override // android.app.Activity
    public Object onRetainNonConfigurationInstance() {
        DownloadFileTask downloadFileTask = this.downloadTask;
        if (downloadFileTask != null) {
            downloadFileTask.detach();
        }
        return this.downloadTask;
    }

    protected void startDownloadTask() {
        startDownloadTask(getIntent().getStringExtra("url"));
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        enableUpdateButton();
        if (strArr.length == 0 || iArr.length == 0 || i != 1) {
            return;
        }
        if (iArr[0] == 0) {
            prepareDownload();
            return;
        }
        Log.w("HockeyApp", "User denied write permission, can't continue with updater task.");
        UpdateManagerListener lastListener = UpdateManager.getLastListener();
        if (lastListener != null) {
            lastListener.onUpdatePermissionsNotGranted();
        } else {
            new AlertDialog.Builder(this.context).setTitle(Strings.get(Strings.PERMISSION_UPDATE_TITLE_ID)).setMessage(Strings.get(Strings.PERMISSION_UPDATE_MESSAGE_ID)).setNegativeButton(Strings.get(Strings.PERMISSION_DIALOG_NEGATIVE_BUTTON_ID), (DialogInterface.OnClickListener) null).setPositiveButton(Strings.get(Strings.PERMISSION_DIALOG_POSITIVE_BUTTON_ID), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.UpdateActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    this.prepareDownload();
                }
            }).create().show();
        }
    }

    protected void startDownloadTask(String str) {
        createDownloadTask(str, new DownloadFileListener() { // from class: net.hockeyapp.android.UpdateActivity.3
            @Override // net.hockeyapp.android.listeners.DownloadFileListener
            public void downloadSuccessful(DownloadFileTask downloadFileTask) {
                UpdateActivity.this.enableUpdateButton();
            }

            @Override // net.hockeyapp.android.listeners.DownloadFileListener
            public void downloadFailed(DownloadFileTask downloadFileTask, Boolean bool) {
                if (bool.booleanValue()) {
                    UpdateActivity.this.startDownloadTask();
                } else {
                    UpdateActivity.this.enableUpdateButton();
                }
            }

            @Override // net.hockeyapp.android.StringListener
            public String getStringForResource(int i) {
                UpdateManagerListener lastListener = UpdateManager.getLastListener();
                if (lastListener != null) {
                    return lastListener.getStringForResource(i);
                }
                return null;
            }
        });
        AsyncTaskUtils.execute(this.downloadTask);
    }

    protected void createDownloadTask(String str, DownloadFileListener downloadFileListener) {
        this.downloadTask = new DownloadFileTask(this, str, downloadFileListener);
    }

    public void enableUpdateButton() {
        findViewById(UpdateView.UPDATE_BUTTON_ID).setEnabled(true);
    }

    @Override // net.hockeyapp.android.UpdateInfoListener
    public int getCurrentVersionCode() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 128).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    @Override // net.hockeyapp.android.UpdateActivityInterface
    public ViewGroup getLayoutView() {
        return new UpdateView(this);
    }

    public String getAppName() {
        try {
            PackageManager packageManager = getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(getPackageName(), 0)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    private boolean isWriteExternalStorageSet(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    private boolean isUnknownSourcesChecked() {
        try {
            if (Build.VERSION.SDK_INT < 17 || Build.VERSION.SDK_INT >= 21) {
                return Settings.Secure.getInt(getContentResolver(), "install_non_market_apps") == 1;
            }
            return Settings.Global.getInt(getContentResolver(), "install_non_market_apps") == 1;
        } catch (Settings.SettingNotFoundException unused) {
            return true;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        prepareDownload();
        view.setEnabled(false);
    }

    protected void prepareDownload() {
        if (!isWriteExternalStorageSet(this.context)) {
            if (Build.VERSION.SDK_INT >= 23) {
                requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
                return;
            }
            this.error = new ErrorObject();
            this.error.setMessage("The permission to access the external storage permission is not set. Please contact the developer.");
            runOnUiThread(new Runnable() { // from class: net.hockeyapp.android.UpdateActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
            return;
        }
        if (!isUnknownSourcesChecked()) {
            this.error = new ErrorObject();
            this.error.setMessage("The installation from unknown sources is not enabled. Please check the device settings.");
            runOnUiThread(new Runnable() { // from class: net.hockeyapp.android.UpdateActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    UpdateActivity.this.showDialog(0);
                }
            });
            return;
        }
        startDownloadTask();
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        return onCreateDialog(i, null);
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        if (i != 0) {
            return null;
        }
        return new AlertDialog.Builder(this).setMessage("An error has occured").setCancelable(false).setTitle("Error").setIcon(R.drawable.ic_dialog_alert).setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.UpdateActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                UpdateActivity.this.error = null;
                dialogInterface.cancel();
            }
        }).create();
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i, Dialog dialog) {
        if (i != 0) {
            return;
        }
        AlertDialog alertDialog = (AlertDialog) dialog;
        ErrorObject errorObject = this.error;
        if (errorObject != null) {
            alertDialog.setMessage(errorObject.getMessage());
        } else {
            alertDialog.setMessage("An unknown error has occured.");
        }
    }
}
