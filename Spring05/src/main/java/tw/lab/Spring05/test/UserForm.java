package tw.lab.Spring05.test;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
    @NotBlank(message = "Email 不可為空")
    @Email(message = "請輸入有效的Email")
    private String email;
    @Size(min = 6, message = "密碼長度至少6碼")
    private String passwd;
    @NotBlank(message = "姓名不可為空")
    private String name;
}
