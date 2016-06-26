package com.buaa.cloudstore.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="G_L_COMPANY")
public class Company extends BaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1502286683327608131L;
	private String id;
	private String name;
	private String size;
	private String province;
	private String city;
	private String address;
	private Consumer owner;
	
	public Company() {
		
	}
	
	public Company(String id, String name, String size, String province,
			String city, String address, Consumer owner) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.province = province;
		this.city = city;
		this.address = address;
		this.owner = owner;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(length=45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(length=32)
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	@Column(length=11)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	@Column(length=11)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Column(length=140)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL)  
	@JoinColumn(name = "consumer_id",unique=true)
	public Consumer getOwner() {
		return owner;
	}
	public void setOwner(Consumer owner) {
		this.owner = owner;
	}
	
}
