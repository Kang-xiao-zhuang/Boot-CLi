package com.zhuang.aspect.dto;

public class PageDto {

    private static final int MAX_SIZE = 1000;
    private static final int DEFAULT_SIZE = 10;
    private static final int START_PAGE = 1;

    private Integer pageNum;
    private Integer pageSize;

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        if (null == pageNum || pageNum < 1) {
            return START_PAGE;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (null == pageSize) {
            return DEFAULT_SIZE;
        }
        if (pageSize > MAX_SIZE) {
            return MAX_SIZE;
        }
        return pageSize;
    }
}
