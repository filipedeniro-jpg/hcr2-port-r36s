package net.hockeyapp.android;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.device.ads.WebRequest;
import net.hockeyapp.android.listeners.DownloadFileListener;
import net.hockeyapp.android.tasks.DownloadFileTask;
import net.hockeyapp.android.tasks.GetFileSizeTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.VersionHelper;
import net.hockeyapp.android.views.UpdateView;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateFragment extends DialogFragment implements View.OnClickListener, UpdateInfoListener {
    private DownloadFileTask downloadTask;
    private String urlString;
    private VersionHelper versionHelper;
    private JSONArray versionInfo;

    public static UpdateFragment newInstance(JSONArray jSONArray, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        bundle.putString("versionInfo", jSONArray.toString());
        UpdateFragment updateFragment = new UpdateFragment();
        updateFragment.setArguments(bundle);
        return updateFragment;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.urlString = getArguments().getString("url");
            this.versionInfo = new JSONArray(getArguments().getString("versionInfo"));
            setStyle(1, R.style.Theme.Holo.Light.Dialog);
        } catch (JSONException unused) {
            dismiss();
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String str;
        View layoutView = getLayoutView();
        this.versionHelper = new VersionHelper(getActivity(), this.versionInfo.toString(), this);
        ((TextView) layoutView.findViewById(4098)).setText(getAppName());
        final TextView textView = (TextView) layoutView.findViewById(4099);
        final String str2 = "Version " + this.versionHelper.getVersionString();
        final String fileDateString = this.versionHelper.getFileDateString();
        long fileSizeBytes = this.versionHelper.getFileSizeBytes();
        if (fileSizeBytes >= 0) {
            str = String.format("%.2f", Float.valueOf(fileSizeBytes / 1048576.0f)) + " MB";
        } else {
            AsyncTaskUtils.execute(new GetFileSizeTask(getActivity(), this.urlString, new DownloadFileListener() { // from class: net.hockeyapp.android.UpdateFragment.1
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
        ((Button) layoutView.findViewById(UpdateView.UPDATE_BUTTON_ID)).setOnClickListener(this);
        WebView webView = (WebView) layoutView.findViewById(UpdateView.WEB_VIEW_ID);
        webView.clearCache(true);
        webView.destroyDrawingCache();
        webView.loadDataWithBaseURL(Constants.BASE_URL, this.versionHelper.getReleaseNotes(false), WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
        return layoutView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        prepareDownload();
    }

    public void prepareDownload() {
        if (Build.VERSION.SDK_INT >= 23 && getActivity().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        } else {
            startDownloadTask(getActivity());
            dismiss();
        }
    }

    @Override // android.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr.length == 0 || iArr.length == 0 || i != 1) {
            return;
        }
        if (iArr[0] == 0) {
            startDownloadTask(getActivity());
            return;
        }
        Log.w("HockeyApp", "User denied write permission, can't continue with updater task.");
        UpdateManagerListener lastListener = UpdateManager.getLastListener();
        if (lastListener != null) {
            lastListener.onUpdatePermissionsNotGranted();
        } else {
            new AlertDialog.Builder(getActivity()).setTitle(Strings.get(Strings.PERMISSION_UPDATE_TITLE_ID)).setMessage(Strings.get(Strings.PERMISSION_UPDATE_MESSAGE_ID)).setNegativeButton(Strings.get(Strings.PERMISSION_DIALOG_NEGATIVE_BUTTON_ID), (DialogInterface.OnClickListener) null).setPositiveButton(Strings.get(Strings.PERMISSION_DIALOG_POSITIVE_BUTTON_ID), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.UpdateFragment.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    this.prepareDownload();
                }
            }).create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDownloadTask(final Activity activity) {
        this.downloadTask = new DownloadFileTask(activity, this.urlString, new DownloadFileListener() { // from class: net.hockeyapp.android.UpdateFragment.3
            @Override // net.hockeyapp.android.listeners.DownloadFileListener
            public void downloadSuccessful(DownloadFileTask downloadFileTask) {
            }

            @Override // net.hockeyapp.android.listeners.DownloadFileListener
            public void downloadFailed(DownloadFileTask downloadFileTask, Boolean bool) {
                if (bool.booleanValue()) {
                    UpdateFragment.this.startDownloadTask(activity);
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

    @Override // net.hockeyapp.android.UpdateInfoListener
    public int getCurrentVersionCode() {
        try {
            return getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 128).versionCode;
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            return -1;
        }
    }

    public String getAppName() {
        Activity activity = getActivity();
        try {
            PackageManager packageManager = activity.getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(activity.getPackageName(), 0)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public View getLayoutView() {
        return new UpdateView(getActivity(), false, true);
    }
}
