package com.goinhn.kon.mapper;

import com.goinhn.kon.model.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

/**
 * @author goinhn
 */
@Repository
public interface AdminMapper {

    /**
     * 根据Id查找管理员
     *
     * @param admin 管理员
     * @return
     */
    @Select("select * from tab_admin where eid = #{eid}")
    @Results(
            id = "adminMap",
            value = {
                    @Result(id = true, column = "eid", property = "eid"),
                    @Result(column = "username", property = "username"),
                    @Result(column = "email", property = "email"),
                    @Result(column = "password", property = "password"),
                    @Result(column = "token", property = "token"),
                    @Result(column = "logintime", property = "loginTime"),
                    @Result(column = "expiretime", property = "expireTime")
            }
    )
    Admin selectById(@NotNull Admin admin);


    /**
     * 根据管理员名来查找管理员
     *
     * @param admin 管理员
     * @return
     */
    @Select("select * from tab_admin where username = #{username})")
    @ResultMap("adminMap")
    Admin selectByUsername(@NotNull Admin admin);


    /**
     * 根据token来查找管理员
     *
     * @param admin 管理员
     * @return
     */
    @Select("select * from tab_admin where token = #{token}")
    @ResultMap("adminMap")
    Admin selectByToken(@NotNull Admin admin);


    /**
     * 根据管理员名和密码来查询管理员
     *
     * @param admin 管理员
     * @return
     */
    @Select("select * from tab_admin where username = #{username} and password = #{password}")
    @ResultMap("adminMap")
    Admin selectByUsernameAndPassword(@NotNull Admin admin);


    /**
     * 根据邮箱和密码来查询管理员
     *
     * @param admin 管理员
     * @return
     */
    @Select("select * from tab_admin where email = #{email} and password = #{password}")
    @ResultMap("adminMap")
    Admin selectByEmailAndPassword(@NotNull Admin admin);


    /**
     * 查询总数
     *
     * @return
     */
    @Select("select count(*) from tab_admin")
    int selectCount();


    /**
     * 存储管理员信息
     *
     * @param admin 管理员
     * @return
     */
    @Insert("insert into tab_admin" +
            "(username, email, password, token, logintime, expiretime) " +
            "values(#{username}, #{email}, #{password}, #{token}, #{loginTime}, #{expireTime})")
    @SelectKey(keyColumn = "eid", keyProperty = "eid",
            resultType = Long.class, before = false, statement = {"select last_insert_id()"})
    int saveUser(@NotNull Admin admin);


    /**
     * 根据指定的id删除哟凝固
     *
     * @param admin 管理员
     * @return
     */
    @Delete("delete from tab_admin where eid = #{eid}")
    int deleteUserById(@NotNull Admin admin);


    /**
     * 根据管理员名和密码进行删除管理员
     *
     * @param admin 管理员
     * @return
     */
    @Delete("delete from tab_admin where username = {username} and password = #{password}")
    int deleteUserByUsernameAndPassword(@NotNull Admin admin);


    /**
     * 更新管理员的密码
     *
     * @param admin 管理员
     * @return
     */
    @Update("update tab_admin set password = #{password} where eid = #{eid}")
    int updatePasswordById(@NotNull Admin admin);


    /**
     * 更新管理员的token
     *
     * @param admin 管理员
     * @return
     */
    @Update("update tab_admin set token = #{token} where eid = #{eid}")
    int updateTokenById(@NotNull Admin admin);


    /**
     * 更新登录时间
     *
     * @param admin 管理员
     * @return
     */
    @Update("update tab_admin set logintime = #{loginTime} where eid = #{eid}")
    int updateLoginTimeById(@NotNull Admin admin);


    /**
     * 更新token失效时间
     *
     * @param admin 管理员
     * @return
     */
    @Update("update tab_admin set expiretime = #{expireTime} where eid = #{eid}")
    int updateExpireTimeById(@NotNull Admin admin);


    /**
     * 更新token，登录时间，token失效时间
     *
     * @param admin 管理员
     * @return
     */
    @Update("update tab_admin set token = #{token}, logintime = #{loginTime}, expiretime = #{expireTime} where eid = #{eid}")
    int updateTokenLoginTimeExpireTimeById(@NotNull Admin admin);

}
