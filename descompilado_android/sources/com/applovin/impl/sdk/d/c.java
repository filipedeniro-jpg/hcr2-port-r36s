package com.applovin.impl.sdk.d;

import android.net.Uri;
import com.applovin.impl.sdk.AppLovinAdBase;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.appsflyer.share.Constants;
import com.my.target.bj;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
abstract class c extends a {
    protected final com.applovin.impl.sdk.ad.g a;
    private AppLovinAdLoadListener c;
    private final com.applovin.impl.sdk.l d;
    private final Collection<Character> e;
    private final com.applovin.impl.sdk.c.e f;

    c(String str, com.applovin.impl.sdk.ad.g gVar, com.applovin.impl.sdk.h hVar, AppLovinAdLoadListener appLovinAdLoadListener) {
        super(str, hVar);
        if (gVar == null) {
            throw new IllegalArgumentException("No ad specified.");
        }
        this.a = gVar;
        this.c = appLovinAdLoadListener;
        this.d = hVar.Q();
        this.e = h();
        this.f = new com.applovin.impl.sdk.c.e();
    }

    private Uri a(Uri uri, String str) {
        StringBuilder sb;
        String str2;
        if (uri != null) {
            String string = uri.toString();
            if (com.applovin.impl.sdk.utils.k.b(string)) {
                a("Caching " + str + " image...");
                return f(string);
            }
            sb = new StringBuilder();
            sb.append("Failed to cache ");
            sb.append(str);
            str2 = " image";
        } else {
            sb = new StringBuilder();
            sb.append("No ");
            sb.append(str);
            str2 = " image to cache";
        }
        sb.append(str2);
        a(sb.toString());
        return null;
    }

