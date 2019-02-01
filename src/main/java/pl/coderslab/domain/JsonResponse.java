package pl.coderslab.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JsonResponse {
    String success;
    String message;
}
