package com.google.ads.mediation.inmobi;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.inmobi.sdk.InMobiSdk;
import java.util.Calendar;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
class InMobiAdapterUtils {
    InMobiAdapterUtils() {
    }

    public static void buildAdRequest(MediationAdRequest mediationAdRequest, Bundle bundle) {
        InMobiSdk.AgeGroup ageGroup;
        InMobiSdk.Education education;
        if (bundle == null) {
            Log.d("InMobiAdapter", "Bundle extras are null");
            bundle = new Bundle();
        }
        String str = "";
        String str2 = str;
        String str3 = str2;
        for (String str4 : bundle.keySet()) {
            String string = bundle.getString(str4);
            if (str4.equals(InMobiNetworkKeys.AREA_CODE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setAreaCode(string);
                }
            } else if (str4.equals(InMobiNetworkKeys.AGE)) {
                try {
                    if (!"".equals(string)) {
                        InMobiSdk.setAge(Integer.parseInt(string));
                    }
                } catch (NumberFormatException e) {
                    Log.d("Please Set age properly", e.getMessage());
                    e.printStackTrace();
                }
            } else if (str4.equals(InMobiNetworkKeys.POSTAL_CODE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setPostalCode(string);
                }
            } else if (str4.equals(InMobiNetworkKeys.LANGUAGE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setLanguage(string);
                }
            } else if (str4.equals(InMobiNetworkKeys.CITY)) {
                str = string;
            } else if (str4.equals(InMobiNetworkKeys.STATE)) {
                str2 = string;
            } else if (str4.equals(InMobiNetworkKeys.COUNTRY)) {
                str3 = string;
            } else if (str4.equals(InMobiNetworkKeys.AGE_GROUP)) {
                if (string != null && (ageGroup = getAgeGroup(string)) != null) {
                    InMobiSdk.setAgeGroup(ageGroup);
                }
            } else if (str4.equals(InMobiNetworkKeys.EDUCATION)) {
                if (string != null && (education = getEducation(string)) != null) {
                    InMobiSdk.setEducation(education);
                }
            } else if (str4.equals(InMobiNetworkKeys.LOGLEVEL)) {
                if (string != null) {
                    InMobiSdk.setLogLevel(getLogLevel(string));
                } else {
                    InMobiSdk.setLogLevel(InMobiSdk.LogLevel.NONE);
                }
            } else if (str4.equals(InMobiNetworkKeys.INTERESTS)) {
                InMobiSdk.setInterests(string);
            }
        }
        if (Build.VERSION.SDK_INT >= 19 && !Objects.equals(str, "") && !Objects.equals(str2, "") && !Objects.equals(str3, "")) {
            InMobiSdk.setLocationWithCityStateCountry(str, str2, str3);
        }
        if (mediationAdRequest.getLocation() != null) {
            InMobiSdk.setLocation(mediationAdRequest.getLocation());
        }
        if (mediationAdRequest.getBirthday() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(mediationAdRequest.getBirthday());
            InMobiSdk.setYearOfBirth(calendar.get(1));
        }
        if (mediationAdRequest.getGender() != -1) {
            int gender = mediationAdRequest.getGender();
            if (gender == 1) {
                InMobiSdk.setGender(InMobiSdk.Gender.MALE);
            } else {
                if (gender != 2) {
                    return;
                }
                InMobiSdk.setGender(InMobiSdk.Gender.FEMALE);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:29:0x0058  */
    private static InMobiSdk.AgeGroup getAgeGroup(String str) {
        switch (str) {
            case "ABOVE_65":
                return InMobiSdk.AgeGroup.ABOVE_65;
            case "BELOW_18":
                return InMobiSdk.AgeGroup.BELOW_18;
            case "BETWEEN_18_AND_24":
                return InMobiSdk.AgeGroup.BETWEEN_18_AND_24;
            case "BETWEEN_25_AND_29":
                return InMobiSdk.AgeGroup.BETWEEN_25_AND_29;
            case "BETWEEN_30_AND_34":
                return InMobiSdk.AgeGroup.BETWEEN_30_AND_34;
            case "BETWEEN_35_AND_44":
                return InMobiSdk.AgeGroup.BETWEEN_35_AND_44;
            case "BETWEEN_45_AND_54":
                return InMobiSdk.AgeGroup.BETWEEN_45_AND_54;
            case "BETWEEN_55_AND_65":
                return InMobiSdk.AgeGroup.BETWEEN_55_AND_65;
            default:
                return null;
        }
    }

    private static InMobiSdk.Education getEducation(String str) {
        if (str.equals(InMobiNetworkValues.EDUCATION_COLLEGEORGRADUATE)) {
            return InMobiSdk.Education.COLLEGE_OR_GRADUATE;
        }
        if (str.equals(InMobiNetworkValues.EDUCATION_HIGHSCHOOLORLESS)) {
            return InMobiSdk.Education.HIGH_SCHOOL_OR_LESS;
        }
        if (str.equals(InMobiNetworkValues.EDUCATION_POSTGRADUATEORABOVE)) {
            return InMobiSdk.Education.POST_GRADUATE_OR_ABOVE;
        }
        return null;
    }

    private static InMobiSdk.LogLevel getLogLevel(String str) {
        if (str.equals(InMobiNetworkValues.LOGLEVEL_DEBUG)) {
            return InMobiSdk.LogLevel.DEBUG;
        }
        if (str.equals(InMobiNetworkValues.LOGLEVEL_ERROR)) {
            return InMobiSdk.LogLevel.ERROR;
        }
        if (str.equals(InMobiNetworkValues.LOGLEVEL_NONE)) {
            return InMobiSdk.LogLevel.NONE;
        }
        return InMobiSdk.LogLevel.NONE;
    }

    public static <T> T mandatoryChecking(@Nullable T t, String str) throws MandatoryParamException {
        if (t != null && !t.toString().isEmpty()) {
            return t;
        }
        throw new MandatoryParamException("Mandatory param " + str + " not present");
    }
}
