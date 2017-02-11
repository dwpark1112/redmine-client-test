package com.github.dwpark1112.common.service;

import com.github.dwpark1112.redmine.service.RedmineClient;

/**
 * @author daniel
 */
public interface ThirdPartyClientService {
	/**
	 * RedmineClient application 조회
	 */
	RedmineClient getRedmineClient();
}
