package org.ljf.sjvm.rtda.heap;

/**
 * @author: ljf
 * @date: 2021/2/5 11:17
 * @description: 方法参数解析器
 * @modified By：
 * @version: $ 1.0
 */
public class MethodDescriptorParser {
    private String raw;
    private int offset;
    private MethodDescriptor parsed;

    public MethodDescriptor parseMethodDescriptor(String descriptor) {
        return parse(descriptor);
    }

    private MethodDescriptor parse(String descriptor) {
        this.raw = descriptor;
        this.parsed = new MethodDescriptor();
        this.startParams();
        this.parseParamTypes();
        this.endParams();
        this.parseReturnType();
        this.finish();
        return this.parsed;
    }

    private void finish() {
        if (this.offset != this.raw.length()) {
            throw new IllegalArgumentException("finish method, BAD descriptor: " + this.raw);
        }
    }

    private void parseReturnType() {
        if (this.readUint8() == 'V') {
            this.parsed.setReturnType("V");
            return;
        }

        this.unreadUit8();
        String objectType = this.parseObjectType();
        if (!objectType.equals("")) {
            this.parsed.setReturnType(objectType);
            return;
        }

        throw new IllegalArgumentException("BAD descriptor: " + this.raw);
    }

    private void endParams() {
        if (this.readUint8() != ')') {
            throw new IllegalArgumentException("BAD descriptor: " + this.raw);
        }
    }

    private void parseParamTypes() {
        String fieldType = this.parseFieldType();
        while (!fieldType.equals("")) {
            this.parsed.addParameterType(fieldType);
        }
    }

    private String parseFieldType() {
        switch (this.readUint8()) {
            case 'B':
                return "B";
            case 'C':
                return "C";
            case 'D':
                return "D";
            case 'F':
                return "F";
            case 'I':
                return "I";
            case 'J':
                return "J";
            case 'S':
                return "S";
            case 'Z':
                return "Z";
            case 'L':
                return this.parseObjectType();
            case '[':
                return this.parseArrayType();
            default:
                this.unreadUit8();
                return "";

        }
    }

    private String parseArrayType() {
        int arrStart = this.offset - 1;
        this.parseFieldType();
        int arrEnd = this.offset;
        return this.raw.substring(arrStart, arrEnd);
    }

    private String parseObjectType() {
        String unread = this.raw.substring(this.offset);
        int semicolonIndex = unread.indexOf(';');
        if (semicolonIndex == -1) {
            throw new IllegalArgumentException();
        } else {
            int objStart = this.offset - 1;
            int objEnd = this.offset + semicolonIndex + 1;
            this.offset = objEnd;
            return this.raw.substring(objStart, objEnd);
        }
    }

    private void startParams() {
        if (this.readUint8() != '(') {
            throw new IllegalArgumentException("BAD descriptor: " + this.raw);
        }
    }

    private char readUint8() {
        char c = this.raw.charAt(this.offset);
        this.offset++;
        return c;
    }

    private void unreadUit8() {
        this.offset--;
    }
}
