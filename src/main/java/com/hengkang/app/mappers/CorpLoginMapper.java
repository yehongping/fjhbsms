package com.hengkang.app.mappers;

import com.hengkang.app.models.CorpLogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CorpLoginMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CORPLOGIN
     *
     * @mbggenerated
     */
    int insert(CorpLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CORPLOGIN
     *
     * @mbggenerated
     */
    int insertSelective(CorpLogin record);

    List<CorpLogin> selectByParam(@Param("param")String param);
}