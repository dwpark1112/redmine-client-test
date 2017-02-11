#Redmine java api

회사에서는 아직 레드마인을 주로 사용하고 있는데, api server에서 주기적으로 특정 정보를 redmine issue로 남겨주길 원하는 것 같다.  
예를 들면 일일 통계라던가? 미리 파악해야 하는 정보 들 등등

redmine api를 쉽게 사용할 수 있도록 만들어둔 sdk가 있길래 가져다가 테스트로 만들어보았다.

새벽이라 그런지 피곤해서 왜 저렇게 만들었는지는 나중에 써야겠다. 괜한 고민 많이한 듯...

아래처럼 대충 만들어도 돌아간ㄷ...

```java
String uri = "";
String apiAccessKey = "";
String projectKey = "";
String trackerName = "";
String subject = "this is test";
String description = "this is my first test, redmine are you there?";

RedmineManager redmineManager = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
ProjectManager projectManager = redmineManager.getProjectManager();

Project project = projectManager.getProjectByKey(projectKey);
Tracker tracker = project.getTrackerByName(trackerName);

Issue issue = IssueFactory.create(project.getId(), subject);
issue.setDescription(description);
issue.setTracker(tracker);

IssueManager issueManager = redmineManager.getIssueManager();
issueManager.createIssue(issue);

```

그런데 아래처럼 호출되도록 만들었다. 하다보니깐 재미있어서는 아니고;;
왠지 계속해서 유사한 요청이 반복될 것 같았다. 그래서 반복되는 부분들을 싸그리 모아두고자 했던 것...

```java
RedmineClient redmineClient = thirdPartyClientService.getRedmineClient();

RedmineIssue redmineIssue = redmineClient.getIssueTemplate(TemplateType.STATISTICS);
redmineIssue.setSubject("this is test");
redmineIssue.setDescription("test description, redmine are you there?");

return redmineClient.register(redmineIssue);
```
