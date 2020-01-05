package cn.kgc.service;

import cn.kgc.domain.District;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DemoService {
   /**
    *查询区域带分页
    * @param pageUtil
    * page接收页码  rows接收页大小
    * @return
    */
   public PageInfo<District> getDistrict(PageUtil pageUtil);

   /**
    *添加区域
    * @param district 区域实体信息
    * @return 影响行数
    */
   public int addDistrict(District district);

   /**
    *
    * @param id 主键编号
    * @return 返回单条实体对象
    */
   public District getDistrictById(Integer id);

   /**
    * 修改区域
    * @param district  区域实体
    * @return 返回影响的行数
    *
    */
   public int updateDistrict(District district);

   /**
    * 根据id删除信息
    * @param id
    * @return
    */
   public int deletDistrict(Integer id);
   /**
    * 实现区域的批量删除
    * @param ids 批量删除的id
    * @return 返回影响的行数
    *
    */
   public int delectDistrict(Integer [] ids);
   /**
    *
    * @return
    */
   List<District> getAllDistrict();
}
