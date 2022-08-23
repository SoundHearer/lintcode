package javaexpert.designpattern.factorymethod;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 16:03
 */
public class PyhonCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("录制Python课程");
    }
}
