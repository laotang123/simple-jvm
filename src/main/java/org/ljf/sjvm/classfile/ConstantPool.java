package org.ljf.sjvm.classfile;

/**
 * @author: ljf
 * @date: 2021/1/22 8:04
 * @description: 常量池
 * @modified By:
 * @version: $ 1.0
 */
public final class ConstantPool {
    private int constantPoolCount;
    private ConstantInfo[] constantInfos;

    public ConstantPool(ClassReader reader) {
        constantPoolCount = reader.readUint16();
        constantInfos = new ConstantInfo[constantPoolCount];
        //常量池索引是从1 到constantPoolCount-1
        for (int i = 1; i < constantPoolCount; i++) {
            constantInfos[i] = ConstantInfo.readConstantInfo(reader,this);

            //double和long常量占两个常量位；
            // http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5
            // All 8-byte constants take up two entries in the constant_pool table of the class file.
            // If a CONSTANT_Long_info or CONSTANT_Double_info structure is the item in the constant_pool
            // table at index n, then the next usable item in the pool is located at index n+2.
            // The constant_pool index n+1 must be valid but is considered unusable.
            if (constantInfos[i] instanceof ConstantDoubleInfo || constantInfos[i] instanceof ConstantLongInfo) {
                i++;
            }
        }
    }

}
