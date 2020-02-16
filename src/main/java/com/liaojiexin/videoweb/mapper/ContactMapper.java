package com.liaojiexin.videoweb.mapper;

import com.liaojiexin.videoweb.entity.Contact;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ContactMapper {

    //插入留言
    int insert(Contact record);

    int insertSelective(Contact record);
}