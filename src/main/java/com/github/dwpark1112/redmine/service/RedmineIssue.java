package com.github.dwpark1112.redmine.service;

import com.github.dwpark1112.common.persistence.Template;
import lombok.Getter;

/**
 * RedmineClient Issue
 */
@Getter
public class RedmineIssue {
    private String publishedUrl;
    private String projectKey;
    private String trackerName;

    private String subject;
    private String description;

    private int assigneeId;

    public RedmineIssue(Template template) {
        this.projectKey = template.getProjectKey();
        this.trackerName = template.getTrackerName();
        this.assigneeId = template.getAssigneeId();
    }

    public RedmineIssue setSubject(String subject )
    {
        this.subject = subject;
        return this;
    }

    public RedmineIssue setDescription(String description) {
        this.description = description;
        return this;
    }
    
	public RedmineIssue setPublishedUrl(int id, String url) {
		this.publishedUrl = url + "/issues/" + id;
		return this;
	}
}
