package com.github.dwpark1112.common.service;

import com.github.dwpark1112.redmine.service.RedmineClient;
import com.github.dwpark1112.common.ThirdPartyName;
import com.github.dwpark1112.common.persistence.ThirdParty;
import com.github.dwpark1112.common.persistence.ThirdPartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author daniel
 */
@Service
class ThirdPartyClientServiceImpl implements ThirdPartyClientService {
	@Autowired
	private ThirdPartyRepository thirdPartyRepository;

	@Override
	public RedmineClient getRedmineClient() {
		ThirdParty thirdParty = thirdPartyRepository.findOneByName(ThirdPartyName.REDMINE.toString());
		return new RedmineClient(thirdParty);
	}
}
