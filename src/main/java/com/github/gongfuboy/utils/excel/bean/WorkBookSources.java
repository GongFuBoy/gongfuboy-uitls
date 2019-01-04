package com.github.gongfuboy.utils.excel.bean;


import java.util.List;

/**
 * excel实体类
 * */
public class WorkBookSources<T> {

    /*
    * 分页名称
    * */
    private String sheetName;

    /*
    * 分页对应的数据
    * */
    private List<T> sourcesList;

    public WorkBookSources(String sheetName, List<T> sourcesList) {
        this.sheetName = sheetName;
        this.sourcesList = sourcesList;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<T> getSourcesList() {
        return sourcesList;
    }

    public void setSourcesList(List<T> sourcesList) {
        this.sourcesList = sourcesList;
    }
}
