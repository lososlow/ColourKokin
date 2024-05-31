package com.example.colourkokin

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var colorDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorDisplay = findViewById(R.id.colorDisplay)
        val openDialogButton: Button = findViewById(R.id.openDialogButton)

        openDialogButton.setOnClickListener {
            openColorPickerDialog()
        }
    }

    private fun openColorPickerDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_color_picker, null)
        val redSeekBar: SeekBar = dialogView.findViewById(R.id.redSeekBar)
        val greenSeekBar: SeekBar = dialogView.findViewById(R.id.greenSeekBar)
        val blueSeekBar: SeekBar = dialogView.findViewById(R.id.blueSeekBar)
        val redInput: EditText = dialogView.findViewById(R.id.redInput)
        val greenInput: EditText = dialogView.findViewById(R.id.greenInput)
        val blueInput: EditText = dialogView.findViewById(R.id.blueInput)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Выбери цвет")
            .setPositiveButton("OK") { _, _ ->
                val red = redInput.text.toString().toIntOrNull() ?: redSeekBar.progress
                val green = greenInput.text.toString().toIntOrNull() ?: greenSeekBar.progress
                val blue = blueInput.text.toString().toIntOrNull() ?: blueSeekBar.progress
                updateColorDisplay(red, green, blue)
            }
            .setNegativeButton("Cancel", null)
            .create()

        redSeekBar.setOnSeekBarChangeListener(createSeekBarChangeListener(redInput))
        greenSeekBar.setOnSeekBarChangeListener(createSeekBarChangeListener(greenInput))
        blueSeekBar.setOnSeekBarChangeListener(createSeekBarChangeListener(blueInput))

        dialog.show()
    }

    private fun createSeekBarChangeListener(input: EditText): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                input.setText(progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        }
    }

    private fun updateColorDisplay(red: Int, green: Int, blue: Int) {
        val color = Color.rgb(red, green, blue)
        colorDisplay.setBackgroundColor(color)
    }
}