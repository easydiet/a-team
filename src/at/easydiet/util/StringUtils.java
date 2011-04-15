package at.easydiet.util;

public class StringUtils
{
    public static boolean isNullOrWhitespaceOnly(String value)
    {
        if(value == null) return true;
        if("".equals(value.trim())) return true;
        return false;
    }
}
