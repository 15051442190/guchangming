package cn.kgc.service;

import cn.kgc.domain.Street;
import cn.kgc.domain.Type;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StreetService {
   /**
    *查询对应区域下的街道
 @return
    */
   List<Street> getStreetByDistrict(Integer did);
}
