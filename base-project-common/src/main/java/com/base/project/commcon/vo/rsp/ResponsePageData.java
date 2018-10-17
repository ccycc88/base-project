package com.base.project.commcon.vo.rsp;

public class ResponsePageData {

    private Integer total = 0;
    private Object rows = null;

    public ResponsePageData() {

    }
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
