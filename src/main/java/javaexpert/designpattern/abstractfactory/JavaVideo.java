package javaexpert.designpattern.abstractfactory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/23 0:33
 */
public class JavaVideo implements IVideo {
    @Override
    public void record() {
        System.out.println("录制Java视频");
    }
}
