package com.cxp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntityInfo {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8" )
	@Column(nullable=true,columnDefinition="datetime comment '创建时间' ")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	@Column(nullable=true,columnDefinition="varchar(50) comment '创建人' ")
	private String createUser;
	
	@Column(nullable=true,columnDefinition="datetime comment '更新时间' ")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8" )
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	@Column(nullable=true,columnDefinition="varchar(50) comment '修改人' ")
	private String updateUser;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	
	
}
