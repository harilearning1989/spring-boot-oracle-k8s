package com.web.demo.utils;

import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

public interface IDemoUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(IDemoUtils.class.getName());

    public static String readResource(final String fileName, Charset charset) throws IOException {
        return Resources.toString(Resources.getResource(fileName), charset);
    }
}
