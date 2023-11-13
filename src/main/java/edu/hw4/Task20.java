package edu.hw4;

import edu.hw4.Task19.ValidationError;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

final class Task20 {

    private Task20() {

    }

    //Task20 convert errors to string
    public static Map<String, String> convertErrorsToStr(Map<String, Set<ValidationError>> errorsMap) {
        return errorsMap.entrySet().stream().collect(Collectors.toMap(
            Map.Entry::getKey,
            entry -> entry.getValue().stream().map(ValidationError::getMessage).collect(Collectors.joining(", "))
        ));
    }

}
