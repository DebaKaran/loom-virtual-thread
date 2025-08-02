package com.dk.concurrent.fileprocessor;

public class LineMessage {

    private final String line;
    private final boolean isEnd;

    private LineMessage(String line, boolean isEnd) {
        this.line = line;
        this.isEnd = isEnd;
    }

    public static LineMessage end() {
        return new LineMessage(null, true);
    }

    public static LineMessage of(String line) {
        return new LineMessage(line, false);
    }

    public String getLine() {
        return line;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
