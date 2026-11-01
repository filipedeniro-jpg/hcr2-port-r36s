package com.crashlytics.android.answers;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import io.fabric.sdk.android.services.concurrency.internal.Backoff;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
class RandomBackoff implements Backoff {
    final Backoff backoff;
    final double jitterPercent;
    final Random random;

    public RandomBackoff(Backoff backoff, double d) {
        this(backoff, d, new Random());
    }

    public RandomBackoff(Backoff backoff, double d, Random random) {
        if (d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || d > 1.0d) {
            throw new IllegalArgumentException("jitterPercent must be between 0.0 and 1.0");
        }
        if (backoff == null) {
            throw new NullPointerException("backoff must not be null");
        }
        if (random == null) {
            throw new NullPointerException("random must not be null");
        }
        this.backoff = backoff;
        this.jitterPercent = d;
        this.random = random;
    }

    @Override // io.fabric.sdk.android.services.concurrency.internal.Backoff
    public long getDelayMillis(int i) {
        double dRandomJitter = randomJitter();
        double delayMillis = this.backoff.getDelayMillis(i);
        Double.isNaN(delayMillis);
        return (long) (dRandomJitter * delayMillis);
    }

    double randomJitter() {
        double d = this.jitterPercent;
        double d2 = 1.0d - d;
        return d2 + (((d + 1.0d) - d2) * this.random.nextDouble());
    }
}
