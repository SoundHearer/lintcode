package javaexpert.designpattern.abstractfactory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/23 0:37
 */
public class PythonNote implements INote {
    @Override
    public void edit() {
        System.out.println("编写Python笔记");
    }
}
