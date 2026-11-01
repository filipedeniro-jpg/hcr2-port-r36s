package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class ExpandedProductResultParser extends ResultParser {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:135:0x020d  */
    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v5 int, still in use, count: 10, list:
  (r3v5 int) from 0x0053: IF  (r3v5 int) != (1536 int)  -> B:13:0x0055 A[HIDDEN]
  (r3v5 int) from 0x0057: IF  (r3v5 int) != (1537 int)  -> B:15:0x0059 A[HIDDEN]
  (r3v5 int) from 0x005b: IF  (r3v5 int) != (1567 int)  -> B:17:0x005d A[HIDDEN]
  (r3v5 int) from 0x005f: IF  (r3v5 int) != (1568 int)  -> B:19:0x0061 A[HIDDEN]
  (r3v5 int) from 0x0063: IF  (r3v5 int) != (1570 int)  -> B:21:0x0065 A[HIDDEN]
  (r3v5 int) from 0x0067: IF  (r3v5 int) != (1572 int)  -> B:23:0x0069 A[HIDDEN]
  (r3v5 int) from 0x006b: IF  (r3v5 int) != (1574 int)  -> B:25:0x006d A[HIDDEN]
  (r3v5 int) from 0x0070: SWITCH (r3v5 int)
 case 1568927: goto B:81:0x0147
 case 1568928: goto B:78:0x013b
 case 1568929: goto B:75:0x012f
 case 1568930: goto B:72:0x0123
 case 1568931: goto B:69:0x0117
 case 1568932: goto B:66:0x010b
 case 1568933: goto B:63:0x00ff
 case 1568934: goto B:60:0x00f3
 case 1568935: goto B:57:0x00e7
 case 1568936: goto B:54:0x00db
 default: goto B:27:0x0073 A[RegionRef:SW:26]
  (r3v5 int) from 0x0073: SWITCH (r3v5 int)
 case 1575716: goto B:51:0x00cf
 case 1575717: goto B:48:0x00c3
 case 1575718: goto B:45:0x00b7
 case 1575719: goto B:42:0x00ab
 default: goto B:28:0x0076 A[RegionRef:SW:27]
  (r3v5 int) from 0x0076: SWITCH (r3v5 int)
 case 1575747: goto B:39:0x009f
 case 1575748: goto B:36:0x0093
 case 1575749: goto B:33:0x0087
 case 1575750: goto B:30:0x007b
 default: goto B:135:0x020d A[RegionRef:SW:28]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:226)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:215)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.replaceWithMergedSwitch(SwitchOverStringVisitor.java:355)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:111)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.zxing.client.result.ResultParser
    public ExpandedProductParsedResult parse(Result result) {
        byte b;
        ExpandedProductParsedResult expandedProductParsedResult = null;
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        String massagedText = getMassagedText(result);
        HashMap map = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String strSubstring = null;
        String strSubstring2 = null;
        String strSubstring3 = null;
        String strSubstring4 = null;
        int length = 0;
        while (length < massagedText.length()) {
            String strFindAIvalue = findAIvalue(length, massagedText);
            if (strFindAIvalue == null) {
                return expandedProductParsedResult;
            }
            int length2 = length + strFindAIvalue.length() + 2;
            String strFindValue = findValue(length2, massagedText);
            length = length2 + strFindValue.length();
            String str10 = strSubstring2;
            String str11 = strSubstring;
            if (iHashCode != 1536) {
                if (iHashCode != 1537) {
                    if (iHashCode != 1567) {
                        if (iHashCode != 1568) {
                            if (iHashCode != 1570) {
                                if (iHashCode != 1572) {
                                    if (iHashCode != 1574) {
                                        switch (strFindAIvalue) {
                                            case "3100":
                                                b = 7;
                                                break;
                                            case "3101":
                                                b = 8;
                                                break;
                                            case "3102":
                                                b = 9;
                                                break;
                                            case "3103":
                                                b = 10;
                                                break;
                                            case "3104":
                                                b = 11;
                                                break;
                                            case "3105":
                                                b = 12;
                                                break;
                                            case "3106":
                                                b = 13;
                                                break;
                                            case "3107":
                                                b = 14;
                                                break;
                                            case "3108":
                                                b = 15;
                                                break;
                                            case "3109":
                                                b = 16;
                                                break;
                                            default:
                                                switch (strFindAIvalue) {
                                                    case 1568927:
                                                        if (!strFindAIvalue.equals("3200")) {
                                                            b = -1;
                                                        } else {
                                                            b = 17;
                                                        }
                                                        break;
                                                    case 1568928:
                                                        if (!strFindAIvalue.equals("3201")) {
                                                            b = -1;
                                                        } else {
                                                            b = 18;
                                                        }
                                                        break;
                                                    case 1568929:
                                                        if (!strFindAIvalue.equals("3202")) {
                                                            b = -1;
                                                        } else {
                                                            b = 19;
                                                        }
                                                        break;
                                                    case 1568930:
                                                        if (!strFindAIvalue.equals("3203")) {
                                                            b = -1;
                                                        } else {
                                                            b = 20;
                                                        }
                                                        break;
                                                    case 1568931:
                                                        if (!strFindAIvalue.equals("3204")) {
                                                            b = -1;
                                                        } else {
                                                            b = 21;
                                                        }
                                                        break;
                                                    case 1568932:
                                                        if (!strFindAIvalue.equals("3205")) {
                                                            b = -1;
                                                        } else {
                                                            b = 22;
                                                        }
                                                        break;
                                                    case 1568933:
                                                        if (!strFindAIvalue.equals("3206")) {
                                                            b = -1;
                                                        } else {
                                                            b = 23;
                                                        }
                                                        break;
                                                    case 1568934:
                                                        if (!strFindAIvalue.equals("3207")) {
                                                            b = -1;
                                                        } else {
                                                            b = 24;
                                                        }
                                                        break;
                                                    case 1568935:
                                                        if (!strFindAIvalue.equals("3208")) {
                                                            b = -1;
                                                        } else {
                                                            b = 25;
                                                        }
                                                        break;
                                                    case 1568936:
                                                        if (!strFindAIvalue.equals("3209")) {
                                                            b = -1;
                                                        } else {
                                                            b = 26;
                                                        }
                                                        break;
                                                    default:
                                                        switch (strFindAIvalue) {
                                                            case 1575716:
                                                                if (!strFindAIvalue.equals("3920")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 27;
                                                                }
                                                                break;
                                                            case 1575717:
                                                                if (!strFindAIvalue.equals("3921")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 28;
                                                                }
                                                                break;
                                                            case 1575718:
                                                                if (!strFindAIvalue.equals("3922")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 29;
                                                                }
                                                                break;
                                                            case 1575719:
                                                                if (!strFindAIvalue.equals("3923")) {
                                                                    b = -1;
                                                                } else {
                                                                    b = 30;
                                                                }
                                                                break;
                                                            default:
                                                                switch (strFindAIvalue) {
                                                                    case 1575747:
                                                                        if (!strFindAIvalue.equals("3930")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 31;
                                                                        }
                                                                        break;
                                                                    case 1575748:
                                                                        if (!strFindAIvalue.equals("3931")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 32;
                                                                        }
                                                                        break;
                                                                    case 1575749:
                                                                        if (!strFindAIvalue.equals("3932")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 33;
                                                                        }
                                                                        break;
                                                                    case 1575750:
                                                                        if (!strFindAIvalue.equals("3933")) {
                                                                            b = -1;
                                                                        } else {
                                                                            b = 34;
                                                                        }
                                                                        break;
                                                                    default:
                                                                        b = -1;
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                        }
                                    } else if (strFindAIvalue.equals("17")) {
                                        b = 6;
                                    } else {
                                        b = -1;
                                    }
                                } else if (strFindAIvalue.equals("15")) {
                                    b = 5;
                                } else {
                                    b = -1;
                                }
                            } else if (strFindAIvalue.equals("13")) {
                                b = 4;
                            } else {
                                b = -1;
                            }
                        } else if (strFindAIvalue.equals("11")) {
                            b = 3;
                        } else {
                            b = -1;
                        }
                    } else if (strFindAIvalue.equals("10")) {
                        b = 2;
                    } else {
                        b = -1;
                    }
                } else if (strFindAIvalue.equals("01")) {
                    b = 1;
                } else {
                    b = -1;
                }
            } else if (strFindAIvalue.equals("00")) {
                b = 0;
            } else {
                b = -1;
            }
            switch (b) {
                case 0:
                    str2 = strFindValue;
                    strSubstring2 = str10;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                case 1:
                    str = strFindValue;
                    strSubstring2 = str10;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                case 2:
                    str3 = strFindValue;
                    strSubstring2 = str10;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                case 3:
                    str4 = strFindValue;
                    strSubstring2 = str10;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                case 4:
                    str5 = strFindValue;
                    strSubstring2 = str10;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                case 5:
                    str6 = strFindValue;
                    strSubstring2 = str10;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                case 6:
                    str7 = strFindValue;
                    strSubstring2 = str10;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                    strSubstring = strFindAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.KILOGRAM;
                    str8 = strFindValue;
                    length = length;
                    strSubstring2 = str10;
                    expandedProductParsedResult = null;
                    break;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    strSubstring = strFindAIvalue.substring(3);
                    str9 = ExpandedProductParsedResult.POUND;
                    str8 = strFindValue;
                    length = length;
                    strSubstring2 = str10;
                    expandedProductParsedResult = null;
                    break;
                case 27:
                case 28:
                case 29:
                case 30:
                    strSubstring3 = strFindAIvalue.substring(3);
                    strSubstring2 = strFindValue;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                case 31:
                case 32:
                case 33:
                case 34:
                    if (strFindValue.length() < 4) {
                        return null;
                    }
                    strSubstring2 = strFindValue.substring(3);
                    strSubstring4 = strFindValue.substring(0, 3);
                    strSubstring3 = strFindAIvalue.substring(3);
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
                default:
                    map.put(strFindAIvalue, strFindValue);
                    strSubstring2 = str10;
                    strSubstring = str11;
                    expandedProductParsedResult = null;
                    break;
            }
        }
        return new ExpandedProductParsedResult(massagedText, str, str2, str3, str4, str5, str6, str7, str8, str9, strSubstring, strSubstring2, strSubstring3, strSubstring4, map);
    }

    private static String findAIvalue(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String strSubstring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt == ')') {
                return sb.toString();
            }
            if (cCharAt < '0' || cCharAt > '9') {
                return null;
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(i);
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt == '(') {
                if (findAIvalue(i2, strSubstring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }
}
