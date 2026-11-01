package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseError;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseAuthWebException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdw {

    @VisibleForTesting
    private static final SparseArray<Pair<String, String>> zza;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:42:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:46:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:48:0x00c3  */
    public static FirebaseException zza(Status status) {
        int statusCode = status.getStatusCode();
        String strZza = zza(zzb(statusCode), status);
        if (statusCode == 17020) {
            return new FirebaseNetworkException(zza("A network error (such as timeout, interrupted connection or unreachable host) has occurred.", status));
        }
        if (statusCode != 17021) {
            if (statusCode != 17051) {
                if (statusCode == 17052) {
                    return new FirebaseTooManyRequestsException(strZza);
                }
                if (statusCode != 17057 && statusCode != 17058) {
                    switch (statusCode) {
                        case FirebaseError.ERROR_INVALID_CUSTOM_TOKEN /* 17000 */:
                        case FirebaseError.ERROR_CUSTOM_TOKEN_MISMATCH /* 17002 */:
                        case FirebaseError.ERROR_INVALID_CREDENTIAL /* 17004 */:
                        case FirebaseError.ERROR_INVALID_EMAIL /* 17008 */:
                        case FirebaseError.ERROR_WRONG_PASSWORD /* 17009 */:
                        case 17049:
                        case 17077:
                        case 17081:
                        case 17082:
                        case 17083:
                        case 17084:
                            break;
                        case FirebaseError.ERROR_USER_DISABLED /* 17005 */:
                        case FirebaseError.ERROR_USER_NOT_FOUND /* 17011 */:
                            break;
                        case FirebaseError.ERROR_OPERATION_NOT_ALLOWED /* 17006 */:
                        case 17068:
                        case 17079:
                        case 17085:
                        case 17086:
                        case 17087:
                        case 17088:
                        case 17089:
                        case 17090:
                        case 17091:
                            return new FirebaseAuthException(zza(statusCode), strZza);
                        case FirebaseError.ERROR_EMAIL_ALREADY_IN_USE /* 17007 */:
                        case FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL /* 17012 */:
                            return new FirebaseAuthUserCollisionException(zza(statusCode), strZza);
                        case FirebaseError.ERROR_TOO_MANY_REQUESTS /* 17010 */:
                            return new FirebaseTooManyRequestsException(zza("We have blocked all requests from this device due to unusual activity. Try again later.", status));
                        case 17080:
                            return new FirebaseApiNotAvailableException(strZza);
                        case FirebaseError.ERROR_NO_SIGNED_IN_USER /* 17495 */:
                            return new FirebaseNoSignedInUserException(zza("Please sign in before trying to get a token.", status));
                        case FirebaseError.ERROR_INTERNAL_ERROR /* 17499 */:
                            return new FirebaseException(zza("An internal error has occurred.", status));
                        default:
                            switch (statusCode) {
                                case FirebaseError.ERROR_REQUIRES_RECENT_LOGIN /* 17014 */:
                                    return new FirebaseAuthRecentLoginRequiredException(zza(statusCode), strZza);
                                case FirebaseError.ERROR_PROVIDER_ALREADY_LINKED /* 17015 */:
                                    return new FirebaseException(zza("User has already been linked to the given provider.", status));
                                case FirebaseError.ERROR_NO_SUCH_PROVIDER /* 17016 */:
                                    return new FirebaseException(zza("User was not linked to an account with the given provider.", status));
                                case FirebaseError.ERROR_INVALID_USER_TOKEN /* 17017 */:
                                    break;
                                default:
                                    switch (statusCode) {
                                        case FirebaseError.ERROR_USER_MISMATCH /* 17024 */:
                                            break;
                                        case FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE /* 17025 */:
                                            return new FirebaseAuthUserCollisionException(zza(statusCode), strZza);
                                        case FirebaseError.ERROR_WEAK_PASSWORD /* 17026 */:
                                            return new FirebaseAuthWeakPasswordException(zza(statusCode), strZza, status.getStatusMessage());
                                        default:
                                            switch (statusCode) {
                                                case FirebaseError.ERROR_APP_NOT_AUTHORIZED /* 17028 */:
                                                    return new FirebaseAuthException(zza(statusCode), strZza);
                                                case 17029:
                                                case 17030:
                                                    return new FirebaseAuthActionCodeException(zza(statusCode), strZza);
                                                case 17031:
                                                case 17032:
                                                case 17033:
                                                    return new FirebaseAuthEmailException(zza(statusCode), strZza);
                                                case 17034:
                                                case 17035:
                                                    break;
                                                default:
                                                    switch (statusCode) {
                                                        case 17040:
                                                            return new FirebaseAuthException(zza(statusCode), strZza);
                                                        case 17041:
                                                        case 17042:
                                                        case 17043:
                                                        case 17044:
                                                        case 17045:
                                                        case 17046:
                                                            break;
                                                        default:
                                                            switch (statusCode) {
                                                                case 17061:
                                                                    return new FirebaseNetworkException(zza("There was a failure in the connection between the web widget and the Firebase Auth backend.", status));
                                                                case 17062:
                                                                case 17065:
                                                                    break;
                                                                case 17063:
                                                                    return new FirebaseApiNotAvailableException(strZza);
                                                                default:
                                                                    switch (statusCode) {
                                                                        case 17071:
                                                                        case 17072:
                                                                        case 17073:
                                                                        case 17074:
                                                                            break;
                                                                        case 17075:
                                                                            break;
                                                                        default:
                                                                            return new FirebaseException("An internal error has occurred.");
                                                                    }
                                                                case 17064:
                                                                    return new FirebaseAuthException(zza(statusCode), strZza);
                                                            }
                                                            break;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                }
                return new FirebaseAuthWebException(zza(statusCode), strZza);
            }
            return new FirebaseAuthInvalidCredentialsException(zza(statusCode), strZza);
        }
        return new FirebaseAuthInvalidUserException(zza(statusCode), strZza);
    }

    public static FirebaseAuthUserCollisionException zza(Status status, AuthCredential authCredential, @Nullable String str, @Nullable String str2) {
        int statusCode = status.getStatusCode();
        return new FirebaseAuthUserCollisionException(zza(statusCode), zza(zzb(statusCode), status)).zza(authCredential).zza(str).zzb(str2);
    }

    public static com.google.firebase.auth.zzs zza(FirebaseAuth firebaseAuth, com.google.android.gms.internal.firebase_auth.zzed zzedVar, @Nullable FirebaseUser firebaseUser) {
        com.google.android.gms.internal.firebase_auth.zzao.zza(firebaseAuth);
        com.google.android.gms.internal.firebase_auth.zzao.zza(zzedVar);
        Pair<String, String> pair = zza.get(17078);
        return new com.google.firebase.auth.zzs((String) pair.first, (String) pair.second, com.google.firebase.auth.internal.zzu.zza(zzedVar, firebaseAuth, firebaseUser));
    }

    private static String zza(String str, Status status) {
        return TextUtils.isEmpty(status.getStatusMessage()) ? str : String.format(String.valueOf(str).concat(" [ %s ]"), status.getStatusMessage());
    }

    private static String zza(int i) {
        Pair<String, String> pair = zza.get(i);
        return pair != null ? (String) pair.first : "INTERNAL_ERROR";
    }

    private static String zzb(int i) {
        Pair<String, String> pair = zza.get(i);
        return pair != null ? (String) pair.second : "An internal error has occurred.";
    }

    static {
        SparseArray<Pair<String, String>> sparseArray = new SparseArray<>();
        zza = sparseArray;
        sparseArray.put(FirebaseError.ERROR_INVALID_CUSTOM_TOKEN, new Pair<>("ERROR_INVALID_CUSTOM_TOKEN", "The custom token format is incorrect. Please check the documentation."));
        zza.put(FirebaseError.ERROR_CUSTOM_TOKEN_MISMATCH, new Pair<>("ERROR_CUSTOM_TOKEN_MISMATCH", "The custom token corresponds to a different audience."));
        zza.put(FirebaseError.ERROR_INVALID_CREDENTIAL, new Pair<>("ERROR_INVALID_CREDENTIAL", "The supplied auth credential is malformed or has expired."));
        zza.put(FirebaseError.ERROR_INVALID_EMAIL, new Pair<>("ERROR_INVALID_EMAIL", "The email address is badly formatted."));
        zza.put(FirebaseError.ERROR_WRONG_PASSWORD, new Pair<>("ERROR_WRONG_PASSWORD", "The password is invalid or the user does not have a password."));
        zza.put(FirebaseError.ERROR_USER_MISMATCH, new Pair<>("ERROR_USER_MISMATCH", "The supplied credentials do not correspond to the previously signed in user."));
        zza.put(FirebaseError.ERROR_REQUIRES_RECENT_LOGIN, new Pair<>("ERROR_REQUIRES_RECENT_LOGIN", "This operation is sensitive and requires recent authentication. Log in again before retrying this request."));
        zza.put(FirebaseError.ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL, new Pair<>("ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL", "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address."));
        zza.put(FirebaseError.ERROR_EMAIL_ALREADY_IN_USE, new Pair<>("ERROR_EMAIL_ALREADY_IN_USE", "The email address is already in use by another account."));
        zza.put(FirebaseError.ERROR_CREDENTIAL_ALREADY_IN_USE, new Pair<>("ERROR_CREDENTIAL_ALREADY_IN_USE", "This credential is already associated with a different user account."));
        zza.put(FirebaseError.ERROR_USER_DISABLED, new Pair<>("ERROR_USER_DISABLED", "The user account has been disabled by an administrator."));
        zza.put(FirebaseError.ERROR_USER_TOKEN_EXPIRED, new Pair<>("ERROR_USER_TOKEN_EXPIRED", "The user's credential is no longer valid. The user must sign in again."));
        zza.put(FirebaseError.ERROR_USER_NOT_FOUND, new Pair<>("ERROR_USER_NOT_FOUND", "There is no user record corresponding to this identifier. The user may have been deleted."));
        zza.put(FirebaseError.ERROR_INVALID_USER_TOKEN, new Pair<>("ERROR_INVALID_USER_TOKEN", "This user's credential isn't valid for this project. This can happen if the user's token has been tampered with, or if the user isn't for the project associated with this API key."));
        zza.put(FirebaseError.ERROR_OPERATION_NOT_ALLOWED, new Pair<>("ERROR_OPERATION_NOT_ALLOWED", "The given sign-in provider is disabled for this Firebase project. Enable it in the Firebase console, under the sign-in method tab of the Auth section."));
        zza.put(FirebaseError.ERROR_WEAK_PASSWORD, new Pair<>("ERROR_WEAK_PASSWORD", "The given password is invalid."));
        zza.put(17029, new Pair<>("ERROR_EXPIRED_ACTION_CODE", "The out of band code has expired."));
        zza.put(17030, new Pair<>("ERROR_INVALID_ACTION_CODE", "The out of band code is invalid. This can happen if the code is malformed, expired, or has already been used."));
        zza.put(17031, new Pair<>("ERROR_INVALID_MESSAGE_PAYLOAD", "The email template corresponding to this action contains invalid characters in its message. Please fix by going to the Auth email templates section in the Firebase Console."));
        zza.put(17033, new Pair<>("ERROR_INVALID_RECIPIENT_EMAIL", "The email corresponding to this action failed to send as the provided recipient email address is invalid."));
        zza.put(17032, new Pair<>("ERROR_INVALID_SENDER", "The email template corresponding to this action contains an invalid sender email or name. Please fix by going to the Auth email templates section in the Firebase Console."));
        zza.put(17034, new Pair<>("ERROR_MISSING_EMAIL", "An email address must be provided."));
        zza.put(17035, new Pair<>("ERROR_MISSING_PASSWORD", "A password must be provided."));
        zza.put(17041, new Pair<>("ERROR_MISSING_PHONE_NUMBER", "To send verification codes, provide a phone number for the recipient."));
        zza.put(17042, new Pair<>("ERROR_INVALID_PHONE_NUMBER", "The format of the phone number provided is incorrect. Please enter the phone number in a format that can be parsed into E.164 format. E.164 phone numbers are written in the format [+][country code][subscriber number including area code]."));
        zza.put(17043, new Pair<>("ERROR_MISSING_VERIFICATION_CODE", "The Phone Auth Credential was created with an empty sms verification Code"));
        zza.put(17044, new Pair<>("ERROR_INVALID_VERIFICATION_CODE", "The sms verification code used to create the phone auth credential is invalid. Please resend the verification code sms and be sure use the verification code provided by the user."));
        zza.put(17045, new Pair<>("ERROR_MISSING_VERIFICATION_ID", "The Phone Auth Credential was created with an empty verification ID"));
        zza.put(17046, new Pair<>("ERROR_INVALID_VERIFICATION_ID", "The verification ID used to create the phone auth credential is invalid."));
        zza.put(17049, new Pair<>("ERROR_RETRY_PHONE_AUTH", "An error occurred during authentication using the PhoneAuthCredential. Please retry authentication."));
        zza.put(17051, new Pair<>("ERROR_SESSION_EXPIRED", "The sms code has expired. Please re-send the verification code to try again."));
        zza.put(17052, new Pair<>("ERROR_QUOTA_EXCEEDED", "The sms quota for this project has been exceeded."));
        zza.put(FirebaseError.ERROR_APP_NOT_AUTHORIZED, new Pair<>("ERROR_APP_NOT_AUTHORIZED", "This app is not authorized to use Firebase Authentication. Please verify that the correct package name and SHA-1 are configured in the Firebase Console."));
        zza.put(17063, new Pair<>("ERROR_API_NOT_AVAILABLE_WITHOUT_GPS", "The API that you are calling is not available on devices without Google Play Services."));
        zza.put(17062, new Pair<>("ERROR_WEB_INTERNAL_ERROR", "There was an internal error in the web widget."));
        zza.put(17064, new Pair<>("ERROR_INVALID_CERT_HASH", "There was an error while trying to get your package certificate hash."));
        zza.put(17065, new Pair<>("ERROR_WEB_STORAGE_UNSUPPORTED", "This browser is not supported or 3rd party cookies and data may be disabled."));
        zza.put(17040, new Pair<>("ERROR_MISSING_CONTINUE_URI", "A continue URL must be provided in the request."));
        zza.put(17068, new Pair<>("ERROR_DYNAMIC_LINK_NOT_ACTIVATED", "Please activate Dynamic Links in the Firebase Console and agree to the terms and conditions."));
        zza.put(17071, new Pair<>("ERROR_INVALID_PROVIDER_ID", "The provider ID provided for the attempted web operation is invalid."));
        zza.put(17057, new Pair<>("ERROR_WEB_CONTEXT_ALREADY_PRESENTED", "A headful operation is already in progress. Please wait for that to finish."));
        zza.put(17058, new Pair<>("ERROR_WEB_CONTEXT_CANCELED", "The web operation was canceled by the user."));
        zza.put(17072, new Pair<>("ERROR_TENANT_ID_MISMATCH", "The provided tenant ID does not match the Auth instance's tenant ID."));
        zza.put(17073, new Pair<>("ERROR_UNSUPPORTED_TENANT_OPERATION", "This operation is not supported in a multi-tenant context."));
        zza.put(17074, new Pair<>("ERROR_INVALID_DYNAMIC_LINK_DOMAIN", "The provided dynamic link domain is not configured or authorized for the current project."));
        zza.put(17075, new Pair<>("ERROR_REJECTED_CREDENTIAL", "The request contains malformed or mismatching credentials"));
        zza.put(17077, new Pair<>("ERROR_PHONE_NUMBER_NOT_FOUND", "The provided phone number does not match any of the second factor phone numbers associated with this user."));
        zza.put(17079, new Pair<>("ERROR_INVALID_TENANT_ID", "The Auth instance's tenant ID is invalid."));
        zza.put(17078, new Pair<>("ERROR_SECOND_FACTOR_REQUIRED", "Please complete a second factor challenge to finish signing into this account."));
        zza.put(17080, new Pair<>("ERROR_API_NOT_AVAILABLE", "The API that you are calling is not available."));
        zza.put(17081, new Pair<>("ERROR_MISSING_MULTI_FACTOR_SESSION", "The request is missing proof of first factor successful sign-in."));
        zza.put(17082, new Pair<>("ERROR_MISSING_MULTI_FACTOR_INFO", "No second factor identifier is provided."));
        zza.put(17083, new Pair<>("ERROR_INVALID_MULTI_FACTOR_SESSION", "The request does not contain a valid proof of first factor successful sign-in."));
        zza.put(17084, new Pair<>("ERROR_MULTI_FACTOR_INFO_NOT_FOUND", "The user does not have a second factor matching the identifier provided."));
        zza.put(17085, new Pair<>("ERROR_ADMIN_RESTRICTED_OPERATION", "This operation is restricted to administrators only."));
        zza.put(17086, new Pair<>("ERROR_UNVERIFIED_EMAIL", "This operation requires a verified email."));
        zza.put(17087, new Pair<>("ERROR_SECOND_FACTOR_ALREADY_ENROLLED", "The second factor is already enrolled on this account."));
        zza.put(17088, new Pair<>("ERROR_MAXIMUM_SECOND_FACTOR_COUNT_EXCEEDED", "The maximum allowed number of second factors on a user has been exceeded."));
        zza.put(17089, new Pair<>("ERROR_UNSUPPORTED_FIRST_FACTOR", "Enrolling a second factor or signing in with a multi-factor account requires sign-in with a supported first factor."));
        zza.put(17090, new Pair<>("ERROR_EMAIL_CHANGE_NEEDS_VERIFICATION", "Multi-factor users must always have a verified email."));
        zza.put(17091, new Pair<>("INTERNAL_SUCCESS_SIGN_OUT", "This is an internal error code indicating that the operation was successful but the user needs to be signed out."));
    }
}
