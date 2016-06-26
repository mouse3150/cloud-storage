package com.buaa.cloudstore.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="G_L_CONSUMER")
public class Consumer extends BaseObject implements java.io.Serializable {
	
	
	public Consumer(String id, String name, String password, String salt, String email,
			String telephone, /*Set<License> licenses, */Company company, String title, Integer status,
			String registerTime, String preLoginTime, String loginTime, Integer licenseDownloadNum,
			Integer downloadNumLimit, Boolean locked) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.salt = salt;
		this.email = email;
		this.telephone = telephone;
		//this.licenses = licenses;
		//this.company = company;
		this.title = title;
		this.status = status;
		this.registerTime = registerTime;
		this.preLoginTime = preLoginTime;
		this.loginTime = loginTime;
		this.licenseDownloadNum = licenseDownloadNum;
		this.downloadNumLimit = downloadNumLimit;
		this.locked = locked;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = -8522898836370870858L;
	
	public Consumer() {
	}



	private String id;
	private String name;
	private String password;
	private String salt;
	private String email;
	private String telephone;
	//private Set<License> licenses = new HashSet<License>(0);
	//private Company company;
	private String title;
	private Integer status;
	private String registerTime;
	private String preLoginTime;
	private String loginTime;
	private Integer licenseDownloadNum;
	private Integer downloadNumLimit;
	private Boolean locked;
	
	private Set<StoreObject> objects;
	
	
	@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="consumer_objects",
            joinColumns=@JoinColumn(name="consumer_id"),
            inverseJoinColumns=@JoinColumn(name="object_id")
    )
	public Set<StoreObject> getObjects() {
		return objects;
	}

	public void setObjects(Set<StoreObject> objects) {
		this.objects = objects;
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

	@Column(length=45)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length=32)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length=20)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(length=32)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(length=11)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="register_time", columnDefinition="DATE")
	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
//	public Set<License> getLicenses() {
//		return licenses;
//	}

//	public void setLicenses(Set<License> licenses) {
//		this.licenses = licenses;
//	}

//	@OneToOne(cascade = CascadeType.ALL, mappedBy="owner")
//	public Company getCompany() {
//		return company;
//	}
//
//
//	public void setCompany(Company company) {
//		this.company = company;
//	}
//	
	@Column(name="prelogin_time", columnDefinition="DATE")
	public String getPreLoginTime() {
		return preLoginTime;
	}

	public void setPreLoginTime(String preLoginTime) {
		this.preLoginTime = preLoginTime;
	}

	@Column(name="login_time", columnDefinition="DATE")
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	@Column(name="download_num",length=11)
	public Integer getLicenseDownloadNum() {
		return licenseDownloadNum;
	}

	public void setLicenseDownloadNum(Integer licenseDownloadNum) {
		this.licenseDownloadNum = licenseDownloadNum;
	}

	@Column(name="download_limit",length=11)
	public Integer getDownloadNumLimit() {
		return downloadNumLimit;
	}

	public void setDownloadNumLimit(Integer downloadNumLimit) {
		this.downloadNumLimit = downloadNumLimit;
	}
	
	@Column(name="salt")
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String getCredentialsSalt() {
        return name + salt;
    }
	
	public void setCredentialsSalt(String credentialsSalt) {
        
    }

	@Column(name="locked")
	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

}
