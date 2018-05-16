package com.ztgm.iot.service;

import com.ztgm.iot.pojo.Combination;
import com.ztgm.iot.pojo.SceneDevice;

import java.util.List;

public interface SceneService {

    //场景集合
    List<Combination> sceneList();

    //场景集合
    List<Combination> sceneListBygroupId(String groupId);

    //查找场景（主键）
    SceneDevice findSceneBySceneId(String sceneId);

    //查找场景（主键）
    List<Combination> findSceneBySceneName(String sceneName);

    //新建场景
    int createScene(String combination);

    //更新场景
    int updateScene(String combination);

    //删除场景
    int deleteScene(String sceneId);

    //控制场景
    void controlScene(String sceneId);


}

