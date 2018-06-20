package com.ztgm.mall.util;

import com.ztgm.mall.pojo.Result;
import com.ztgm.mall.pojo.SvrFileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Timestamp;

import static com.ztgm.mall.util.md5.Md5EncryptFile.getMD5;

/**
 * @author handsomeXiao
 * @date 2018/6/7 10:51
 */
public class UploadUtil {


    public static Result writeFile (MultipartFile file) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        Result result = new Result();
        result.setResultCode(0);
        try {
            inputStream = file.getInputStream();
            String uploadName = file.getOriginalFilename();
            String suffix = uploadName.split("[.]")[1];
            String storeName = UUIDUtil.getUUID() + "." + suffix;
            String fileDirectory = UploadUtil.class.getResource("/").getPath() + "static/file/apk/" + DateUtil.getNowStr("yyyyMM");
            File targetDirectory = new File(fileDirectory);
            if (!targetDirectory.exists()) {
                targetDirectory.mkdirs();
            }
            String fullPath = fileDirectory + "/" + storeName;
            File outFile = new File(fullPath);
            outputStream = new BufferedOutputStream(new FileOutputStream(outFile));
            byte[] readBytes = new byte[1024];
            while (inputStream.read(readBytes) != -1) {
                outputStream.write(readBytes);
            }
            String md5 = getMD5(file);
            SvrFileInfo svrFileInfo = new SvrFileInfo();
            svrFileInfo.setId(UUIDUtil.getUUID());
            svrFileInfo.setFile_size(file.getSize());
            svrFileInfo.setFull_path(fullPath);
            svrFileInfo.setHttp_relative_path(fullPath.substring(fullPath.indexOf("apk")));
            svrFileInfo.setOld_name(uploadName);
            svrFileInfo.setSave_date(new Timestamp(System.currentTimeMillis()).toString());
            svrFileInfo.setSave_name(storeName);
            svrFileInfo.setFile_md5(md5);
            svrFileInfo.setHttp_down_url("/FileSvr/downFile.action?m5=" + md5);
            result.setResultCode(1);
            result.setResultData(svrFileInfo);
        } catch (IOException e) {
            e.printStackTrace();
            result.setResultCode(0);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
