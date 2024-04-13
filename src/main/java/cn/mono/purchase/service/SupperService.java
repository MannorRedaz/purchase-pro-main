package cn.mono.purchase.service;

import cn.mono.purchase.dto.Message;
import cn.mono.purchase.dto.SchAdmin;
import cn.mono.purchase.pojo.Supper;


/**
 * @author nihao
 * @time 2021/4/16
 */
public interface SupperService {

    Message selectSchoolAdminByName(String name);

    Message selectSchool();

    Message resetAdminPwd(int id);

    Message deleteAdmin(int id);

    Message infoUpdate(Supper supper);

    Message addSchAdmin(SchAdmin sa);

    Message selectSchoolByName(String name);
}
