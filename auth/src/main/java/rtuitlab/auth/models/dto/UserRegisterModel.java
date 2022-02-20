package rtuitlab.auth.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterModel {
    private String username;
    private String password;
    private String role;
}
