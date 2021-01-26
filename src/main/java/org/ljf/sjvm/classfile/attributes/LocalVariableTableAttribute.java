package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/25 16:06
 * @description: LocalVariableTable_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 local_variable_table_length;
 * { u2 start_pc;
 * u2 length;
 * u2 name_index;
 * u2 descriptor_index;
 * u2 index;
 * } local_variable_table[local_variable_table_length];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class LocalVariableTableAttribute implements AttributeInfo {
    private LocalVariableTableEntry[] localVariableTable;

    @Override
    public void readInfo(ClassReader reader) {
        int len = reader.readUint16();
        this.localVariableTable = new LocalVariableTableEntry[len];
        for (int i = 0; i < len; i++) {
            LocalVariableTableEntry entry = new LocalVariableTableEntry();
            entry.readInfo(reader);
            this.localVariableTable[i] = entry;
        }
    }

    private static class LocalVariableTableEntry {
        private int startPc;
        private int length;
        private int nameIndex;
        private int descriptorIndex;
        private int index;

        private void readInfo(ClassReader reader) {
            this.startPc = reader.readUint16();
            this.length = reader.readUint16();
            this.nameIndex = reader.readUint16();
            this.descriptorIndex = reader.readUint16();
            this.index = reader.readUint16();
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLength() {
            return length;
        }

        public int getNameIndex() {
            return nameIndex;
        }

        public int getDescriptorIndex() {
            return descriptorIndex;
        }

        public int getIndex() {
            return index;
        }
    }

    public LocalVariableTableEntry[] getLocalVariableTable() {
        return localVariableTable;
    }
}
