package com.appsflyer;

/* JADX INFO: loaded from: classes.dex */
final class p implements b {

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private b f194 = this;

    interface b {
        /* JADX INFO: renamed from: ˎ */
        Class<?> mo171(String str) throws ClassNotFoundException;
    }

    enum e {
        UNITY("android_unity", "com.unity3d.player.UnityPlayer"),
        REACT_NATIVE("android_reactNative", "com.facebook.react.ReactApplication"),
        CORDOVA("android_cordova", "org.apache.cordova.CordovaActivity"),
        SEGMENT("android_segment", "com.segment.analytics.integrations.Integration"),
        COCOS2DX("android_cocos2dx", "org.cocos2dx.lib.Cocos2dxActivity"),
        DEFAULT("android_native", "android_native");


        /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
        private String f202;

        /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
        private String f203;

        e(String str, String str2) {
            this.f203 = str;
            this.f202 = str2;
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final String m172() {
        for (e eVar : e.values()) {
            if (m170(eVar.f202)) {
                return eVar.f203;
            }
        }
        return e.DEFAULT.f203;
    }

    p() {
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private boolean m170(String str) {
        try {
            this.f194.mo171(str);
            StringBuilder sb = new StringBuilder("Class: ");
            sb.append(str);
            sb.append(" is found.");
            AFLogger.afRDLog(sb.toString());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            AFLogger.afErrorLog(th.getMessage(), th);
            return false;
        }
    }

    @Override // com.appsflyer.p.b
    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    public final Class<?> mo171(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }
}
