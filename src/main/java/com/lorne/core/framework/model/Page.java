package com.lorne.core.framework.model;

import com.lorne.core.framework.utils.json.JsonFormatUtils;

import java.util.*;

/**
 * Created by yuliang on 2017/4/7.
 */

public class Page<T> extends JsonModel {
    private int total;
    private Collection<T> rows;
    private int nowPage;
    private int pageNumber;
    private int pageSize;

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection<T> getRows() {
        return this.rows;
    }

    public void setRows(Collection<T> rows) {
        this.rows = rows;
    }

    public int getNowPage() {
        return this.nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public String toRowsJsonString() {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("total", total);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        if (rows != null && rows.size() > 0) {
            for (T t : rows) {
                if (t instanceof BaseEntity) {
                    maps.add(((BaseEntity) t).toMap());
                } else if (t instanceof Map) {
                    maps.add((Map) t);
                }
            }
        }
        data.put("rows", maps);
        return JsonFormatUtils.toJsonString(data);
    }
}
