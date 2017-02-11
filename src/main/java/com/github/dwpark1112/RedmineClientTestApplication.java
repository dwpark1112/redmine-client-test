package com.github.dwpark1112;

import com.github.dwpark1112.common.TemplateType;
import com.github.dwpark1112.common.service.ThirdPartyClientService;
import com.github.dwpark1112.redmine.service.RedmineClient;
import com.github.dwpark1112.redmine.service.RedmineIssue;
import com.taskadapter.redmineapi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RedmineClientTestApplication {

	@Autowired
	private ThirdPartyClientService thirdPartyClientService;

	@RequestMapping(method = RequestMethod.GET)
	public RedmineIssue getCreatedIssueUrl() throws RedmineException {

		RedmineClient redmineClient = thirdPartyClientService.getRedmineClient();

		RedmineIssue redmineIssue = redmineClient.getIssueTemplate(TemplateType.STATISTICS);
		redmineIssue.setSubject("this is test");
		redmineIssue.setDescription("test description, redmine are you there?");

		return redmineClient.register(redmineIssue);
	}

//		String uri = "";
//		String apiAccessKey = "";
//		String projectKey = "";
//		String trackerName = "";
//
//		RedmineManager redmineManager = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
//		ProjectManager projectManager = redmineManager.getProjectManager();
//
//	 	Project project = projectManager.getProjectByKey(projectKey);
//	 	Tracker tracker = project.getTrackerByName(trackerName);
//
//		String subject = "this is test";
//		String description = "this is my first test, redmine are you there?";
//
//		Issue issue = IssueFactory.create(project.getId(), subject);
//		issue.setDescription(description);
//		issue.setTracker(tracker);
//
//		IssueManager issueManager = redmineManager.getIssueManager();
//		issueManager.createIssue(issue);
//
//		return uri + "/issues/" + issue.getId();

	public static void main(String[] args) {
		SpringApplication.run(RedmineClientTestApplication.class, args);
	}
}
