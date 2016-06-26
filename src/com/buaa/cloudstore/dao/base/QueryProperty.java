package com.buaa.cloudstore.dao.base;

/**
 * 查询属性参数
 * 
 */
public class QueryProperty {

    // 查询属性名称
    private String propertyName;

    // 查询属性数值
    private Object propertyValue;

    // 查询属性处理
    private String handleType = "=";

    /**
     * 获取查询属性名称
     * 
     * @return 查询属性名称
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 设置查询属性名称
     * 
     * @param propertyName 查询属性名称
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 获取查询属性数值
     * 
     * @return 查询属性数值
     */
    public Object getPropertyValue() {
        return propertyValue;
    }

    /**
     * 设置查询属性数值
     * 
     * @param propertyValue 查询属性数值
     */
    public void setPropertyValue(Object propertyValue) {
        this.propertyValue = propertyValue;
    }

    /**
     * 获取查询属性处理
     * 
     * @return 查询属性处理
     */
    public String getHandleType() {
        return handleType;
    }

    /**
     * 设置查询属性处理
     * 
     * @param handleType 查询属性处理
     */
    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }
}
