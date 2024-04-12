//package cn.mono.purchase.util;
//
//
//import cn.mono.ces.admin.dal.entity.SystemAdmin;
//
///**
// * @author : 秋雨
// * @since : 2024/04/08
// * Description  :
// */
//public class ThreadLocalUtil {
//    //提供ThreadLocal对象,
//    private static final ThreadLocal<SystemAdmin> THREAD_LOCAL = new ThreadLocal<>();
//
//    //根据键获取值
//    public static SystemAdmin get() {
//        return THREAD_LOCAL.get();
//    }
//
//    //存储键值对
//    public static void set(SystemAdmin userDO) {
//        THREAD_LOCAL.set(userDO);
//    }
//
//
//    //清除ThreadLocal 防止内存泄漏
//    public static void remove() {
//        THREAD_LOCAL.remove();
//    }
//}