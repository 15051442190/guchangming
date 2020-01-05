package cn.kgc.service.impl;

import cn.kgc.domain.District;
import cn.kgc.domain.DistrictExample;
import cn.kgc.mapper.DistrictMapper;
import cn.kgc.service.DemoService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public PageInfo<District> getDistrict(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询所有
        DistrictExample districtExample=new DistrictExample();
        //封装pageInfo
        List<District> districtList = districtMapper.selectByExample(districtExample);
        PageInfo<District> pageInfo=new PageInfo<>(districtList);
        return pageInfo;
    }

    @Override
    public int addDistrict(District district) {
        /**
         * insert添加所有字段
         * insertSelective选择性添加
         */
        int i = districtMapper.insertSelective(district);
        return i;
    }

    @Override
    public District getDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override
    public int deletDistrict(Integer id) {
        return districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delectDistrict(Integer[] ids) {
        return districtMapper.delectDistrict(ids);
    }

    @Override
    public List<District> getAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
