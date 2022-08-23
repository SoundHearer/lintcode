package javaexpert.designpattern.abstractfactory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/23 0:39
 */
public class PythonVideo implements IVideo {
    @Override
    public void record() {
        System.out.println("录制Python视频");
    }
}
