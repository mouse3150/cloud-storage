package com.buaa.cloudstore.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="store_object")
public class StoreObject extends BaseObject implements Serializable {

	private static final long serialVersionUID = -9012492911952299910L;
	private Long id;
	private String name;
	private String etag;
	private String mimeType;
	private String suffix;
	private long size;
	private String path;
	
	private String createTime;
	private String modifyTime;
	private String deleteTime;
	
	//类型，对象类型：分目录或文件
	private Integer objType;
	
	private Long parent;

//	@Id
//	@GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name = "system-uuid",strategy="uuid")
	
	@Column(name="parent_id")
	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	//	@GeneratedValue(generator="idGenerator")
//	@GenericGenerator(name = "idGenerator",strategy="identity")
//	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_seq_gen")
	@SequenceGenerator(name="my_seq_gen", sequenceName="ENTITY_SEQ")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Column(name="create_time", columnDefinition="DATE")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name="modify_time", columnDefinition="DATE")
	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name="delete_time", columnDefinition="DATE")
	public String getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(String deleteTime) {
		this.deleteTime = deleteTime;
	}

	@Column(name="object_type")
	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}

}
