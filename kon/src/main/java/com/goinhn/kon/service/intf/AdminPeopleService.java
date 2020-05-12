package com.goinhn.kon.service.intf;

import com.goinhn.kon.model.dto.UserDTO;

import java.util.List;

public interface AdminPeopleService {

    /**
     * 查询过滤后的用户总数
     *
     * @param search
     * @param pageIndex
     * @param pageSize
     * @return
     */
    int countLikeUser(String search, int pageIndex, int pageSize);


    /**
     * 查询用户总数
     *
     * @return
     */
    int countUser();


    /**
     * 查询过滤后的用户
     *
     * @param search
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<UserDTO> filterUser(String search, int pageIndex, int pageSize);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteUser(Long id);

}
