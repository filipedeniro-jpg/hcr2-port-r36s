package com.scottyab.rootbeer;

import com.scottyab.rootbeer.util.QLog;

/* JADX INFO: loaded from: classes2.dex */
public class RootBeerNative {
    static boolean libraryLoaded = false;

    public native int checkForRoot(Object[] objArr);

    public native int setLogDebugMessages(boolean z);

    static {
        try {
            System.loadLibrary("tool-checker");
            libraryLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            QLog.e(e);
        }
    }

    public boolean wasNativeLibraryLoaded() {
        return libraryLoaded;
    }
}
