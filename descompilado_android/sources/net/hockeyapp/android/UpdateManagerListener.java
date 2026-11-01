package net.hockeyapp.android;

import java.util.Date;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public abstract class UpdateManagerListener extends StringListener {
    public boolean canUpdateInMarket() {
        return false;
    }

    public Date getExpiryDate() {
        return null;
    }

    public boolean onBuildExpired() {
        return true;
    }

    public void onCancel() {
    }

    public void onNoUpdateAvailable() {
    }

    public void onUpdateAvailable() {
    }

    public void onUpdatePermissionsNotGranted() {
    }

    public Class<? extends UpdateActivity> getUpdateActivityClass() {
        return UpdateActivity.class;
    }

    public Class<? extends UpdateFragment> getUpdateFragmentClass() {
        return UpdateFragment.class;
    }

    public void onUpdateAvailable(JSONArray jSONArray, String str) {
        onUpdateAvailable();
    }
}
