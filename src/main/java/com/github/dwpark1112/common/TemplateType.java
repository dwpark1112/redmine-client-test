package com.github.dwpark1112.common;

/**
 * 레드마인 Template 의 종류 이름
 *
 * @author daniel
 */
public enum TemplateType {
    STATISTICS("statistics");

    private String value;

    TemplateType(String typeName) {
        this.value = typeName;
    }

    @Override
    public String toString() {
        return value;
    }
}
