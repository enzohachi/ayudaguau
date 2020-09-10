package cl.grupo2.ayudaguau.login.domain

class LoginUserPassUseCase(
    private val repository: AutentificacionRepository
) {
    suspend fun execute (email: String, pass: String) = repository.doLogin(email,pass)
}