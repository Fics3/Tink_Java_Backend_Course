package edu.hw8.Task1.Server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TestSocket extends Socket {

    private final InputStream inputStream;
    private final OutputStream outputStream;

    public TestSocket(String input) {
        this.inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        this.outputStream = new ByteArrayOutputStream();
    }

    @Override
    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public OutputStream getOutputStream() {
        return outputStream;
    }

    public String getOutput() {
        return new String(((ByteArrayOutputStream) outputStream).toByteArray(), StandardCharsets.UTF_8);
    }

}
