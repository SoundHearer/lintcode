package javaexpert.designpattern.factory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 16:08
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
       CourseFactory factory = new CourseFactory();
//       ICourse course = factory.create("javaexpert.designpattern.factory.JavaCourse");
        ICourse course = factory.create(JavaCourse.class);
       course.record();
    }
}
