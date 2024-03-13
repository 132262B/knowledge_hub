package com.testcontainer

import org.junit.Ignore
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import java.io.File
import java.time.Duration


@Ignore
@Transactional
@SpringBootTest
@ContextConfiguration(initializers = [IntegrationTest.IntegrationTestInitializer::class])
class IntegrationTest{

    companion object {
        val rdbms = DockerComposeContainer(File("infra/test/docker-compose.yaml"))
            .withExposedService(
                "local-db",
                3306,
                Wait.forLogMessage(".*ready for connections.*", 1)
                    .withStartupTimeout(Duration.ofSeconds(300))
            )
            .withExposedService(
                "local-db-migrate",
                0,
                Wait.forLogMessage("(.*Successfully applied.*)|(.*Successfully validated.*)", 1)
                    .withStartupTimeout(Duration.ofSeconds(300))
            )

        init {
            rdbms.start()
        }
    }

    internal class IntegrationTestInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            val properties: MutableMap<String, String> = HashMap()

            val rdbmsHost: String = rdbms.getServiceHost("local-db", 3306)
            val rdbmsPort: Int = rdbms.getServicePort("local-db", 3306)

            properties["spring.datasource.url"] = "jdbc:mysql://$rdbmsHost:$rdbmsPort/score"
            TestPropertyValues.of(properties)
                .applyTo(applicationContext)
        }
    }
}