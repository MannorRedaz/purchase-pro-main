package cn.mono.purchase.util;

public class PageUtil {
int start=0;
int last=0;
int count=5;
public PageUtil(int start, int last, int count) {
	super();
	this.start = start;
	this.last = last;
	this.count = count;
}
public PageUtil() {
	super();
	// TODO Auto-generated constructor stub
}
public int getStart() {
	return start;
}
public void setStart(int start) {
	this.start = start;
}
public int getLast() {
	return last;
}
public void setLast(int last) {
	this.last = last;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public void calculateLast(int total) {
	if (total/count==0) {
		last=total-count;
	}else {
		last=total-total%count;
	}
}
@Override
public String toString() {
	return "PageUtil [start=" + start + ", last=" + last + ", count=" + count + "]";
}
}
