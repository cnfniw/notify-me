package org.xiqi.notifyme.domain;

import lombok.Data;

@Data
public class NotifyMe {
    private String apiKey;
    private String extValue;
    private String siteUrl;
    private Boolean wechatStatus = false;
    private Boolean commentStatus = true;
    private Boolean commentAuditsStatus = false;
    private Boolean postStatus = false;
    private Boolean postAuditsStatus = false;
    private Boolean postDelStatus = false;
    private Boolean selfNotify = false;
    private String publishUrl;
}
