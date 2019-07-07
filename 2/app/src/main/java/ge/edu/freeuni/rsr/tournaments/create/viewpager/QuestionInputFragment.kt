package ge.edu.freeuni.rsr.tournaments.create.viewpager

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder
import ge.edu.freeuni.rsr.R


class QuestionInputFragment : Fragment() {

    @BindView(R.id.etQuestionContent)
    lateinit var etQuestionContent: EditText

    @BindView(R.id.etAnswerContent)
    lateinit var etAnswerContent: EditText

    @OnClick(R.id.txtNext)
    fun onNextClick() {
        listener?.onSaveQuestionClick(etQuestionContent.text.toString(),
                etAnswerContent.text.toString().split(";").map { it.trim() })
        etAnswerContent.setText("")
        etQuestionContent.setText("")
    }

    @OnClick(R.id.txtCreateTournament)
    fun onFinishClick() {
        listener?.onFinishCreatingClick()
    }

    private var unbinder: Unbinder? = null
    private var listener: OnFragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_question_input, container, false)
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
        unbinder?.unbind()
    }


    interface OnFragmentInteractionListener {
        fun onSaveQuestionClick(question: String, answers: List<String>)
        fun onFinishCreatingClick()
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuestionInputFragment()
    }
}
