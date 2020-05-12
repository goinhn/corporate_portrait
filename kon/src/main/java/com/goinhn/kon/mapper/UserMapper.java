package com.goinhn.kon.mapper;

import com.goinhn.kon.model.dto.Page;
import com.goinhn.kon.model.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author goinhn
 */
@Repository
public interface UserMapper {

    /**
     * 根据Id查找用户
     *
     * @param user 用户
     * @return
     */
    @Select("select * from tab_user where eid = #{eid}")
    @Results(
            id = "userMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "email", property = "email"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "token", property = "token"),
                    @Result(column = "logintime", property = "loginTime"),
                    @Result(column = "expiretime", property = "expireTime"),
                    @Result(column = "active_code", property = "activeCode"),
                    @Result(column = "active_status", property = "activeStatus")
            }
    )
    User selectById(@NotNull User user);


    /**
     * 根据用户名来查找用户
     *
     * @param user 用户
     * @return
     */
    @Select("select * from tab_user where username = #{username}")
    @ResultMap("userMap")
    User selectByUsername(@NotNull User user);


    /**
     * 根据邮箱来查询用户
     *
     * @param user 用户
     * @return
     */
    @Select("select * from tab_user where email = #{email}")
    @ResultMap("userMap")
    User selectByEmail(@NotNull User user);


    /**
     * 根据token来查询用户
     *
     * @param user 用户
     * @return
     */
    @Select("select * from tab_user where token = #{token}")
    @ResultMap("userMap")
    User selectByToken(@NotNull User user);


    /**
     * 根据active_code来查询用户
     *
     * @param user
     * @return
     */
    @Select("select * from tab_user where active_code = #{activeCode}")
    @ResultMap("userMap")
    User selectByActiveCode(@NotNull User user);


    /**
     * 根据用户名和密码来查询用户
     *
     * @param user 用户
     * @return
     */
    @Select("select * from tab_user where username = #{username} and password = #{password}")
    @ResultMap("userMap")
    User selectByUsernameAndPassword(@NotNull User user);


    /**
     * 根据邮箱和密码来查询用户
     *
     * @param user 用户
     * @return
     */
    @Select("select * from tab_user where email = #{email} and password = #{password}")
    @ResultMap("userMap")
    User selectByEmailAndPassword(@NotNull User user);


    /**
     * 分页查询用户
     *
     * @param page
     * @return
     */
    @Select("select * from tab_user where username like concat('%', #{name}, '%') limit #{pageIndex}, #{pageSize}")
    @ResultMap("userMap")
    List<User> selectLikeUsername(@NotNull Page page);


    /**
     * 查询总数
     *
     * @return
     */
    @Select("select count(*) from tab_user")
    int selectCount();


    /**
     * 查询条件过滤后的总数
     *
     * @return
     */
    @Select("select count(*) from tab_user where username like concat('%', #{name}, '%')")
    int selectLikeCount(@NotNull Page page);

    /**
     * 存储用户信息
     *
     * @param user 用户
     * @return
     */
    @Insert("insert into tab_user" +
            "(username, email, password, token, logintime, expiretime, active_code, active_status) " +
            "values(#{username}, #{email}, #{password}, #{token}, #{loginTime}, #{expireTime}, #{activeCode}, #{activeStatus})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveUser(@NotNull User user);


    /**
     * 根据指定的id删除哟凝固
     *
     * @param user 用户
     * @return
     */
    @Delete("delete from tab_user where eid = #{eid}")
    int deleteUserById(@NotNull User user);


    /**
     * 根据用户名和密码进行删除用户
     *
     * @param user 用户
     * @return
     */
    @Delete("delete from tab_user where username = {username} and password = #{password}")
    int deleteUserByUsernameAndPassword(@NotNull User user);


    /**
     * 更新用户的密码
     *
     * @param user 用户
     * @return
     */
    @Update("update tab_user set password = #{password} where eid = #{eid}")
    int updatePasswordById(@NotNull User user);


    /**
     * 更新用户的token
     *
     * @param user 用户
     * @return
     */
    @Update("update tab_user set token = #{token} where eid = #{eid}")
    int updateTokenById(@NotNull User user);


    /**
     * 更新登录时间
     *
     * @param user 用户
     * @return
     */
    @Update("update tab_user set logintime = #{loginTime} where eid = #{eid}")
    int updateLoginTimeById(@NotNull User user);


    /**
     * 更新token失效时间
     *
     * @param user 用户
     * @return
     */
    @Update("update tab_user set expiretime = #{expireTime} where eid = #{eid}")
    int updateExpireTimeById(@NotNull User user);


    /**
     * 更新用户的激活状态
     *
     * @param user 用户
     * @return
     */
    @Update("update tab_user set active_status = #{activeStatus} where eid = #{eid}")
    int updateActiveStatusById(@NotNull User user);


    /**
     * 更新用户的激活码
     *
     * @param user 用户
     * @return
     */
    @Update("update tab_user set active_code = #{activeCode} where eid = #{eid}")
    int updateActiveCodeById(@NotNull User user);


    /**
     * 更新登录时间、失效时间、token
     *
     * @param user 用户
     * @return
     */
    @Update("update tab_user set token = #{token}, logintime = #{loginTime}, expiretime = #{expireTime} where eid = #{eid}")
    int updateTokenLoginTimeExpireTimeById(@NotNull User user);
}
