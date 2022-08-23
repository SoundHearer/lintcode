package javaexpert.designpattern.factorymethod;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 16:08
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
       ICourseFactory factory = new PythonCourseFactory();
//       ICourse course = factory.create("javaexpert.designpattern.factory.JavaCourse");
        ICourse course = factory.create();
       course.record();

        factory = new JavaCourseFactory();
        course = factory.create();
        course.record();
    }
}
