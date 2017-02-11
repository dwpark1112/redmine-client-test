package com.github.dwpark1112.common;

/**
 * ThirdParty application 이름 정보
 *
 * @author daniel
 */
public enum ThirdPartyName {
    REDMINE("redmine");

    private String value;

    ThirdPartyName(String value) {
        this.value = value;
    }

    /**
     * ThirdParty as String
     */
    @Override
    public String toString() {
        return value;
    }
}
