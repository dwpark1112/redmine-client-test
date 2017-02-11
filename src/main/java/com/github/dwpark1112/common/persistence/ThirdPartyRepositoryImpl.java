package com.github.dwpark1112.common.persistence;

import com.github.dwpark1112.common.TemplateType;
import com.github.dwpark1112.common.ThirdPartyName;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * dummy
 */
@Repository
class ThirdPartyRepositoryImpl implements ThirdPartyRepository {
	@Override
	public ThirdParty findOneByName(String name) {

        ThirdParty dummy = new ThirdParty();
        dummy.setTest(true);

        dummy.setUrl("http://www.redmine.com");
        dummy.setApiKey("apikeydummy");
		dummy.setName(ThirdPartyName.REDMINE.toString());

		Template template = new Template();
		template.setAssigneeId(63);
		template.setProjectKey("project_identifier");
		template.setTrackerName("issue_management");
		template.setType(TemplateType.STATISTICS.toString());

        List<Template> dummyTemplates = new ArrayList<>();
		dummyTemplates.add(template);

		dummy.setTemplates(dummyTemplates);
		
		return dummy;
	}
}
