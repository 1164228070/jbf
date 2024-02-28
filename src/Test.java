import conf.FruitsConf;
import conf.GlobalConf;
import conf.PromoteSaleConf;
import dto.FruitsDto;
import utils.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 题目1-4
 */
public class Test {
    public static BigDecimal Test_1() throws ParseException {
        GlobalConf globalConf = new GlobalConf(false,null,null,
                false,null,null,null);
        List<FruitsDto> dtos = new ArrayList<>();
        dtos.add(new FruitsDto(1,10));
        dtos.add(new FruitsDto(2,10));
        return sale(dtos, globalConf);
    }

    public static BigDecimal Test_2() throws ParseException {
        GlobalConf globalConf = new GlobalConf(false,null,null,
                false,null,null,null);
        List<FruitsDto> dtos = new ArrayList<>();
        dtos.add(new FruitsDto(1,10));
        dtos.add(new FruitsDto(2,10));
        dtos.add(new FruitsDto(3,10));
        return sale(dtos, globalConf);
    }

    public static BigDecimal Test_3() throws ParseException {
        GlobalConf globalConf = new GlobalConf(false,null,null,
                false,null,null,null);
        List<FruitsDto> dtos = new ArrayList<>();
        dtos.add(new FruitsDto(1,10));
        dtos.add(new FruitsDto(2,10));
        dtos.add(new FruitsDto(3,10));
        return sale(dtos, globalConf);
    }

    public static BigDecimal Test_4() throws ParseException {
        GlobalConf globalConf = new GlobalConf(false,null,null,
                true,new BigDecimal("100"),new BigDecimal(10),"2024-03-28 23:00:00");//题目4时打开
        List<FruitsDto> dtos = new ArrayList<>();
        dtos.add(new FruitsDto(1,10));
        dtos.add(new FruitsDto(2,10));
        dtos.add(new FruitsDto(3,10));
        return sale(dtos, globalConf);
    }


    /**
     *
     * @param dtos 模拟前端传入的水果购买的json数据
     * @param globalConf 全场打折，全场满减配置
     * @return 顾客最终购买价格
     * @throws ParseException
     */
    private static BigDecimal sale(List<FruitsDto> dtos, GlobalConf globalConf) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BigDecimal allPrice = BigDecimal.ZERO;
        for(FruitsDto dto : dtos){
            if(dto.getCode() == null || dto.getNum() == null || dto.getNum() <= 0){
                continue;
            }
            FruitsConf fruitsConf = FruitsConf.getFruitsConfByCode(dto.getCode());
            PromoteSaleConf promoteSaleConf = PromoteSaleConf.getPromoteSaleConfByCode(dto.getCode());
            if(fruitsConf == null){
                continue;
            }else {
                BigDecimal speciesPrice = fruitsConf.price.multiply(new BigDecimal(dto.getNum()));
                if(promoteSaleConf != null){
                    //不为null且在有效时间内则代表单品折扣有效
                    if(StringUtils.isNotEmpty(promoteSaleConf.discount)
                            && sdf.parse(promoteSaleConf.discountEedTime).compareTo(new Date()) > 0){
                        speciesPrice = speciesPrice.multiply(new BigDecimal(promoteSaleConf.discount));//单品折扣后的价格
                    }
                    //不为null且在有效时间内则代表单品满减有效
                    if(promoteSaleConf.thresholdValue != null
                            && sdf.parse(promoteSaleConf.subEndTime).compareTo(new Date()) > 0){
                        if(speciesPrice.compareTo(promoteSaleConf.thresholdValue) >= 0){
                            //超过满减阈值,单品满减
                            speciesPrice = speciesPrice.subtract(promoteSaleConf.subPrice);
                        }
                    }
                }
                allPrice = allPrice.add(speciesPrice);
            }
        }
        if(globalConf != null){
            if(globalConf.whetherDiscount && StringUtils.isNotEmpty(globalConf.discount)
                    && sdf.parse(globalConf.discountEedTime).compareTo(new Date()) > 0){
                //全场打折
                allPrice = allPrice.multiply(new BigDecimal(globalConf.discount));
            }
            if(globalConf.whetherSubPrice && globalConf.thresholdValue != null
                    && globalConf.subPrice != null
                    && sdf.parse(globalConf.subEndTime).compareTo(new Date()) > 0){
                //全场满减
                if(allPrice.compareTo(globalConf.thresholdValue) >= 0){
                    BigDecimal divide = allPrice.divide(globalConf.thresholdValue, 0, RoundingMode.DOWN);//需要满减的倍数
                    allPrice = allPrice.subtract(globalConf.subPrice.multiply(divide));
                }
            }
        }
        return allPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("137.23").divide(new BigDecimal("100"),0, RoundingMode.DOWN);
        System.out.println(bigDecimal);
    }
}
