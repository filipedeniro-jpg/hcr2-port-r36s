package com.facebook.ads.redexgen.X;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Locale;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class M1 implements LY {
    public File B;
    private RandomAccessFile C;
    private final M0 D;

    public M1(File file, M0 diskUsage) throws C0667Le {
        try {
            if (diskUsage != null) {
                this.D = diskUsage;
                M5.C(file.getParentFile());
                boolean zExists = file.exists();
                this.B = zExists ? file : new File(file.getParentFile(), file.getName() + ".download");
                this.C = new RandomAccessFile(this.B, zExists ? "r" : "rw");
                return;
            }
            throw new NullPointerException();
        } catch (IOException e) {
            throw new C0667Le("Error using file " + file + " as disc cache", e);
        }
    }

    private boolean B(File file) {
        return file.getName().endsWith(".download");
    }

    @Override // com.facebook.ads.redexgen.X.LY
    public final synchronized boolean RC() {
        return !B(this.B);
    }

    @Override // com.facebook.ads.redexgen.X.LY
    public final synchronized void YB(byte[] data, int length) throws C0667Le {
        try {
            if (RC()) {
                throw new C0667Le("Error append cache: cache file " + this.B + " is completed!");
            }
            this.C.seek(available());
            this.C.write(data, 0, length);
        } catch (IOException e) {
            throw new C0667Le(String.format(Locale.US, "Error writing %d bytes to %s from buffer with size %d", Integer.valueOf(length), this.C, Integer.valueOf(data.length)), e);
        }
    }

    @Override // com.facebook.ads.redexgen.X.LY
    public final synchronized int available() throws C0667Le {
        try {
        } catch (IOException e) {
            throw new C0667Le("Error reading length of file " + this.B, e);
        }
        return (int) this.C.length();
    }

    @Override // com.facebook.ads.redexgen.X.LY
    public final synchronized void close() throws C0667Le {
        try {
            this.C.close();
            this.D.vF(this.B);
        } catch (IOException e) {
            throw new C0667Le("Error closing file " + this.B, e);
        }
    }

    @Override // com.facebook.ads.redexgen.X.LY
    public final synchronized int gF(byte[] buffer, long offset, int length) throws C0667Le {
        try {
            this.C.seek(offset);
        } catch (IOException e) {
            throw new C0667Le(String.format(Locale.US, "Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", Integer.valueOf(length), Long.valueOf(offset), Integer.valueOf(available()), Integer.valueOf(buffer.length)), e);
        }
        return this.C.read(buffer, 0, length);
    }

    @Override // com.facebook.ads.redexgen.X.LY
    public final synchronized void iB() throws C0667Le {
        if (!RC()) {
            close();
            File file = new File(this.B.getParentFile(), this.B.getName().substring(0, this.B.getName().length() - ".download".length()));
            if (!this.B.renameTo(file)) {
                String fileName = "Error renaming file " + this.B + " to " + file + " for completion!";
                throw new C0667Le(fileName);
            }
            this.B = file;
            try {
                this.C = new RandomAccessFile(this.B, "r");
            } catch (IOException e) {
                throw new C0667Le("Error opening " + this.B + " as disc cache", e);
            }
        }
    }
}
