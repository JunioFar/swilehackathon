package com.example.swilehackathon.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.acesso.acessobio_android.AcessoBioListener
import com.acesso.acessobio_android.iAcessoBioSelfie
import com.acesso.acessobio_android.onboarding.AcessoBio
import com.acesso.acessobio_android.onboarding.camera.CameraListener
import com.acesso.acessobio_android.onboarding.camera.UnicoCheckCameraOpener
import com.acesso.acessobio_android.services.dto.ErrorBio
import com.acesso.acessobio_android.services.dto.ResultCamera
import com.example.swilehackathon.SwileHackathonViewModel
import com.example.swilehackathon.databinding.FragmentDocumentBinding
import com.example.swilehackathon.unicoconfig.UnicoConfigLiveness
import com.example.swilehackathon.unicoconfig.UnicoTheme
import com.example.swilehackathon.util.TextMask
import com.example.swilehackathon.util.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DocumentFragment : Fragment(), AcessoBioListener,
    iAcessoBioSelfie, CameraListener {

    private lateinit var binding: FragmentDocumentBinding

    private val viewModel: SwileHackathonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDocumentBinding.inflate(layoutInflater)
        binding.documentEditText.let {
            it.addTextChangedListener(TextMask.createCPFMask(it))
        }
        binding.proceedButton.setOnClickListener {
            if (viewModel.isCPFValid(binding.documentEditText.text.toString())) {
                binding.signupProgressIndicator.visibility = View.VISIBLE
                it.isEnabled = false
                openCameraLiveness()
            } else {
                showErrorDialog(requireContext(), "Digite um CPF válido!")
            }
        }

        viewModel.candidateLiveData.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() != null) {
                navigateToProperScreen()
            }
        }
        viewModel.errorLiveDate.observe(viewLifecycleOwner) {
            if (it.getContentIfNotHandled() != null) {
                binding.signupProgressIndicator.visibility = View.GONE
                binding.proceedButton.isEnabled = true
                showErrorDialog(requireContext(), it.content?: "Algo deu errado")
            }
        }
        return binding.root
    }

    private fun openCameraLiveness() {
        AcessoBio(requireContext(), this)
            .setTheme(UnicoTheme())
            .build()
            .prepareCamera(UnicoConfigLiveness(), this)
    }

    override fun onErrorAcessoBio(error: ErrorBio?) {
        showErrorDialog(requireContext(), error?.description ?: "Unknown error")
    }

    override fun onUserClosedCameraManually() {
        showErrorDialog(requireContext(), "Camera closed")
    }

    override fun onSystemClosedCameraTimeoutSession() {
        showErrorDialog(requireContext(), "Camera closed timeout")
    }

    override fun onSystemChangedTypeCameraTimeoutFaceInference() {
        showErrorDialog(requireContext(), "Camera closed timeout")
    }

    override fun onSuccessSelfie(result: ResultCamera?) {
        result?.let {
            binding.proceedButton.isEnabled = false
            binding.signupProgressIndicator.visibility = View.VISIBLE
            viewModel.postUserInfo(
                binding.documentEditText.toString(),
                it.base64,
                it.encrypted
            )
        }
    }

    private fun navigateToProperScreen() {
        findNavController().navigate(
            DocumentFragmentDirections.actionDocumentFragmentToResultFragment()
        )
    }

    override fun onErrorSelfie(error: ErrorBio?) {
        showErrorDialog(requireContext(), error?.description ?: "Unknown error")
    }

    override fun onCameraFailed(message: String?) {
        showErrorDialog(requireContext(), message ?: "Camera error")
    }

    override fun onCameraReady(cameraOpener: UnicoCheckCameraOpener.Camera) {
        binding.signupProgressIndicator.visibility = View.GONE
        binding.proceedButton.isEnabled = true
        cameraOpener.open(this)
    }

}