package com.github.dwpark1112.common.persistence;

import lombok.Data;

/**
 * ThirdParty application redmine issue template
 *
 * @author daniel
 */
@Data
public class Template {
    private String type;
    private String projectKey;
    private String trackerName;
    private int assigneeId;
}
