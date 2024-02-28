package dto;

/**
 * 顾客购买水果dto
 */
public class FruitsDto {
    //购买水果编号
    private Integer code;
    //购买数量
    private Integer num;

    public FruitsDto(Integer code, Integer num) {
        this.code = code;
        this.num = num;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
