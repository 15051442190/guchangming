package cn.kgc.service;

import cn.kgc.domain.Type;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {
   /**
    *查询区域带分页
    * @param pageUtil
    * page接收页码  rows接收页大小
    * @return
    */
   public PageInfo<Type> getType(PageUtil pageUtil);

   /**
    *添加区域
    * @param type 区域实体信息
    * @return 影响行数
    */
   public int addType(Type type);

   /**
    *
    * @param id 主键编号
    * @return 返回单条实体对象
    */
   public Type getTypeById(Integer id);

   /**
    * 修改区域
    * @param type  区域实体
    * @return 返回影响的行数
    *
    */
   public int updateType(Type type);

   /**
    * 根据id删除信息
    * @param id
    * @return
    */
   public int deletType(Integer id);
   /**
    * 实现区域的批量删除
    * @param ids 批量删除的id
    * @return 返回影响的行数
    *
    */
   public int delectType(Integer[] ids);

   /**
    *
    * @return
    */
   List<Type> getAllType();
}
