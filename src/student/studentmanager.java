package student;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class studentmanager {
    public static void main(String args[]) {
        //创建集合对象，用于存储集合数据
        ArrayList<student> array = new ArrayList<student>();

        /*
         * 用输出语句完成主界面的编写
         * 用Scanner实现键盘录入数据
         * 用switch语句完成操作的选择
         * 用循环完成再次回到主界面
         */
        while (true) {
            System.out.println("********欢迎来到学生管理系统********");
            System.out.println("1.添加入学");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查看所有学生");
            System.out.println("5.退出系统");
            System.out.println("请输入你的选择");

            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            switch (line) {
                case "1":
                    addstudent(array);
                    break;
                case "2":
                    deletestudent(array);
                    break;
                case "3":
                    updatastudent(array);
                    break;
                case "4":
                    findallstudent(array);
                    break;
                case "5":
                    System.out.println("退出成功");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误");
                    break;
            }
        }
    }

    public static void addstudent(ArrayList<student> array) {
        //键盘录入学生信息
        Scanner sc = new Scanner(System.in);

        //为了sid在while循环外可以被访问到
        String sid;

        //调用方法来检查学号是否重复
        //while循环可以使在学号已重复的情况下无需返回主界面
        while (true) {
            System.out.println("请输入学号");
            sid = sc.nextLine();

            boolean flag = isused(array, sid);
            if (flag) {
                System.out.println("该学号已存在，请重新录入");
            } else {
                break;//跳出循环执行下一步
            }
        }

        //方法内部实现学号重复检查
/*        for(int i=0;i<array.size();i++){
            student s= array.get(i);
            if(s.getSid().equals(sid)){
                System.out.println("该学号已存在，请重新录入");
                return;
            }
        }*/


        System.out.println("请输入姓名");
        String name = sc.nextLine();
        System.out.println("请输入年龄");
        String age = sc.nextLine();
        System.out.println("请输入居住地");
        String address = sc.nextLine();

        //创建学生对象，把键盘录入信息赋值给学生对象的成员变量
        student s = new student();//创建一个名为s的学生对象
        s.setSid(sid);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);

        //将学生对象添加到集合中
        array.add(s);
        System.out.println("添加学生成功");

    }

    public static boolean isused(ArrayList<student> array, String sid) {
        boolean flag = false;
        for (int i = 0; i < array.size(); i++) {
            student s = array.get(i);
            if (s.getSid().equals(sid)) {
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    public static void findallstudent(ArrayList<student> array) {
        if (array.size() == 0) {
            System.out.println("暂无信息，请先添加信息");
            return;
        }
        System.out.println("学号\t\t姓名\t\t年龄\t\t居住地");
        for (int i = 0; i < array.size(); i++) {
            student s = array.get(i);
            System.out.println(s.getSid() + "\t\t" + s.getName() + "\t\t" + s.getAge() + "\t\t" + s.getAddress());
        }
    }

    public static void deletestudent(ArrayList<student> array) {
        if (array.size() == 0) {
            System.out.println("暂无学生信息");
            return;
        }
        System.out.println("请输入要删除学生的学号");
        Scanner sc = new Scanner(System.in);
        String sid = sc.nextLine();
        int index = -1;
        for (int i = 0; i < array.size(); i++) {
            student s = array.get(i);
            if (s.getSid().equals(sid)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("该学号不存在");
            return;
        } else {
            array.remove(index);
            System.out.println("删除学生成功");
            return;
        }
    }

    public static void updatastudent(ArrayList<student> array) {
        if (array.size() == 0) {
            System.out.println("暂无学生数据");
            return;
        }

        System.out.println("请输入要修改学生的学号");
        Scanner sc = new Scanner(System.in);
        String sid = sc.nextLine();

        int index = -1;
        for (int i = 0; i < array.size(); i++) {
            student student = array.get(i);
            if (student.getSid().equals(sid)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("暂无该学生");
            return;
        } else
            System.out.println("请输入学生的薪姓名");
        String name = sc.nextLine();
        System.out.println("请输入学生的薪年龄");
        String age = sc.nextLine();
        System.out.println("请输入学生的薪住址");
        String address = sc.nextLine();

        student s = new student();
        s.setSid(sid);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);

        for (int i = 0; i < array.size(); i++) {
            student student = array.get(i);
            if (student.getSid().equals(sid)) {
                array.set(i, s);
                break;
            }
        }
        System.out.println("修改学生成功");
    }
}
