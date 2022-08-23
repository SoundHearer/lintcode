package javaexpert.designpattern.factory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 16:04
 */
public class CourseFactory {
    public ICourse create(Class<? extends ICourse> clazz) {
//        if ("java".equals(name)) {
//            return new JavaCourse();
//        } else if ("python".equals(name)) {
//            return new PyhonCourse();
//        } else {
//            return null;
//        }
        try {
//            if (!(null == className || "".equals(className))) {
//                return (ICourse) Class.forName(className).newInstance();
//            }
            if (null != clazz) {
                return clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
