package okhttp3.internal.ws;

import com.google.android.gms.games.Notifications;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: loaded from: classes3.dex */
final class WebSocketReader {
    boolean closed;
    long frameBytesRead;
    final FrameCallback frameCallback;
    long frameLength;
    final boolean isClient;
    boolean isControlFrame;
    boolean isFinalFrame;
    boolean isMasked;
    int opcode;
    final BufferedSource source;
    final byte[] maskKey = new byte[4];
    final byte[] maskBuffer = new byte[8192];

    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    WebSocketReader(boolean z, BufferedSource bufferedSource, FrameCallback frameCallback) {
        if (bufferedSource == null) {
            throw new NullPointerException("source == null");
        }
        if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        }
        this.isClient = z;
        this.source = bufferedSource;
        this.frameCallback = frameCallback;
    }

    void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    private void readHeader() throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        long jTimeoutNanos = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        try {
            int i = this.source.readByte() & 255;
            this.source.timeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            this.opcode = i & 15;
            this.isFinalFrame = (i & 128) != 0;
            this.isControlFrame = (i & 8) != 0;
            if (this.isControlFrame && !this.isFinalFrame) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z = (i & 64) != 0;
            boolean z2 = (i & 32) != 0;
            boolean z3 = (i & 16) != 0;
            if (z || z2 || z3) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            int i2 = this.source.readByte() & 255;
            this.isMasked = (i2 & 128) != 0;
            boolean z4 = this.isMasked;
            boolean z5 = this.isClient;
            if (z4 == z5) {
                throw new ProtocolException(z5 ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
            }
            this.frameLength = i2 & Notifications.NOTIFICATION_TYPES_ALL;
            long j = this.frameLength;
            if (j == 126) {
                this.frameLength = ((long) this.source.readShort()) & 65535;
            } else if (j == 127) {
                this.frameLength = this.source.readLong();
                if (this.frameLength < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            this.frameBytesRead = 0L;
            if (this.isControlFrame && this.frameLength > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (this.isMasked) {
                this.source.readFully(this.maskKey);
            }
        } catch (Throwable th) {
            this.source.timeout().timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    private void readControlFrame() throws IOException {
        String utf8;
        Buffer buffer = new Buffer();
        long j = this.frameBytesRead;
        long j2 = this.frameLength;
        if (j < j2) {
            if (!this.isClient) {
                while (true) {
                    long j3 = this.frameBytesRead;
                    long j4 = this.frameLength;
                    if (j3 >= j4) {
                        break;
                    }
                    int i = this.source.read(this.maskBuffer, 0, (int) Math.min(j4 - j3, this.maskBuffer.length));
                    if (i == -1) {
                        throw new EOFException();
                    }
                    long j5 = i;
                    WebSocketProtocol.toggleMask(this.maskBuffer, j5, this.maskKey, this.frameBytesRead);
                    buffer.write(this.maskBuffer, 0, i);
                    this.frameBytesRead += j5;
                }
            } else {
                this.source.readFully(buffer, j2);
            }
        }
        switch (this.opcode) {
            case 8:
                short s = 1005;
                long size = buffer.size();
                if (size == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (size != 0) {
                    s = buffer.readShort();
                    utf8 = buffer.readUtf8();
                    String strCloseCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(s);
                    if (strCloseCodeExceptionMessage != null) {
                        throw new ProtocolException(strCloseCodeExceptionMessage);
                    }
                } else {
                    utf8 = "";
                }
                this.frameCallback.onReadClose(s, utf8);
                this.closed = true;
                return;
            case 9:
                this.frameCallback.onReadPing(buffer.readByteString());
                return;
            case 10:
                this.frameCallback.onReadPong(buffer.readByteString());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
        }
    }

    private void readMessageFrame() throws IOException {
        int i = this.opcode;
        if (i != 1 && i != 2) {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(i));
        }
        Buffer buffer = new Buffer();
        readMessage(buffer);
        if (i == 1) {
            this.frameCallback.onReadMessage(buffer.readUtf8());
        } else {
            this.frameCallback.onReadMessage(buffer.readByteString());
        }
    }

    void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (!this.isControlFrame) {
                return;
            } else {
                readControlFrame();
            }
        }
    }

    private void readMessage(Buffer buffer) throws IOException {
        long j;
        while (!this.closed) {
            if (this.frameBytesRead == this.frameLength) {
                if (this.isFinalFrame) {
                    return;
                }
                readUntilNonControlFrame();
                if (this.opcode != 0) {
                    throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.opcode));
                }
                if (this.isFinalFrame && this.frameLength == 0) {
                    return;
                }
            }
            long j2 = this.frameLength - this.frameBytesRead;
            if (this.isMasked) {
                j = this.source.read(this.maskBuffer, 0, (int) Math.min(j2, this.maskBuffer.length));
                if (j == -1) {
                    throw new EOFException();
                }
                WebSocketProtocol.toggleMask(this.maskBuffer, j, this.maskKey, this.frameBytesRead);
                buffer.write(this.maskBuffer, 0, (int) j);
            } else {
                j = this.source.read(buffer, j2);
                if (j == -1) {
                    throw new EOFException();
                }
            }
            this.frameBytesRead += j;
        }
        throw new IOException("closed");
    }
}
