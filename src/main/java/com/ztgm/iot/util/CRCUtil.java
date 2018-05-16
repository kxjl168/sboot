package com.ztgm.iot.util;

/**
 * 校验码生成工具
 */
public class CRCUtil {
    private static int[] fan_yushi = {0x0000, 0xCC01, 0xD801, 0x1400, 0xF001,
            0x3C00, 0x2800, 0xE401, 0xA001, 0x6C00, 0x7800, 0xB401, 0x5000,
            0x9C01, 0x8801, 0x4400};


    private static int modbusCrc(byte[] ptr) {
        if (ptr == null) {
            return -1;
        }
        int len = ptr.length;
        int ptr2;
        int crc;
        int da;
        crc = 0xffff;
        for (byte aPtr : ptr) {
            ptr2 = aPtr & 0xff;
            da = (int) (crc & 0x000f);
            crc >>= 4;
            crc ^= fan_yushi[da ^ (ptr2 & 0x0f)];
            da = (int) (crc & 0x000f);
            crc >>= 4;
            crc ^= fan_yushi[da ^ (ptr2 / 16)];
        }
        return (crc);
    }

    /**
     * @param buf 需要计算校验码的byte数组
     * @return 2位校验码
     */
    public static byte[] createCrc(byte[] buf) {
        int crc_result;
        crc_result = modbusCrc(buf);
        return new byte[]{(byte) (crc_result & 0x00ff),
                (byte) (crc_result >> 8)};
    }

}
