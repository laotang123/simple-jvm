package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/27 10:08
 * @description: LocalVariableTypeTable_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 local_variable_type_table_length;
 * { u2 start_pc;
 * u2 length;
 * u2 name_index;
 * u2 signature_index;
 * u2 index;
 * } local_variable_type_table[local_variable_type_table_length];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class LocalVariableTypeTableAttribute implements AttributeInfo {
    private LocalVariableTypeTableEntry[] localVariableTypeTable;

    @Override
    public void readInfo(ClassReader reader) {
        int len = reader.readUint16();
        this.localVariableTypeTable = new LocalVariableTypeTableEntry[len];
        for (int i = 0; i < len; i++) {
            LocalVariableTypeTableEntry entry = new LocalVariableTypeTableEntry();
            entry.readInfo(reader);
            this.localVariableTypeTable[i] = entry;
        }
    }

    private static class LocalVariableTypeTableEntry {
        private int startPc;
        private int length;
        private int nameIndex;
        private int signatureIndex;
        private int index;

        private void readInfo(ClassReader reader) {
            this.startPc = reader.readUint16();
            this.length = reader.readUint16();
            this.nameIndex = reader.readUint16();
            this.signatureIndex = reader.readUint16();
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

        public int getSignatureIndex() {
            return signatureIndex;
        }

        public int getIndex() {
            return index;
        }
    }

    public LocalVariableTypeTableEntry[] getLocalVariableTypeTable() {
        return localVariableTypeTable;
    }
}
