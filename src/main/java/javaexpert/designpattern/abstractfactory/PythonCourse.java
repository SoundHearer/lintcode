package javaexpert.designpattern.abstractfactory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/23 0:36
 */
public class PythonCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("录制Python课程");
    }
}
