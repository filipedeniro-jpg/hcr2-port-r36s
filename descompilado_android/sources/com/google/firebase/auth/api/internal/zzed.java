package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.internal.firebase_auth.zzdv;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzed extends com.google.android.gms.internal.firebase_auth.zza implements zzee {
    public zzed() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    @Override // com.google.android.gms.internal.firebase_auth.zza
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzdz zzebVar = null;
        switch (i) {
            case 1:
                String string = parcel.readString();
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface;
                    } else {
                        zzebVar = new zzeb(strongBinder);
                    }
                }
                zza(string, zzebVar);
                break;
            case 2:
                String string2 = parcel.readString();
                IBinder strongBinder2 = parcel.readStrongBinder();
                if (strongBinder2 != null) {
                    IInterface iInterfaceQueryLocalInterface2 = strongBinder2.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface2 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface2;
                    } else {
                        zzebVar = new zzeb(strongBinder2);
                    }
                }
                zzb(string2, zzebVar);
                break;
            case 3:
                zzfr zzfrVar = (zzfr) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, zzfr.CREATOR);
                IBinder strongBinder3 = parcel.readStrongBinder();
                if (strongBinder3 != null) {
                    IInterface iInterfaceQueryLocalInterface3 = strongBinder3.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface3 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface3;
                    } else {
                        zzebVar = new zzeb(strongBinder3);
                    }
                }
                zza(zzfrVar, zzebVar);
                break;
            case 4:
                String string3 = parcel.readString();
                UserProfileChangeRequest userProfileChangeRequest = (UserProfileChangeRequest) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, UserProfileChangeRequest.CREATOR);
                IBinder strongBinder4 = parcel.readStrongBinder();
                if (strongBinder4 != null) {
                    IInterface iInterfaceQueryLocalInterface4 = strongBinder4.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface4 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface4;
                    } else {
                        zzebVar = new zzeb(strongBinder4);
                    }
                }
                zza(string3, userProfileChangeRequest, zzebVar);
                break;
            case 5:
                String string4 = parcel.readString();
                String string5 = parcel.readString();
                IBinder strongBinder5 = parcel.readStrongBinder();
                if (strongBinder5 != null) {
                    IInterface iInterfaceQueryLocalInterface5 = strongBinder5.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface5 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface5;
                    } else {
                        zzebVar = new zzeb(strongBinder5);
                    }
                }
                zza(string4, string5, zzebVar);
                break;
            case 6:
                String string6 = parcel.readString();
                String string7 = parcel.readString();
                IBinder strongBinder6 = parcel.readStrongBinder();
                if (strongBinder6 != null) {
                    IInterface iInterfaceQueryLocalInterface6 = strongBinder6.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface6 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface6;
                    } else {
                        zzebVar = new zzeb(strongBinder6);
                    }
                }
                zzb(string6, string7, zzebVar);
                break;
            case 7:
                String string8 = parcel.readString();
                String string9 = parcel.readString();
                IBinder strongBinder7 = parcel.readStrongBinder();
                if (strongBinder7 != null) {
                    IInterface iInterfaceQueryLocalInterface7 = strongBinder7.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface7 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface7;
                    } else {
                        zzebVar = new zzeb(strongBinder7);
                    }
                }
                zzc(string8, string9, zzebVar);
                break;
            case 8:
                String string10 = parcel.readString();
                String string11 = parcel.readString();
                IBinder strongBinder8 = parcel.readStrongBinder();
                if (strongBinder8 != null) {
                    IInterface iInterfaceQueryLocalInterface8 = strongBinder8.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface8 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface8;
                    } else {
                        zzebVar = new zzeb(strongBinder8);
                    }
                }
                zzd(string10, string11, zzebVar);
                break;
            case 9:
                String string12 = parcel.readString();
                IBinder strongBinder9 = parcel.readStrongBinder();
                if (strongBinder9 != null) {
                    IInterface iInterfaceQueryLocalInterface9 = strongBinder9.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface9 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface9;
                    } else {
                        zzebVar = new zzeb(strongBinder9);
                    }
                }
                zzc(string12, zzebVar);
                break;
            case 10:
                String string13 = parcel.readString();
                IBinder strongBinder10 = parcel.readStrongBinder();
                if (strongBinder10 != null) {
                    IInterface iInterfaceQueryLocalInterface10 = strongBinder10.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface10 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface10;
                    } else {
                        zzebVar = new zzeb(strongBinder10);
                    }
                }
                zzd(string13, zzebVar);
                break;
            case 11:
                String string14 = parcel.readString();
                String string15 = parcel.readString();
                String string16 = parcel.readString();
                IBinder strongBinder11 = parcel.readStrongBinder();
                if (strongBinder11 != null) {
                    IInterface iInterfaceQueryLocalInterface11 = strongBinder11.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface11 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface11;
                    } else {
                        zzebVar = new zzeb(strongBinder11);
                    }
                }
                zza(string14, string15, string16, zzebVar);
                break;
            case 12:
                String string17 = parcel.readString();
                zzfr zzfrVar2 = (zzfr) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, zzfr.CREATOR);
                IBinder strongBinder12 = parcel.readStrongBinder();
                if (strongBinder12 != null) {
                    IInterface iInterfaceQueryLocalInterface12 = strongBinder12.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface12 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface12;
                    } else {
                        zzebVar = new zzeb(strongBinder12);
                    }
                }
                zza(string17, zzfrVar2, zzebVar);
                break;
            case 13:
                String string18 = parcel.readString();
                IBinder strongBinder13 = parcel.readStrongBinder();
                if (strongBinder13 != null) {
                    IInterface iInterfaceQueryLocalInterface13 = strongBinder13.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface13 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface13;
                    } else {
                        zzebVar = new zzeb(strongBinder13);
                    }
                }
                zze(string18, zzebVar);
                break;
            case 14:
                String string19 = parcel.readString();
                String string20 = parcel.readString();
                IBinder strongBinder14 = parcel.readStrongBinder();
                if (strongBinder14 != null) {
                    IInterface iInterfaceQueryLocalInterface14 = strongBinder14.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface14 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface14;
                    } else {
                        zzebVar = new zzeb(strongBinder14);
                    }
                }
                zze(string19, string20, zzebVar);
                break;
            case 15:
                String string21 = parcel.readString();
                IBinder strongBinder15 = parcel.readStrongBinder();
                if (strongBinder15 != null) {
                    IInterface iInterfaceQueryLocalInterface15 = strongBinder15.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface15 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface15;
                    } else {
                        zzebVar = new zzeb(strongBinder15);
                    }
                }
                zzf(string21, zzebVar);
                break;
            case 16:
                IBinder strongBinder16 = parcel.readStrongBinder();
                if (strongBinder16 != null) {
                    IInterface iInterfaceQueryLocalInterface16 = strongBinder16.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface16 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface16;
                    } else {
                        zzebVar = new zzeb(strongBinder16);
                    }
                }
                zza(zzebVar);
                break;
            case 17:
                String string22 = parcel.readString();
                IBinder strongBinder17 = parcel.readStrongBinder();
                if (strongBinder17 != null) {
                    IInterface iInterfaceQueryLocalInterface17 = strongBinder17.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface17 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface17;
                    } else {
                        zzebVar = new zzeb(strongBinder17);
                    }
                }
                zzg(string22, zzebVar);
                break;
            case 18:
                String string23 = parcel.readString();
                IBinder strongBinder18 = parcel.readStrongBinder();
                if (strongBinder18 != null) {
                    IInterface iInterfaceQueryLocalInterface18 = strongBinder18.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface18 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface18;
                    } else {
                        zzebVar = new zzeb(strongBinder18);
                    }
                }
                zzh(string23, zzebVar);
                break;
            case 19:
                String string24 = parcel.readString();
                IBinder strongBinder19 = parcel.readStrongBinder();
                if (strongBinder19 != null) {
                    IInterface iInterfaceQueryLocalInterface19 = strongBinder19.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface19 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface19;
                    } else {
                        zzebVar = new zzeb(strongBinder19);
                    }
                }
                zzi(string24, zzebVar);
                break;
            case 20:
                String string25 = parcel.readString();
                IBinder strongBinder20 = parcel.readStrongBinder();
                if (strongBinder20 != null) {
                    IInterface iInterfaceQueryLocalInterface20 = strongBinder20.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface20 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface20;
                    } else {
                        zzebVar = new zzeb(strongBinder20);
                    }
                }
                zzj(string25, zzebVar);
                break;
            case 21:
                String string26 = parcel.readString();
                String string27 = parcel.readString();
                IBinder strongBinder21 = parcel.readStrongBinder();
                if (strongBinder21 != null) {
                    IInterface iInterfaceQueryLocalInterface21 = strongBinder21.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface21 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface21;
                    } else {
                        zzebVar = new zzeb(strongBinder21);
                    }
                }
                zzf(string26, string27, zzebVar);
                break;
            case 22:
                com.google.android.gms.internal.firebase_auth.zzfk zzfkVar = (com.google.android.gms.internal.firebase_auth.zzfk) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzfk.CREATOR);
                IBinder strongBinder22 = parcel.readStrongBinder();
                if (strongBinder22 != null) {
                    IInterface iInterfaceQueryLocalInterface22 = strongBinder22.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface22 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface22;
                    } else {
                        zzebVar = new zzeb(strongBinder22);
                    }
                }
                zza(zzfkVar, zzebVar);
                break;
            case 23:
                PhoneAuthCredential phoneAuthCredential = (PhoneAuthCredential) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, PhoneAuthCredential.CREATOR);
                IBinder strongBinder23 = parcel.readStrongBinder();
                if (strongBinder23 != null) {
                    IInterface iInterfaceQueryLocalInterface23 = strongBinder23.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface23 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface23;
                    } else {
                        zzebVar = new zzeb(strongBinder23);
                    }
                }
                zza(phoneAuthCredential, zzebVar);
                break;
            case 24:
                String string28 = parcel.readString();
                PhoneAuthCredential phoneAuthCredential2 = (PhoneAuthCredential) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, PhoneAuthCredential.CREATOR);
                IBinder strongBinder24 = parcel.readStrongBinder();
                if (strongBinder24 != null) {
                    IInterface iInterfaceQueryLocalInterface24 = strongBinder24.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface24 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface24;
                    } else {
                        zzebVar = new zzeb(strongBinder24);
                    }
                }
                zza(string28, phoneAuthCredential2, zzebVar);
                break;
            case 25:
                String string29 = parcel.readString();
                ActionCodeSettings actionCodeSettings = (ActionCodeSettings) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder strongBinder25 = parcel.readStrongBinder();
                if (strongBinder25 != null) {
                    IInterface iInterfaceQueryLocalInterface25 = strongBinder25.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface25 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface25;
                    } else {
                        zzebVar = new zzeb(strongBinder25);
                    }
                }
                zza(string29, actionCodeSettings, zzebVar);
                break;
            case 26:
                String string30 = parcel.readString();
                ActionCodeSettings actionCodeSettings2 = (ActionCodeSettings) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder strongBinder26 = parcel.readStrongBinder();
                if (strongBinder26 != null) {
                    IInterface iInterfaceQueryLocalInterface26 = strongBinder26.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface26 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface26;
                    } else {
                        zzebVar = new zzeb(strongBinder26);
                    }
                }
                zzb(string30, actionCodeSettings2, zzebVar);
                break;
            case 27:
                String string31 = parcel.readString();
                IBinder strongBinder27 = parcel.readStrongBinder();
                if (strongBinder27 != null) {
                    IInterface iInterfaceQueryLocalInterface27 = strongBinder27.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface27 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface27;
                    } else {
                        zzebVar = new zzeb(strongBinder27);
                    }
                }
                zzk(string31, zzebVar);
                break;
            case 28:
                String string32 = parcel.readString();
                ActionCodeSettings actionCodeSettings3 = (ActionCodeSettings) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, ActionCodeSettings.CREATOR);
                IBinder strongBinder28 = parcel.readStrongBinder();
                if (strongBinder28 != null) {
                    IInterface iInterfaceQueryLocalInterface28 = strongBinder28.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface28 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface28;
                    } else {
                        zzebVar = new zzeb(strongBinder28);
                    }
                }
                zzc(string32, actionCodeSettings3, zzebVar);
                break;
            case 29:
                EmailAuthCredential emailAuthCredential = (EmailAuthCredential) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, EmailAuthCredential.CREATOR);
                IBinder strongBinder29 = parcel.readStrongBinder();
                if (strongBinder29 != null) {
                    IInterface iInterfaceQueryLocalInterface29 = strongBinder29.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                    if (iInterfaceQueryLocalInterface29 instanceof zzdz) {
                        zzebVar = (zzdz) iInterfaceQueryLocalInterface29;
                    } else {
                        zzebVar = new zzeb(strongBinder29);
                    }
                }
                zza(emailAuthCredential, zzebVar);
                break;
            default:
                switch (i) {
                    case 101:
                        com.google.android.gms.internal.firebase_auth.zzch zzchVar = (com.google.android.gms.internal.firebase_auth.zzch) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzch.CREATOR);
                        IBinder strongBinder30 = parcel.readStrongBinder();
                        if (strongBinder30 != null) {
                            IInterface iInterfaceQueryLocalInterface30 = strongBinder30.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface30 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface30;
                            } else {
                                zzebVar = new zzeb(strongBinder30);
                            }
                        }
                        zza(zzchVar, zzebVar);
                        break;
                    case 102:
                        com.google.android.gms.internal.firebase_auth.zzdf zzdfVar = (com.google.android.gms.internal.firebase_auth.zzdf) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdf.CREATOR);
                        IBinder strongBinder31 = parcel.readStrongBinder();
                        if (strongBinder31 != null) {
                            IInterface iInterfaceQueryLocalInterface31 = strongBinder31.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface31 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface31;
                            } else {
                                zzebVar = new zzeb(strongBinder31);
                            }
                        }
                        zza(zzdfVar, zzebVar);
                        break;
                    case 103:
                        com.google.android.gms.internal.firebase_auth.zzdd zzddVar = (com.google.android.gms.internal.firebase_auth.zzdd) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdd.CREATOR);
                        IBinder strongBinder32 = parcel.readStrongBinder();
                        if (strongBinder32 != null) {
                            IInterface iInterfaceQueryLocalInterface32 = strongBinder32.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface32 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface32;
                            } else {
                                zzebVar = new zzeb(strongBinder32);
                            }
                        }
                        zza(zzddVar, zzebVar);
                        break;
                    case 104:
                        com.google.android.gms.internal.firebase_auth.zzdx zzdxVar = (com.google.android.gms.internal.firebase_auth.zzdx) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdx.CREATOR);
                        IBinder strongBinder33 = parcel.readStrongBinder();
                        if (strongBinder33 != null) {
                            IInterface iInterfaceQueryLocalInterface33 = strongBinder33.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface33 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface33;
                            } else {
                                zzebVar = new zzeb(strongBinder33);
                            }
                        }
                        zza(zzdxVar, zzebVar);
                        break;
                    case 105:
                        com.google.android.gms.internal.firebase_auth.zzbr zzbrVar = (com.google.android.gms.internal.firebase_auth.zzbr) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzbr.CREATOR);
                        IBinder strongBinder34 = parcel.readStrongBinder();
                        if (strongBinder34 != null) {
                            IInterface iInterfaceQueryLocalInterface34 = strongBinder34.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface34 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface34;
                            } else {
                                zzebVar = new zzeb(strongBinder34);
                            }
                        }
                        zza(zzbrVar, zzebVar);
                        break;
                    case 106:
                        com.google.android.gms.internal.firebase_auth.zzbt zzbtVar = (com.google.android.gms.internal.firebase_auth.zzbt) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzbt.CREATOR);
                        IBinder strongBinder35 = parcel.readStrongBinder();
                        if (strongBinder35 != null) {
                            IInterface iInterfaceQueryLocalInterface35 = strongBinder35.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface35 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface35;
                            } else {
                                zzebVar = new zzeb(strongBinder35);
                            }
                        }
                        zza(zzbtVar, zzebVar);
                        break;
                    case 107:
                        com.google.android.gms.internal.firebase_auth.zzbz zzbzVar = (com.google.android.gms.internal.firebase_auth.zzbz) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzbz.CREATOR);
                        IBinder strongBinder36 = parcel.readStrongBinder();
                        if (strongBinder36 != null) {
                            IInterface iInterfaceQueryLocalInterface36 = strongBinder36.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface36 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface36;
                            } else {
                                zzebVar = new zzeb(strongBinder36);
                            }
                        }
                        zza(zzbzVar, zzebVar);
                        break;
                    case 108:
                        com.google.android.gms.internal.firebase_auth.zzdh zzdhVar = (com.google.android.gms.internal.firebase_auth.zzdh) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdh.CREATOR);
                        IBinder strongBinder37 = parcel.readStrongBinder();
                        if (strongBinder37 != null) {
                            IInterface iInterfaceQueryLocalInterface37 = strongBinder37.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface37 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface37;
                            } else {
                                zzebVar = new zzeb(strongBinder37);
                            }
                        }
                        zza(zzdhVar, zzebVar);
                        break;
                    case 109:
                        com.google.android.gms.internal.firebase_auth.zzcj zzcjVar = (com.google.android.gms.internal.firebase_auth.zzcj) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcj.CREATOR);
                        IBinder strongBinder38 = parcel.readStrongBinder();
                        if (strongBinder38 != null) {
                            IInterface iInterfaceQueryLocalInterface38 = strongBinder38.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                            if (iInterfaceQueryLocalInterface38 instanceof zzdz) {
                                zzebVar = (zzdz) iInterfaceQueryLocalInterface38;
                            } else {
                                zzebVar = new zzeb(strongBinder38);
                            }
                        }
                        zza(zzcjVar, zzebVar);
                        break;
                    default:
                        switch (i) {
                            case 111:
                                com.google.android.gms.internal.firebase_auth.zzcl zzclVar = (com.google.android.gms.internal.firebase_auth.zzcl) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcl.CREATOR);
                                IBinder strongBinder39 = parcel.readStrongBinder();
                                if (strongBinder39 != null) {
                                    IInterface iInterfaceQueryLocalInterface39 = strongBinder39.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (iInterfaceQueryLocalInterface39 instanceof zzdz) {
                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface39;
                                    } else {
                                        zzebVar = new zzeb(strongBinder39);
                                    }
                                }
                                zza(zzclVar, zzebVar);
                                break;
                            case 112:
                                com.google.android.gms.internal.firebase_auth.zzcn zzcnVar = (com.google.android.gms.internal.firebase_auth.zzcn) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcn.CREATOR);
                                IBinder strongBinder40 = parcel.readStrongBinder();
                                if (strongBinder40 != null) {
                                    IInterface iInterfaceQueryLocalInterface40 = strongBinder40.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (iInterfaceQueryLocalInterface40 instanceof zzdz) {
                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface40;
                                    } else {
                                        zzebVar = new zzeb(strongBinder40);
                                    }
                                }
                                zza(zzcnVar, zzebVar);
                                break;
                            case 113:
                                com.google.android.gms.internal.firebase_auth.zzdt zzdtVar = (com.google.android.gms.internal.firebase_auth.zzdt) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdt.CREATOR);
                                IBinder strongBinder41 = parcel.readStrongBinder();
                                if (strongBinder41 != null) {
                                    IInterface iInterfaceQueryLocalInterface41 = strongBinder41.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (iInterfaceQueryLocalInterface41 instanceof zzdz) {
                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface41;
                                    } else {
                                        zzebVar = new zzeb(strongBinder41);
                                    }
                                }
                                zza(zzdtVar, zzebVar);
                                break;
                            case 114:
                                zzdv zzdvVar = (zzdv) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, zzdv.CREATOR);
                                IBinder strongBinder42 = parcel.readStrongBinder();
                                if (strongBinder42 != null) {
                                    IInterface iInterfaceQueryLocalInterface42 = strongBinder42.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (iInterfaceQueryLocalInterface42 instanceof zzdz) {
                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface42;
                                    } else {
                                        zzebVar = new zzeb(strongBinder42);
                                    }
                                }
                                zza(zzdvVar, zzebVar);
                                break;
                            case 115:
                                com.google.android.gms.internal.firebase_auth.zzcr zzcrVar = (com.google.android.gms.internal.firebase_auth.zzcr) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcr.CREATOR);
                                IBinder strongBinder43 = parcel.readStrongBinder();
                                if (strongBinder43 != null) {
                                    IInterface iInterfaceQueryLocalInterface43 = strongBinder43.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (iInterfaceQueryLocalInterface43 instanceof zzdz) {
                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface43;
                                    } else {
                                        zzebVar = new zzeb(strongBinder43);
                                    }
                                }
                                zza(zzcrVar, zzebVar);
                                break;
                            case 116:
                                com.google.android.gms.internal.firebase_auth.zzdb zzdbVar = (com.google.android.gms.internal.firebase_auth.zzdb) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdb.CREATOR);
                                IBinder strongBinder44 = parcel.readStrongBinder();
                                if (strongBinder44 != null) {
                                    IInterface iInterfaceQueryLocalInterface44 = strongBinder44.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (iInterfaceQueryLocalInterface44 instanceof zzdz) {
                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface44;
                                    } else {
                                        zzebVar = new zzeb(strongBinder44);
                                    }
                                }
                                zza(zzdbVar, zzebVar);
                                break;
                            case 117:
                                com.google.android.gms.internal.firebase_auth.zzcb zzcbVar = (com.google.android.gms.internal.firebase_auth.zzcb) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcb.CREATOR);
                                IBinder strongBinder45 = parcel.readStrongBinder();
                                if (strongBinder45 != null) {
                                    IInterface iInterfaceQueryLocalInterface45 = strongBinder45.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                    if (iInterfaceQueryLocalInterface45 instanceof zzdz) {
                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface45;
                                    } else {
                                        zzebVar = new zzeb(strongBinder45);
                                    }
                                }
                                zza(zzcbVar, zzebVar);
                                break;
                            default:
                                switch (i) {
                                    case 119:
                                        com.google.android.gms.internal.firebase_auth.zzbv zzbvVar = (com.google.android.gms.internal.firebase_auth.zzbv) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzbv.CREATOR);
                                        IBinder strongBinder46 = parcel.readStrongBinder();
                                        if (strongBinder46 != null) {
                                            IInterface iInterfaceQueryLocalInterface46 = strongBinder46.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (iInterfaceQueryLocalInterface46 instanceof zzdz) {
                                                zzebVar = (zzdz) iInterfaceQueryLocalInterface46;
                                            } else {
                                                zzebVar = new zzeb(strongBinder46);
                                            }
                                        }
                                        zza(zzbvVar, zzebVar);
                                        break;
                                    case 120:
                                        com.google.android.gms.internal.firebase_auth.zzbp zzbpVar = (com.google.android.gms.internal.firebase_auth.zzbp) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzbp.CREATOR);
                                        IBinder strongBinder47 = parcel.readStrongBinder();
                                        if (strongBinder47 != null) {
                                            IInterface iInterfaceQueryLocalInterface47 = strongBinder47.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (iInterfaceQueryLocalInterface47 instanceof zzdz) {
                                                zzebVar = (zzdz) iInterfaceQueryLocalInterface47;
                                            } else {
                                                zzebVar = new zzeb(strongBinder47);
                                            }
                                        }
                                        zza(zzbpVar, zzebVar);
                                        break;
                                    case 121:
                                        com.google.android.gms.internal.firebase_auth.zzbx zzbxVar = (com.google.android.gms.internal.firebase_auth.zzbx) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzbx.CREATOR);
                                        IBinder strongBinder48 = parcel.readStrongBinder();
                                        if (strongBinder48 != null) {
                                            IInterface iInterfaceQueryLocalInterface48 = strongBinder48.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (iInterfaceQueryLocalInterface48 instanceof zzdz) {
                                                zzebVar = (zzdz) iInterfaceQueryLocalInterface48;
                                            } else {
                                                zzebVar = new zzeb(strongBinder48);
                                            }
                                        }
                                        zza(zzbxVar, zzebVar);
                                        break;
                                    case 122:
                                        com.google.android.gms.internal.firebase_auth.zzcx zzcxVar = (com.google.android.gms.internal.firebase_auth.zzcx) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcx.CREATOR);
                                        IBinder strongBinder49 = parcel.readStrongBinder();
                                        if (strongBinder49 != null) {
                                            IInterface iInterfaceQueryLocalInterface49 = strongBinder49.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (iInterfaceQueryLocalInterface49 instanceof zzdz) {
                                                zzebVar = (zzdz) iInterfaceQueryLocalInterface49;
                                            } else {
                                                zzebVar = new zzeb(strongBinder49);
                                            }
                                        }
                                        zza(zzcxVar, zzebVar);
                                        break;
                                    case 123:
                                        com.google.android.gms.internal.firebase_auth.zzdl zzdlVar = (com.google.android.gms.internal.firebase_auth.zzdl) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdl.CREATOR);
                                        IBinder strongBinder50 = parcel.readStrongBinder();
                                        if (strongBinder50 != null) {
                                            IInterface iInterfaceQueryLocalInterface50 = strongBinder50.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (iInterfaceQueryLocalInterface50 instanceof zzdz) {
                                                zzebVar = (zzdz) iInterfaceQueryLocalInterface50;
                                            } else {
                                                zzebVar = new zzeb(strongBinder50);
                                            }
                                        }
                                        zza(zzdlVar, zzebVar);
                                        break;
                                    case MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES /* 124 */:
                                        com.google.android.gms.internal.firebase_auth.zzcp zzcpVar = (com.google.android.gms.internal.firebase_auth.zzcp) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcp.CREATOR);
                                        IBinder strongBinder51 = parcel.readStrongBinder();
                                        if (strongBinder51 != null) {
                                            IInterface iInterfaceQueryLocalInterface51 = strongBinder51.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                            if (iInterfaceQueryLocalInterface51 instanceof zzdz) {
                                                zzebVar = (zzdz) iInterfaceQueryLocalInterface51;
                                            } else {
                                                zzebVar = new zzeb(strongBinder51);
                                            }
                                        }
                                        zza(zzcpVar, zzebVar);
                                        break;
                                    default:
                                        switch (i) {
                                            case 126:
                                                com.google.android.gms.internal.firebase_auth.zzct zzctVar = (com.google.android.gms.internal.firebase_auth.zzct) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzct.CREATOR);
                                                IBinder strongBinder52 = parcel.readStrongBinder();
                                                if (strongBinder52 != null) {
                                                    IInterface iInterfaceQueryLocalInterface52 = strongBinder52.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface52 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface52;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder52);
                                                    }
                                                }
                                                zza(zzctVar, zzebVar);
                                                break;
                                            case Notifications.NOTIFICATION_TYPES_ALL /* 127 */:
                                                com.google.android.gms.internal.firebase_auth.zzcz zzczVar = (com.google.android.gms.internal.firebase_auth.zzcz) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcz.CREATOR);
                                                IBinder strongBinder53 = parcel.readStrongBinder();
                                                if (strongBinder53 != null) {
                                                    IInterface iInterfaceQueryLocalInterface53 = strongBinder53.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface53 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface53;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder53);
                                                    }
                                                }
                                                zza(zzczVar, zzebVar);
                                                break;
                                            case 128:
                                                com.google.android.gms.internal.firebase_auth.zzcv zzcvVar = (com.google.android.gms.internal.firebase_auth.zzcv) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcv.CREATOR);
                                                IBinder strongBinder54 = parcel.readStrongBinder();
                                                if (strongBinder54 != null) {
                                                    IInterface iInterfaceQueryLocalInterface54 = strongBinder54.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface54 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface54;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder54);
                                                    }
                                                }
                                                zza(zzcvVar, zzebVar);
                                                break;
                                            case TsExtractor.TS_STREAM_TYPE_AC3 /* 129 */:
                                                com.google.android.gms.internal.firebase_auth.zzdj zzdjVar = (com.google.android.gms.internal.firebase_auth.zzdj) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdj.CREATOR);
                                                IBinder strongBinder55 = parcel.readStrongBinder();
                                                if (strongBinder55 != null) {
                                                    IInterface iInterfaceQueryLocalInterface55 = strongBinder55.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface55 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface55;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder55);
                                                    }
                                                }
                                                zza(zzdjVar, zzebVar);
                                                break;
                                            case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                                                com.google.android.gms.internal.firebase_auth.zzdn zzdnVar = (com.google.android.gms.internal.firebase_auth.zzdn) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdn.CREATOR);
                                                IBinder strongBinder56 = parcel.readStrongBinder();
                                                if (strongBinder56 != null) {
                                                    IInterface iInterfaceQueryLocalInterface56 = strongBinder56.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface56 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface56;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder56);
                                                    }
                                                }
                                                zza(zzdnVar, zzebVar);
                                                break;
                                            case 131:
                                                com.google.android.gms.internal.firebase_auth.zzdr zzdrVar = (com.google.android.gms.internal.firebase_auth.zzdr) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdr.CREATOR);
                                                IBinder strongBinder57 = parcel.readStrongBinder();
                                                if (strongBinder57 != null) {
                                                    IInterface iInterfaceQueryLocalInterface57 = strongBinder57.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface57 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface57;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder57);
                                                    }
                                                }
                                                zza(zzdrVar, zzebVar);
                                                break;
                                            case 132:
                                                com.google.android.gms.internal.firebase_auth.zzcd zzcdVar = (com.google.android.gms.internal.firebase_auth.zzcd) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcd.CREATOR);
                                                IBinder strongBinder58 = parcel.readStrongBinder();
                                                if (strongBinder58 != null) {
                                                    IInterface iInterfaceQueryLocalInterface58 = strongBinder58.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface58 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface58;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder58);
                                                    }
                                                }
                                                zza(zzcdVar, zzebVar);
                                                break;
                                            case 133:
                                                com.google.android.gms.internal.firebase_auth.zzdp zzdpVar = (com.google.android.gms.internal.firebase_auth.zzdp) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdp.CREATOR);
                                                IBinder strongBinder59 = parcel.readStrongBinder();
                                                if (strongBinder59 != null) {
                                                    IInterface iInterfaceQueryLocalInterface59 = strongBinder59.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface59 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface59;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder59);
                                                    }
                                                }
                                                zza(zzdpVar, zzebVar);
                                                break;
                                            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /* 134 */:
                                                com.google.android.gms.internal.firebase_auth.zzcf zzcfVar = (com.google.android.gms.internal.firebase_auth.zzcf) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzcf.CREATOR);
                                                IBinder strongBinder60 = parcel.readStrongBinder();
                                                if (strongBinder60 != null) {
                                                    IInterface iInterfaceQueryLocalInterface60 = strongBinder60.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface60 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface60;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder60);
                                                    }
                                                }
                                                zza(zzcfVar, zzebVar);
                                                break;
                                            case TsExtractor.TS_STREAM_TYPE_E_AC3 /* 135 */:
                                                com.google.android.gms.internal.firebase_auth.zzdz zzdzVar = (com.google.android.gms.internal.firebase_auth.zzdz) com.google.android.gms.internal.firebase_auth.zzd.zza(parcel, com.google.android.gms.internal.firebase_auth.zzdz.CREATOR);
                                                IBinder strongBinder61 = parcel.readStrongBinder();
                                                if (strongBinder61 != null) {
                                                    IInterface iInterfaceQueryLocalInterface61 = strongBinder61.queryLocalInterface("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
                                                    if (iInterfaceQueryLocalInterface61 instanceof zzdz) {
                                                        zzebVar = (zzdz) iInterfaceQueryLocalInterface61;
                                                    } else {
                                                        zzebVar = new zzeb(strongBinder61);
                                                    }
                                                }
                                                zza(zzdzVar, zzebVar);
                                                break;
                                            default:
                                                return false;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
