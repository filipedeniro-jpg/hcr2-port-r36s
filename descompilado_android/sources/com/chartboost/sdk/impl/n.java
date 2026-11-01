package com.chartboost.sdk.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class n {
    /* JADX WARN: Code duplicated, block: B:74:0x0115 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x0110 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static String a(File file, Map<String, String> map) throws Exception {
        FileReader fileReader;
        BufferedReader bufferedReader;
        int iMax;
        FileReader fileReader2 = null;
        try {
            fileReader = new FileReader(file);
            try {
                bufferedReader = new BufferedReader(fileReader);
                try {
                    HashMap map2 = new HashMap();
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        if (key.startsWith("{{") || key.startsWith("{%")) {
                            map2.put(key, entry.getValue());
                        }
                    }
                    Set<Map.Entry> setEntrySet = map2.entrySet();
                    Iterator it = setEntrySet.iterator();
                    int length = 0;
                    while (it.hasNext()) {
                        length += ((String) ((Map.Entry) it.next()).getValue()).length() * 3;
                    }
                    StringBuilder sb = new StringBuilder(((int) file.length()) + length);
                    StringBuilder sb2 = new StringBuilder(2048);
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        int iIndexOf = line.indexOf("{{");
                        int iIndexOf2 = line.indexOf("{%");
                        if (iIndexOf != -1 && iIndexOf2 != -1) {
                            iMax = Math.min(iIndexOf, iIndexOf2);
                        } else {
                            iMax = Math.max(iIndexOf, iIndexOf2);
                        }
                        if (iMax == -1) {
                            sb.append(line);
                        } else {
                            sb2.setLength(0);
                            sb2.append(line);
                            for (Map.Entry entry2 : setEntrySet) {
                                String str = (String) entry2.getKey();
                                String str2 = (String) entry2.getValue();
                                int length2 = str.length();
                                while (true) {
                                    iMax = sb2.indexOf(str, iMax);
                                    if (-1 != iMax) {
                                        sb2.replace(iMax, iMax + length2, str2);
                                        iMax += str2.length();
                                    }
                                }
                            }
                            sb.append((CharSequence) sb2);
                        }
                        sb.append("\n");
                    }
                    String string = sb.toString();
                    if (string.contains("{{")) {
                        throw new IllegalArgumentException("Missing required template parameter");
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                    try {
                        fileReader.close();
                    } catch (IOException unused2) {
                    }
                    return string;
                } catch (OutOfMemoryError e) {
                    e = e;
                    fileReader2 = fileReader;
                    try {
                        throw new Exception(e);
                    } catch (Throwable th) {
                        th = th;
                        fileReader = fileReader2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused3) {
                            }
                        }
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (IOException unused4) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    throw th;
                }
            } catch (OutOfMemoryError e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (OutOfMemoryError e3) {
            e = e3;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            fileReader = null;
            bufferedReader = null;
        }
    }
}
