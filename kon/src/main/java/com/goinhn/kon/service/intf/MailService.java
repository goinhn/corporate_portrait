package com.goinhn.kon.service.intf;

/**
 * 发送邮件服务
 *
 * @author goinhn
 */
public interface MailService {

    /**
     *  发送多媒体类型邮件
     * @param to 发送邮件目标
     * @param subject 主题
     * @param content 内容
     */
    void sendMimeMail(String to, String subject, String content);

    /**
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
