package com.example.androidkotlin

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private val cameraResaultLauncher: ActivityResultLauncher<String> = registerForActivityResult(
            ActivityResultContracts.RequestPermission()) {
                isGranted ->
                if(isGranted) {
                    Toast.makeText(this,"Camera granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this,"Camera NOT granted", Toast.LENGTH_LONG).show()
                }
        }

    private val cameraAndLocationResaultLauncher: ActivityResultLauncher<Array<String>> = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                permissions.entries.forEach {
                    var permissionName = it.key
                    val isGranted = it.value

                    if (isGranted) {
                        if(permissionName == Manifest.permission.ACCESS_FINE_LOCATION) {
                            Toast.makeText(this,"Location granted", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this,"Camera granted", Toast.LENGTH_LONG).show()
                        }
                    } else {
                        if(permissionName == Manifest.permission.ACCESS_FINE_LOCATION) {
                            Toast.makeText(this,"Location not granted", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this,"Camera not granted", Toast.LENGTH_LONG).show()
                        }
                    }
                }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCameraPermission: Button = findViewById(R.id.btnCameraPermission)
        btnCameraPermission.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                showRationaleDialog("Permiswion demo req came",
                        "Camera cannot be used because Camera access is denied")
            } else {
//                cameraResaultLauncher.launch((Manifest.permission.CAMERA))
                cameraAndLocationResaultLauncher.launch(
                        arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION)
                )
            }
        }
    }

    private fun showRationaleDialog(title: String, message: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
        builder.create().show()
    }
}