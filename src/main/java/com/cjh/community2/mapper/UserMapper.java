package com.cjh.community2.mapper;

import com.cjh.community2.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert("insert into user (name,accountid,token,gmtcreate,gmtmodified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatar_url})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    @Select("select * from user where id = #{creator}")
    User findById(int creator);
    @Select("select * from user where accountid = #{accountId}")
    User findByAccountId(String accountId);

    @Update("update user set name=#{name},token=#{token},gmtcreate=#{gmtCreate},gmtmodified=#{gmtModified},avatar_url=#{avatar_url} where id = #{id}")
    void update(User user);
}
