package xebab.fitnesse.kettle;

/*
 * copyright (c) 2013 Alexander Thiel <stderr@web.de>
 */

import java.util.HashMap;
import java.util.Map;

public class KettleSettings
{
    public static Map<String,String> PARAMETERS = new HashMap<String,String>();

    public static Map<String,String> VARIABLES = new HashMap<String,String>();

    public void setParameterTo(String key, String value)
    {
        PARAMETERS.put(key, value);
    }

    public void setVariableTo(String key, String value)
    {
        VARIABLES.put(key, value);
    }
}
