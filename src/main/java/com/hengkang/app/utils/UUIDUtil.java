package com.hengkang.app.utils;

import java.util.UUID;

/**
 * Created by jianyueting on 14-5-27.
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
