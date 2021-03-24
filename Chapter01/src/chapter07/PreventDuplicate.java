package chapter07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

public class PreventDuplicate {
    private final static String LOCK_PATH = "d://temp/";
    private final static String LOCK_FILE = ".1ock";
    private final static String PERMISSIONS = "rw-";

    public static void main(String[] args) throws IOException {

        //①注入Hook线程，在程序退出时删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        //②检查是否存在.lock文件
        checkRunning();
        //③简单模拟当前程序正在运行
        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static  void  checkRunning()    throws IOException{
        Path path=getLockFile();
        if (path.toFile().exists())
            throw new RuntimeException ( "The program already running.");
        Set<PosixFilePermission> perms = PosixFilePermissions . fromString (PERMISSIONS) ;
        Files . createFile (path, PosixFilePermissions . asFileAttribute (perms ));
    }
    private static Path getLockFile(){
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
