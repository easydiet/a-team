package at.easydiet.view.content;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.pivot.wtk.TextInput.TextBindMapping;

public class QuantityBindMapping implements TextBindMapping
{
    public static final org.apache.log4j.Logger LOG    = org.apache.log4j.Logger
                                                               .getLogger(QuantityBindMapping.class);
    public static final DecimalFormat           FORMAT = new DecimalFormat("0.00");

    public String toString(Object value)
    {
        return FORMAT.format(value);
    }

    public Object valueOf(String text)
    {
        try
        {
            return FORMAT.parse(text).floatValue();
        }
        catch (ParseException e)
        {
            return 0;
        }
    }
}
