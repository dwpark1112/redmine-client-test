package com.github.dwpark1112.common.persistence;

import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;

import com.github.dwpark1112.common.TemplateType;

import lombok.Data;

/**
 * Created by daniel on 2017. 2. 12..
 */
@Data
public class ThirdParty {
    private String id;
    private String name;
    private String apiKey;
    private String url;
    private boolean isTest = false;

    // Not required (레드마인 템플릿)
    private List<Template> templates;

    public Template getTemplateByType(TemplateType type) {
        EqualPredicate equalPredicate = new EqualPredicate(type.toString());
        BeanPredicate beanPredicate = new BeanPredicate("type", equalPredicate);
        return (Template) CollectionUtils.find(this.templates, beanPredicate);
    }
}
