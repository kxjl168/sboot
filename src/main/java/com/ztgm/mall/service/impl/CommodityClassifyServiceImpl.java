package com.ztgm.mall.service.impl;


import com.ztgm.mall.dao.CommodityFirstClassifyMapper;
import com.ztgm.mall.dao.CommoditySecondClassifyMapper;
import com.ztgm.mall.dao.SvrFileInfoMapper;
import com.ztgm.mall.pojo.CommodityFirstClassify;
import com.ztgm.mall.pojo.Result;
import com.ztgm.mall.pojo.SvrFileInfo;
import com.ztgm.mall.service.CommodityClassifyService;
import com.ztgm.mall.util.DateUtil;
import com.ztgm.mall.util.UUIDUtil;
import com.ztgm.mall.util.UploadUtil;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author handsomeXiao
 * @date 2018/5/30 16:25
 */
@Service
public class CommodityClassifyServiceImpl implements CommodityClassifyService {

    @Autowired
    private CommodityFirstClassifyMapper commodityFirstClassifyMapper;

    @Autowired
    private CommoditySecondClassifyMapper commoditySecondClassifyMapper;

    @Autowired
    private SvrFileInfoMapper svrFileInfoMapper;

    private static final String UPDATE = "update";

    private static final String INSERT = "insert";

    private static final String INTEGER = "Integer";
    
    
    /**
	 * 根据级别和父id，获取父分类下的小分类
	 * @param level
	 * @param pid
	 * @return
	 * @author zj
	 * @date 2018年6月11日
	 */
	public List<CommodityFirstClassify>	getClassifyByPidAndLevel(String level, String pid){
		 return commodityFirstClassifyMapper.getClassifyByPidAndLevel(level,pid);
	 }
	 
    public List<CommodityFirstClassify> showTopUserfullClassify() {
        List<CommodityFirstClassify> commodityFirstClassifyList = commodityFirstClassifyMapper.showTopUserfullClassify();
        return commodityFirstClassifyList;
    }
  
    @Override
    public List<CommodityFirstClassify> showAllClassify() {
        List<CommodityFirstClassify> commodityFirstClassifyList = commodityFirstClassifyMapper.showAllClassify();
        return commodityFirstClassifyList;
    }

    @Override
    public List getAllTopClassify(String level) {
        return commodityFirstClassifyMapper.getAllTopClassify(level);
    }

    @Override
    public List<CommodityFirstClassify> getTopClassify() {
        List<CommodityFirstClassify> commodityFirstClassifyList = commodityFirstClassifyMapper.getTopClassify(8);
        return commodityFirstClassifyList;
    }

    @Override
    public Integer classifyOperate(Map<String, String> parameter) {
        //将要执行的动作是插入还是更新
        String action = parameter.get("action");
        String level = parameter.get("level");
        String topClassify = parameter.get("topClassify");
        CommodityFirstClassify commodityFirstClassify = new CommodityFirstClassify();
        Integer result = 0;
        //获得当前用户
        Subject currentUser = SecurityUtils.getSubject();
        String userId = ((Map)currentUser.getPrincipal()).get("userId").toString();
        try {
            packageCommodityFirstClassify (commodityFirstClassify, parameter);
            commodityFirstClassify.setUpdater(userId);
            if (UPDATE.equals(action)) {
                result = commodityFirstClassifyMapper.updateByCondition (commodityFirstClassify, level, topClassify);
            } else if (INSERT.equals(action)){
                commodityFirstClassify.setCreator(userId);
                commodityFirstClassify.setId(UUIDUtil.getUUID());
                result = commodityFirstClassifyMapper.insertByCondition (commodityFirstClassify, level, topClassify);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    @Override
    public Integer deleteClassify(Map<String, String> parameter) {
        String id = parameter.get("id");
        String level = parameter.get("level");

        Integer result = 0;

        Integer relationCount = commodityFirstClassifyMapper.getRelationCount(id, level);
        if (relationCount != 0) {
            return result;
        }
        result = commodityFirstClassifyMapper.deleteClassify (id, level);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String classifyPictureUpload(String id, String type, MultipartFile file) {
        Result writeResult = UploadUtil.writeFile(file);
        Integer resultCode = writeResult.getResultCode();
        type = type.substring(0, type.indexOf("Upload"));
        String resultString;
        try {
            if (resultCode == 1) {
                SvrFileInfo svrFileInfo = (SvrFileInfo) writeResult.getResultData();
                String pictureId = svrFileInfo.getId();
                svrFileInfoMapper.SaveFileInfo(svrFileInfo);
                commodityFirstClassifyMapper.updatePictureInfo (id, type, pictureId);
                resultString = "图片上传成功";
            } else {
                resultString = "图片上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultString = "图片上传失败";
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return resultString;
    }

    private void packageCommodityFirstClassify(CommodityFirstClassify commodityFirstClassify, Map<String, String> parameter) throws IllegalAccessException {
        Class firstClassifyClass = commodityFirstClassify.getClass();
        Field[] fields = firstClassifyClass.getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);

        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            for (Field field : fields) {
                if (field.getName().equals(entry.getKey())) {
                    Class typeClass = field.getType();
                    String value = entry.getValue();
                    if (!typeClass.getSimpleName().equals("String")) {
                        if (INTEGER.equals(typeClass.getSimpleName())) {
                            Integer fieldVal = getVal (value, (Class<Integer>) typeClass);
                            field.set(commodityFirstClassify, fieldVal);
                        }
                    } else {
                        field.set(commodityFirstClassify, value);
                    }
                }
            }
        }
    }

    private <T>T getVal(String value, Class<T> typeClass) {
        T resultValue = null;
        try {
            String methodName = "valueOf";
            Method method = typeClass.getMethod(methodName, String.class);
            resultValue = (T) method.invoke(null, value);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return resultValue;
    }

    private Map getConditions(String key,String value){
        Map rtn = new HashMap();

        return rtn;
    }

    @Override
    public Map getClassifyById(Integer level,String id) {
        return commodityFirstClassifyMapper.getClassifyById(level,id);
    }

    @Override
    public List<Map> getSubClassifies(Integer level,String id) {
        List<Map> commodityFirstClassifyList = commodityFirstClassifyMapper.getSubClassifies(level,id);
        return commodityFirstClassifyList;
    }
}
