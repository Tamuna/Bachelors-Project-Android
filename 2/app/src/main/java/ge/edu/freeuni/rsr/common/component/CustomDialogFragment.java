package ge.edu.freeuni.rsr.common.component;

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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ge.edu.freeuni.rsr.R;

public class CustomDialogFragment extends DialogFragment {

    public static final String IS_CORRECT = "isCorrect";
    public static final String CORRECT_ANSWER = "correctAnswer";
    public static final String IS_TOURNAMENT = "isTournament";

    public interface AnswerSubmissionDialogListener {
        void OnNextQuestionClicked(DialogFragment dialog);
    }

    private AnswerSubmissionDialogListener listener;
    private boolean isCorrect;
    private String correctAnswer;
    private boolean isTournament;


    public static CustomDialogFragment newInstance(boolean isCorrect, String answer, boolean isTournament) {
        Bundle args = new Bundle();
        args.putBoolean(IS_CORRECT, isCorrect);
        args.putString(CORRECT_ANSWER, answer);
        args.putBoolean(IS_TOURNAMENT, isTournament);
        CustomDialogFragment fragment = new CustomDialogFragment();
        fragment.setArguments(args);
        fragment.setCancelable(false);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCorrect = getArguments().getBoolean(IS_CORRECT);
        correctAnswer = getArguments().getString(CORRECT_ANSWER);
        isTournament = getArguments().getBoolean(IS_TOURNAMENT);
        if (!isTournament)
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
        if (isTournament) {
            btnNext.setVisibility(View.INVISIBLE);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view);
        btnNext.setOnClickListener(v -> listener.OnNextQuestionClicked(CustomDialogFragment.this));

        return builder.create();
    }


    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commitAllowingStateLoss();
    }
}
