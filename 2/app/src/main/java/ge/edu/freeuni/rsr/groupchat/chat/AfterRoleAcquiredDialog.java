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


/*
 * created by tgeldiashvili on 6/25/2019
 */

public class AfterRoleAcquiredDialog extends DialogFragment {
    public static String ROLE_NAME = "ROLE_NAME";
    public static String ROLE_OWNER = "ROLE_OWNER";

    private String roleName;
    private String roleOwner;

    public static AfterRoleAcquiredDialog newInstance(String roleOwner, String roleName) {
        Bundle args = new Bundle();
        args.putString(ROLE_NAME, roleName);
        args.putString(ROLE_OWNER, roleOwner);
        AfterRoleAcquiredDialog fragment = new AfterRoleAcquiredDialog();
        fragment.setArguments(args);
        fragment.setCancelable(false);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roleName = getArguments().getString(ROLE_NAME);
        roleOwner = getArguments().getString(ROLE_OWNER);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final View view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_after_answer_submission, null);

        ImageView imgCheck = view.findViewById(R.id.img_corrctness);
        imgCheck.setVisibility(View.GONE);
        Button btnOk = view.findViewById(R.id.btn_next);
        btnOk.setText("ძალიან კარგი");
        TextView tvUsernameHolder = view.findViewById(R.id.txt_correct_answer);
        TextView tvInfoHolder = view.findViewById(R.id.txt_correctness);

        btnOk.setBackgroundColor(getResources().getColor(R.color.green));
        imgCheck.setImageResource(R.drawable.ic_correct);
        tvUsernameHolder.setTextColor(getResources().getColor(R.color.green));
        tvInfoHolder.setTextColor(getResources().getColor(R.color.green));

        tvUsernameHolder.setText(roleOwner);
        tvInfoHolder.setText(roleName + "გახდა:");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view);
        btnOk.setOnClickListener(v -> dismiss());
        return builder.create();
    }
}
