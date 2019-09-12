import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;
import org.pentaho.di.repository.Repository;

import java.io.File;

public class KJBRunner {
    public static void main(String[] args){
        try {
            String kjbFileNameAndPath = System.getProperty("user.dir") + File.separator + "/src/main/resources/ktr files/test.kjb";
            Repository repository = null;

            JobMeta jobMeta = new JobMeta(kjbFileNameAndPath, repository);
            Job job = new Job(repository, jobMeta);

            /* Doing the basic initialization for executing a job */
            job.initializeVariablesFrom(null);
            job.getJobMeta().setInternalKettleVariables(job);
            job.copyParametersFrom(job.getJobMeta());

            /* Setting Parameters to the job object */
            // job.setParameterValue("key", "value");

            /* Activating the parameters and executing the Job */
            job.activateParameters();
            job.execute(0, null);
            job.waitUntilFinished();

            if(job.getErrors() > 0){
                System.out.println("Error in Job");
            }

            job.setFinished(Boolean.TRUE);
            job.clearParameters();

        } catch (KettleException /*|  UnknownParamException */ e) {
            e.printStackTrace();
        }
    }
}
