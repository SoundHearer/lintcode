package javaexpert.designpattern.abstractfactory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/23 0:23
 */
public class JavaCourseFactory extends CourseFactory {
    public INote createNote() {
        super.init();
        return new JavaNote();
    }

    public IVideo createVideo() {
        super.init();
        return new JavaVideo();
    }
}
