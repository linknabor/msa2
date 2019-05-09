package com.eshequ.msa.crm.model.targetcust;

import com.eshequ.msa.common.BaseModel;

public class CrmChannelLinkKey extends BaseModel {
    private String channelLinkId;

    private String channelId;

    public String getChannelLinkId() {
        return channelLinkId;
    }

    public void setChannelLinkId(String channelLinkId) {
        this.channelLinkId = channelLinkId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}