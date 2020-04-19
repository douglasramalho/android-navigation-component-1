package br.com.douglasmotta.naivagtioncomponentappmirror.ui.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import br.com.douglasmotta.naivagtioncomponentappmirror.MainActivity

import br.com.douglasmotta.naivagtioncomponentappmirror.R
import br.com.douglasmotta.naivagtioncomponentappmirror.extensions.dismissError
import br.com.douglasmotta.naivagtioncomponentappmirror.extensions.navigateWithAnimations
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_login.*
import java.lang.ref.WeakReference
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity() as MainActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val validationFields = initValidationFields()
        listenToAuthenticationStateEvent(validationFields)
        registerViewListeners()
        registerDeviceBackStackCallback()
    }

    private fun initValidationFields() = mapOf(
        LoginViewModel.INPUT_USERNAME.first to inputLayoutLoginUsername,
        LoginViewModel.INPUT_PASSWORD.first to inputLayoutLoginPassword
    )

    private fun listenToAuthenticationStateEvent(validationFields: Map<String, TextInputLayout>) {
        viewModel.authenticationStateEvent.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                is LoginViewModel.AuthenticationState.Authenticated -> {
                    navController.popBackStack()
                }
                is LoginViewModel.AuthenticationState.InvalidAuthentication -> {
                    authenticationState.fields.forEach { fieldError ->
                        validationFields[fieldError.first]?.error = getString(fieldError.second)
                    }
                }
            }
        })
    }

    private fun registerViewListeners() {
        buttonLoginSignIn.setOnClickListener {
            val username = inputLoginUsername.text.toString()
            val password = inputLoginPassword.text.toString()

            viewModel.authenticate(username, password)
        }

        buttonLoginSignUp.setOnClickListener {
            navController.navigateWithAnimations(R.id.action_loginFragment_to_navigation)
        }

        inputLoginUsername.addTextChangedListener {
            inputLayoutLoginUsername.dismissError()
        }

        inputLoginPassword.addTextChangedListener {
            inputLayoutLoginPassword.dismissError()
        }
    }

    private fun registerDeviceBackStackCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelAuthentication()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        cancelAuthentication()
        WeakReference
        return true
    }

    private fun cancelAuthentication() {
        viewModel.refuseAuthentication()
        navController.popBackStack(R.id.startFragment, false)
    }
}
