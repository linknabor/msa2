package com.eshequ.msa.crm.model.targetcust;

public class CrmChannelCommunicationRecord extends CrmChannelCommunicationRecordKey {
    private String communicationDate;

    private String communicationContent;

    public String getCommunicationDate() {
        return communicationDate;
    }

    public void setCommunicationDate(String communicationDate) {
        this.communicationDate = communicationDate;
    }

    public String getCommunicationContent() {
        return communicationContent;
    }

    public void setCommunicationContent(String communicationContent) {
        this.communicationContent = communicationContent;
    }
}