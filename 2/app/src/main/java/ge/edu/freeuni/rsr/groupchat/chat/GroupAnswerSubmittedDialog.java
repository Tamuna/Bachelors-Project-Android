package ge.edu.freeuni.rsr.groupchat.chat;

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

public class GroupAnswerSubmittedDialog extends DialogFragment {

    private static final String GROUP_ANSWER = "GROUP_ANSWER";
    private String groupAnswer;

    public interface AnswerDecisionListener {
        void onPositiveDecision(DialogFragment dialog);

        void onNegativeDecision(DialogFragment dialog);
    }

    private AnswerDecisionListener listener;

    public static GroupAnswerSubmittedDialog newInstance(String groupAnswer) {

        Bundle args = new Bundle();
        args.putString(GROUP_ANSWER, groupAnswer);
        GroupAnswerSubmittedDialog fragment = new GroupAnswerSubmittedDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        groupAnswer = getArguments().getString(GROUP_ANSWER);
        listener = (AnswerDecisionListener) getContext();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_after_answer_submission, null);
        ImageView imgCheck = view.findViewById(R.id.img_corrctness);
        imgCheck.setVisibility(View.GONE);

        Button btnOk = view.findViewById(R.id.btn_next);
        btnOk.setText("სწორია");
        Button btnNot = view.findViewById(R.id.btn_additional);
        btnNot.setVisibility(View.VISIBLE);
        btnNot.setText("არასწორია");

        TextView tvAnswer = view.findViewById(R.id.txt_correct_answer);
        TextView tvInfo = view.findViewById(R.id.txt_correctness);
        tvInfo.setText("გუნდის დაფიქსირებული პასუხი კითხვაზე არის:");
        tvAnswer.setText(groupAnswer);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view);
        btnOk.setOnClickListener(
                v -> listener.onPositiveDecision(GroupAnswerSubmittedDialog.this)
        );
        btnNot.setOnClickListener(
                v -> listener.onNegativeDecision(GroupAnswerSubmittedDialog.this)
        );
        return builder.create();
    }
}
