package com.amazon.device.ads;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/* JADX INFO: loaded from: classes.dex */
class FileOutputHandler extends FileHandler {
    private static final String LOGTAG = "FileOutputHandler";
    private BufferedWriter bufferedWriter;
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private OutputStream outputStream;
    private WriteMethod writeMethod;

    public enum WriteMethod {
        APPEND,
        OVERWRITE
    }

    FileOutputHandler() {
    }

    @Override // com.amazon.device.ads.FileHandler
    public boolean isOpen() {
        return this.outputStream != null;
    }

    public boolean open(WriteMethod writeMethod) {
        if (this.file == null) {
            this.logger.e("A file must be set before it can be opened.");
            return false;
        }
        if (this.outputStream != null) {
            this.logger.e("The file is already open.");
            return false;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.file, WriteMethod.APPEND.equals(writeMethod));
            this.writeMethod = writeMethod;
            this.outputStream = new BufferedOutputStream(fileOutputStream);
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.outputStream));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.device.ads.FileHandler, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        flush();
        closeCloseables();
        this.bufferedWriter = null;
        this.outputStream = null;
    }

    public void flush() {
        OutputStream outputStream = this.outputStream;
        if (outputStream != null) {
            try {
                outputStream.flush();
            } catch (IOException e) {
                this.logger.e("Could not flush the OutputStream. %s", e.getMessage());
            }
        }
        BufferedWriter bufferedWriter = this.bufferedWriter;
        if (bufferedWriter != null) {
            try {
                bufferedWriter.flush();
            } catch (IOException e2) {
                this.logger.e("Could not flush the BufferedWriter. %s", e2.getMessage());
            }
        }
    }

    @Override // com.amazon.device.ads.FileHandler
    protected Closeable getCloseableStream() {
        return this.outputStream;
    }

    @Override // com.amazon.device.ads.FileHandler
    protected Closeable getCloseableReaderWriter() {
        return this.bufferedWriter;
    }

    private void checkWritable() {
        if (this.bufferedWriter == null) {
            throw new IllegalStateException("Could not write to the file because no file has been opened yet. Please set the file, then call open() before attempting to write.");
        }
    }

    public void write(String str) throws IOException {
        checkWritable();
        this.bufferedWriter.write(str);
    }

    public void write(byte[] bArr) throws IOException {
        checkWritable();
        this.outputStream.write(bArr);
    }

    public boolean overwriteFile(String str) {
        return writeToFile(str, WriteMethod.OVERWRITE);
    }

    public boolean appendToFile(String str) {
        return writeToFile(str, WriteMethod.APPEND);
    }

    private boolean writeToFile(String str, WriteMethod writeMethod) {
        if (isOpen()) {
            if (!writeMethod.equals(this.writeMethod)) {
                close();
                if (!open(writeMethod)) {
                    this.logger.e("Could not reopen the file for %s.", writeMethod.toString());
                    return false;
                }
            }
        } else if (!open(writeMethod)) {
            this.logger.e("Could not open the file for writing.");
            return false;
        }
        try {
            write(str);
            close();
            return true;
        } catch (IOException unused) {
            this.logger.e("Failed to write data to the file.");
            return false;
        }
    }
}
