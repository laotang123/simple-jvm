package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/27 9:46
 * @description: InnerClasses_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 number_of_classes;
 * { u2 inner_class_info_index;
 * u2 outer_class_info_index;
 * u2 inner_name_index;
 * u2 inner_class_access_flags;
 * } classes[number_of_classes];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class InnerClassAttribute implements AttributeInfo {
    private InnerClassInfo[] classes;

    @Override
    public void readInfo(ClassReader reader) {
        int numberOfClasses = reader.readUint16();
        classes = new InnerClassInfo[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            InnerClassInfo innerClassInfo = new InnerClassInfo();
            innerClassInfo.setInnerClassInfoIndex(reader.readUint16());
            innerClassInfo.setOuterClassInfoIndex(reader.readUint16());
            innerClassInfo.setInnerNameIndex(reader.readUint16());
            innerClassInfo.setInnerClassAccessFlags(reader.readUint16());
        }
    }


    private static class InnerClassInfo {
        private int innerClassInfoIndex;
        private int outerClassInfoIndex;
        private int innerNameIndex;
        private int innerClassAccessFlags;

        public int getInnerClassInfoIndex() {
            return innerClassInfoIndex;
        }

        public int getOuterClassInfoIndex() {
            return outerClassInfoIndex;
        }

        public int getInnerNameIndex() {
            return innerNameIndex;
        }

        public int getInnerClassAccessFlags() {
            return innerClassAccessFlags;
        }

        public void setInnerClassInfoIndex(int innerClassInfoIndex) {
            this.innerClassInfoIndex = innerClassInfoIndex;
        }

        public void setOuterClassInfoIndex(int outerClassInfoIndex) {
            this.outerClassInfoIndex = outerClassInfoIndex;
        }

        public void setInnerNameIndex(int innerNameIndex) {
            this.innerNameIndex = innerNameIndex;
        }

        public void setInnerClassAccessFlags(int innerClassAccessFlags) {
            this.innerClassAccessFlags = innerClassAccessFlags;
        }
    }

    public InnerClassInfo[] getClasses() {
        return classes;
    }
}
