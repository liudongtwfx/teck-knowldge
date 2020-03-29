package jl.usage.classloader;

import java.io.*;

//继承ClassLoader类，重写findclass方法。
public class MyClassloader extends ClassLoader {
    private String path;
    private String classloaderName;

    public MyClassloader(String path, String classloaderName) {
        this.path = path;
        this.classloaderName = classloaderName;
    }

    public static void main(String[] args)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyClassloader classloader = new MyClassloader("./", "myclasscloderz");
        Class<?> c = classloader.loadClass("World");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        c.newInstance();
    }

    //用于寻找类文件
    @Override
    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    public byte[] loadClassData(String name) {
        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;

        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }
}