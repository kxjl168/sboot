package com.ztgm.iot.dao;

import com.ztgm.iot.pojo.Combination;

import java.util.List;

public interface CombinationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Combination record);

    int insertSelective(Combination record);

    Combination selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Combination record);

    int updateByPrimaryKeyWithBLOBs(Combination record);

    int updateByPrimaryKey(Combination record);

    List<Combination> sceneList();

    List<Combination> sceneListBygroupId(String groupid);

    Combination findSceneBySceneId(String sceneId);

    List<Combination> findSceneBySceneName(String sceneName);

    List<Combination> sceneListByUserId(String userId);

    List<Combination> selectDefaultScenes(String userId);

}