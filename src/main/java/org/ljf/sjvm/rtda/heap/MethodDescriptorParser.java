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

    public MethodDescriptor parseMethodDescriptor(String descriptor){
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

    }

    private void parseReturnType() {

    }

    private void endParams() {

    }

    private void parseParamTypes() {
        
    }

    private void startParams() {
        
    }
}
