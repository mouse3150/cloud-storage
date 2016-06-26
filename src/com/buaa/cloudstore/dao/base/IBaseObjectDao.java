package com.buaa.cloudstore.dao.base;

import java.util.List;

/**
 * DAO对象基类接口
 * 
 */
public interface IBaseObjectDao<T> {

    /**
     * 获取entity所有记录
     * 
     * @return 数据记录
     */
    List<T> list();

     /**
     * 分页获取entity记录
     * 
     * @param pageVo 分页属性
     * @return 分页数据记录
     */
    Page<T> listPage(Page<T> pageVo);
    
    
    Page<T> listPageByProperty(Page<T> pageVo, QueryProperty qp);
    
    
    Page<T> listPageByProperties(Page<T> pageVo, List<QueryProperty> qps);

    /**
     * 根据entity ID获取entity对象
     * 
     * @param id entity ID
     * @return entity
     */
    T getById(String id);

    /**
     * 对Entity对象执行新增操作
     * 
     * @param entityObj entity对象
     * @return entity对象
     */
    T add(T entityObj);

    /**
     * 批量对Entity对象执行新增操作
     * 
     * @param entityObjList entity对象集合
     * @return entity对象集合
     */
    List<T> add(List<T> entityObjList);

    /**
     * 对Entity对象执行变更操作
     * 
     * @param entityObj entity对象
     * @return entity对象
     */
    T update(T entityObj);

    /**
     * 批量对Entity对象执行变更操作
     * 
     * @param entityObjList entity对象集合
     * @return entity对象集合
     */
    List<T> update(List<T> entityObjList);

    /**
     * Entity对象执行删除
     * 
     * @param entityObj entity对象
     */
    void delete(T entityObj);

    /**
     * 批量对Entity对象执行删除
     * 
     * @param entityObjList entity对象集合
     */
    void delete(List<T> entityObjList);

    /**
     * 根据ID对Entity对象执行删除
     * 
     * @param id entity ID
     */
    void deleteById(String id);

    /**
     * 根据ID集合批量对Entity对象执行删除
     * 
     * @param ids entity ID集合
     */
    void deleteByIds(List<String> ids);
    
    /**
     * 根据QueryProperty条件查询
     * 
     * @param ids entity ID集合
     */
    public List<T> listByProperty(QueryProperty queryProperty);
}