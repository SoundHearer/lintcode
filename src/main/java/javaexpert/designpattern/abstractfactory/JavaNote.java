package javaexpert.designpattern.abstractfactory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/23 0:32
 */
public class JavaNote implements INote {
    @Override
    public void edit() {
        System.out.println("编写Java笔记");
    }
}
