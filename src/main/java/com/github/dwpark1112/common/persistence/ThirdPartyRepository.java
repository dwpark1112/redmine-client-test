package com.github.dwpark1112.common.persistence;

/**
 * Created by daniel on 2017. 2. 12..
 */
public interface ThirdPartyRepository {
	ThirdParty findOneByName(String name);
}
