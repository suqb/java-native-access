package com.suqb.www;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class HelloWorld {

    /**
     * 定义一个接口，默认的是继承Library，如果动态链接库里额函数是以stdcall方式输出的，那么就继承StdCallLibrary
     * 这个接口对应一个动态连接文件（windows:.dll, linux:.so, mac:.dylib）
     */
    public interface CLibrary extends Library {
        /**
         * 接口内部需要一个公共静态常量INSTANCE，通过这个常量就可以获得这个接口的实例，从而使用接口的方法，也就是调用外部dll/so/dylib的函数
         * 该常量通过Native.load()这个API获得
         * 第一个参数为共享库的名称（不带后缀）
         * 第二个参数为本接口的Class类型，JNA通过这个这个Class类型，反射创建接口的实例
         * 共享库的查找顺序是：
         *  先从当前类的当前文件夹找，如果没找到
         *  再从工程当前文件夹下面找，如果找不到
         *  最后在当前平台下面去搜索
         */
        CLibrary INSTANCE = Native.load("lib_common_util", CLibrary.class);

        /**
         * 接口中只需要定义要用到的函数或者公共变量，不需要的可以不定义
         * 注意参数和返回值的类型，应该和共享库中的函数诶行保持一致
         */
//        void printf(String format, Object... args);
//        void printf(String format);
        void hello();
    }


    public static void main(String[] args) {
        CLibrary.INSTANCE.hello();
//        CLibrary.INSTANCE.printf("Hello,World\n");
//        for (int i = 0; i < args.length; i++) {
//            CLibrary.INSTANCE.printf("Argument %d:%s\n", i, args[i]);
//        }
    }
}
