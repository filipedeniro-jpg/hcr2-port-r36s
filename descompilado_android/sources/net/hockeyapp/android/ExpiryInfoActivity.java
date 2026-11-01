package net.hockeyapp.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import net.hockeyapp.android.views.ExpiryInfoView;

/* JADX INFO: loaded from: classes2.dex */
public class ExpiryInfoActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getStringResource(Strings.EXPIRY_INFO_TITLE_ID));
        setContentView(getLayoutView());
    }

    protected View getLayoutView() {
        return new ExpiryInfoView(this, getStringResource(Strings.EXPIRY_INFO_TEXT_ID));
    }

    protected String getStringResource(int i) {
        return Strings.get(UpdateManager.getLastListener(), i);
    }
}
