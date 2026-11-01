package org.json.simple;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/* JADX INFO: loaded from: classes3.dex */
public class JSONValue {
    public static Object parse(Reader reader) {
        try {
            return new JSONParser().parse(reader);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object parse(String str) {
        return parse(new StringReader(str));
    }

    public static Object parseWithException(Reader reader) throws ParseException, IOException {
        return new JSONParser().parse(reader);
    }

    public static Object parseWithException(String str) throws ParseException {
        return new JSONParser().parse(str);
    }

    public static void writeJSONString(Object obj, Writer writer) throws IOException {
        if (obj == null) {
            writer.write("null");
            return;
        }
        if (obj instanceof String) {
            writer.write(34);
            writer.write(escape((String) obj));
            writer.write(34);
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (d.isInfinite() || d.isNaN()) {
                writer.write("null");
                return;
            } else {
                writer.write(obj.toString());
                return;
            }
        }
        if (obj instanceof Float) {
            Float f = (Float) obj;
            if (f.isInfinite() || f.isNaN()) {
                writer.write("null");
                return;
            } else {
                writer.write(obj.toString());
                return;
            }
        }
        if (obj instanceof Number) {
            writer.write(obj.toString());
            return;
        }
        if (obj instanceof Boolean) {
            writer.write(obj.toString());
            return;
        }
        if (obj instanceof JSONStreamAware) {
            ((JSONStreamAware) obj).writeJSONString(writer);
            return;
        }
        if (obj instanceof JSONAware) {
            writer.write(((JSONAware) obj).toJSONString());
            return;
        }
        if (obj instanceof Map) {
            JSONObject.writeJSONString((Map) obj, writer);
        } else if (obj instanceof List) {
            JSONArray.writeJSONString((List) obj, writer);
        } else {
            writer.write(obj.toString());
        }
    }

    public static String toJSONString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("\"");
            stringBuffer.append(escape((String) obj));
            stringBuffer.append("\"");
            return stringBuffer.toString();
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            return (d.isInfinite() || d.isNaN()) ? "null" : obj.toString();
        }
        if (obj instanceof Float) {
            Float f = (Float) obj;
            return (f.isInfinite() || f.isNaN()) ? "null" : obj.toString();
        }
        if (obj instanceof Number) {
            return obj.toString();
        }
        if (obj instanceof Boolean) {
            return obj.toString();
        }
        if (obj instanceof JSONAware) {
            return ((JSONAware) obj).toJSONString();
        }
        if (obj instanceof Map) {
            return JSONObject.toJSONString((Map) obj);
        }
        if (obj instanceof List) {
            return JSONArray.toJSONString((List) obj);
        }
        return obj.toString();
    }

    public static String escape(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        escape(str, stringBuffer);
        return stringBuffer.toString();
    }

    static void escape(String str, StringBuffer stringBuffer) {
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\f') {
                stringBuffer.append("\\f");
            } else if (cCharAt == '\r') {
                stringBuffer.append("\\r");
            } else if (cCharAt == '\"') {
                stringBuffer.append("\\\"");
            } else if (cCharAt == '/') {
                stringBuffer.append("\\/");
            } else if (cCharAt == '\\') {
                stringBuffer.append("\\\\");
            } else {
                switch (cCharAt) {
                    case '\b':
                        stringBuffer.append("\\b");
                        break;
                    case '\t':
                        stringBuffer.append("\\t");
                        break;
                    case '\n':
                        stringBuffer.append("\\n");
                        break;
                    default:
                        if ((cCharAt >= 0 && cCharAt <= 31) || ((cCharAt >= 127 && cCharAt <= 159) || (cCharAt >= 8192 && cCharAt <= 8447))) {
                            String hexString = Integer.toHexString(cCharAt);
                            stringBuffer.append("\\u");
                            for (int i2 = 0; i2 < 4 - hexString.length(); i2++) {
                                stringBuffer.append('0');
                            }
                            stringBuffer.append(hexString.toUpperCase());
                        } else {
                            stringBuffer.append(cCharAt);
                        }
                        break;
                }
            }
        }
    }
}
