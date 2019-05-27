package ge.edu.freeuni.rsr.component;

/*
 * created by tgeldiashvili on 5/17/2019
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ge.edu.freeuni.rsr.R;

public class AfterAnswerSubmissionDialog extends DialogFragment {

    public static final String IS_CORRECT = "isCorrect";
    public static final String CORRECT_ANSWER = "correctAnswer";

    public interface AnswerSubmissionDialogListener {
        void OnNextQuestionClicked(DialogFragment dialog);
    }

    private AnswerSubmissionDialogListener listener;
    private boolean isCorrect;
    private String correctAnswer;


    public static AfterAnswerSubmissionDialog newInstance(boolean isCorrect, String answer) {

        Bundle args = new Bundle();
        args.putBoolean(IS_CORRECT, isCorrect);
        args.putString(CORRECT_ANSWER, answer);
        AfterAnswerSubmissionDialog fragment = new AfterAnswerSubmissionDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCorrect = getArguments().getBoolean(IS_CORRECT);
        correctAnswer = getArguments().getString(CORRECT_ANSWER);
        listener = (AnswerSubmissionDialogListener) getContext();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_after_answer_submission, null);
        ImageView imgCorrectness = view.findViewById(R.id.img_corrctness);
        Button btnNext = view.findViewById(R.id.btn_next);
        TextView correctAnswerHolder = view.findViewById(R.id.txt_correct_answer);
        TextView correctness = view.findViewById(R.id.txt_correctness);

        if (isCorrect) {
            btnNext.setBackgroundColor(getResources().getColor(R.color.green));
            imgCorrectness.setImageResource(R.drawable.ic_correct);
            correctness.setTextColor(getResources().getColor(R.color.green));
            correctness.setText("ეს სწორი პასუხია!");
        } else {
            btnNext.setBackgroundColor(getResources().getColor(R.color.light_red));
            imgCorrectness.setImageResource(R.drawable.ic_wrong);
            correctness.setTextColor(getResources().getColor(R.color.light_red));
            correctness.setText("სწორი პასუხია:");
            correctAnswerHolder.setText(correctAnswer);
            correctAnswerHolder.setTextColor(getResources().getColor(R.color.light_red));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        btnNext.setOnClickListener(v -> listener.OnNextQuestionClicked(AfterAnswerSubmissionDialog.this));
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
