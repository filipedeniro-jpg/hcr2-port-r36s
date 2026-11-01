package com.google.android.gms.internal.ads;

import android.util.Log;
import com.appsflyer.share.Constants;
import com.google.android.exoplayer2.C;
import com.ironsource.mediationsdk.IronSourceSegment;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkc {
    private static final int zzavg = zzoh.zzbi("nam");
    private static final int zzavh = zzoh.zzbi("trk");
    private static final int zzavi = zzoh.zzbi("cmt");
    private static final int zzavj = zzoh.zzbi("day");
    private static final int zzavk = zzoh.zzbi("ART");
    private static final int zzavl = zzoh.zzbi("too");
    private static final int zzavm = zzoh.zzbi("alb");
    private static final int zzavn = zzoh.zzbi("com");
    private static final int zzavo = zzoh.zzbi("wrt");
    private static final int zzavp = zzoh.zzbi("lyr");
    private static final int zzavq = zzoh.zzbi(IronSourceSegment.GENDER);
    private static final int zzavr = zzoh.zzbi("covr");
    private static final int zzavs = zzoh.zzbi("gnre");
    private static final int zzavt = zzoh.zzbi("grp");
    private static final int zzavu = zzoh.zzbi("disk");
    private static final int zzavv = zzoh.zzbi("trkn");
    private static final int zzavw = zzoh.zzbi("tmpo");
    private static final int zzavx = zzoh.zzbi("cpil");
    private static final int zzavy = zzoh.zzbi("aART");
    private static final int zzavz = zzoh.zzbi("sonm");
    private static final int zzawa = zzoh.zzbi("soal");
    private static final int zzawb = zzoh.zzbi("soar");
    private static final int zzawc = zzoh.zzbi("soaa");
    private static final int zzawd = zzoh.zzbi("soco");
    private static final int zzawe = zzoh.zzbi("rtng");
    private static final int zzawf = zzoh.zzbi("pgap");
    private static final int zzawg = zzoh.zzbi("sosn");
    private static final int zzawh = zzoh.zzbi("tvsh");
    private static final int zzawi = zzoh.zzbi("----");
    private static final String[] zzawj = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    public static zzkv.zza zzd(zzoa zzoaVar) {
        String str;
        zzlf zzlfVar;
        int position = zzoaVar.getPosition() + zzoaVar.readInt();
        int i = zzoaVar.readInt();
        int i2 = i >>> 24;
        zzkv.zza zzlbVar = null;
        try {
            if (i2 == 169 || i2 == 65533) {
                int i3 = 16777215 & i;
                if (i3 == zzavi) {
                    int i4 = zzoaVar.readInt();
                    if (zzoaVar.readInt() == zzjq.zzatw) {
                        zzoaVar.zzbe(8);
                        String strZzbf = zzoaVar.zzbf(i4 - 16);
                        zzlbVar = new zzlb(C.LANGUAGE_UNDETERMINED, strZzbf, strZzbf);
                    } else {
                        String strValueOf = String.valueOf(zzjq.zzal(i));
                        Log.w("MetadataUtil", strValueOf.length() != 0 ? "Failed to parse comment attribute: ".concat(strValueOf) : new String("Failed to parse comment attribute: "));
                    }
                    zzoaVar.zzbd(position);
                    return zzlbVar;
                }
                if (i3 != zzavg && i3 != zzavh) {
                    if (i3 != zzavn && i3 != zzavo) {
                        if (i3 == zzavj) {
                            zzlf zzlfVarZza = zza(i, "TDRC", zzoaVar);
                            zzoaVar.zzbd(position);
                            return zzlfVarZza;
                        }
                        if (i3 == zzavk) {
                            zzlf zzlfVarZza2 = zza(i, "TPE1", zzoaVar);
                            zzoaVar.zzbd(position);
                            return zzlfVarZza2;
                        }
                        if (i3 == zzavl) {
                            zzlf zzlfVarZza3 = zza(i, "TSSE", zzoaVar);
                            zzoaVar.zzbd(position);
                            return zzlfVarZza3;
                        }
                        if (i3 == zzavm) {
                            zzlf zzlfVarZza4 = zza(i, "TALB", zzoaVar);
                            zzoaVar.zzbd(position);
                            return zzlfVarZza4;
                        }
                        if (i3 == zzavp) {
                            zzlf zzlfVarZza5 = zza(i, "USLT", zzoaVar);
                            zzoaVar.zzbd(position);
                            return zzlfVarZza5;
                        }
                        if (i3 == zzavq) {
                            zzlf zzlfVarZza6 = zza(i, "TCON", zzoaVar);
                            zzoaVar.zzbd(position);
                            return zzlfVarZza6;
                        }
                        if (i3 == zzavt) {
                            zzlf zzlfVarZza7 = zza(i, "TIT1", zzoaVar);
                            zzoaVar.zzbd(position);
                            return zzlfVarZza7;
                        }
                    }
                    zzlf zzlfVarZza8 = zza(i, "TCOM", zzoaVar);
                    zzoaVar.zzbd(position);
                    return zzlfVarZza8;
                }
                zzlf zzlfVarZza9 = zza(i, "TIT2", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza9;
            }
            if (i == zzavs) {
                int iZze = zze(zzoaVar);
                String str2 = (iZze <= 0 || iZze > zzawj.length) ? null : zzawj[iZze - 1];
                if (str2 != null) {
                    zzlfVar = new zzlf("TCON", null, str2);
                } else {
                    Log.w("MetadataUtil", "Failed to parse standard genre code");
                    zzlfVar = null;
                }
                zzoaVar.zzbd(position);
                return zzlfVar;
            }
            if (i == zzavu) {
                zzlf zzlfVarZzb = zzb(i, "TPOS", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZzb;
            }
            if (i == zzavv) {
                zzlf zzlfVarZzb2 = zzb(i, "TRCK", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZzb2;
            }
            if (i == zzavw) {
                zzlc zzlcVarZza = zza(i, "TBPM", zzoaVar, true, false);
                zzoaVar.zzbd(position);
                return zzlcVarZza;
            }
            if (i == zzavx) {
                zzlc zzlcVarZza2 = zza(i, "TCMP", zzoaVar, true, true);
                zzoaVar.zzbd(position);
                return zzlcVarZza2;
            }
            if (i == zzavr) {
                int i5 = zzoaVar.readInt();
                if (zzoaVar.readInt() == zzjq.zzatw) {
                    int iZzak = zzjq.zzak(zzoaVar.readInt());
                    if (iZzak == 13) {
                        str = "image/jpeg";
                    } else {
                        str = iZzak == 14 ? "image/png" : null;
                    }
                    if (str == null) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append("Unrecognized cover art flags: ");
                        sb.append(iZzak);
                        Log.w("MetadataUtil", sb.toString());
                    } else {
                        zzoaVar.zzbe(4);
                        byte[] bArr = new byte[i5 - 16];
                        zzoaVar.zze(bArr, 0, bArr.length);
                        zzlbVar = new zzkz(str, null, 3, bArr);
                    }
                } else {
                    Log.w("MetadataUtil", "Failed to parse cover art attribute");
                }
                zzoaVar.zzbd(position);
                return zzlbVar;
            }
            if (i == zzavy) {
                zzlf zzlfVarZza10 = zza(i, "TPE2", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza10;
            }
            if (i == zzavz) {
                zzlf zzlfVarZza11 = zza(i, "TSOT", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza11;
            }
            if (i == zzawa) {
                zzlf zzlfVarZza12 = zza(i, "TSO2", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza12;
            }
            if (i == zzawb) {
                zzlf zzlfVarZza13 = zza(i, "TSOA", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza13;
            }
            if (i == zzawc) {
                zzlf zzlfVarZza14 = zza(i, "TSOP", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza14;
            }
            if (i == zzawd) {
                zzlf zzlfVarZza15 = zza(i, "TSOC", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza15;
            }
            if (i == zzawe) {
                zzlc zzlcVarZza3 = zza(i, "ITUNESADVISORY", zzoaVar, false, false);
                zzoaVar.zzbd(position);
                return zzlcVarZza3;
            }
            if (i == zzawf) {
                zzlc zzlcVarZza4 = zza(i, "ITUNESGAPLESS", zzoaVar, false, true);
                zzoaVar.zzbd(position);
                return zzlcVarZza4;
            }
            if (i == zzawg) {
                zzlf zzlfVarZza16 = zza(i, "TVSHOWSORT", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza16;
            }
            if (i == zzawh) {
                zzlf zzlfVarZza17 = zza(i, "TVSHOW", zzoaVar);
                zzoaVar.zzbd(position);
                return zzlfVarZza17;
            }
            if (i == zzawi) {
                String strZzbf2 = null;
                String strZzbf3 = null;
                int i6 = -1;
                int i7 = -1;
                while (zzoaVar.getPosition() < position) {
                    int position2 = zzoaVar.getPosition();
                    int i8 = zzoaVar.readInt();
                    int i9 = zzoaVar.readInt();
                    zzoaVar.zzbe(4);
                    if (i9 == zzjq.zzatu) {
                        strZzbf2 = zzoaVar.zzbf(i8 - 12);
                    } else if (i9 == zzjq.zzatv) {
                        strZzbf3 = zzoaVar.zzbf(i8 - 12);
                    } else {
                        if (i9 == zzjq.zzatw) {
                            i6 = position2;
                            i7 = i8;
                        }
                        zzoaVar.zzbe(i8 - 12);
                    }
                }
                if ("com.apple.iTunes".equals(strZzbf2) && "iTunSMPB".equals(strZzbf3) && i6 != -1) {
                    zzoaVar.zzbd(i6);
                    zzoaVar.zzbe(16);
                    zzlbVar = new zzlb(C.LANGUAGE_UNDETERMINED, strZzbf3, zzoaVar.zzbf(i7 - 16));
                }
                zzoaVar.zzbd(position);
                return zzlbVar;
            }
            String strValueOf2 = String.valueOf(zzjq.zzal(i));
            Log.d("MetadataUtil", strValueOf2.length() != 0 ? "Skipped unknown metadata entry: ".concat(strValueOf2) : new String("Skipped unknown metadata entry: "));
            zzoaVar.zzbd(position);
            return null;
        } catch (Throwable th) {
            zzoaVar.zzbd(position);
            throw th;
        }
    }

    private static zzlf zza(int i, String str, zzoa zzoaVar) {
        int i2 = zzoaVar.readInt();
        if (zzoaVar.readInt() == zzjq.zzatw) {
            zzoaVar.zzbe(8);
            return new zzlf(str, null, zzoaVar.zzbf(i2 - 16));
        }
        String strValueOf = String.valueOf(zzjq.zzal(i));
        Log.w("MetadataUtil", strValueOf.length() != 0 ? "Failed to parse text attribute: ".concat(strValueOf) : new String("Failed to parse text attribute: "));
        return null;
    }

    private static zzlc zza(int i, String str, zzoa zzoaVar, boolean z, boolean z2) {
        int iZze = zze(zzoaVar);
        if (z2) {
            iZze = Math.min(1, iZze);
        }
        if (iZze >= 0) {
            if (z) {
                return new zzlf(str, null, Integer.toString(iZze));
            }
            return new zzlb(C.LANGUAGE_UNDETERMINED, str, Integer.toString(iZze));
        }
        String strValueOf = String.valueOf(zzjq.zzal(i));
        Log.w("MetadataUtil", strValueOf.length() != 0 ? "Failed to parse uint8 attribute: ".concat(strValueOf) : new String("Failed to parse uint8 attribute: "));
        return null;
    }

    private static zzlf zzb(int i, String str, zzoa zzoaVar) {
        int i2 = zzoaVar.readInt();
        if (zzoaVar.readInt() == zzjq.zzatw && i2 >= 22) {
            zzoaVar.zzbe(10);
            int unsignedShort = zzoaVar.readUnsignedShort();
            if (unsignedShort > 0) {
                StringBuilder sb = new StringBuilder(11);
                sb.append(unsignedShort);
                String string = sb.toString();
                int unsignedShort2 = zzoaVar.readUnsignedShort();
                if (unsignedShort2 > 0) {
                    String strValueOf = String.valueOf(string);
                    StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf).length() + 12);
                    sb2.append(strValueOf);
                    sb2.append(Constants.URL_PATH_DELIMITER);
                    sb2.append(unsignedShort2);
                    string = sb2.toString();
                }
                return new zzlf(str, null, string);
            }
        }
        String strValueOf2 = String.valueOf(zzjq.zzal(i));
        Log.w("MetadataUtil", strValueOf2.length() != 0 ? "Failed to parse index/count attribute: ".concat(strValueOf2) : new String("Failed to parse index/count attribute: "));
        return null;
    }

    private static int zze(zzoa zzoaVar) {
        zzoaVar.zzbe(4);
        if (zzoaVar.readInt() == zzjq.zzatw) {
            zzoaVar.zzbe(8);
            return zzoaVar.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}
