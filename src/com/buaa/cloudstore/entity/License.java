package com.buaa.cloudstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="G_L_LICENSE")
public class License extends BaseObject implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4910899105578739109L;

	private String id;
	private String productName;
	private String productVersion;
	private String projectName;
	private String consumerName;
	private String orderId;
	
	
	private String creator;
	//license type Release | Trial | Temp
	private String type;
	//license type Basic | Standard | Advance
	private String level;
	private String createDate;
	private String expireDate;
	private String maxCpuNum;
	
	private boolean isBoundHostname;
	private String boundHostname;
	
	private boolean isBoundIpAddress;
	private String boundIpAddress;
	
	private boolean isBoundMacAddress;
	private String boundMacAddress;
	
	
	private String filePath;
	private int isDelete;
	
	
	private String addTime;
	public License() {
		
	}
	
	
	public License(String id, String productName, String productVersion,
			String projectName, String consumerName, String orderId, String creator, String type,
			String level, String createDate, String expireDate,
			String maxCpuNum, boolean isBoundHostname, String boundHostname,
			boolean isBoundIpAddress, String boundIpAddress,
			boolean isBoundMacAddress, String boundMacAddress, String filePath,
			int isDelete, String addTime) {
		super();
		this.id = id;
		this.productName = productName;
		this.productVersion = productVersion;
		this.projectName = projectName;
		this.orderId = orderId;
		this.creator = creator;
		this.type = type;
		this.level = level;
		this.createDate = createDate;
		this.expireDate = expireDate;
		this.maxCpuNum = maxCpuNum;
		this.isBoundHostname = isBoundHostname;
		this.boundHostname = boundHostname;
		this.isBoundIpAddress = isBoundIpAddress;
		this.boundIpAddress = boundIpAddress;
		this.isBoundMacAddress = isBoundMacAddress;
		this.boundMacAddress = boundMacAddress;
		this.filePath = filePath;
		this.isDelete = isDelete;
		this.consumerName = consumerName;
		this.addTime = addTime;
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
	
	@Column(name="product_name",length=45,nullable=false)
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Column(name="product_version",length=11,nullable=false)
	public String getProductVersion() {
		return productVersion;
	}
	
	@Column(name="consumer_name",length=45,nullable=false)
	public String getConsumerName() {
		return consumerName;
	}


	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}


	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}
	
	// 延迟加载：多对一方式
	// 关联信息：外键name = "consumer_id"
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "consumer_id")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Column(name="type",length=11)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	//@Column(name="create_date", columnDefinition="DATE")
	@Column(name="create_date", length=32)
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	//@Column(name="expire_date", columnDefinition="DATE")
	@Column(name="expire_date", length=32)
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	@Column(name="file_path",length=100)
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Column(name="is_delete",length=11)
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}


	@Column(name="project_name",length=120)
	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	@Column(name="order_id",length=45)
	public String getOrderId() {
		return orderId;
	}



	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	@Column(name="level",length=11)
	public String getLevel() {
		return level;
	}



	public void setLevel(String level) {
		this.level = level;
	}


	@Column(name="max_cpu_num", length=11)
	public String getMaxCpuNum() {
		return maxCpuNum;
	}



	public void setMaxCpuNum(String maxCpuNum) {
		this.maxCpuNum = maxCpuNum;
	}


	@Column(name="is_bound_hostname", length=1)
	public boolean getIsBoundHostname() {
		return isBoundHostname;
	}


	
	public void setIsBoundHostname(boolean isBoundHostname) {
		this.isBoundHostname = isBoundHostname;
	}


	@Column(name="bound_hostname",length=45)
	public String getBoundHostname() {
		return boundHostname;
	}



	public void setBoundHostname(String boundHostname) {
		this.boundHostname = boundHostname;
	}


	@Column(name="is_bound_ipaddress", length=1)
	public boolean getIsBoundIpAddress() {
		return isBoundIpAddress;
	}



	public void setIsBoundIpAddress(boolean isBoundIpAddress) {
		this.isBoundIpAddress = isBoundIpAddress;
	}


	@Column(name="bound_ipaddress",length=45)
	public String getBoundIpAddress() {
		return boundIpAddress;
	}



	public void setBoundIpAddress(String boundIpAddress) {
		this.boundIpAddress = boundIpAddress;
	}


	@Column(name="is_bound_macaddress",length=1)
	public boolean getIsBoundMacAddress() {
		return isBoundMacAddress;
	}


	
	public void setIsBoundMacAddress(boolean isBoundMacAddress) {
		this.isBoundMacAddress = isBoundMacAddress;
	}


	@Column(name="bound_macaddress", length=45)
	public String getBoundMacAddress() {
		return boundMacAddress;
	}



	public void setBoundMacAddress(String boundMacAddress) {
		this.boundMacAddress = boundMacAddress;
	}

	@Column(name="add_time", length=32)
	public String getAddTime() {
		return addTime;
	}

	
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}


}
