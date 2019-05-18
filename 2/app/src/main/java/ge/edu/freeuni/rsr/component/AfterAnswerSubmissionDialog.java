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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ge.edu.freeuni.rsr.R;

public class AfterAnswerSubmissionDialog extends DialogFragment {

    public static final String IS_CORRECT = "isCorrect";

    public interface AnswerSubmissionDialogListener {
        void OnNextQuestionClicked(DialogFragment dialog);
    }

    private AnswerSubmissionDialogListener listener;
    private boolean isCorrect;


    public static AfterAnswerSubmissionDialog newInstance(boolean isCorrect) {

        Bundle args = new Bundle();
        args.putBoolean(IS_CORRECT, isCorrect);
        AfterAnswerSubmissionDialog fragment = new AfterAnswerSubmissionDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCorrect = getArguments().getBoolean(IS_CORRECT);
        listener = (AnswerSubmissionDialogListener) getContext();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_after_answer_submission, null);
        ImageView imgCorrectness = view.findViewById(R.id.img_corrctness);
        Button btnNext = view.findViewById(R.id.btn_next);

        if (isCorrect) {
            btnNext.setBackgroundColor(getResources().getColor(R.color.green));
            imgCorrectness.setImageResource(R.drawable.ic_correct);
        } else {
            btnNext.setBackgroundColor(getResources().getColor(R.color.light_red));
            imgCorrectness.setImageResource(R.drawable.ic_wrong);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        btnNext.setOnClickListener(v -> listener.OnNextQuestionClicked(AfterAnswerSubmissionDialog.this));
        Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }
}
