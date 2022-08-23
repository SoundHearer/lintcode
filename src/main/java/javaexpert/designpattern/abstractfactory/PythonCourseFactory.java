package javaexpert.designpattern.abstractfactory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/23 0:29
 */
public class PythonCourseFactory extends CourseFactory {
    public INote createNote() {
        super.init();
        return new PythonNote();
    }


    public IVideo createVideo() {
        super.init();
        return new PythonVideo();
    }
}
