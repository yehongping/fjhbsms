package com.hengkang.app.mappers;

import com.hengkang.app.annotation.MyBatisDao;
import com.hengkang.app.models.Mtsend_Info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface Mtsend_InfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTSEND_INFO
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Short oidnew);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTSEND_INFO
     *
     * @mbggenerated
     */
    int insert(Mtsend_Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTSEND_INFO
     *
     * @mbggenerated
     */
    int insertSelective(Mtsend_Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTSEND_INFO
     *
     * @mbggenerated
     */
    Mtsend_Info selectByPrimaryKey(Short oidnew);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTSEND_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Mtsend_Info record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table MTSEND_INFO
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Mtsend_Info record);

    List<Mtsend_Info> selectByParam(@Param("param")String param);

    int updateByParam(String param);
}