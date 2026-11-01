package com.my.target.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MyTargetActivity extends Activity {

    @Nullable
    public static ActivityEngine activityEngine;

    @Nullable
    private ActivityEngine engine;

    @Nullable
    private FrameLayout rootLayout;

    public interface ActivityEngine {
        boolean onActivityBackPressed();

        void onActivityCreate(@NonNull MyTargetActivity myTargetActivity, @NonNull Intent intent, @NonNull FrameLayout frameLayout);

        void onActivityDestroy();

        boolean onActivityOptionsItemSelected(MenuItem menuItem);

        void onActivityPause();

        void onActivityResume();

        void onActivityStart();

        void onActivityStop();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        ActivityEngine activityEngine2 = this.engine;
        if (activityEngine2 == null || activityEngine2.onActivityBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ActivityEngine activityEngine2 = this.engine;
        if (activityEngine2 == null || !activityEngine2.onActivityOptionsItemSelected(menuItem)) {
            return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.engine = activityEngine;
        activityEngine = null;
        if (this.engine != null && intent != null) {
            this.rootLayout = new FrameLayout(this);
            this.engine.onActivityCreate(this, intent, this.rootLayout);
            setContentView(this.rootLayout);
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        ActivityEngine activityEngine2 = this.engine;
        if (activityEngine2 != null) {
            activityEngine2.onActivityStart();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        ActivityEngine activityEngine2 = this.engine;
        if (activityEngine2 != null) {
            activityEngine2.onActivityResume();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        ActivityEngine activityEngine2 = this.engine;
        if (activityEngine2 != null) {
            activityEngine2.onActivityPause();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        ActivityEngine activityEngine2 = this.engine;
        if (activityEngine2 != null) {
            activityEngine2.onActivityStop();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        ActivityEngine activityEngine2 = this.engine;
        if (activityEngine2 != null) {
            activityEngine2.onActivityDestroy();
        }
    }
}
