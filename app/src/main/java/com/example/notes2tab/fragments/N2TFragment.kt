package com.example.notes2tab.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.notes2tab.R
import com.example.notes2tab.databinding.FragmentN2TBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class N2TFragment : Fragment() {

    private lateinit var binding: FragmentN2TBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (permissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }

        binding.imageCaptureButton.setOnClickListener { takePhoto() }
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentN2TBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun takePhoto() {}

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            // Используется для привязки жизненного цикла камер к владельцу жизненного цикла
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            // Делаем заднюю камеру дефолтной
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview)

            } catch(exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun requestPermissions() {
        activityResultLauncher.launch(REQUESTED_PERMISSION)
    }

    //Метод для вызова активности которая запрашивает разрешение у пользователя
    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission())
        { permission ->

            if (!permission) {
                Toast.makeText(requireContext(),
                    R.string.toast_msg_permission_request_denied,
                    Toast.LENGTH_SHORT).show()
            } else {
                startCamera()
            }
        }

    private fun permissionsGranted(): Boolean{ //Проверка наличия разрешений на использование камеры
        if (ContextCompat.checkSelfPermission(
            requireContext(), REQUESTED_PERMISSION) == PackageManager.PERMISSION_GRANTED)
            return true

        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        fun newInstance() = N2TFragment()
        private const val TAG = "CameraX"
        private const val REQUESTED_PERMISSION = Manifest.permission.CAMERA
    }
}