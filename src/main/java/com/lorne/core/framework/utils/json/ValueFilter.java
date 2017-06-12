package com.lorne.core.framework.utils.json;


public class ValueFilter {
    public Class type;
    public String[] values;
    public BeanValueFilter filter;

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public BeanValueFilter getFilter() {
        return filter;
    }

    public void setFilter(BeanValueFilter filter) {
        this.filter = filter;
    }

    public ValueFilter(Class type, String... values) {
        super();
        this.type = type;
        this.values = values;
    }

    public ValueFilter(Class type, BeanValueFilter filter) {
        super();
        this.type = type;
        this.filter = filter;
    }

    public ValueFilter(Class type, BeanValueFilter filter, String... values) {
        super();
        this.type = type;
        this.values = values;
        this.filter = filter;
    }

}