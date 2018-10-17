package com.base.project.commcon.constant;

public class SysEnvVar {

    final public static String SYS_OS_VERSION = System
            .getProperty("os.version");
    final public static String SYS_OS_NAME = System.getProperty("os.name");
    final public static String SYS_JAVA_VERSION = System
            .getProperty("java.version");
    final public static String SYS_JAVA_HOME = System.getProperty("java.home");
    final public static String SYS_FILE_SPARATOR = System
            .getProperty("file.separator");


    public final static String SYS_VERSION = "1.0";
}
