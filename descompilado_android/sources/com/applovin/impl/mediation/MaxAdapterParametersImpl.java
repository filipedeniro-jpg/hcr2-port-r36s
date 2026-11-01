package com.applovin.impl.mediation;

import android.content.Context;
import android.os.Bundle;
import com.applovin.mediation.adapter.parameters.MaxAdapterInitializationParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters;
import com.applovin.mediation.adapter.parameters.MaxAdapterSignalCollectionParameters;

/* JADX INFO: loaded from: classes.dex */
public class MaxAdapterParametersImpl implements MaxAdapterInitializationParameters, MaxAdapterResponseParameters, MaxAdapterSignalCollectionParameters {
    private final String a;
    private final Bundle b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final String f;

    public static class a {
        private String a;
        private Bundle b;
        private boolean c;
        private boolean d;
        private boolean e;
        private String f;

        public a a(com.applovin.impl.mediation.b.a aVar, Context context) {
            if (aVar != null) {
                this.a = aVar.d();
                this.f = aVar.c();
            }
            return a((com.applovin.impl.mediation.b.e) aVar, context);
        }

        public a a(com.applovin.impl.mediation.b.e eVar, Context context) {
            if (eVar != null) {
                this.e = eVar.w();
                this.c = eVar.b(context);
                this.d = eVar.a(context);
                this.b = eVar.y();
            }
            return this;
        }

        public a a(boolean z) {
            this.c = z;
            return this;
        }

        public MaxAdapterParametersImpl a() {
            return new MaxAdapterParametersImpl(this);
        }

        public a b(boolean z) {
            this.d = z;
            return this;
        }
    }

    private MaxAdapterParametersImpl(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("No builder specified");
        }
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    @Override // com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters
    public String getBidResponse() {
        return this.f;
    }

    @Override // com.applovin.mediation.adapter.parameters.MaxAdapterParameters
    public Bundle getServerParameters() {
        return this.b;
    }

    @Override // com.applovin.mediation.adapter.parameters.MaxAdapterResponseParameters
    public String getThirdPartyAdPlacementId() {
        return this.a;
    }

    @Override // com.applovin.mediation.adapter.parameters.MaxAdapterParameters
    public boolean hasUserConsent() {
        return this.d;
    }

    @Override // com.applovin.mediation.adapter.parameters.MaxAdapterParameters
    public boolean isAgeRestrictedUser() {
        return this.c;
    }

    @Override // com.applovin.mediation.adapter.parameters.MaxAdapterParameters
    public boolean isTesting() {
        return this.e;
    }

    public String toString() {
        return "MaxAdapterParameters{thirdPartyAdPlacementId='" + this.a + "', serverParameters=" + this.b + ", isAgeRestrictedUser=" + this.c + ", hasUserConsent=" + this.d + ", isTesting=" + this.e + ", bidResponse='" + this.f + "'}";
    }
}
