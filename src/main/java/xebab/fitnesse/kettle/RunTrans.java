package xebab.fitnesse.kettle;

/*
 * copyright (c) 2013 Alexander Thiel <stderr@web.de>
 */

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RunTrans
{
    private String filename;

    public RunTrans(String arg)
    {
        this.filename = arg;
    }

    public List<List<List<String>>> query()
    throws Exception
    {
        KettleEnvironment.init();

        TransMeta transMeta = new TransMeta(this.filename, (Repository) null);

        // properties are set on the metadata object
        for (Map.Entry<String,String> e : KettleSettings.PARAMETERS.entrySet()) {
            transMeta.setParameterValue(e.getKey(), e.getValue());
        }

        Trans transformation = new Trans(transMeta);

        // variables are set on the transformation object
        for (Map.Entry<String,String> e : KettleSettings.VARIABLES.entrySet()) {
            transformation.setVariable(e.getKey(), e.getValue());
        }

        transformation.execute(new String[0]);
        transformation.waitUntilFinished();

        Result result = transformation.getResult();

        List<List<List<String>>> lll = new LinkedList<List<List<String>>>();
        for (RowMetaAndData row : result.getRows())
        {
            List<List<String>> foo = new LinkedList<List<String>>();
            RowMetaInterface meta = row.getRowMeta();
            String[] fieldNames = meta.getFieldNames();
            Object[] data = row.getData();
            for (int i = 0; i < fieldNames.length; ++i)
            {
                String[] cell = new String[2];
                cell[0] = fieldNames[i];
                cell[1] = data[i].toString();
                foo.add(Arrays.asList(cell));
            }
            lll.add(foo);
        }

        return lll;
    }
}
