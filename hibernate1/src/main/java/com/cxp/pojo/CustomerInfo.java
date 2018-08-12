package com.cxp.pojo;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "customerinfo")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CustomerInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 30, nullable = false, unique = true, columnDefinition = "varchar(30) comment '客户号'")
    private String custNo;
    @Column(length = 30, columnDefinition = "varchar(30) comment '客户名字'")
    private String name;
    @Column(length = 11, columnDefinition = "varchar(30) comment '客户电话号'")
    private String phone;
    @Column(name = "birthday", nullable = true, columnDefinition = "date comment '生日日期'")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    @Column(name = "createDate", nullable = true, columnDefinition = "datetime comment '创建日期'")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Column(name = "updateDatetime", nullable = true, columnDefinition = "datetime comment '更新时间'")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDatetime;

    public int getAge() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int now = cal.get(Calendar.YEAR);
        cal.setTime(this.birthday);
        int bir = cal.get(Calendar.YEAR);
        return now - bir;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustomerInfo(String custNo, String name, String phone) {
        this.custNo = custNo;
        this.name = name;
        this.phone = phone;
    }

    public CustomerInfo() {
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
	public String toString() {
		return "CustomerInfo [id=" + id + ", custNo=" + custNo + ", name=" + name + ", phone=" + phone + ", birthday="
				+ birthday + ", createDate=" + createDate + ", updateDatetime=" + updateDatetime + "]";
	}

	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
