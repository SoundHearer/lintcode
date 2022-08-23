package javaexpert.designpattern.factorymethod;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 21:30
 */
public class PythonCourseFactory implements ICourseFactory{
    @Override
    public ICourse create() {
        return new PyhonCourse();
    }
}
