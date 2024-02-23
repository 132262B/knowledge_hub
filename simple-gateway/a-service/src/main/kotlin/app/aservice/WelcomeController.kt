package app.aservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {

    @GetMapping("/welcome")
    fun welcomeMessage() = "환영합니다. A 서비스 입니다."

    @GetMapping("/a/welcome")
    fun welcomeMessageA() = "환영합니다. A 서비스 입니다."
}