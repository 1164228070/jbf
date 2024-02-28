package conf;

import java.math.BigDecimal;

public enum FruitsConf {
    APPLE(1,"苹果","新鲜的苹果",new BigDecimal("8")),
    STRAWBERRY(2,"草莓","新鲜的草莓",new BigDecimal("13")),
    MANGO(3,"芒果","新鲜的芒果",new BigDecimal("20"));
    //水果编号
    public final Integer code;
    //水果名称
    public final String name;
    //水果描述
    public final String desc;
    //水果原价
    public final BigDecimal price;
    private FruitsConf(Integer code, String name, String desc,BigDecimal price){
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }
    public static FruitsConf getFruitsConfByCode(Integer code){
        for (FruitsConf conf : FruitsConf.values()){
            if(code.equals(conf.code)){
                return conf;
            }
        }
        return null;
    }

}
