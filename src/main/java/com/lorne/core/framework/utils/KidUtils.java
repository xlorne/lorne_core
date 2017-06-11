package com.lorne.core.framework.utils;

import java.util.UUID;

/**
 * Created by yuliang on 2015/8/8.
 */
public class KidUtils {

    public static String getKid() {
        String kid = generateShortUuid();
        kid = DateUtil.getCurrentDateTime() + kid;
        return kid;
    }

    public static String getKKid() {
        String kid = generateShortUuid();
        kid = "k" + DateUtil.getCurrentDateTime() + kid;
        return kid;
    }

    public static String getUUID() {
        String kid = generateShortUuid();
        kid = DateUtil.getCurrentDateTime() + "ud" + kid;
        return kid;
    }

    public static boolean isUUID(String uuid) {
        if (uuid != null) {
            if (uuid.length() == 24) {
                String time = uuid.substring(0, 14);
                try {
                    DateUtil.parseDate(time, DateUtil.LOCATE_DATE_FORMAT);
                    if ("ud".equals(uuid.substring(14, 16))) {
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    private static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};


    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

}
