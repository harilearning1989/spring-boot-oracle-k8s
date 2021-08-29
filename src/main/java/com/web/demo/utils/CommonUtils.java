package com.web.demo.utils;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.Charset;

public interface CommonUtils {

    static String getOsName() {
        return System.getProperty("os.name");
    }

    static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    static boolean isLinux() {
        return getOsName().startsWith("Linux");
    }

    static boolean isMac() {
        return getOsName().startsWith("Mac");
    }

    static String readResource(final String fileName, Charset charset) throws IOException {
        return Resources.toString(Resources.getResource(fileName), charset);
    }
}
