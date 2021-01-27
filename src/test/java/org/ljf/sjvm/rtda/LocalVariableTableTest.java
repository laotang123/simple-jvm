package org.ljf.sjvm.rtda;

/**
 * @author: ljf
 * @date: 2021/1/28 7:29
 * @description: 本地方法表测试
 * 栈帧的元素之一，包含slots数组
 * @modified By:
 * @version: $ 1.0
 */
public class LocalVariableTableTest {
    public static void main(String[] args) {
        LocalVariableTable table = new LocalVariableTable(20);
        table.setInt(0, Integer.MAX_VALUE);
        System.out.println(table.getInt(0) == Integer.MAX_VALUE);
        table.setInt(1, Integer.MIN_VALUE);
        System.out.println(table.getInt(1) == Integer.MIN_VALUE);

        //long，占两个slot
        table.setLong(2, Long.MAX_VALUE);
        System.out.println(table.getLong(2) == Long.MAX_VALUE);
        table.setLong(4, Long.MIN_VALUE);
        System.out.println(table.getLong(4) == Long.MIN_VALUE);

        //float
        table.setFloat(6, Float.MAX_VALUE);
        System.out.println(table.getFloat(6) == Float.MAX_VALUE);
        table.setFloat(7, Float.MIN_VALUE);
        System.out.println(table.getFloat(7) == Float.MIN_VALUE);

        //double，占两个slot
        table.setDouble(8, Double.MAX_VALUE);
        System.out.println(table.getDouble(8) == Double.MAX_VALUE);
        table.setDouble(10, Double.MIN_VALUE);
        System.out.println(table.getDouble(10) == Double.MIN_VALUE);

        //ref
        table.setRef(12, null);
        System.out.println(table.getRef(12) == null);
    }
}
