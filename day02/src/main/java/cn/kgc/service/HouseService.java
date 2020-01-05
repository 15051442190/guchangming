package cn.kgc.service;

import cn.kgc.domain.House;
import cn.kgc.util.PageUtil;
import cn.kgc.util.SearchCondition;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HouseService {
    /**
     * //添加出租房
     * @param house 出租房实体
     * @return 影响行数
     */
    public int addHours(House house);

    /**
     * 查询当前用户的出租房信息
     * @param id 用户id
     * @param pageUtil
     * @return 出租房信息
     */
    public PageInfo<House> getHouseByUserid(Integer id, PageUtil pageUtil);

    /**
     * 查询单条
     * @param id 编号
     * @return 返回实体
     */
    House getHouse(String id);

    /**
     * 修改出租屋信息
     * @param house 出租房实体
     * @return 返回影响行数
     */
    int updateHouse(House house);

    /**
     * 更新出租房删除的状态
     * @param houseId 出租房编号
     * @param delState   删除的状态
     * 状态为1 表示删除出租房     0表示恢复出租房，不删除
     * @return 影响
     */
    public int deleleHouse(String houseId,Integer delState);

    /**
     * 查询所有的出租房信息
     * @param pageUtil 分页的参数
     * @return 出租房信息的实体
     */
    public PageInfo<House> getAllByuser(PageUtil pageUtil);

    /**
     * 修改出租房的审核状态
     * @param houseId 出租房编号
     * @param passState 审核状态
     *
     * @return 影响行数
     */
    public int passHouse(String houseId,Integer passState);

    public PageInfo<House> getBrowHouses(SearchCondition searchCondition);
}
