package cl.grupo2.ayudaguau.registro.domain

interface RegistroRepository {
    suspend fun registrarUsuario (registroUsuario: RegistroUsuario): Boolean
}