    private String a(String str, String str2) {
        StringBuilder sb;
        String strReplace = str2.replace(Constants.URL_PATH_DELIMITER, EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
        String strI = this.a.I();
        if (com.applovin.impl.sdk.utils.k.b(strI)) {
            strReplace = strI + strReplace;
        }
        File fileA = this.d.a(strReplace, this.b.z(), true);
        if (fileA == null) {
            return null;
        }
        if (fileA.exists()) {
            this.f.b(fileA.length());
            sb = new StringBuilder();
        } else {
            if (!this.d.a(fileA, str + str2, Arrays.asList(str), this.f)) {
                return null;
            }
            sb = new StringBuilder();
        }
        sb.append("file://");
        sb.append(fileA.getAbsolutePath());
        return sb.toString();
    }

    private Collection<Character> h() {
        HashSet hashSet = new HashSet();
        for (char c : ((String) this.b.a(com.applovin.impl.sdk.b.b.bA)).toCharArray()) {
            hashSet.add(Character.valueOf(c));
        }
        hashSet.add('\"');
        return hashSet;
    }

    Uri a(String str, List<String> list, boolean z) {
        String str2;
        try {
            if (com.applovin.impl.sdk.utils.k.b(str)) {
                a("Caching video " + str + "...");
                String strA = this.d.a(e(), str, this.a.I(), list, z, this.f);
                if (com.applovin.impl.sdk.utils.k.b(strA)) {
                    File fileA = this.d.a(strA, e(), false);
                    if (fileA != null) {
                        Uri uriFromFile = Uri.fromFile(fileA);
                        if (uriFromFile != null) {
                            a("Finish caching video for ad #" + this.a.getAdIdNumber() + ". Updating ad with cachedVideoFilename = " + strA);
                            return uriFromFile;
                        }
                        str2 = "Unable to create URI from cached video file = " + fileA;
                    } else {
                        str2 = "Unable to cache video = " + str + "Video file was missing or null - please make sure your app has the WRITE_EXTERNAL_STORAGE permission!";
                    }
                } else if (((Boolean) this.b.a(com.applovin.impl.sdk.b.b.bF)).booleanValue()) {
                    d("Failed to cache video");
                    com.applovin.impl.sdk.utils.n.a(this.c, this.a.getAdZone(), AppLovinErrorCodes.UNABLE_TO_PRECACHE_VIDEO_RESOURCES, this.b);
                    this.c = null;
                } else {
                    str2 = "Failed to cache video, but not failing ad load";
                }
                d(str2);
            }
        } catch (Exception e) {
            a("Encountered exception while attempting to cache video.", e);
        }
        return null;
    }

    String a(String str, List<String> list) {
        return c(str, list, true);
    }

    String a(String str, List<String> list, com.applovin.impl.sdk.ad.g gVar) {
        int i;
        if (!com.applovin.impl.sdk.utils.k.b(str)) {
            return str;
        }
        if (!((Boolean) this.b.a(com.applovin.impl.sdk.b.b.bE)).booleanValue()) {
            a("Resource caching is disabled, skipping cache...");
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        boolean zShouldCancelHtmlCachingIfShown = gVar.shouldCancelHtmlCachingIfShown();
        for (String str2 : list) {
            int iIndexOf = 0;
            for (int i2 = 0; iIndexOf < sb.length() && (iIndexOf = sb.indexOf(str2, i2)) != -1; i2 = i) {
                int length = sb.length();
                i = iIndexOf;
                while (!this.e.contains(Character.valueOf(sb.charAt(i))) && i < length) {
                    i++;
                }
                if (i <= iIndexOf || i == length) {
                    d("Unable to cache resource; ad HTML is invalid.");
                    return str;
                }
                String strSubstring = sb.substring(str2.length() + iIndexOf, i);
                if (!com.applovin.impl.sdk.utils.k.b(strSubstring)) {
                    a("Skip caching of non-resource " + strSubstring);
                } else {
                    if (zShouldCancelHtmlCachingIfShown && gVar.hasShown()) {
                        a("Cancelling HTML caching due to ad being shown already");
                        this.f.a();
                        return str;
                    }
                    String strA = a(str2, strSubstring);
                    if (strA != null) {
                        sb.replace(iIndexOf, i, strA);
                        this.f.e();
                    } else {
                        this.f.f();
                    }
                }
            }
        }
        return sb.toString();
    }

    protected void a(AppLovinAdBase appLovinAdBase) {
        com.applovin.impl.sdk.c.d.a(this.f, appLovinAdBase, this.b);
    }

    Uri b(String str, List<String> list, boolean z) {
        String str2;
        try {
            String strA = this.d.a(e(), str, this.a.I(), list, z, this.f);
            if (!com.applovin.impl.sdk.utils.k.b(strA)) {
                return null;
            }
            File fileA = this.d.a(strA, e(), false);
            if (fileA != null) {
                Uri uriFromFile = Uri.fromFile(fileA);
                if (uriFromFile != null) {
                    return uriFromFile;
                }
                str2 = "Unable to extract Uri from image file";
            } else {
                str2 = "Unable to retrieve File from cached image filename = " + strA;
            }
            d(str2);
            return null;
        } catch (MalformedURLException e) {
            a("Failed to cache image at url = " + str, e);
            return null;
        }
    }

    void b() {
        a("Caching mute images...");
        Uri uriA = a(this.a.ay(), bj.gv);
        if (uriA != null) {
            this.a.b(uriA);
        }
        Uri uriA2 = a(this.a.az(), bj.gw);
        if (uriA2 != null) {
            this.a.c(uriA2);
        }
        a("Ad updated with muteImageFilename = " + this.a.ay() + ", unmuteImageFilename = " + this.a.az());
    }

    String c(String str, List<String> list, boolean z) throws Throwable {
        if (com.applovin.impl.sdk.utils.k.b(str)) {
            Uri uri = Uri.parse(str);
            if (uri == null) {
                a("Nothing to cache, skipping...");
                return null;
            }
            String lastPathSegment = uri.getLastPathSegment();
            if (com.applovin.impl.sdk.utils.k.b(this.a.I())) {
                lastPathSegment = this.a.I() + lastPathSegment;
            }
            File fileA = this.d.a(lastPathSegment, e(), true);
            ByteArrayOutputStream byteArrayOutputStreamA = (fileA == null || !fileA.exists()) ? null : this.d.a(fileA);
            if (byteArrayOutputStreamA == null) {
                byteArrayOutputStreamA = this.d.a(str, list, z);
                if (byteArrayOutputStreamA != null) {
                    this.d.a(byteArrayOutputStreamA, fileA);
                    this.f.a(byteArrayOutputStreamA.size());
                }
            } else {
                this.f.b(byteArrayOutputStreamA.size());
            }
            try {
                return byteArrayOutputStreamA.toString("UTF-8");
            } catch (UnsupportedEncodingException e) {
                a("UTF-8 encoding not supported.", e);
            } catch (Throwable th) {
                a("String resource at " + str + " failed to load.", th);
                return null;
            }
        }
        return null;
    }

    Uri e(String str) {
        return a(str, this.a.H(), true);
    }

    Uri f(String str) {
        return b(str, this.a.H(), true);
    }

    String g(final String str) throws Throwable {
        if (!com.applovin.impl.sdk.utils.k.b(str)) {
            return null;
        }
        com.applovin.impl.sdk.network.b bVarA = com.applovin.impl.sdk.network.b.a(this.b).a(str).b(HttpRequest.METHOD_GET).a("").a(0).a();
        final AtomicReference atomicReference = new AtomicReference(null);
        this.b.E().a(bVarA, new com.applovin.impl.sdk.network.a.C0027a(), new com.applovin.impl.sdk.network.a.b<String>() { // from class: com.applovin.impl.sdk.d.c.1
            @Override // com.applovin.impl.sdk.network.a.b
            public void a(int i) {
                c.this.d("Failed to load resource from '" + str + "'");
            }

            @Override // com.applovin.impl.sdk.network.a.b
            public void a(String str2, int i) {
                atomicReference.set(str2);
            }
        });
        String str2 = (String) atomicReference.get();
        if (str2 != null) {
            this.f.a(str2.length());
        }
        return str2;
    }

    void g() {
        if (this.c != null) {
            a("Rendered new ad:" + this.a);
            this.c.adReceived(this.a);
            this.c = null;
        }
    }
}
