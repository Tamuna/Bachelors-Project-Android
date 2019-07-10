package ge.edu.freeuni.rsr.common.component;

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

public class CustomTwoButtonDialog extends DialogFragment {

    private static final String INFO = "INFO";
    private static final String IS_FRIEND_ADDITION = "IS_FRIEND_ADDITION";
    private String passedInfo;
    private boolean isFriendAddition;

    public interface AnswerDecisionListener {
        void onPositiveDecision(DialogFragment dialog);

        void onNegativeDecision(DialogFragment dialog);
    }

    private AnswerDecisionListener listener;

    public static CustomTwoButtonDialog newInstance(String info, boolean isFriendAddition) {

        Bundle args = new Bundle();
        args.putString(INFO, info);
        args.putBoolean(IS_FRIEND_ADDITION, isFriendAddition);
        CustomTwoButtonDialog fragment = new CustomTwoButtonDialog();
        fragment.setArguments(args);
        fragment.setCancelable(false);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passedInfo = getArguments().getString(INFO);
        isFriendAddition = getArguments().getBoolean(IS_FRIEND_ADDITION);
        listener = (AnswerDecisionListener) getContext();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_after_answer_submission, null);
        ImageView imgCheck = view.findViewById(R.id.img_corrctness);
        imgCheck.setVisibility(View.GONE);
        TextView tvAnswer = view.findViewById(R.id.txt_correct_answer);
        TextView tvInfo = view.findViewById(R.id.txt_correctness);


        Button btnNot = view.findViewById(R.id.btn_additional);
        btnNot.setBackgroundResource(R.color.light_red);
        btnNot.setVisibility(View.VISIBLE);
        Button btnOk = view.findViewById(R.id.btn_next);
        btnOk.setBackgroundResource(R.color.green);

        if (!isFriendAddition) {
            btnOk.setText("სწორია");
            btnNot.setText("არასწორია");
            tvInfo.setText("გუნდის დაფიქსირებული პასუხი კითხვაზე არის:");

        } else {
            btnOk.setText("დამატება");
            btnNot.setText("უარყოფა");
            tvInfo.setText("თქვენ გაქვთ მეგობრობის შემოთავაზება მოაზროვნისგან:");
        }

        tvAnswer.setText(passedInfo);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view);
        btnOk.setOnClickListener(
                v -> listener.onPositiveDecision(CustomTwoButtonDialog.this)
        );
        btnNot.setOnClickListener(
                v -> listener.onNegativeDecision(CustomTwoButtonDialog.this)
        );
        return builder.create();
    }
}
