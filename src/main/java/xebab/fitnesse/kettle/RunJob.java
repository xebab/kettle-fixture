package xebab.fitnesse.kettle;

/*
 * copyright (c) 2013 Alexander Thiel <stderr@web.de>
 */

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RunJob
{
    private String filename;

    public RunJob(String arg)
    {
        this.filename = arg;
    }

    public List<List<List<String>>> query()
    throws Exception
    {
        KettleEnvironment.init();

        JobMeta jobMeta = new JobMeta(this.filename, null);

        // properties are set on the metadata object
        for (Map.Entry<String,String> e : KettleSettings.PARAMETERS.entrySet()) {
            jobMeta.setParameterValue(e.getKey(), e.getValue());
        }

        Job job = new Job(null, jobMeta);

        // variables are set on the transformation object
        for (Map.Entry<String,String> e : KettleSettings.VARIABLES.entrySet()) {
            job.setVariable(e.getKey(), e.getValue());
        }

        job.start();
        job.waitUntilFinished();

        Result result = job.getResult();

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
                cell[1] = (data[i] == null ? "" : data[i].toString());
                foo.add(Arrays.asList(cell));
            }
            lll.add(foo);
        }

        return lll;
    }
}
