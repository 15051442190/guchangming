package cn.kgc.service.impl;

import cn.kgc.domain.Type;
import cn.kgc.domain.TypeExample;
import cn.kgc.mapper.TypeMapper;
import cn.kgc.service.DemoService;
import cn.kgc.service.TypeService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public PageInfo<Type> getType(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询所有
        TypeExample typeExample=new TypeExample();
        //封装pageInfo
        List<Type> typeList = typeMapper.selectByExample(typeExample);
        PageInfo<Type> pageInfo=new PageInfo<>(typeList);
        return pageInfo;
    }

    @Override
    public int addType(Type type) {
        /**
         * insert添加所有字段
         * insertSelective选择性添加
         */
        int i = typeMapper.insertSelective(type);
        return i;
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public int deletType(Integer id) {
        return typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int delectType(Integer[] ids) {
        return typeMapper.delectType(ids);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
