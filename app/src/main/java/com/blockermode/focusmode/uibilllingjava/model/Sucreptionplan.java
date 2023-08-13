package com.blockermode.focusmode.uibilllingjava.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Duration;

@Entity(tableName = "Sucreptionplan")
public class Sucreptionplan {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String productId;
    private String basePlanId;
    private String type;
   private String statrtdate;
   private String endDate;

    public String getStatrtdate() {
        return statrtdate;
    }

    public void setStatrtdate(String statrtdate) {
        this.statrtdate = statrtdate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBasePlanId() {
        return basePlanId;
    }

    public void setBasePlanId(String basePlanId) {
        this.basePlanId = basePlanId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
