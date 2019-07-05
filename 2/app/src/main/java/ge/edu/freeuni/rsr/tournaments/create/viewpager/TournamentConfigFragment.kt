package ge.edu.freeuni.rsr.tournaments.create.viewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import ge.edu.freeuni.rsr.R
import java.util.*

class TournamentConfigFragment : Fragment(), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private var date: String = ""
    private var dateTime: String = ""

    @BindView(R.id.etTournirName)
    lateinit var etTournirName: EditText

    @BindView(R.id.tvTime)
    lateinit var tvTime: TextView


    @OnClick(R.id.txtNext)
    fun onNextClick() {
        if (dateTime.isEmpty()) Toast.makeText(activity, "მითითეთ ტურნირის დაწყების დრო", LENGTH_SHORT).show()
        else listener?.onNextClick(dateTime, etTournirName.text.toString())
    }


    @OnClick(R.id.viewTimeChooser)
    fun onFocusedToDateTimePicker() {
        val now = Calendar.getInstance()
        val dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        )
        dpd.minDate = now
        dpd.show(fragmentManager!!, "tagtag")
    }

    companion object {
        @JvmStatic
        fun newInstance() = TournamentConfigFragment()
    }

    private var unbinder: Unbinder? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tournament_config, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onNextClick(dateTime: String, tourName: String)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder?.unbind()
    }

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        dateTime = "$date $hourOfDay:$minute"
        tvTime.text = dateTime
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        date = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year
        val now = Calendar.getInstance()
        val tpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        )
        tpd.show(fragmentManager!!, "tag")
    }


}
