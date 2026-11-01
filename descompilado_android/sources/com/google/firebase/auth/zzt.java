package com.google.firebase.auth;

import com.google.firebase.FirebaseApp;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzt implements ComponentFactory {
    static final ComponentFactory zza = new zzt();

    private zzt() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return new com.google.firebase.auth.internal.zzl((FirebaseApp) componentContainer.get(FirebaseApp.class));
    }
}
