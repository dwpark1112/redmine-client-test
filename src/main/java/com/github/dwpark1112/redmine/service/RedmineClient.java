package com.github.dwpark1112.redmine.service;

import com.github.dwpark1112.common.TemplateType;
import com.github.dwpark1112.common.persistence.Template;
import com.github.dwpark1112.common.persistence.ThirdParty;
import com.taskadapter.redmineapi.*;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.Tracker;

public class RedmineClient {
    
    private RedmineManager redmineManager;
    private ThirdParty thirdParty;

	public RedmineClient(ThirdParty thirdParty) {
		this.thirdParty = thirdParty;
		this.redmineManager = RedmineManagerFactory.createWithApiKey( thirdParty.getUrl(), thirdParty.getApiKey() );
	}

	public RedmineIssue getIssueTemplate(TemplateType type) {
		Template template = thirdParty.getTemplateByType(type);
		return new RedmineIssue(template);
	}

	public RedmineIssue register(RedmineIssue redmineIssue) throws RedmineException {
		if (thirdParty.isTest()){
			redmineIssue.setPublishedUrl(1000, thirdParty.getUrl());
			return redmineIssue;
		}

		// Project 설정
		ProjectManager projectManager = redmineManager.getProjectManager();
		Project targetProject = projectManager.getProjectByKey(redmineIssue.getProjectKey());
		Tracker tracker = targetProject.getTrackerByName(redmineIssue.getTrackerName());

		// Issue 설정
		IssueManager issueManager = redmineManager.getIssueManager();
		Issue issue = IssueFactory.create(targetProject.getId(), redmineIssue.getSubject());
		issue.setDescription(redmineIssue.getDescription());
		issue.setTracker(tracker);
		issue.setAssigneeId(redmineIssue.getAssigneeId());

		// 이슈 생성
		issue = issueManager.createIssue(issue);

		redmineIssue.setPublishedUrl(issue.getId(), thirdParty.getUrl());
		return redmineIssue;
	}
}
