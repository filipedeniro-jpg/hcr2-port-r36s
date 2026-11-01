package com.crashlytics.android.core;

import io.fabric.sdk.android.Fabric;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
final class ProcMapEntryParser {
    private static final Pattern MAP_REGEX = Pattern.compile("\\s*(\\p{XDigit}+)-\\s*(\\p{XDigit}+)\\s+(.{4})\\s+\\p{XDigit}+\\s+.+\\s+\\d+\\s+(.*)");

    private ProcMapEntryParser() {
    }

    public static ProcMapEntry parse(String str) {
        Matcher matcher = MAP_REGEX.matcher(str);
        if (!matcher.matches()) {
            return null;
        }
        try {
            long jLongValue = Long.valueOf(matcher.group(1), 16).longValue();
            return new ProcMapEntry(jLongValue, Long.valueOf(matcher.group(2), 16).longValue() - jLongValue, matcher.group(3), matcher.group(4));
        } catch (Exception unused) {
            Fabric.getLogger().d(CrashlyticsCore.TAG, "Could not parse map entry: " + str);
            return null;
        }
    }
}
