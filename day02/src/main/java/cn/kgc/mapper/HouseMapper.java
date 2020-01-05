package cn.kgc.mapper;

import cn.kgc.domain.House;
import cn.kgc.domain.HouseExample;
import cn.kgc.util.SearchCondition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    //查询当前用户的出租房
    List<House> getHouseByUserId(int id);
    //查询单调数据
    House getHouse(String id);
    //查询所有的出租房信息
    List<House> getAllByUser();

    //浏览所有的出租房信息
    List<House> getBrowseHouse(SearchCondition searchCondition);
}