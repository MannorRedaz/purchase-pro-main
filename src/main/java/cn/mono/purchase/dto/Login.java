package cn.mono.purchase.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nihao
 * @time 2021/3/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Login {

    @NotEmpty
    String name;
    @NotEmpty
    String pwd;

}
