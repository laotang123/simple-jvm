package org.ljf.sjvm.rtda.heap;

import com.sun.org.apache.bcel.internal.classfile.ConstantInteger;
import org.ljf.sjvm.classfile.constantpool.*;

/**
 * @author: ljf
 * @date: 2021/2/1 7:52
 * @description: 运行时常量池
 * 存放两类信息：
 * 1.字面量：包括整数，浮点数和字符串字面量；
 * 2.符号引用：包括类符号引用，字段符号引用，方法符号引用和接口方法符号引用。
 * 运行时常量池与classFile的常量池是一一对应的
 * 多个Class类的运行时常量池的字符创常量池是共享的？
 * @modified By:
 * @version: $ 1.0
 */
public class ConstantPool {
    private final Class clazz;//类对象，所有实例对象共有
    private final Constant[] consts;

    public ConstantPool(Class clazz, org.ljf.sjvm.classfile.constantpool.ConstantPool cfConstantPool) {
        int cpCount = cfConstantPool.getConstantPoolCount();
        this.consts = new Constant[cpCount];
//        ConstantPool rtConstantPool = new ConstantPool(clazz, cfConstantPool);
        this.clazz = clazz;

        ConstantInfo[] cfConstantInfos = cfConstantPool.getConstantInfos();
        ConstantInfo cpInfo;
        //将 classfile中的ConstantInfo转换为运行时常量池中的constant
        for (int i = 0; i < cpCount; i++) {
            cpInfo = cfConstantInfos[i];
            if (cpInfo instanceof NumericInfo.ConstantIntegerInfo) {
                consts[i] = new Literal.IntegerLiteral(((NumericInfo.ConstantIntegerInfo) cpInfo).getValue());
            } else if (cpInfo instanceof NumericInfo.ConstantLongInfo) {
                consts[i] = new Literal.LongLiteral(((NumericInfo.ConstantLongInfo) cpInfo).getValue());
                i++;
            } else if (cpInfo instanceof NumericInfo.ConstantFloatInfo) {
                consts[i] = new Literal.FloatLiteral(((NumericInfo.ConstantFloatInfo) cpInfo).getValue());
            } else if (cpInfo instanceof NumericInfo.ConstantDoubleInfo) {
                consts[i] = new Literal.DoubleLiteral(((NumericInfo.ConstantDoubleInfo) cpInfo).getValue());
                i++;
            } else if (cpInfo instanceof ConstantStringInfo) {
                consts[i] = new Literal.StringLiteral(((ConstantStringInfo) cpInfo).getValue());
            } else if (cpInfo instanceof ConstantClassInfo) {
                consts[i] = new ClassRef(this, (ConstantClassInfo) cpInfo);
            } else if (cpInfo instanceof ConstantMemberRefInfo.ConstantFieldRefInfo) {
                consts[i] = new FieldRef(this, (ConstantMemberRefInfo.ConstantFieldRefInfo) cpInfo);
            }else if (cpInfo instanceof ConstantMemberRefInfo.ConstantMethodRefInfo) {
                consts[i] = new MethodRef(this, (ConstantMemberRefInfo.ConstantMethodRefInfo) cpInfo);
            }else if (cpInfo instanceof ConstantMemberRefInfo.ConstantInterfaceMethodRefInfo) {
                consts[i] = new InterfaceMethodRef(this,
                        (ConstantMemberRefInfo.ConstantInterfaceMethodRefInfo) cpInfo);
            }else{
                //todo
            }
        }
    }

    public Constant getConstant(int index) {
        Constant constant = this.consts[index];
        if (constant != null) {
            return constant;
        }
        throw new IllegalArgumentException("No constant at index: " + index);
    }

    public Class getClazz() {
        return clazz;
    }
}
