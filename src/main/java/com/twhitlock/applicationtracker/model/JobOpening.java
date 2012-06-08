package com.twhitlock.applicationtracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: twhitlock
 * Date: 6/6/12
 * Time: 10:27 PM
 */
@Entity
@XmlRootElement
@Table//(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class JobOpening {
    @Id
    @GeneratedValue
    private Long id;

    private String companyName;
    private String positionName;
    private Date date;

    @ManyToOne
    private User updateBy;



    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User manyToOne) {
        this.updateBy = manyToOne;
    }
}
