package com.dk.concurrent.fileprocessor;

public class LineMessage {

    private final String line;
    private final boolean isEnd;
    private final String resourceName;

    private LineMessage(String line, boolean isEnd, String resourceName) {
        this.line = line;
        this.isEnd = isEnd;
        this.resourceName = resourceName;
    }

    public static LineMessage end(String resourceName) {
        return new LineMessage(null, true, resourceName);
    }

    public static LineMessage of(String line, String resourceName) {
        return new LineMessage(line, false, resourceName);
    }

    public String getLine() {
        return line;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
