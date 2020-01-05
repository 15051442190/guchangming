package cn.kgc.service.impl;


import cn.kgc.domain.House;
import cn.kgc.mapper.HouseMapper;
import cn.kgc.service.HouseService;
import cn.kgc.util.PageUtil;
import cn.kgc.util.SearchCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHours(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseByUserid(Integer id, PageUtil pageUtil) {

        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houseList = houseMapper.getHouseByUserId(id);
        PageInfo<House> pageInfo=new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public House getHouse(String id) {
        return houseMapper.getHouse(id);
    }

    @Override
    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int deleleHouse(String houseId, Integer delState) {
        House house=new House();
        house.setId(houseId);
        house.setIsdel(delState);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getAllByuser(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //调用方法
        List<House> houseList = houseMapper.getAllByUser();
        PageInfo<House> pageInfo=new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public int passHouse(String houseId, Integer passState) {
        House house=new House();
        house.setId(houseId);//条件
        house.setIspass(passState);//更新审核状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getBrowHouses(SearchCondition searchCondition) {
        PageHelper.startPage(searchCondition.getPage(),searchCondition.getRows());
        //调用方法
        List<House> houseList = houseMapper.getBrowseHouse(searchCondition);

        return new PageInfo<>(houseList);
    }
}
