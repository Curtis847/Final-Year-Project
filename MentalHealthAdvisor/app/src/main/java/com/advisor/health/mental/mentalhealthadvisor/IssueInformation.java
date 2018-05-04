package com.advisor.health.mental.mentalhealthadvisor;

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
}
