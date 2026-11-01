package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.io.Writer;

/* JADX INFO: loaded from: classes.dex */
public class bk extends Writer implements Serializable {
    private final StringBuilder a;

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
    }

    public bk() {
        this.a = new StringBuilder();
    }

    public bk(int i) {
        this.a = new StringBuilder(i);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(char c) {
        this.a.append(c);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence) {
        this.a.append(charSequence);
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(CharSequence charSequence, int i, int i2) {
        this.a.append(charSequence, i, i2);
        return this;
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str != null) {
            this.a.append(str);
        }
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        if (cArr != null) {
            this.a.append(cArr, i, i2);
        }
    }

    public String toString() {
        return this.a.toString();
    }
}
