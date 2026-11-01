package com.google.firebase.auth.internal;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseError;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzy {
    @NonNull
    public static Status zza(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return new Status(FirebaseError.ERROR_INTERNAL_ERROR);
        }
        String[] strArrSplit = str.split(":", 2);
        strArrSplit[0] = strArrSplit[0].trim();
        if (strArrSplit.length > 1 && strArrSplit[1] != null) {
            strArrSplit[1] = strArrSplit[1].trim();
        }
        List listAsList = Arrays.asList(strArrSplit);
        return listAsList.size() > 1 ? zza((String) listAsList.get(0), (String) listAsList.get(1)) : zza((String) listAsList.get(0), null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:200:0x0305  */
    @NonNull
    private static Status zza(String str, @Nullable String str2) {
        int i;
        switch (str) {
            case "NO_SUCH_PROVIDER":
                i = FirebaseError.ERROR_NO_SUCH_PROVIDER;
                break;
            case "CREDENTIAL_MISMATCH":
                i = FirebaseError.ERROR_CUSTOM_TOKEN_MISMATCH;
                break;
            case "INVALID_CUSTOM_TOKEN":
                i = FirebaseError.ERROR_INVALID_CUSTOM_TOKEN;
                break;
            case "INVALID_PENDING_TOKEN":
            case "INVALID_IDP_RESPONSE":
                i = FirebaseError.ERROR_INVALID_CREDENTIAL;
                break;
            case "USER_DISABLED":
                i = FirebaseError.ERROR_USER_DISABLED;
                break;
            case "INVALID_IDENTIFIER":
            case "INVALID_EMAIL":
                i = FirebaseError.ERROR_INVALID_EMAIL;
                break;
            case "EMAIL_NOT_FOUND":
            case "USER_NOT_FOUND":
                i = FirebaseError.ERROR_USER_NOT_FOUND;
                break;
            case "EMAIL_EXISTS":
                i = FirebaseError.ERROR_EMAIL_ALREADY_IN_USE;
                break;
            case "INVALID_PASSWORD":
                i = FirebaseError.ERROR_WRONG_PASSWORD;
                break;
            case "FEDERATED_USER_ID_ALREADY_LINKED":
                i = FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE;
                break;
            case "INVALID_ID_TOKEN":
                i = FirebaseError.ERROR_INVALID_USER_TOKEN;
                break;
            case "TIMEOUT":
            case "<<Network Error>>":
                i = FirebaseError.ERROR_NETWORK_REQUEST_FAILED;
                break;
            case "WEAK_PASSWORD":
                i = FirebaseError.ERROR_WEAK_PASSWORD;
                break;
            case "OPERATION_NOT_ALLOWED":
            case "PASSWORD_LOGIN_DISABLED":
                i = FirebaseError.ERROR_OPERATION_NOT_ALLOWED;
                break;
            case "INVALID_APP_CREDENTIAL":
                i = FirebaseError.ERROR_APP_NOT_AUTHORIZED;
                break;
            case "CREDENTIAL_TOO_OLD_LOGIN_AGAIN":
                i = FirebaseError.ERROR_REQUIRES_RECENT_LOGIN;
                break;
            case "TOO_MANY_ATTEMPTS_TRY_LATER":
            case "RESET_PASSWORD_EXCEED_LIMIT":
                i = FirebaseError.ERROR_TOO_MANY_REQUESTS;
                break;
            case "TOKEN_EXPIRED":
                i = FirebaseError.ERROR_USER_TOKEN_EXPIRED;
                break;
            case "INVALID_OOB_CODE":
                i = 17030;
                break;
            case "EXPIRED_OOB_CODE":
                i = 17029;
                break;
            case "INVALID_MESSAGE_PAYLOAD":
                i = 17031;
                break;
            case "INVALID_SENDER":
                i = 17032;
                break;
            case "INVALID_RECIPIENT_EMAIL":
                i = 17033;
                break;
            case "MISSING_EMAIL":
                i = 17034;
                break;
            case "MISSING_PASSWORD":
                i = 17035;
                break;
            case "MISSING_PHONE_NUMBER":
                i = 17041;
                break;
            case "INVALID_PHONE_NUMBER":
                i = 17042;
                break;
            case "MISSING_CODE":
                i = 17043;
                break;
            case "INVALID_CODE":
                i = 17044;
                break;
            case "MISSING_SESSION_INFO":
                i = 17045;
                break;
            case "INVALID_SESSION_INFO":
                i = 17046;
                break;
            case "INVALID_VERIFICATION_PROOF":
                i = 17049;
                break;
            case "SESSION_EXPIRED":
                i = 17051;
                break;
            case "QUOTA_EXCEEDED":
                i = 17052;
                break;
            case "INVALID_CERT_HASH":
                i = 17064;
                break;
            case "WEB_NETWORK_REQUEST_FAILED":
                i = 17061;
                break;
            case "WEB_INTERNAL_ERROR":
                i = 17062;
                break;
            case "WEB_STORAGE_UNSUPPORTED":
                i = 17065;
                break;
            case "MISSING_CONTINUE_URI":
                i = 17040;
                break;
            case "DYNAMIC_LINK_NOT_ACTIVATED":
                i = 17068;
                break;
            case "INVALID_PROVIDER_ID":
                i = 17071;
                break;
            case "WEB_CONTEXT_ALREADY_PRESENTED":
                i = 17057;
                break;
            case "WEB_CONTEXT_CANCELED":
                i = 17058;
                break;
            case "UNSUPPORTED_TENANT_OPERATION":
                i = 17073;
                break;
            case "INVALID_TENANT_ID":
                i = 17079;
                break;
            case "INVALID_DYNAMIC_LINK_DOMAIN":
                i = 17074;
                break;
            case "REJECTED_CREDENTIAL":
                i = 17075;
                break;
            case "REQUIRES_SECOND_FACTOR_AUTH":
                i = 17078;
                break;
            case "MISSING_MFA_PENDING_CREDENTIAL":
                i = 17081;
                break;
            case "MISSING_MFA_ENROLLMENT_ID":
                i = 17082;
                break;
            case "INVALID_MFA_PENDING_CREDENTIAL":
                i = 17083;
                break;
            case "MFA_ENROLLMENT_NOT_FOUND":
                i = 17084;
                break;
            case "ADMIN_ONLY_OPERATION":
                i = 17085;
                break;
            case "UNVERIFIED_EMAIL":
                i = 17086;
                break;
            case "SECOND_FACTOR_EXISTS":
                i = 17087;
                break;
            case "SECOND_FACTOR_LIMIT_EXCEEDED":
                i = 17088;
                break;
            case "UNSUPPORTED_FIRST_FACTOR":
                i = 17089;
                break;
            case "EMAIL_CHANGE_NEEDS_VERIFICATION":
                i = 17090;
                break;
            case "INTERNAL_SUCCESS_SIGN_OUT":
                i = 17091;
                break;
            default:
                i = FirebaseError.ERROR_INTERNAL_ERROR;
                break;
        }
        if (i != 17499) {
            return new Status(i, str2);
        }
        if (str2 != null) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length());
            sb.append(str);
            sb.append(":");
            sb.append(str2);
            return new Status(i, sb.toString());
        }
        return new Status(i, str);
    }
}
