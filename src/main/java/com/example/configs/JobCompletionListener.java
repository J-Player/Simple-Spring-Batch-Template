package com.example.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            jdbcTemplate.query("SELECT count(*) FROM users",
                    (rs, row) -> rs.getString(1)
            ).forEach(user -> log.info("Total users saved: {}", user));
        }
    }
}
