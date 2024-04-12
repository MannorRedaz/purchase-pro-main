package cn.mono.purchase.util;

import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class Logger {
	public void beforPrint(JoinPoint jp) {

		System.out.println(jp.getSignature().getName()+"被执行！\t传入的参数是"+Arrays.toString(jp.getArgs()));
	}
	public void afterruningPrint(JoinPoint jp,Object res) {
		System.out.println(jp.getSignature().getName()+"被执行完毕！\t执行的结果是的是："+res);
	}
	public void throwPrint(JoinPoint jp,Object tr) {
		System.out.println(jp.getSignature().getName()+"被执行，但是发生了异常！\t发生的异常是："+tr);
	}
	public void afterruning(JoinPoint jp) {
		System.out.println(jp.getSignature().getName()+"被执行结束！");
	}
	public void around(){

	}

}
