package com.goinhn.kon.service.intf;

/**
 * @author goinhn
 */
public interface AdminCountService {

    /**
     * 返回用户总数
     *
     * @return
     */
    int countUser();

    /**
     * 返回管理员总数
     *
     * @return
     */
    int countAdmin();

    /**
     * 返回企业总数
     *
     * @return
     */
    int countBusiness();

}
