package com.hengkang.app.utils;

import java.util.List;

public class StringListHelper {
    public static String stringOfList(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : list) {
            if (str == null) {
                stringBuilder.append("\"\",");
            } else {
                stringBuilder.append("\"").append(str).append("\",");
            }
        }
        return stringBuilder.toString().replaceAll(",$", "\r\n");
    }
}