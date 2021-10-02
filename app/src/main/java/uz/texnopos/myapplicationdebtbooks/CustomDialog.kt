package uz.texnopos.myapplicationdebtbooks

import android.app.Dialog
import android.content.Context


import android.os.Bundle
import androidx.core.view.get
import org.koin.core.instance.getArguments
import uz.texnopos.myapplicationdebtbooks.databinding.CalendarBinding
import uz.texnopos.myapplicationdebtbooks.databinding.FloatingActionButtonBinding
import uz.texnopos.myapplicationdebtbooks.databinding.TimeBinding
import java.text.SimpleDateFormat

import java.util.*

class CustomDialog(
    private var mContext: Context,
    val plus: (name: String, amount: Double, coment: String, date: Long) -> Unit,
    val minus: (name: String, amount: Double, coment: String, date: Long) -> Unit
) : Dialog(mContext) {
    private lateinit var binding: FloatingActionButtonBinding
    private lateinit var calendar: CalendarBinding
    private lateinit var timePicker: TimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calendar = CalendarBinding.inflate(layoutInflater)
        timePicker = TimeBinding.inflate(layoutInflater)
        binding = FloatingActionButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val cal = Calendar.getInstance()

        calendarTekseriw(System.currentTimeMillis())

        val simpleDate = SimpleDateFormat("dd")
        var dateString = simpleDate.format(System.currentTimeMillis())
        var calendarTVkun = String.format(dateString)
        val simpleDatejil = SimpleDateFormat("yyyy")
        var dateStringjil = simpleDatejil.format(System.currentTimeMillis())
        var calendarTVjil = String.format(dateStringjil)
        binding.calendarTextView.text =
            calendarTVkun + " " + calendarTekseriw(System.currentTimeMillis()) + calendarTVjil
        var hour = cal.get(Calendar.HOUR)
        var minute = cal.get(Calendar.MINUTE)
        binding.otmenButton.setOnClickListener {
            cancel()
        }
        binding.calculatorImageView.setOnClickListener {
            setContentView(layoutInflater.inflate(R.layout.calculator, null))
        }

        //Calendar//
        binding.calendarTextView.setOnClickListener {
            setContentView(calendar.root)
            calendar.tvTime.text = hour.toString() + ":" + minute.toString()
            calendar.otmenaButton.setOnClickListener {
                setContentView(binding.root)
            }
            calendar.gotobaButton.setOnClickListener {
                setContentView(binding.root)
            }
            calendar.tvTime.setOnClickListener {
                setContentView(timePicker.root)
                timePicker.timeOkButton.setOnClickListener {
                    setContentView(calendar.root)
                }
                timePicker.timeOtmenaButton.setOnClickListener {
                    setContentView(calendar.root)
                }
            }
        }
        //Calendar//

        binding.plusButton.setOnClickListener {
            if (binding.editTextKontakt.text.isNullOrEmpty()) {
                binding.editTextKontakt.error = "Kontaktti kiritin"
            } else if (binding.summaEditText.text.isNullOrEmpty()||binding.summaEditText.text.toString().equals("0")) {
                binding.summaEditText.error = "summani kiritin"
            } else {

                plus.invoke(
                    binding.editTextKontakt.text.toString(),
                    binding.summaEditText.text.toString().toDouble(),
                    binding.KomentariyaEditText.text.toString(),
                    System.currentTimeMillis().toString().toLong()
                )

            }
        }

        binding.minusButton.setOnClickListener {
            if (binding.editTextKontakt.text.isNullOrEmpty()) {
                binding.editTextKontakt.error = "Kontaktti kiritin"
            } else if (binding.summaEditText.text.isNullOrEmpty()||binding.summaEditText.text.toString().equals("0")) {
                binding.summaEditText.error = "summani kiritin"
            } else {

                minus.invoke(
                    binding.editTextKontakt.text.toString(),
                    binding.summaEditText.text.toString().toDouble(),
                    binding.KomentariyaEditText.text.toString(),
                    System.currentTimeMillis().toString().toLong()
                )


            }
        }

    }

    private fun calendarTekseriw(calendarTextView: Long): String {
        val simpleDateFormat = SimpleDateFormat("M")
        var dateS = simpleDateFormat.format(calendarTextView)
        var date = String.format(dateS)
        var cal = ""
        if (date.toString().toInt() == 1) {
            cal = "янв."
        } else if (date.toString().toInt() == 2) {
            cal = "февр."
        } else if (date.toString().toInt() == 3) {
            cal = "мар."
        } else if (date.toString().toInt() == 4) {
            cal = "апр."
        } else if (date.toString().toInt() == 5) {
            cal = "мая"
        } else if (date.toString().toInt() == 6) {
            cal = "июн."
        } else if (date.toString().toInt() == 7) {
            cal = "июл."
        } else if (date.toString().toInt() == 8) {
            cal = "авг."
        } else if (date.toString().toInt() == 9) {
            cal = "сент."
        } else if (date.toString().toInt() == 10) {
            cal = "окт."
        } else if (date.toString().toInt() == 11) {
            cal = "нояб."
        } else if (date.toString().toInt() == 12) {
            cal = "дек."
        }
        return cal
    }


}