package com.lorne.core.framework.model;


import com.lorne.core.framework.utils.json.JsonFormatUtils;
import com.lorne.core.framework.utils.json.ValueFilter;

/**
 * @author yuliang
 * @version 1.0
 * @date 2015-3-16 下午09:51:29
 */
public class JsonModel extends MapModel{

    public String toJsonString() {
        return JsonFormatUtils.toJsonString(this);
    }

    public String toJsonString(ValueFilter... vfs) {
        return JsonFormatUtils.toJsonString(this, vfs);
    }

}
