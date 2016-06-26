package com.buaa.cloudstore.dao.base;

/**
 * 排序属性
 */
public class QuerySort {

    /**
     * 升序
     */
    public static final int SORT_ASC = 0;

    /**
     * 降序
     */
    public static final int SORT_DESC = 1;

    // 排序属性名称
    private String sortName;

    // 排序类型
    private int sortType;

    /**
     * 获取排序属性名称
     * 
     * @return 排序属性名称
     */
    public String getSortName() {
        return sortName;
    }

    /**
     * 设置排序属性名称
     * 
     * @param sortName 排序属性名称
     */
    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    /**
     * 获取排序类型
     * 
     * @return 排序类型
     */
    public int getSortType() {
        return sortType;
    }

    /**
     * 设置排序类型
     * 
     * @param sortType 排序类型
     */
    public void setSortType(int sortType) {
        this.sortType = sortType;
    }
}
