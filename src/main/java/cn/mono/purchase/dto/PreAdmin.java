package cn.mono.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nihao
 * @time 2021/4/26
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreAdmin {
        private String contract_name;

        private String tel;

        private String count;

        private String name;
        private String academy_name;
        private  int budget;
        private  int maximum_order;
        private  int sid;
}
