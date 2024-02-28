package conf;

import java.math.BigDecimal;

/**
 * 单个水果促销配置
 */
public enum PromoteSaleConf {
    STRAWBERRY(2,"0.8","2024-03-28 23:00:00",null,null,"2024-03-28 23:00:00");

    //水果编号
    public Integer code;
    //折扣力度，null则无打折
    public String discount;
    //打折结束时间
    public String discountEedTime;
    //满减阈值(达到该金额才满减),null则无满减
    public BigDecimal thresholdValue;
    //满减金额
    public BigDecimal subPrice;
    //满减活动结束时间
    public String subEndTime;

    PromoteSaleConf(Integer code, String discount, String discountEedTime, BigDecimal thresholdValue, BigDecimal subPrice, String subEndTime) {
        this.code = code;
        this.discount = discount;
        this.discountEedTime = discountEedTime;
        this.thresholdValue = thresholdValue;
        this.subPrice = subPrice;
        this.subEndTime = subEndTime;
    }

    public static PromoteSaleConf getPromoteSaleConfByCode(Integer code){
        for (PromoteSaleConf conf : PromoteSaleConf.values()){
            if(code.equals(conf.code)){
                return conf;
            }
        }
        return null;
    }
}
