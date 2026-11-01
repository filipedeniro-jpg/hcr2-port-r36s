package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.chukong.cocosplay.client.CocosPlayClient;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Cocos2dxActivity extends Activity implements Cocos2dxHelper.Cocos2dxHelperListener {
    public static final String APP_TAG = "hcr2_cocos - ";
    private static final String TAG = "hcr2_cocos - Cocos2dxActivity";
    private static Cocos2dxActivity sContext;
    private Cocos2dxGLSurfaceView mGLSurfaceView = null;
    private int[] mGLContextAttrs = null;
    private Cocos2dxHandler mHandler = null;
    private Cocos2dxVideoHelper mVideoHelper = null;
    private Cocos2dxWebViewHelper mWebViewHelper = null;
    protected FrameLayout mFrameLayout = null;

    private static native int[] getGLContextAttrs();

    public class Cocos2dxEGLConfigChooser implements GLSurfaceView.EGLConfigChooser {
        protected int[] configAttribs;

        public Cocos2dxEGLConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
            this.configAttribs = new int[]{i, i2, i3, i4, i5, i6};
        }

        public Cocos2dxEGLConfigChooser(int[] iArr) {
            this.configAttribs = iArr;
        }

        public EGLConfig selectConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr, int[] iArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int iFindConfigAttrib = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int iFindConfigAttrib2 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (iFindConfigAttrib >= iArr[4] && iFindConfigAttrib2 >= iArr[5]) {
                    int iFindConfigAttrib3 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int iFindConfigAttrib4 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int iFindConfigAttrib5 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    int iFindConfigAttrib6 = findConfigAttrib(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (iFindConfigAttrib3 >= iArr[0] && iFindConfigAttrib4 >= iArr[1] && iFindConfigAttrib5 >= iArr[2] && iFindConfigAttrib6 >= iArr[3]) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int findConfigAttrib(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            int[] iArr = new int[1];
            return egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, iArr) ? iArr[0] : i2;
        }

        @Override // android.opengl.GLSurfaceView.EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr;
            int[] iArr2 = new int[1];
            if (egl10.eglGetConfigs(eGLDisplay, null, 0, iArr2)) {
                EGLConfig[] eGLConfigArr = new EGLConfig[iArr2[0]];
                int[] iArr3 = this.configAttribs;
                int[] iArr4 = new int[1];
                egl10.eglChooseConfig(eGLDisplay, new int[]{12324, iArr3[0], 12323, iArr3[1], 12322, iArr3[2], 12321, iArr3[3], 12325, iArr3[4], 12326, iArr3[5], 12352, 4, 12344}, eGLConfigArr, iArr2[0], iArr4);
                if (iArr4[0] > 0) {
                    return selectConfig(egl10, eGLDisplay, eGLConfigArr, this.configAttribs);
                }
                int[] iArr5 = {12324, 5, 12323, 6, 12322, 5, 12321, 0, 12325, 0, 12326, 0, 12352, 4, 12344};
                int[] iArr6 = {12324, 4, 12323, 4, 12322, 4, 12321, 4, 12325, 0, 12326, 0, 12352, 4, 12344};
                if (this.configAttribs[3] == 0) {
                    egl10.eglChooseConfig(eGLDisplay, iArr5, eGLConfigArr, iArr2[0], iArr4);
                    iArr = new int[]{5, 6, 5, 0, 0, 0};
                } else {
                    egl10.eglChooseConfig(eGLDisplay, iArr6, eGLConfigArr, iArr2[0], iArr4);
                    iArr = new int[]{4, 4, 4, 4, 0, 0};
                }
                if (iArr4[0] > 0) {
                    return selectConfig(egl10, eGLDisplay, eGLConfigArr, iArr);
                }
                Log.e("device_policy", "Can not select an EGLConfig for rendering.");
                return null;
            }
            Log.e("device_policy", "Can not select an EGLConfig for rendering.");
            return null;
        }
    }

    public static Context getContext() {
        return sContext;
    }

    public void setKeepScreenOn(final boolean z) {
        runOnUiThread(new Runnable() { // from class: org.cocos2dx.lib.Cocos2dxActivity.1
            @Override // java.lang.Runnable
            public void run() {
                Cocos2dxActivity.this.mGLSurfaceView.setKeepScreenOn(z);
            }
        });
    }

    protected void onLoadNativeLibraries() {
        try {
            System.loadLibrary(getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("android.app.lib_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CocosPlayClient.init(this, false);
        onLoadNativeLibraries();
        sContext = this;
        this.mHandler = new Cocos2dxHandler(this);
        Cocos2dxHelper.init(this);
        this.mGLContextAttrs = getGLContextAttrs();
        init();
        if (this.mVideoHelper == null) {
            this.mVideoHelper = new Cocos2dxVideoHelper(this, this.mFrameLayout);
        }
        if (this.mWebViewHelper == null) {
            this.mWebViewHelper = new Cocos2dxWebViewHelper(this.mFrameLayout);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Cocos2dxHelper.onResume();
        this.mGLSurfaceView.onResume();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Cocos2dxHelper.onPause();
        this.mGLSurfaceView.onPause();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void showDialog(String str, String str2) {
        Message message = new Message();
        message.what = 1;
        message.obj = new Cocos2dxHandler.DialogMessage(str, str2);
        this.mHandler.sendMessage(message);
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void showEditTextDialog(String str, String str2, int i, int i2, int i3, int i4) {
        Message message = new Message();
        message.what = 2;
        message.obj = new Cocos2dxHandler.EditBoxMessage(str, str2, i, i2, i3, i4);
        this.mHandler.sendMessage(message);
    }

    @Override // org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener
    public void runOnGLThread(Runnable runnable) {
        this.mGLSurfaceView.queueEvent(runnable);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Iterator<PreferenceManager.OnActivityResultListener> it = Cocos2dxHelper.getOnActivityResultListeners().iterator();
        while (it.hasNext()) {
            it.next().onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void init() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        this.mFrameLayout = new FrameLayout(this);
        this.mFrameLayout.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -2);
        Cocos2dxEditText cocos2dxEditText = new Cocos2dxEditText(this);
        cocos2dxEditText.setLayoutParams(layoutParams2);
        this.mFrameLayout.addView(cocos2dxEditText);
        this.mGLSurfaceView = onCreateView();
        this.mGLSurfaceView.setPreserveEGLContextOnPause(true);
        this.mFrameLayout.addView(this.mGLSurfaceView);
        if (isAndroidEmulator()) {
            this.mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        }
        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        this.mGLSurfaceView.setCocos2dxEditText(cocos2dxEditText);
        setContentView(this.mFrameLayout);
    }

    public Cocos2dxGLSurfaceView onCreateView() {
        Cocos2dxGLSurfaceView cocos2dxGLSurfaceView = new Cocos2dxGLSurfaceView(this);
        if (this.mGLContextAttrs[3] > 0) {
            cocos2dxGLSurfaceView.getHolder().setFormat(-3);
        }
        cocos2dxGLSurfaceView.setEGLConfigChooser(new Cocos2dxEGLConfigChooser(this.mGLContextAttrs));
        return cocos2dxGLSurfaceView;
    }

    private static final boolean isAndroidEmulator() {
        Log.d(TAG, "model=" + Build.MODEL);
        String str = Build.PRODUCT;
        Log.d(TAG, "product=" + str);
        boolean z = false;
        if (str != null && (str.equals("sdk") || str.contains("_sdk") || str.contains("sdk_"))) {
            z = true;
        }
        Log.d(TAG, "isEmulator=" + z);
        return z;
    }
}
