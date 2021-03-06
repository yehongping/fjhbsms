package com.hengkang.app.mappers;

import com.hengkang.app.annotation.MyBatisDao;
import com.hengkang.app.models.Traffic_Statistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface Traffic_StatisticsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRAFFIC_STATISTICS
     *
     * @mbggenerated
     */
    int insert(Traffic_Statistics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TRAFFIC_STATISTICS
     *
     * @mbggenerated
     */
    int insertSelective(Traffic_Statistics record);

    List<Traffic_Statistics> selectByParam(@Param("param")String param);

    int updateByParam(String param);

    Integer count(@Param("param")String param);
}