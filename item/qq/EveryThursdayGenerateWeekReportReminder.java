package com.faw_vk.qak.mgmt.job;

import com.efast.cafe.commponent.job.base.AbstractStatefulJobBean;
import com.efast.cafe.framework.spring.SpringContextUtils;
import com.faw_vk.qak.mgmt.service.report.IQualityIssueReportService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class EveryThursdayGenerateWeekReportReminder extends AbstractStatefulJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("每周四生成质量要求周报数据插入数据库表........");
        IQualityIssueReportService qualityIssueReportService = SpringContextUtils.getBean(IQualityIssueReportService.class);
        qualityIssueReportService.scheduleInsertWeekReport();

    }
}
