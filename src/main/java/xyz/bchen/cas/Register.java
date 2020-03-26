package xyz.bchen.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {
    private String name;
    private String password;
    private String email;
    private String phone;
    private String firstQuestion;
    private String firstAnswer;
    private String secondQuestion;
    private String secondAnswer;
    private String thirdQuestion;
    private String thirdAnswer;
}
