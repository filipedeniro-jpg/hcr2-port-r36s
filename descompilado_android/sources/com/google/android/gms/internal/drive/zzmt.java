package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
final class zzmt {
    static String zzc(zzjc zzjcVar) {
        zzmu zzmuVar = new zzmu(zzjcVar);
        StringBuilder sb = new StringBuilder(zzmuVar.size());
        for (int i = 0; i < zzmuVar.size(); i++) {
            byte bZzs = zzmuVar.zzs(i);
            if (bZzs == 34) {
                sb.append("\\\"");
            } else if (bZzs == 39) {
                sb.append("\\'");
            } else if (bZzs != 92) {
                switch (bZzs) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (bZzs < 32 || bZzs > 126) {
                            sb.append('\\');
                            sb.append((char) (((bZzs >>> 6) & 3) + 48));
                            sb.append((char) (((bZzs >>> 3) & 7) + 48));
                            sb.append((char) ((bZzs & 7) + 48));
                        } else {
                            sb.append((char) bZzs);
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
