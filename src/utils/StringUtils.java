package utils;

public class StringUtils {
    public static Boolean isNotEmpty(String value){
        if(value != null && !"".equals(value)){
            return true;
        }
        return false;
    }
}
