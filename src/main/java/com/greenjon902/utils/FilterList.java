package com.greenjon902.utils;

import java.util.ArrayList;
import java.util.List;

public class FilterList {
    public static List<String> filterListByStart(List<String> list, String start) {
        ArrayList<String> ret = new ArrayList<>();
        for (String string : list) {
            if (string.startsWith(start)) {
                ret.add(string);
            }
        }
        return ret;
    }
}
