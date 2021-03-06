package cl.grupo2.ayudaguau.login.data.remote

import cl.grupo2.ayudaguau.login.domain.AutentificacionRepository
import cl.grupo2.ayudaguau.login.domain.UserAuth
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAutentificacionRepository (
    private val firebaseAuth: FirebaseAuth
) : AutentificacionRepository {

    override suspend fun doLogin(email: String, pass: String): UserAuth {
        firebaseAuth
            .signInWithEmailAndPassword(email, pass)
            .await()
        val user = firebaseAuth.currentUser
        return UserAuth(user?.displayName ?: "", user?.email ?: "")
    }
}