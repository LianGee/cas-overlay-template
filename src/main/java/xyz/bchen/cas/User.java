package xyz.bchen.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private Long created_at;
    private Long updated_at;
}
