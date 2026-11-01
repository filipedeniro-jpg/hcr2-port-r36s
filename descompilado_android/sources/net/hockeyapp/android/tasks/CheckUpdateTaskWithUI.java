package net.hockeyapp.android.tasks;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import net.hockeyapp.android.Strings;
import net.hockeyapp.android.UpdateActivity;
import net.hockeyapp.android.UpdateFragment;
import net.hockeyapp.android.UpdateManagerListener;
import net.hockeyapp.android.utils.Util;
import net.hockeyapp.android.utils.VersionCache;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class CheckUpdateTaskWithUI extends CheckUpdateTask {
    private Activity activity;
    private AlertDialog dialog;
    protected boolean isDialogRequired;

    public CheckUpdateTaskWithUI(WeakReference<Activity> weakReference, String str, String str2, UpdateManagerListener updateManagerListener, boolean z) {
        super(weakReference, str, str2, updateManagerListener);
        this.activity = null;
        this.dialog = null;
        this.isDialogRequired = false;
        if (weakReference != null) {
            this.activity = weakReference.get();
        }
        this.isDialogRequired = z;
    }

    @Override // net.hockeyapp.android.tasks.CheckUpdateTask
    public void detach() {
        super.detach();
        this.activity = null;
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.dialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.hockeyapp.android.tasks.CheckUpdateTask, android.os.AsyncTask
    public void onPostExecute(JSONArray jSONArray) {
        super.onPostExecute(jSONArray);
        if (jSONArray == null || !this.isDialogRequired) {
            return;
        }
        showDialog(jSONArray);
    }

    @TargetApi(11)
    private void showDialog(final JSONArray jSONArray) {
        if (getCachingEnabled()) {
            VersionCache.setVersionInfo(this.activity, jSONArray.toString());
        }
        Activity activity = this.activity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle(Strings.get(this.listener, 513));
        if (!this.mandatory.booleanValue()) {
            builder.setMessage(Strings.get(this.listener, 514));
            builder.setNegativeButton(Strings.get(this.listener, Strings.UPDATE_DIALOG_NEGATIVE_BUTTON_ID), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.tasks.CheckUpdateTaskWithUI.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    CheckUpdateTaskWithUI.this.cleanUp();
                    if (CheckUpdateTaskWithUI.this.listener != null) {
                        CheckUpdateTaskWithUI.this.listener.onCancel();
                    }
                }
            });
            builder.setPositiveButton(Strings.get(this.listener, Strings.UPDATE_DIALOG_POSITIVE_BUTTON_ID), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.tasks.CheckUpdateTaskWithUI.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (CheckUpdateTaskWithUI.this.getCachingEnabled()) {
                        VersionCache.setVersionInfo(CheckUpdateTaskWithUI.this.activity, "[]");
                    }
                    WeakReference weakReference = new WeakReference(CheckUpdateTaskWithUI.this.activity);
                    if (!Util.fragmentsSupported().booleanValue() || !Util.runsOnTablet(weakReference).booleanValue()) {
                        CheckUpdateTaskWithUI.this.startUpdateIntent(jSONArray, false);
                    } else {
                        CheckUpdateTaskWithUI.this.showUpdateFragment(jSONArray);
                    }
                }
            });
            this.dialog = builder.create();
            this.dialog.show();
            return;
        }
        Toast.makeText(this.activity, Strings.get(this.listener, 512), 1).show();
        startUpdateIntent(jSONArray, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(11)
    public void showUpdateFragment(JSONArray jSONArray) {
        Activity activity = this.activity;
        if (activity != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = activity.getFragmentManager().beginTransaction();
            fragmentTransactionBeginTransaction.setTransition(4097);
            Fragment fragmentFindFragmentByTag = this.activity.getFragmentManager().findFragmentByTag("hockey_update_dialog");
            if (fragmentFindFragmentByTag != null) {
                fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
            }
            fragmentTransactionBeginTransaction.addToBackStack(null);
            Class<? extends UpdateFragment> updateFragmentClass = UpdateFragment.class;
            if (this.listener != null) {
                updateFragmentClass = this.listener.getUpdateFragmentClass();
            }
            try {
                ((DialogFragment) updateFragmentClass.getMethod("newInstance", JSONArray.class, String.class).invoke(null, jSONArray, getURLString("apk"))).show(fragmentTransactionBeginTransaction, "hockey_update_dialog");
            } catch (Exception e) {
                Log.d("HockeyApp", "An exception happened while showing the update fragment:");
                e.printStackTrace();
                Log.d("HockeyApp", "Showing update activity instead.");
                startUpdateIntent(jSONArray, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(11)
    public void startUpdateIntent(JSONArray jSONArray, Boolean bool) {
        Class<? extends UpdateActivity> updateActivityClass = this.listener != null ? this.listener.getUpdateActivityClass() : null;
        if (updateActivityClass == null) {
            updateActivityClass = UpdateActivity.class;
        }
        if (this.activity != null) {
            Intent intent = new Intent();
            intent.setClass(this.activity, updateActivityClass);
            intent.putExtra("json", jSONArray.toString());
            intent.putExtra("url", getURLString("apk"));
            this.activity.startActivity(intent);
            if (bool.booleanValue()) {
                this.activity.finish();
            }
        }
        cleanUp();
    }

    @Override // net.hockeyapp.android.tasks.CheckUpdateTask
    protected void cleanUp() {
        super.cleanUp();
        this.activity = null;
        this.dialog = null;
    }
}
