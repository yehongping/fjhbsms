package com.hengkang.app.mappers;

import com.hengkang.app.annotation.MyBatisDao;
import com.hengkang.app.models.YsSmsMt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@MyBatisDao
public interface YsSmsMtMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table YS_SMS_MT
     *
     * @mbggenerated
     */
    int insert(YsSmsMt record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table YS_SMS_MT
     *
     * @mbggenerated
     */
    int insertSelective(YsSmsMt record);

    List<YsSmsMt> selectByParam(@Param("param")String param);

    int updateByParam(String param);

    Integer count(@Param("param")String param);
}