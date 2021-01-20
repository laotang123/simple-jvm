import org.junit.Test;
import org.ljf.sjvm.book.ch02.classpath.Entry;
import org.ljf.sjvm.book.ch02.classpath.EntryFactory;
import org.ljf.sjvm.book.util.IOUtil;

import java.io.File;
import java.util.Arrays;

/**
 * @author: ljf
 * @date: 2021/1/20 9:34
 * @description:
 * @modified By：
 * @version: $ 1.0
 */
public class Ch02Test {
    @Test
    public void testDirEntry() {
        Entry entry = EntryFactory.newEntry("target/classes");
        System.out.println(entry);
        String className = "org.ljf.sjvm.book.ch01.Cmd";
        byte[] result = entry.readClass(className);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testClassZipEntry() {
        Entry entry = EntryFactory.newEntry("target/SimpleJVM-1.0-SNAPSHOT-jar-with-dependencies.jar");
        byte[] result = entry.readClass("org.ljf.sjvm.book.ch01.Cmd");
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testCompositeEntry() {
        Entry entry = EntryFactory.newEntry("target/classes;target/test-classes;target/SimpleJVM-1.0-SNAPSHOT-jar-with-dependencies.jar");

        System.out.println(entry);
        byte[] result1 = entry.readClass("Ch02Test");
        System.out.println(Arrays.toString(result1));
        byte[] result2 = entry.readClass("org.ljf.sjvm.book.ch01.Main");
        System.out.println(Arrays.toString(result2));

    }

    @Test
    public void testWildcardEntry() {
        String contextPath = "target/classes/org/ljf/sjvm/book/ch02/*";

        Entry entry = EntryFactory.newEntry(contextPath);
        System.out.println(entry);
        String className = "Cmd";
        byte[] result = entry.readClass(className);
        System.out.println(Arrays.toString(result));

    }

    @Test
    public void test01(){
        String contextPath = "target/classes/org/ljf/sjvm/book/ch02/Main.class";
        String absPath = IOUtil.abs(contextPath);
        File file = new File(absPath);
        System.out.println(file.getParent());
    }
}
