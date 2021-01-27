package org.ljf.sjvm.classfile.attributes;

import org.ljf.sjvm.classfile.ClassReader;

/**
 * @author: ljf
 * @date: 2021/1/27 10:13
 * @description: BootstrapMethods_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 num_bootstrap_methods;
 * { u2 bootstrap_method_ref;
 * u2 num_bootstrap_arguments;
 * u2 bootstrap_arguments[num_bootstrap_arguments];
 * } bootstrap_methods[num_bootstrap_methods];
 * }
 * @modified By：
 * @version: $ 1.0
 */
public class BootstrapMethodsAttribute implements AttributeInfo {
    private BootstrapMethod[] bootstrapMethods;

    @Override
    public void readInfo(ClassReader reader) {
        int numBootstrapMethods = reader.readUint16();
        bootstrapMethods = new BootstrapMethod[numBootstrapMethods];
        for (int i = 0; i < numBootstrapMethods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(reader.readUint16(), reader.readUint16s());
        }

    }

    private static class BootstrapMethod {
        private final int bootstrapMethodRef;
        private final int[] bootstrapArguments;

        private BootstrapMethod(int bootstrapMethodRef, int[] bootstrapArguments) {
            this.bootstrapArguments = bootstrapArguments;
            this.bootstrapMethodRef = bootstrapMethodRef;
        }

        public int getBootstrapMethodRef() {
            return bootstrapMethodRef;
        }

        public int[] getBootstrapArguments() {
            return bootstrapArguments;
        }
    }

    public BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }
}
