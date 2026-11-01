package com.crashlytics.android.core;

import java.io.File;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
interface Report {

    public enum Type {
        JAVA,
        NATIVE
    }

    Map<String, String> getCustomHeaders();

    File getFile();

    String getFileName();

    File[] getFiles();

    String getIdentifier();

    Type getType();

    void remove();
}
