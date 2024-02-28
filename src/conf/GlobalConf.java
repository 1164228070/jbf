package conf;

import java.math.BigDecimal;

/**
 * 全场打折，全场满减
 */
public class GlobalConf {
    //是否打折(true则开启全场打折)
    public Boolean whetherDiscount;
    //打折力度
    public String discount;
    //打折结束时间
    public String discountEedTime;
    //是否开启满减活动(true则开启满减活动)
    public Boolean whetherSubPrice;
    //满减阈值(达到该金额才满减)
    public BigDecimal thresholdValue;
    //满减金额
    public BigDecimal subPrice;
    //满减活动结束时间
    public String subEndTime;

    public GlobalConf(Boolean whetherDiscount, String discount, String discountEedTime, Boolean whetherSubPrice, BigDecimal thresholdValue, BigDecimal subPrice, String subEndTime) {
        this.whetherDiscount = whetherDiscount;
        this.discount = discount;
        this.discountEedTime = discountEedTime;
        this.whetherSubPrice = whetherSubPrice;
        this.thresholdValue = thresholdValue;
        this.subPrice = subPrice;
        this.subEndTime = subEndTime;
    }
}
