package com.buaa.cloudstore.dao.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.buaa.cloudstore.entity.BaseObject;



/**
 * Dao对象基类
 */
public class BaseObjectDaoImpl<T extends BaseObject> extends BaseDaoImpl implements
		IBaseObjectDao<T> {

	private Class<T> persistClass;

	public List<T> list() {
		logger.trace("The method list() is called.");

		return listByPropertiesWithSort(null, null);
	}

	public List<T> listByProperty(QueryProperty queryProperty) {
		logger.trace("The method listByProperty() is called.");

		List<QueryProperty> queryProperties = null;
		if (queryProperty != null) {
			queryProperties = new ArrayList<QueryProperty>();
			queryProperties.add(queryProperty);
		}
		return listByPropertiesWithSort(queryProperties, null);
	}

	public List<T> listByProperties(List<QueryProperty> queryProperties) {
		logger.trace("The method listByProperties() is called.");
		return listByPropertiesWithSort(queryProperties, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> listByPropertiesWithSort(List<QueryProperty> queryProperties,
			List<QuerySort> querySorts) {
		logger.trace("The method listByPropertiesWithSort() is called.");

		// 生成查询参数
		Criterion criterion = null;
		if (queryProperties != null && queryProperties.size() != 0) {
			for (QueryProperty queryProperty : queryProperties) {
				Criterion tempCriterion = addConditionCriterion(queryProperty);
				if (tempCriterion != null) {
					if (criterion == null) {
						criterion = tempCriterion;
					} else {
						criterion = Restrictions.and(criterion, tempCriterion);
					}
				}
			}
		}
		Criteria criteria = getSession().createCriteria(persistClass);
		if (criterion != null) {
			criteria.add(criterion);
		} else {
			return null;
		}
		if (querySorts != null && querySorts.size() != 0) {
			Iterator<QuerySort> iterator = querySorts.iterator();
			while (iterator.hasNext()) {
				criteria.addOrder(getSortType(iterator.next()));
			}
		}
		return criteria.list();
	}

	
	public Page<T> listPage(Page<T> pageVo) {
		logger.trace("The method listPage() is called.");

		return listPageByPropertiesWithSort(pageVo, null, null);
	}

	
	public Page<T> listPageByProperty(Page<T> pageVo, QueryProperty queryProperty) {
		logger.trace("The method listPage() is called.");

		List<QueryProperty> queryProperties = new ArrayList<QueryProperty>();
		queryProperties.add(queryProperty);
		return listPageByPropertiesWithSort(pageVo, queryProperties, null);
	}

	public Page<T> listPageByProperties(Page<T> pageVo, List<QueryProperty> queryProperties) {
		logger.trace("The method listPage() is called.");
		return listPageByPropertiesWithSort(pageVo, queryProperties, null);
	}

	@SuppressWarnings("unchecked")
	public Page<T> listPageByPropertiesWithSort(Page<T> pageVo,
			List<QueryProperty> queryProperties, List<QuerySort> querySorts) {
		logger.trace("The method listPage() is called.");


		// 生成查询参数
		Criterion criterion = null;
		if (queryProperties != null && queryProperties.size() != 0) {
			for (QueryProperty queryProperty : queryProperties) {
				Criterion tempCriterion = addConditionCriterion(queryProperty);
				if (tempCriterion != null) {
					if (criterion == null) {
						criterion = tempCriterion;
					} else {
						criterion = Restrictions.and(criterion, tempCriterion);
					}
				}
			}
		}
		Criteria criteriaCount = getSession().createCriteria(persistClass);
		if (criterion != null) {
			criteriaCount.add(criterion);
		}
		if (querySorts != null && querySorts.size() != 0) {
			Iterator<QuerySort> iterator = querySorts.iterator();
			while (iterator.hasNext()) {
				criteriaCount.addOrder(getSortType(iterator.next()));
			}
		}
		criteriaCount.setProjection(Projections.rowCount());
		Object countObj = criteriaCount.list().get(0);
		if (countObj instanceof Integer) {
			pageVo.setTotalRecords(((Integer) countObj).intValue());
		} else if (countObj instanceof Long) {
			pageVo.setTotalRecords(((Long) countObj).intValue());
		}

		// 取得总页数
		int maxpageNum = pageVo.getTotalPage();

		// 判断是否页号是否超过尾页
		if (pageVo.getFrom() >= maxpageNum) {
			pageVo.setFrom(maxpageNum);
			// 判断是否是尾页
		} else if (pageVo.getFrom() == -1) {
			pageVo.setFrom(maxpageNum);
		}
		if (pageVo.getFrom() == 0) {
			pageVo.setFrom(1);
		}

		Criteria criteria = getSession().createCriteria(persistClass);
		if (criterion != null) {
			criteria.add(criterion);
		}
		if (querySorts != null && querySorts.size() != 0) {
			Iterator<QuerySort> iterator = querySorts.iterator();
			while (iterator.hasNext()) {
				criteria.addOrder(getSortType(iterator.next()));
			}
		}
		criteria.setFirstResult((pageVo.getFrom() - 1) * pageVo.getLimit());
		criteria.setMaxResults(pageVo.getLimit());
		pageVo.setData(criteria.list());

		return pageVo;
	}

	public T getById(String id) {
		logger.trace("The method getById() is called.");
		logger.debug("The parameter id is " + id);

		QueryProperty property = new QueryProperty();
		property.setPropertyName("id");
		property.setPropertyValue(id);
		List<T> list = listByProperty(property);
		if (list != null && list.size() != 0) { return list.get(0); }
		// return getHibernateTemplate().get(persistClass, id);
		return null;
	}

	public T add(T entityObj) {
		logger.trace("The method add() is called.");

		// if (StringUtils.isBlank(entityObj.getId())) {
		// entityObj.setId(UUIDGenerator.generatorID());
		// }
		getSession().saveOrUpdate(entityObj);
		getSession().flush();
		return entityObj;
	}

	public List<T> add(List<T> entityObjList) {
		logger.trace("The method add() is called.");

		if (entityObjList != null && entityObjList.size() != 0) {
			for (T obj : entityObjList) {
				add(obj);
			}
		}
		return entityObjList;
	}

	public T update(T entityObj) {
		logger.trace("The method update() is called.");
		if (entityObj != null) {
			getSession().merge(entityObj);
			getSession().flush();
		}
		return entityObj;
	}

	public List<T> update(List<T> entityObjList) {
		logger.trace("The method update() is called.");

		if (entityObjList != null && entityObjList.size() != 0) {
			for (T obj : entityObjList) {
				update(obj);
			}
		}
		return entityObjList;
	}

	public void delete(T entityObj) {
		logger.trace("The method delete() is called.");
		getSession().delete(entityObj);
		getSession().flush();
	}

	public void delete(List<T> entityObjList) {
		logger.trace("The method delete() is called.");

		for(T t : entityObjList) {
			delete(t);
		}
	}
	

	public void deleteById(String id) {
		logger.trace("The method deleteById() is called.");
		logger.debug("The parameter id is " + id);
		T obj = (T)getSession().load(persistClass, id);
		delete(obj);
	}

	public void deleteByIds(List<String> ids) {
		logger.trace("The method deleteByIds() is called.");

		if (ids != null && ids.size() != 0) {
			for (String id : ids) {
				deleteById(id);
			}
		}
	}

	/*
	 * 追加查询条件，返回条件HQL及参数
	 */
	private Criterion addConditionCriterion(QueryProperty queryProperty) {
		Object propertyValue = queryProperty.getPropertyValue();
		Criterion criterion = null;
		if (propertyValue instanceof String || propertyValue instanceof Integer
				|| propertyValue instanceof Long || propertyValue instanceof Float
				|| propertyValue instanceof Double || propertyValue instanceof Date) {
			criterion = addConditionTypeCriteria(queryProperty.getPropertyName(),
					queryProperty.getPropertyValue(), queryProperty.getHandleType());
		} else if (propertyValue instanceof List) {
			List<?> vals = (List<?>) propertyValue;
			if (vals != null && vals.size() != 0) {
				if ("between".equals(queryProperty.getHandleType())) {
					criterion = Restrictions.between(queryProperty.getPropertyName(), vals.get(0),
							vals.get(1));
				} else {
					Iterator<?> iter = vals.iterator();
					while (iter.hasNext()) {
						Criterion tempCriterion = null;
						Object val = iter.next();
						tempCriterion = addConditionTypeCriteria(queryProperty.getPropertyName(),
								val, queryProperty.getHandleType());
						if (tempCriterion != null) {
							if (criterion == null) {
								criterion = tempCriterion;
							} else {
								criterion = Restrictions.or(criterion, tempCriterion);
							}
						}
					}
				}
			}
		}
		return criterion;
	}

	private Criterion addConditionTypeCriteria(String propertyName, Object propertyValue,
			String handleType) {
		Criterion criterion = null;
		if ("=".equals(handleType)) {
			criterion = Restrictions.eq(propertyName, propertyValue);
		} else if (">".equals(handleType)) {
			criterion = Restrictions.gt(propertyName, propertyValue);
		} else if (">=".equals(handleType)) {
			criterion = Restrictions.ge(propertyName, propertyValue);
		} else if ("<".equals(handleType)) {
			criterion = Restrictions.lt(propertyName, propertyValue);
		} else if ("<=".equals(handleType)) {
			criterion = Restrictions.le(propertyName, propertyValue);
		} else if ("<>".equals(handleType)) {
			criterion = Restrictions.ne(propertyName, propertyValue);
		} else if ("like".equals(handleType)) {
			criterion = Restrictions.like(propertyName, formatQueryParam((String) propertyValue));
		}
		return criterion;
	}

	/**
	 * 格式化参数
	 * 
	 * @param propertyVal
	 * @param isMySql
	 * @return
	 * @throws Exception
	 */
	private String formatQueryParam(String propertyVal) {
		if (StringUtils.isNotEmpty(propertyVal) && !"*".equals(propertyVal)) {
			if (propertyVal.indexOf("*") != 0) {
				propertyVal = "*" + propertyVal;
			}
			if (propertyVal.indexOf("*") != propertyVal.length() - 1) {
				propertyVal = propertyVal + "*";
			}
		} else if (StringUtils.isEmpty(propertyVal)) {
			propertyVal = "*";
		}
		propertyVal = propertyVal.replace("\\", "\\\\");
		propertyVal = propertyVal.replace("%", "\\%");
		propertyVal = propertyVal.replace("*", "%");
		propertyVal = propertyVal.replace("'", "''");
		propertyVal = propertyVal.replace("_", "\\_");
		propertyVal = propertyVal.replace("?", "_");

		return propertyVal;
	}

	private Order getSortType(QuerySort querySort) {
		if (QuerySort.SORT_ASC == querySort.getSortType()) {
			return Order.asc(querySort.getSortName());
		} else if (QuerySort.SORT_DESC == querySort.getSortType()) {
			return Order.desc(querySort.getSortName());
		} else {
			return Order.asc(querySort.getSortName());
		}
	}

	/**
	 * 获取persistClass
	 * 
	 * @return persistClass
	 */
	public Class<T> getPersistClass() {
		return persistClass;
	}

	/**
	 * 设置persistClass
	 * 
	 * @param persistClass
	 *            persistClass
	 */
	public void setPersistClass(Class<T> persistClass) {
		this.persistClass = persistClass;
	}
	
}
