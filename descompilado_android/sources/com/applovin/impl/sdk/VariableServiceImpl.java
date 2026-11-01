package com.applovin.impl.sdk;

import android.os.Bundle;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinSdkUtils;
import com.applovin.sdk.AppLovinVariableService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VariableServiceImpl implements AppLovinVariableService {
    private final h a;
    private AppLovinVariableService.OnVariablesUpdateListener c;
    private Bundle d;
    private final AtomicBoolean b = new AtomicBoolean();
    private final Object e = new Object();

    VariableServiceImpl(h hVar) {
        this.a = hVar;
        String str = (String) hVar.a(com.applovin.impl.sdk.b.d.d);
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            updateVariables(com.applovin.impl.sdk.utils.g.a(str, hVar));
        }
    }

    private Object a(String str, Object obj, Class<?> cls) {
        if (TextUtils.isEmpty(str)) {
            this.a.w().e("AppLovinVariableService", "Unable to retrieve variable value for empty name");
            return obj;
        }
        synchronized (this.e) {
            if (this.d == null) {
                this.a.w().e("AppLovinVariableService", "Unable to retrieve variable value, none retrieved from server yet. Please set a listener to be notified when values are retrieved from the server.");
                return obj;
            }
            if (cls.equals(String.class)) {
                return this.d.getString(str, (String) obj);
            }
            if (cls.equals(Boolean.class)) {
                return Boolean.valueOf(this.d.getBoolean(str, ((Boolean) obj).booleanValue()));
            }
            throw new IllegalStateException("Unable to retrieve variable value for " + str);
        }
    }

    private void a() {
        synchronized (this.e) {
            if (this.c != null && this.d != null) {
                final Bundle bundle = (Bundle) this.d.clone();
                AppLovinSdkUtils.runOnUiThread(true, new Runnable() { // from class: com.applovin.impl.sdk.VariableServiceImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VariableServiceImpl.this.c.onVariablesUpdate(bundle);
                    }
                });
            }
        }
    }

    @Override // com.applovin.sdk.AppLovinVariableService
    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    @Override // com.applovin.sdk.AppLovinVariableService
    public boolean getBoolean(String str, boolean z) {
        return ((Boolean) a(str, Boolean.valueOf(z), Boolean.class)).booleanValue();
    }

    @Override // com.applovin.sdk.AppLovinVariableService
    public String getString(String str) {
        return getString(str, null);
    }

    @Override // com.applovin.sdk.AppLovinVariableService
    public String getString(String str, String str2) {
        return (String) a(str, str2, String.class);
    }

    @Override // com.applovin.sdk.AppLovinVariableService
    public void setOnVariablesUpdateListener(AppLovinVariableService.OnVariablesUpdateListener onVariablesUpdateListener) {
        this.c = onVariablesUpdateListener;
        synchronized (this.e) {
            if (onVariablesUpdateListener != null) {
                if (this.d != null && this.b.compareAndSet(false, true)) {
                    this.a.w().a("AppLovinVariableService", "Setting initial listener");
                    a();
                }
            }
        }
    }

    public String toString() {
        return "VariableService{variables=" + this.d + ", listener=" + this.c + '}';
    }

    public void updateVariables(JSONObject jSONObject) {
        this.a.w().a("AppLovinVariableService", "Updating variables...");
        synchronized (this.e) {
            this.d = com.applovin.impl.sdk.utils.g.c(jSONObject);
            a();
            this.a.a(com.applovin.impl.sdk.b.d.d, jSONObject.toString());
        }
    }
}
