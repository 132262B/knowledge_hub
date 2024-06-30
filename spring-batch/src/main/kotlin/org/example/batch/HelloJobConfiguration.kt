package org.example.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager


@Configuration
class HelloJobConfiguration {


    @Bean
    fun job(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager
    ): Job {
        return JobBuilder( "job", jobRepository)
            .start(testStep1(jobRepository, transactionManager))
            .next(testStep2(jobRepository, transactionManager))
            .build()
    }

    @Bean
    fun testStep1(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager
    ): Step {
        return StepBuilder("testStep", jobRepository)
            .tasklet({ contribution: StepContribution, chunkContext: ChunkContext ->
                println("testStep1 started")
                RepeatStatus.FINISHED
            }, transactionManager).build()
    }

    @Bean
    fun testStep2(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager
    ): Step {
        return StepBuilder("testStep", jobRepository)
            .tasklet({ contribution: StepContribution, chunkContext: ChunkContext ->
                println("testStep2 started")
                RepeatStatus.FINISHED
            }, transactionManager).build()
    }

}