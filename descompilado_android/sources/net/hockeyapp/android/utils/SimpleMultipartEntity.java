package net.hockeyapp.android.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class SimpleMultipartEntity {
    private static final char[] BOUNDARY_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private String boundary;
    private boolean isSetFirst = false;
    private boolean isSetLast = false;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    public SimpleMultipartEntity() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            char[] cArr = BOUNDARY_CHARS;
            stringBuffer.append(cArr[random.nextInt(cArr.length)]);
        }
        this.boundary = stringBuffer.toString();
    }

    public String getBoundary() {
        return this.boundary;
    }

    public void writeFirstBoundaryIfNeeds() throws IOException {
        if (!this.isSetFirst) {
            this.out.write(("--" + this.boundary + "\r\n").getBytes());
        }
        this.isSetFirst = true;
    }

    public void writeLastBoundaryIfNeeds() {
        if (this.isSetLast) {
            return;
        }
        try {
            this.out.write(("\r\n--" + this.boundary + "--\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.isSetLast = true;
    }

    public void addPart(String str, String str2) throws IOException {
        writeFirstBoundaryIfNeeds();
        this.out.write(("Content-Disposition: form-data; name=\"" + str + "\"\r\n").getBytes());
        this.out.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes());
        this.out.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes());
        this.out.write(str2.getBytes());
        this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
    }

    public void addPart(String str, File file, boolean z) throws IOException {
        addPart(str, file.getName(), new FileInputStream(file), z);
    }

    public void addPart(String str, String str2, InputStream inputStream, boolean z) throws IOException {
        addPart(str, str2, inputStream, "application/octet-stream", z);
    }

    public void addPart(String str, String str2, InputStream inputStream, String str3, boolean z) throws IOException {
        writeFirstBoundaryIfNeeds();
        try {
            this.out.write(("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + str2 + "\"\r\n").getBytes());
            this.out.write(("Content-Type: " + str3 + "\r\n").getBytes());
            this.out.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    this.out.write(bArr, 0, i);
                }
            }
            this.out.flush();
            if (z) {
                writeLastBoundaryIfNeeds();
            } else {
                this.out.write(("\r\n--" + this.boundary + "\r\n").getBytes());
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public long getContentLength() {
        writeLastBoundaryIfNeeds();
        return this.out.toByteArray().length;
    }

    public String getContentType() {
        return "multipart/form-data; boundary=" + getBoundary();
    }

    public ByteArrayOutputStream getOutputStream() {
        writeLastBoundaryIfNeeds();
        return this.out;
    }
}
