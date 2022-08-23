package javaexpert.designpattern.factory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 15:58
 */
public class JavaCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("录制Java课程");
    }
}
