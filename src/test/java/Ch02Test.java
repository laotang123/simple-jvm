import org.junit.Test;
import org.ljf.sjvm.Main;
import org.ljf.sjvm.classpath.ClassPathContainer;
import org.ljf.sjvm.classpath.Entry;
import org.ljf.sjvm.classpath.EntryFactory;

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
//        String contextPath = "target/classes/org/ljf/sjvm/book/ch02/*";
        String contextPath = "target/*";

        Entry entry = EntryFactory.newEntry(contextPath);
//        System.out.println(entry);
//        String className = "Cmd";
        String className = "org.ljf.sjvm.book.ch01.Main";
        byte[] result = entry.readClass(className);
        System.out.println(Arrays.toString(result));

    }

    @Test
    public void TestClassPath(){
        String jreOption = "";
        String cpOption = "target/class";
        ClassPathContainer classPath = ClassPathContainer.parse(jreOption, cpOption);
        System.out.println(classPath);
    }

    @Test
    public void testMain(){
//        String[] args = new String[]{"-Xjre","C:\\Program Files\\Java\\jdk1.8.0_151\\jre","java.lang.Object"};
//        String[] args = new String[]{"-cp","target/classes","org.ljf.sjvm.book.ch01.Main"};
        String[] args = new String[]{"-cp","target/SimpleJVM-1.0-SNAPSHOT-jar-with-dependencies.jar","org.ljf.sjvm.book.ch01.Cmd"};
        Main.main(args);
    }
}
