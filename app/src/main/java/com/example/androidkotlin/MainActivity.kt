package com.example.androidkotlin

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.androidkotlin.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var selectedDateTextView: TextView
    lateinit var selectedDateInMinutesTextView: TextView


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        selectedDateTextView = findViewById(R.id.tvSelectedDate)
        selectedDateInMinutesTextView = findViewById(R.id.tvSelectedDateInMinutes)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        { view, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(this, "The year is $year", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"

            val sdf = SimpleDateFormat("dd//MM/yyyy", Locale.ENGLISH)
            val theDAte = sdf.parse(selectedDate)

            selectedDateTextView.setText(selectedDate)

            val selectedDateInMinutes = theDAte!!.time / 60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000
            val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
            selectedDateInMinutesTextView.setText(differenceInMinutes.toString())
        }, year, month, day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}