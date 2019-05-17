package ge.edu.freeuni.rsr.component;

/*
 * created by tgeldiashvili on 5/17/2019
 */

import androidx.fragment.app.DialogFragment;

public class AfterAnswerSubmissionDialog extends DialogFragment {
    public interface AnswerSubmissionDialogListener{
        void OnNextQuestionClicked(DialogFragment dialog);
    }

    private AnswerSubmissionDialogListener listener;
}
