package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.SystemClock;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.4J, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C4J extends C4K {
    private final AudioManager B;

    public C4J(Context context, EnumC02053g bdIntegrationSite) {
        super(context, bdIntegrationSite);
        this.B = (AudioManager) context.getSystemService("audio");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"Nullable Dereference"})
    public AbstractC02043f D(HashMap<Integer, C4L> map) {
        int iA = 0;
        Set<Integer> keySet = map.keySet();
        Iterator<Integer> it = keySet.iterator();
        while (it.hasNext()) {
            iA += map.get(it.next()).A() + 4;
        }
        return new C3Y(SystemClock.elapsedRealtime(), K(), map, C3S.MAP, iA);
    }

    public final C4S L() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4O
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.C(this.B.B.getRingerMode());
            }
        };
    }

    public final C4S M(final List<C02093k> list) {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4N
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                int streamMinVolume;
                HashMap map = new HashMap();
                for (int currentVolume = 0; currentVolume < i; currentVolume++) {
                    C02093k c02093k = (C02093k) list.get(currentVolume);
                    if (c02093k.B()) {
                        int iIntValue = ((Integer) c02093k.A()).intValue();
                        int streamKey = Build.VERSION.SDK_INT;
                        if (streamKey >= 28) {
                            streamMinVolume = this.B.B.getStreamMinVolume(iIntValue);
                        } else {
                            streamMinVolume = -1;
                        }
                        int streamVolume = this.B.B.getStreamVolume(iIntValue);
                        int minVolume = this.B.B.getStreamMaxVolume(iIntValue);
                        map.put(Integer.valueOf(iIntValue), new C4L(streamMinVolume, streamVolume, minVolume));
                    }
                }
                return this.B.D((HashMap<Integer, C4L>) map);
            }
        };
    }

    public final C4S N() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4R
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.A(this.B.B.isMicrophoneMute());
            }
        };
    }

    public final C4S O() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4Q
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.A(this.B.B.isMusicActive());
            }
        };
    }

    public final C4S P() {
        return new C4S() { // from class: com.facebook.ads.redexgen.X.4P
            @Override // com.facebook.ads.redexgen.X.C4S
            public final AbstractC02043f oB() {
                return this.B.A(this.B.B.isSpeakerphoneOn());
            }
        };
    }
}
