package ge.edu.freeuni.rsr;

/*
 * created by tgeldiashvili on 6/19/2019
 */

import java.util.HashMap;
import java.util.Map;

public class ErrorUtils {
    private Map<String, String> errorValues;

    public ErrorUtils() {
        errorValues = new HashMap<>();
        errorValues.put("The user name has already been taken.", "მომხმარებლის სახელი დაკავებულია");
        errorValues.put("The email has already been taken.", "მოცემული იმეილით მომხმარებელი უკვე დარეგისრირებულია");
        errorValues.put("The email must be a valid email address.", "გთხოვთ მიუთითოთ ვალიდური იმეილი");
        errorValues.put("Unauthorized", "მომხმარებლის სახელი ან პაროლი არასწორია");
    }

    public String processError(String errorKey) {
        return errorValues.get(errorKey);
    }
}
