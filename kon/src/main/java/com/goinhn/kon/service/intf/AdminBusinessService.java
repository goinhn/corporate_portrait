package com.goinhn.kon.service.intf;

import com.goinhn.kon.model.dto.BusinessDTO;

import java.util.List;

public interface AdminBusinessService {

    /**
     * 查询过滤后的用户总数
     *
     * @param search
     * @param pageIndex
     * @param pageSize
     * @return
     */
    int countLikeBusiness(String search, int pageIndex, int pageSize);


    /**
     * 查询用户总数
     *
     * @return
     */
    int countBusiness();


    /**
     * 查询过滤后的用户
     *
     * @param search
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<BusinessDTO> filterBusiness(String search, int pageIndex, int pageSize);


    /**
     * 删除指定企业
     *
     * @param id
     * @return
     */
    boolean deleteBusiness(Long id);

}
