package com.eshequ.msa.crm.model.targetcust;

import com.eshequ.msa.common.BaseModel;

public class CrmChannelCommunicationRecordKey extends BaseModel {
    private String recordId;

    private String channelLinkId;

    private String channelId;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

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