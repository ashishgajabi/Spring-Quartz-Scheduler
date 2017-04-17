package in.blogspot.ashish4java.complexspringquartzexample.scheduler;

import java.util.List;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		StringBuilder result = new StringBuilder();

		System.out.println("---------------- JOB STARTS ----------------");
		System.out.println("Job: Previous Fire Time -" + context.getPreviousFireTime());
		System.out.println("Job: Scheduled Fire Time -" + context.getScheduledFireTime());
		System.out.println("Job: Current Fire Time -" + context.getFireTime());
		System.out.println("Job: Next Fire Time -" + context.getNextFireTime());

		JobDetail jobDetail = context.getJobDetail();
		MyJobInput inputData = (MyJobInput) jobDetail.getJobDataMap().get("jobState");
		List<String> processData = inputData.getProcessData();

		if (processData != null && !processData.isEmpty()) {
			for (String raw : processData) {
				result = result.append(raw).append("--");
			}
		} else {
			result = new StringBuilder("NO INPUT DATA");
		}
		System.out.println("Result of this job execution is -" + result);
		System.out.println("---------------- JOB ENDS ----------------");
		System.out.println();
	}

}
