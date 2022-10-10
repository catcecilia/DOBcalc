package cat.test.dobcalc

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDateText : TextView? = null
    private var allMinutes : TextView? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        selectedDateText = findViewById(R.id.tvSelectedDate)
        allMinutes = findViewById(R.id.allMinutes)
        btnDatePicker.setOnClickListener {
            datePicker()
        }
    }

    private fun datePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, { view, selectedYear, selectedMonth, selectedDayofMonth ->
            val selectedDate = "${selectedMonth+1}/$selectedDayofMonth/$selectedYear"
            selectedDateText?.setText(selectedDate)
            val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)

            val theDate = sdf.parse(selectedDate) //created date object
            theDate?.let {
                val selectedDateinDuration = theDate.time / 6300000 //60000 for minutes, 3600000 for hours, 6300000 for shrek 2 duration

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let{
                    val currentDateinDuration = currentDate.time / 6300000 //60000 for minutes, 3600000 for hours, 6300000 for shrek 2 duration
                    val differenceSelectedCurrentDates = currentDateinDuration - selectedDateinDuration

                    allMinutes?.text= differenceSelectedCurrentDates.toString()
                }
            }
        },
            year,
            month,
            day)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }


}