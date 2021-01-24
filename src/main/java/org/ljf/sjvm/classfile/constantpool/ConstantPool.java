package org.ljf.sjvm.classfile.constantpool;

import org.ljf.sjvm.classfile.ClassReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: ljf
 * @date: 2021/1/22 8:04
 * @description: 常量池
 * @modified By:
 * @version: $ 1.0
 */
public final class ConstantPool {
    private static final Logger logger = LoggerFactory.getLogger(ConstantPool.class);

    private final int constantPoolCount;
    private final ConstantInfo[] constantInfos;

    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    //just for test
    public ConstantPool() {
        this.constantPoolCount = 1;
        this.constantInfos = new ConstantInfo[1];
    }

    public ConstantPool(ClassReader reader) {
        constantPoolCount = reader.readUint16();
        constantInfos = new ConstantInfo[constantPoolCount];
        //常量池索引是从1 到constantPoolCount-1
        for (int i = 1; i < constantPoolCount; i++) {
            logger.info("index of constantInfos: " + i);
            constantInfos[i] = ConstantInfo.readConstantInfo(reader, this);

            //double和long常量占两个常量位；
            // http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5
            // All 8-byte constants take up two entries in the constant_pool table of the class file.
            // If a CONSTANT_Long_info or CONSTANT_Double_info structure is the item in the constant_pool
            // table at index n, then the next usable item in the pool is located at index n+2.
            // The constant_pool index n+1 must be valid but is considered unusable.
            if (constantInfos[i] instanceof NumericInfo.ConstantDoubleInfo ||
                    constantInfos[i] instanceof NumericInfo.ConstantLongInfo) {
                i++;
            }
        }
    }

    /**
     * 根据索引获取常量池元素
     *
     * @param index：索引下表
     * @return ：constant info
     */
    public ConstantInfo getConstantInfo(int index) {
        ConstantInfo constantInfo = null;
        if (index >= 0 && index < constantInfos.length) {
            constantInfo = constantInfos[index];
        }
        if (constantInfo != null) {
            return constantInfo;
        }
        throw new IllegalArgumentException("invalid constant pool index!");
    }

    public ConstantNameAndTypeInfo getNameAndType(int index) {
        return (ConstantNameAndTypeInfo) this.getConstantInfo(index);
    }

    /**
     * 先从常量池表中查出ConstantClassInfo信息，再拿nameIndex查出className
     *
     * @param index：常量池索引
     * @return ：索引对应的类名字
     */
    public String getClassName(int index) {
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) this.getConstantInfo(index);
        return this.getUtf8(constantClassInfo.getNameIndex());
    }

    /**
     * ConstantUtf8Info重写了toString方法
     *
     * @param index：常量池表索引
     * @return ：toString方法返回值
     */
    public String getUtf8(int index) {
        ConstantInfo constantInfo = this.getConstantInfo(index);
        return constantInfo.toString();
    }
}
