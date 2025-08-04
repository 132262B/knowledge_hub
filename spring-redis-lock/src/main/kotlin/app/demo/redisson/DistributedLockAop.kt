package app.demo.redisson

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.reflect.Method

@Aspect
@Component
class DistributedLockAop(
    private val redissonClient: RedissonClient,
    private val aopForTransaction: AopForTransaction
) {
    private val log = LoggerFactory.getLogger(DistributedLockAop::class.java)


    @Around("@annotation(app.demo.redisson.DistributedLock)")
    @Throws(Throwable::class)
    fun lock(joinPoint: ProceedingJoinPoint): Any {
        val signature = joinPoint.signature as MethodSignature
        val method: Method = signature.method
        val distributedLock = method.getAnnotation(DistributedLock::class.java)

        val key = REDISSON_LOCK_PREFIX +
                CustomSpringELParser.getDynamicValue(signature.parameterNames, joinPoint.args, distributedLock.key)

        val rLock: RLock = redissonClient.getLock(key)

        return try {
            val available = rLock.tryLock(
                distributedLock.waitTime,
                distributedLock.leaseTime,
                distributedLock.timeUnit
            )

            if (!available) {
                return false
            }

            aopForTransaction.proceed(joinPoint)
        } catch (e: InterruptedException) {
            throw InterruptedException()
        } finally {
            try {
                rLock.unlock()
            } catch (e: IllegalMonitorStateException) {
                log.error(
                    "Redisson Lock Already UnLock serviceName={} key={}",
                    method.name,
                    key
                )
            }
        }
    }

    companion object {
        private const val REDISSON_LOCK_PREFIX = "LOCK:"
    }
}

