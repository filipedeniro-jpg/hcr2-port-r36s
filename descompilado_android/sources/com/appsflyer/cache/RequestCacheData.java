package com.appsflyer.cache;

import java.util.Scanner;

/* JADX INFO: loaded from: classes.dex */
public class RequestCacheData {

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private String f117;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private String f118;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private String f119;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private String f120;

    public RequestCacheData(String str, String str2, String str3) {
        this.f119 = str;
        this.f118 = str2;
        this.f120 = str3;
    }

    public RequestCacheData(char[] cArr) {
        Scanner scanner = new Scanner(new String(cArr));
        while (scanner.hasNextLine()) {
            String strNextLine = scanner.nextLine();
            if (strNextLine.startsWith("url=")) {
                this.f119 = strNextLine.substring(4).trim();
            } else if (strNextLine.startsWith("version=")) {
                this.f120 = strNextLine.substring(8).trim();
            } else if (strNextLine.startsWith("data=")) {
                this.f118 = strNextLine.substring(5).trim();
            }
        }
        scanner.close();
    }

    public String getVersion() {
        return this.f120;
    }

    public void setVersion(String str) {
        this.f120 = str;
    }

    public String getPostData() {
        return this.f118;
    }

    public void setPostData(String str) {
        this.f118 = str;
    }

    public String getRequestURL() {
        return this.f119;
    }

    public void setRequestURL(String str) {
        this.f119 = str;
    }

    public String getCacheKey() {
        return this.f117;
    }

    public void setCacheKey(String str) {
        this.f117 = str;
    }
}
