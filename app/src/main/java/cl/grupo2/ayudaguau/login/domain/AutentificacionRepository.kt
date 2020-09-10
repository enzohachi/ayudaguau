package cl.grupo2.ayudaguau.login.domain

interface AutentificacionRepository {
    suspend fun doLogin (email: String, pass: String) : UserAuth
}