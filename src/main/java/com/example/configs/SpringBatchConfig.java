package com.example.configs;

import com.example.entities.User;
import com.example.processors.UserProcessor;
import com.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.time.Instant;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class SpringBatchConfig {

    @Bean
    public Job job(JobRepository jobRepository, Step step, JobCompletionListener jobCompletionListener) {
        return new JobBuilder(SpringBatchConstraints.JOB_NAME, jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobCompletionListener)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, JpaTransactionManager transactionManager, ItemWriter<User> writer) {
        return new StepBuilder(SpringBatchConstraints.STEP_NAME, jobRepository)
                .<User, User>chunk(200, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }

    @Bean
    public FlatFileItemReader<User> reader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("csvReader")
                .resource(new ClassPathResource("data.csv"))
                .delimited()
                .names(SpringBatchConstraints.ITEM_READER_NAMES)
                .linesToSkip(1)
                .customEditors(Collections.singletonMap(Instant.class, new InstantPropertyEditor()))
                .strict(true)
                .targetType(User.class)
                .build();
    }

    @Bean
    public UserProcessor processor() {
        return new UserProcessor();
    }

    @Bean
    public ItemWriter<User> writer(UserRepository userRepository) {
        return new RepositoryItemWriterBuilder<User>()
                .repository(userRepository)
                .methodName("save")
                .build();
    }

}
