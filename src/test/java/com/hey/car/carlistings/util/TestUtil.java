package com.hey.car.carlistings.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class TestUtil {

    public static String getDataFromFile(String path) throws IOException {
        InputStream is = TestUtil.class.getResourceAsStream(path);
        return IOUtils.toString(is, Charset.defaultCharset());
    }
}
