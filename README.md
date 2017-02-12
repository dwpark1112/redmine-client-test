#Redmine & api server integration demo

회사에서 레드마인을 사용하고 있는데, api server에서 주기적으로 특정 정보를 redmine issue로 남겨주길 원하는 것 같다. 일일 통계라던가? 월간 통계라던가... 새로 가입한 기관의 활동량 등등 마케팅에 필요한 정보들


## Redmine Java API test code

library <http://github.com/taskadapter/redmine-java-api>

redmine api를 쉽게 사용할 수 있도록 만들어둔 sdk가 있길래 가져다가 테스트로 만들어보았다.  
아래처럼 순차적으로 호출해도 기능은 돌아가는데, 내 취향은 아니다.

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

## Wrapping Redmine Java API

아래처럼 redmine api library를 필요한 부분 별로 감싸서 처리하도록 만들었다.  
일단 비슷한 요청이 계속 증가하더라도, 코드 중복은 발생하지 않을 것 같고 가독성이 더 좋다.  

프로젝트 유형, 일감 유형, 레드마인 주소 및 apiKey 등은 데이터베이스에 저장시키는 것으로 했다. 설정파일에 저장할 경우, 설정 정보가 변경될때마다 재배포해야할 것 같고 왠지 그런 상황이 자주 일어날 것 같았다.

```java
RedmineClient redmineClient = thirdPartyClientService.getRedmineClient();

RedmineIssue redmineIssue = redmineClient.getIssueTemplate(TemplateType.STATISTICS);
redmineIssue.setSubject("this is test");
redmineIssue.setDescription("test description, redmine are you there?");

return redmineClient.register(redmineIssue);
```

이런 설정 정보나 컨텐츠 같은 것들은 document db가 정말 편리한 것 같다. 내 경우 mongodb를 쓰고 있는데, 아래처럼 그냥 저장해두고 가져다 쓰면 된다. nosql의 aggregation 모델링 패턴을 사용하면, 별다른 고민없이 다른 api 정보도 추가하면 되고, application layer에서 적절히 제어해서 사용하면 된다.

```json
{
    "_id" : ObjectId("589c1216470205a9c2c8ee7f"),
    "name" : "redmine",
    "apiKey" : "92xxxxxxxxyyyyyyyyzzzzzzzexample",
    "url" : "http://redmine.ihealthhub.net",
    "templates" : [ 
        {
            "type" : "statistics",
            "projectKey" : "issueradnet",
            "trackerName" : "statistics",
            "assigneeId" : 63
        },
        {
            "type" : "dailyActiveUsers",
            "projectKey" : "issueradnet",
            "trackerName" : "dau",
            "assigneeId" : 63
        }        
    ]
}
```

관리자에게 알림으로 메일 보내는 것도 전부 레드마인 이슈로 등록하는게 더 나을 것 같다. 어차피 레드마인에 이슈가 등록되면 거기서 메일이 발송되니깐, 그게 효율적일 듯하다.

## 고찰

* 스프링부트가 간단하게 이것저것 테스트하기 편하다. 엄청나게 간단하다.
* TestCase 안만들고 대충 예제 만든게 약간 후회된다. 앞으로 개인적으로 개발할때라도 테스트케이스 만들어서 해야겠다. 안그러면 TDD는 영원히 남일....;;
