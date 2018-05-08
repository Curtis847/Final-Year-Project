package com.messenger.practive.mentalhealthadvisordr;

public class IssueInformation {
    public String issueName;
    public  String issueText;
    public  String waysToCope;
    public String linksOrResources;

    public IssueInformation(){

    }
    public IssueInformation(String issueName, String issueText, String waysToCope, String linksOrResources){
        this.issueName = issueName;
        this.issueText = issueText;
        this.waysToCope = waysToCope;
        this.linksOrResources = linksOrResources;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueText() {
        return issueText;
    }

    public void setIssueText(String issueText) {
        this.issueText = issueText;
    }

    public String getWaysToCope() {
        return waysToCope;
    }

    public void setWaysToCope(String waysToCope) {
        this.waysToCope = waysToCope;
    }

    public String getLinksOrResources() {
        return linksOrResources;
    }

    public void setLinksOrResources(String linksOrResources) {
        this.linksOrResources = linksOrResources;
    }
}
