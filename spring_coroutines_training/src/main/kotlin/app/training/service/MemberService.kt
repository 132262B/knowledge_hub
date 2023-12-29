package app.training.service

import app.training.dto.MemberDto
import app.training.repository.MemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    @Transactional(readOnly = true)
    suspend fun findMyInfo(id: Long): MemberDto {
        return withContext(Dispatchers.IO) {
            println("MemberService.findMyInfo 내부 : ${Thread.currentThread().name}")
            val member = memberRepository.findByIdOrNull(id) !!
            delay(3000)

            MemberDto(
                id = member.id !!,
                name = member.name,
            )
        }
    }
}