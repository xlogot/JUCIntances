package chapter10;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.StreamSupport;

public class MyClassLoader extends ClassLoader {
    private final static Path DEFAULT_CLASS_DIR= Paths.get("G:","classloader1");
    private final Path classDir;

    public MyClassLoader() {
        super();
        this.classDir=DEFAULT_CLASS_DIR;
    }
    public MyClassLoader(String classDir){
        super();
        this.classDir=Paths.get(classDir);
    }

    public MyClassLoader(String classDir,ClassLoader parent){
        super(parent);
        this.classDir= Paths.get(classDir);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes=this.readClassBytes(name);
        if (null==classBytes||classBytes.length==0){
            throw  new ClassNotFoundException("cannot load the class" + name);
        }

        //调用defineClass方法定义class
        return  this.defineClass(name,classBytes,0,classBytes.length);
    }

    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        //将'.'转成系统认识的'/'符号
        String classPath=name.replace(".","/");
        Path classFullPath=classDir.resolve(Paths.get(classPath+".class"));
        if (!classFullPath.toFile().exists()){
            throw  new ClassNotFoundException("the class "+name+" not found.");
        }
            try(ByteArrayOutputStream baos=new ByteArrayOutputStream()){
            Files.copy(classFullPath,baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new ClassNotFoundException("load the class "+name+ " occur error. ",e);
        }
    }

    @Override
    public String toString() {
        return "my classLoader";
    }
}
