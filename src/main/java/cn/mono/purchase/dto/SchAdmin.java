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
public class SchAdmin {
        private String contract_name;

        private String tel;

        private String count;

        private String name;
        private String schoolName;
        private String tag;


    public SchAdmin(String contract_name, String tel, String count, String name, String schoolName) {
        this.contract_name = contract_name;
        this.tel = tel;
        this.count = count;
        this.name = name;
        this.schoolName = schoolName;
    }
}
