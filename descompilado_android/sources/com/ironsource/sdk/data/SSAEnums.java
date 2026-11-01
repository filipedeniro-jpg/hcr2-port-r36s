package com.ironsource.sdk.data;

/* JADX INFO: loaded from: classes2.dex */
public class SSAEnums {

    public enum BackButtonState {
        None,
        Device,
        Controller
    }

    public enum ControllerState {
        None,
        Loaded,
        Ready,
        Failed
    }

    public enum ProductType {
        Banner,
        OfferWall,
        Interstitial,
        OfferWallCredits,
        RewardedVideo
    }

    public enum DebugMode {
        MODE_0(0),
        MODE_1(1),
        MODE_2(2),
        MODE_3(3);

        private int value;

        DebugMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
