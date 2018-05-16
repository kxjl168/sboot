package com.ztgm.iot.util;

/**
 * 进制转换工具
 */
public class ByteUtil {

    /**
     * 将String类型转换为Byte数组
     *
     * @param hexString String类型字符串
     * @return byte数组
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return new byte[768];
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }


    /**
     * 将Byte数组换为String类型
     *
     * @param b byte数组
     * @return String类型字符串
     */
    public static String Bytes2HexString(byte[] b) {
        String ret = "";
        for (byte aB : b) {
            String hex = Integer.toHexString(aB & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public static int bytesToInt(byte[] bytes){
        int value=0;
        for(int i=0;i<bytes.length;i++){
            value |=(bytes[i]&0xFF)<<(8*i);
        }
        return value;
    }

}
