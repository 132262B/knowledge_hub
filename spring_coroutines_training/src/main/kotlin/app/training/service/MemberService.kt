package app.training.service

import app.training.repository.MemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository : MemberRepository,
) {
    suspend fun findMyInfo(id: Long) {
        withContext(Dispatchers.IO) {
            delay(3000)
            memberRepository.findById(id)
        }
    }
}