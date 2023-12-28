package app.training.service

import app.training.dto.MemberDto
import app.training.repository.MemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    suspend fun findMyInfo(id: Long): MemberDto {
        return withContext(Dispatchers.IO) {
            delay(3000)
            val member = memberRepository.findByIdOrNull(id) !!

            MemberDto(
                id = member.id !!,
                name = member.name,
            )
        }
    }
}