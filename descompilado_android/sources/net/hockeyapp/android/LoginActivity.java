package net.hockeyapp.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.appevents.AppEventsConstants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import net.hockeyapp.android.tasks.LoginTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.views.LoginView;

/* JADX INFO: loaded from: classes2.dex */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Handler loginHandler;
    private LoginTask loginTask;
    private int mode;
    private String secret;
    private String url;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new LoginView(this));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.url = extras.getString("url");
            this.secret = extras.getString("secret");
            this.mode = extras.getInt("mode");
        }
        configureView();
        initLoginHandler();
        Object lastNonConfigurationInstance = getLastNonConfigurationInstance();
        if (lastNonConfigurationInstance != null) {
            this.loginTask = (LoginTask) lastNonConfigurationInstance;
            this.loginTask.attach(this, this.loginHandler);
        }
    }

    private void configureView() {
        if (this.mode == 1) {
            ((EditText) findViewById(12292)).setVisibility(4);
        }
        ((Button) findViewById(LoginView.LOGIN_BUTTON_ID)).setOnClickListener(this);
    }

    private void initLoginHandler() {
        this.loginHandler = new Handler() { // from class: net.hockeyapp.android.LoginActivity.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.getData().getBoolean("success")) {
                    LoginActivity.this.finish();
                    if (LoginManager.listener != null) {
                        LoginManager.listener.onSuccess();
                        return;
                    }
                    return;
                }
                Toast.makeText(LoginActivity.this, "Login failed. Check your credentials.", 2000).show();
            }
        };
    }

    private void performAuthentication() {
        String string = ((EditText) findViewById(12291)).getText().toString();
        String string2 = ((EditText) findViewById(12292)).getText().toString();
        HashMap map = new HashMap();
        int i = this.mode;
        boolean z = false;
        if (i == 1) {
            z = !TextUtils.isEmpty(string);
            map.put("email", string);
            map.put("authcode", md5(this.secret + string));
        } else if (i == 2) {
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                z = true;
            }
            map.put("email", string);
            map.put("password", string2);
        }
        if (z) {
            this.loginTask = new LoginTask(this, this.loginHandler, this.url, this.mode, map);
            AsyncTaskUtils.execute(this.loginTask);
        } else {
            Toast.makeText(this, Strings.get(Strings.LOGIN_MISSING_CREDENTIALS_TOAST_ID), 1000).show();
        }
    }

    public String md5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = AppEventsConstants.EVENT_PARAM_VALUE_NO + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != 12293) {
            return;
        }
        performAuthentication();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (LoginManager.listener != null) {
                LoginManager.listener.onBack();
            } else {
                Intent intent = new Intent(this, LoginManager.mainActivity);
                intent.setFlags(67108864);
                intent.putExtra("net.hockeyapp.android.EXIT", true);
                startActivity(intent);
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public Object onRetainNonConfigurationInstance() {
        LoginTask loginTask = this.loginTask;
        if (loginTask != null) {
            loginTask.detach();
        }
        return this.loginTask;
    }
}
