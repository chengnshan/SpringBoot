package com.cxp.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntityInfo implements Serializable {

	private static final long serialVersionUID = 9072172104517711206L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	@Column(name = "counterno", nullable = true, length = 50, columnDefinition = "varchar(50) comment '' ")
	private String counterNo;
	@Column(name = "deptno", nullable = true, length = 50, columnDefinition = "varchar(50) comment '' ")
	private String deptNo;
	@Column(name = "regionCode", nullable = true, length = 50)
	private String regionCode;
	@Column(name = "status", nullable = true, length = 50)
	private String status;
	@Column(name = "userName", nullable = true, length = 50)
	private String userName;
	@Column(name = "workstatus", nullable = true, length = 50)
	private String workstatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWorkstatus() {
		return workstatus;
	}

	public void setWorkstatus(String workstatus) {
		this.workstatus = workstatus;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", counterNo=" + counterNo + ", deptNo=" + deptNo + ", regionCode=" + regionCode
				+ ", status=" + status + ", userName=" + userName + ", workstatus=" + workstatus + "]";
	}

}
