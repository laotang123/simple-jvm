package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/25 16:06
 * @description: LineNumberTable_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 line_number_table_length;
 * { u2 start_pc;
 * u2 line_number;
 * } line_number_table[line_number_table_length];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class LineNumberTableAttribute implements AttributeInfo {
    private LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader reader) {
        int len = reader.readUint16();
        this.lineNumberTable = new LineNumberTableEntry[len];
        for (int i = 0; i < len; i++) {
            this.lineNumberTable[i] = new LineNumberTableEntry(reader.readUint16(), reader.readUint16());
        }
    }

    private static class LineNumberTableEntry {
        private final int startPc;
        private final int lineNumber;

        LineNumberTableEntry(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }

    /**
     * lineNumberTable方向查找，找出第一个startPc大于等于pc对应的lineNumber
     *
     * @param pc：待查找行开头索引
     * @return ：行号
     */
    public int getLineNumber(int pc) {
        for (int i = lineNumberTable.length - 1; i >= 0; i--) {
            LineNumberTableEntry entry = lineNumberTable[i];
            if (pc >= entry.startPc) {
                return entry.lineNumber;
            }
        }
        return -1;
    }


}
