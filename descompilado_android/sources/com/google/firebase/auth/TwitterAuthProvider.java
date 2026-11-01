package com.google.firebase.auth;

import androidx.annotation.NonNull;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public class TwitterAuthProvider {
    public static final String PROVIDER_ID = "twitter.com";
    public static final String TWITTER_SIGN_IN_METHOD = "twitter.com";

    private TwitterAuthProvider() {
    }

    public static AuthCredential getCredential(@NonNull String str, @NonNull String str2) {
        return new TwitterAuthCredential(str, str2);
    }
}
