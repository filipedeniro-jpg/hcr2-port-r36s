package org.json.simple.parser;

/* JADX INFO: loaded from: classes3.dex */
public class ParseException extends Exception {
    public static final int ERROR_UNEXPECTED_CHAR = 0;
    public static final int ERROR_UNEXPECTED_EXCEPTION = 2;
    public static final int ERROR_UNEXPECTED_TOKEN = 1;
    private static final long serialVersionUID = -7880698968187728548L;
    private int errorType;
    private int position;
    private Object unexpectedObject;

    public ParseException(int i) {
        this(-1, i, null);
    }

    public ParseException(int i, Object obj) {
        this(-1, i, obj);
    }

    public ParseException(int i, int i2, Object obj) {
        this.position = i;
        this.errorType = i2;
        this.unexpectedObject = obj;
    }

    public int getErrorType() {
        return this.errorType;
    }

    public void setErrorType(int i) {
        this.errorType = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public Object getUnexpectedObject() {
        return this.unexpectedObject;
    }

    public void setUnexpectedObject(Object obj) {
        this.unexpectedObject = obj;
    }

    @Override // java.lang.Throwable
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int i = this.errorType;
        if (i == 0) {
            stringBuffer.append("Unexpected character (");
            stringBuffer.append(this.unexpectedObject);
            stringBuffer.append(") at position ");
            stringBuffer.append(this.position);
            stringBuffer.append(".");
        } else if (i == 1) {
            stringBuffer.append("Unexpected token ");
            stringBuffer.append(this.unexpectedObject);
            stringBuffer.append(" at position ");
            stringBuffer.append(this.position);
            stringBuffer.append(".");
        } else if (i == 2) {
            stringBuffer.append("Unexpected exception at position ");
            stringBuffer.append(this.position);
            stringBuffer.append(": ");
            stringBuffer.append(this.unexpectedObject);
        } else {
            stringBuffer.append("Unkown error at position ");
            stringBuffer.append(this.position);
            stringBuffer.append(".");
        }
        return stringBuffer.toString();
    }
}
