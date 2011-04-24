package at.easydiet.util;


public class StringUtils
{
    public static boolean isNullOrWhitespaceOnly(String value)
    {
        if(value == null) return true;
        if("".equals(value.trim())) return true;
        return false;
    }
    
    /**
     * Capitalize the first character of the given string.
     *
     * @param string     String to capitalize.
     * @return           Capitalized string.
     *
     * @throws IllegalArgumentException    String is <kk>null</kk> or empty.
     */
    public static String capitalize(final String string)
    {
       if (string == null)
          throw new NullPointerException("string");
       if (string.equals(""))
          throw new NullPointerException("string");

       return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }
    
    /**
     * Decapitalize the first character of the given string.
     *
     * @param string     String to capitalize.
     * @return           Capitalized string.
     *
     * @throws IllegalArgumentException    String is <kk>null</kk> or empty.
     */
    public static String decapitalize(final String string)
    {
       if (string == null)
          throw new NullPointerException("string");
       if (string.equals(""))
          throw new NullPointerException("string");

       return Character.toLowerCase(string.charAt(0)) + string.substring(1);
    }
}
