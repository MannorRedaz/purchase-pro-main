package cn.mono.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nihao
 * @time 2021/4/22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeSelectDto {
    String t1;
    String t2;
    int id;
    int pid;

}
