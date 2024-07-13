package org.example.batch

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class JobRunner(
    private val simpleJob: Job,
    private val jobLauncher: JobLauncher
) : ApplicationRunner {

    override fun run(args: ApplicationArguments) {

        val jobParameters = JobParametersBuilder()
            .addString("jobParameter", "값 들어옴ㅋㅋㅋ")
            .toJobParameters()

        jobLauncher.run(simpleJob, jobParameters)
    }
}