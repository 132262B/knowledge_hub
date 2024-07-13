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
class JobConfiguration {


    @Bean
    fun simpleJob(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager
    ): Job {
        return JobBuilder("simpleJob", jobRepository)
            .start(simpleStep1(jobRepository, transactionManager))
            .next(simpleStep2(jobRepository, transactionManager))
            .build()
    }

    @Bean
    fun simpleStep1(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager
    ): Step {
        return StepBuilder("simpleStep1", jobRepository)
            .tasklet({ contribution: StepContribution, chunkContext: ChunkContext ->
                val contributionParam = contribution.stepExecution.jobParameters.getString("jobParameter")
                println(contributionParam)
                val chunkContextParam = chunkContext.stepContext.jobParameters
                println(chunkContextParam.toString())
                RepeatStatus.FINISHED
            }, transactionManager)
            .build()
    }

    @Bean
    fun simpleStep2(
        jobRepository: JobRepository,
        transactionManager: PlatformTransactionManager
    ): Step {
        return StepBuilder("simpleStep2", jobRepository)
            .tasklet({ contribution: StepContribution, chunkContext: ChunkContext ->
                println("step2 was executed")
                RepeatStatus.FINISHED
            }, transactionManager)
            .build()
    }

}