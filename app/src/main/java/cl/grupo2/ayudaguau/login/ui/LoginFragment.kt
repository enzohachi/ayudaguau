package cl.grupo2.ayudaguau.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cl.grupo2.ayudaguau.R
import cl.grupo2.ayudaguau.databinding.FragmentLoginBinding
import cl.grupo2.ayudaguau.login.data.remote.FirebaseAutentificacionRepository
import cl.grupo2.ayudaguau.login.domain.LoginUserPassUseCase
import cl.grupo2.ayudaguau.login.presentation.LoginUiState
import cl.grupo2.ayudaguau.login.presentation.LoginViewModel
import cl.grupo2.ayudaguau.login.presentation.LoginViewModelFactory
import cl.grupo2.ayudaguau.utils.extensions.alert
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependences()
        binding = FragmentLoginBinding.bind(view)
        setupLiveData()
        setupListener()
    }

    private fun setupDependences() {
        viewModelFactory = LoginViewModelFactory(
            LoginUserPassUseCase(
            FirebaseAutentificacionRepository(
            FirebaseAuth.getInstance())
        )
        )
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(LoginViewModel::class.java)
    }

    private fun setupListener() {
        binding.apply {
            btnRegistrarseRegistro.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registroFragment)
            }
            //btnIngresar.setOnClickListener()
        }
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state -> state?.let { handleState(it) } }
        )
    }

    private fun handleState(estado: LoginUiState) {
        when(estado){
            is LoginUiState.Loading -> showLoading()
            is LoginUiState.Error -> showError()
            is LoginUiState.Invaliduser -> showInvalidUser()
            is LoginUiState.Success -> showSuccess()
        }
    }

    private fun showSuccess() {
        alert("Login Ok")
    }

    private fun showInvalidUser() {
        alert("Usuario invalido")
    }

    private fun showError() {
        alert("Tenemos un error GRAVE")
    }

    private fun showLoading() {
        alert("Estamos cargando la informacion")
    }


}