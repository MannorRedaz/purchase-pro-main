package cn.mono.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author nihao
 * @time 2021/4/16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Selecte {

    List<Date> value1;
    String academy;
    String categories;
    String type;

}
