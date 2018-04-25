package lyons.page;

import java.util.ArrayList;

import com.sun.java_cup.internal.runtime.Scanner;

import lyons.dao.SalesManDao;
import lyons.entity.SalesMan;
import lyons.tools.QueryPrint;
import lyons.tools.ScannerChoice;

/**
 * 操作售货员界面
 * @author lyons(weizq)
 */
public final class SalesManPage extends ScannerChoice {
    /**
     * 1.添加售货员界面 
     */
    public static void addSalesManPage() {
        System.out.println("\t正在执行添加售货员操作\n");

        System.out.println("\n添加售货员-名字");
        String sName = ScannerInfoString();

        System.out.println("\n添加售货员-密码");
        String sPasswd = ScannerInfoString();

        SalesMan salesMan = new SalesMan(sName, sPassWord);
        boolean bool = new SalesManDao().addSalesMan(salesMan);

        if (bool) {
            System.out.println("\n\t！您已经成功添加售货员到数据库");
        } else {
            System.out.println("添加售货员失败");
        }
        choiceSalesManNext("addSalesMan");
    }

    /**
     * 2.更改售货员界面
     */
    public static void updateSalesManPage() {
        System.out.println("\t正在执行更改售货员操作\n");
        System.out.println("请输入想要更改的售货员名字");
        String sName = ScannerInfoString();

        ArrayList<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
        if (salesManList.size() <= 0) {
            System.err.println("\t!!查无此人!!");
            choiceSalesManNext("updateSalesMan");
        } else {
            System.out.println("\t\t\t售货员信息\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");

            SalesMan salesMan = salesManList.get(0);
            System.out.println(
                    "\t" + salesMan.getSId() + "\t\t" + salesMan.getSName() + "\t\t" + salesMan.getSPassWord());
            System.out.println();

            System.out.println("\n--------请选择您要更改的内容\n");
            System.out.println("\t1.更改售货员-姓名");
            System.out.println("\t2.更改售货员-密码");
            do {
                String choice = ScannerInfoString();
                String regex = "[0-2]";
                if (choice.matches(regex)) {
                    int info = Integer.parseInt(choice);
                    switch (info) {
                    case 0:
                        MainPage.salesManManagementPage();
                        break;
                    case 1:
                        System.out.println("更改售货员-新姓名");
                        String sNewName = ScannerInfoString();

                        SalesMan salesManName = new SalesMan(salesMan.getSId(), sNewName, null);
                        boolean boolsName = new SalesManDao().updateSalesMan(1, salesManName);

                        if (boolsName) {
                            System.out.println("\n\t!!成功更新售货员名字至数据库!!\n");
                        } else {
                            System.err.println("\n\t!!更新售货员名字失败!!");
                        }
                        choiceSalesManNext("updateSalesMan");
                        break;
                    case 2:
                        System.out.println("更改售货员-密码");
                        String sNewPasswd = ScannerInfoString();

                        SalesMan salesManPasswd = new SalesMan(salesMan.getSId(), null, sNewPasswd);
                        boolean boolsPasswd = new SalesManDao().updateSalesMan(2, salesManPasswd);

                        if (boolsPasswd) {
                            System.out.println("\n\t!!成功更新售货员密码至数据库!!\n");
                        } else {
                            System.err.println("\n\t!!更新售货员密码失败!!");
                        }
                        choiceSalesManNext("updateSalesMan");
                        break;
                    default:
                        break;
                    }
                }
                System.out.println("\t！输入有误！");
                System.out.println("\n请选择选项，或者按 0 返回上一级菜单.");
            } while (1);
        }
    }

    /**
     * 3.删除售货员界面
     */
    public static void deleteSalesManPage() {
        System.out.println("\t正在执行 删除售货员 操作\n");
        System.out.println("请输入想要删除的售货员名字");
        String sName = ScannerInfoString();

        ArrayList<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
        if (salesManList.size() <= 0) {
            System.err.println("\t!!查无此人!!");
            choiceSalesManNext("deleteSalesMan");
        } else {
            System.out.println("\t\t\t删除售货员信息\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");

            for (int i = 0, length = salesManList.size(); i < length; i++) {
                SalesMan salesMan = salesManList.get(i);
                System.out.println(
                        "\t" + salesMan.getSId() + "\t\t" + salesMan.getSName() + "\t\t" + salesMan.getSPassWord());
                System.out.println();
            }
            do {
                System.out.println("\n确认删除该售货员:Y/N");
                String choice = ScannerInfoString();
                if ("y".equals(choice) || "Y".equals(choice)) {
                    boolean boolDeleteSalesMan = new SalesManDao().deleteSalesMan(sName);
                    if (boolDeleteSalesMan) {
                        System.out.println("\t!!已成功删除该售货员!!");
                    } else {
                        System.err.println("\t!!删除售货员失败!!");
                    }
                    choiceSalesManNext("deleteGoods");
                } else if ("n".equals(choice) || "N".equals(choice)) {
                    MainPage.salesManManagementPage();
                }
                System.err.println("\t!!输入有误，请重新输入");
            } while (1);
        }
    }

    /**
     * 4.查询售货员界面 
     */
    public static void querySalesManPage() {
        System.out.println("\t\t 正在执行查询售货员操作\n");
        System.out.println("要查询的售货员关键字");
        String sName = ScannerInfoString();

        ArrayList<SalesMan> salesManList = new SalesManDao().querySalesMan(sName);

        if (salesManList.size() <= 0) {
            System.err.println("\t!!没有人员符号查询条件!");
        } else {
            System.out.println("\t\t\t所有售货员列表\n\n");
            System.out.println("\t售货员编号\t\t售货员姓名\t\t售货员密码");

            for (int i = 0, length = salesManList.size(); i < length; i++) {
                SalesMan salesMan = salesManList.get(i);
                System.out.println(
                        "\t" + salesMan.getSId() + "\t\t" + salesMan.getSName() + "\t\t" + salesMan.getSPassWord());
                System.out.println();
            }
        }
        choiceSalesManNext("querySalesMan");
    }

    /**
     * 5.显示所有售货员界面
     */
    public static void displaySalesManPage() {
        ArrayList<SalesMan> salesManList = new SalesManDao().displaySalesMan();
        if (salesManList.size() <= 0) {
            System.err.println("\t!!售货员列表为空");
            MainPage.salesManManagementPage();
        } else {
            System.out.println("\t\t\t所有售货员列表\n\n");
            System.out.println("\t售货员列表\t\t售货员姓名\t\t售货员密码");

            for (int i = 0, length = salesManList.size(); i < length; i++) {
                SalesMan salesMan = salesManList.get(i);
                System.out.println(
                        "\t" + salesMan.getSId() + "\t\t" + salesMan.getSName() + "\t\t" + salesMan.getSPassWord());
            }
            do {
                System.out.println("\n\n输入 0 返回上一级菜单");
                String choice = ScannerInfoString();

                if ("0".equals(choice)) {
                    MainPage.salesManManagementPage();
                }
                System.out.println("\t输入有误!");
            } while (1);
        }
    }
}