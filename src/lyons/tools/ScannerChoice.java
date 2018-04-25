package lyons.tools;

import java.util.Scanner;

import lyons.page.GoodsPage;
import lyons.page.MainPage;
import lyons.page.SalesManPage;

/**
 * 1.各种完成操作后的 选择下一步
 * 2.界面选择操作
 * @author lyons(weizq)
 */

public class ScannerChoice {
    /**
     * @return double
     */
    public static double ScannerInfo() {
        double num = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("保留小数点后两位，请输入:");
            String info = sc.next();
            String regex = "(([1-9][0-9]*)\\.([0-9]{2}))\\.([0-9]{2})"; //保留两位小数
            if (info.matches(regex)) {
                num = Double.parseDouble(info);
            } else {
                System.out.println("! 输入有误！");
                continue;
            }
            break;
        } while (1);
        return num;
    }

    /**
     * @return int 键盘获取商品数量
     */
    public static int ScannerNum() {
        int num = 0;
        String regex = "([1-9])|([1-9][0-9]+)"; //商品数量
        do {
            Scanner sc = new Scanner(System.in);
            system.out.println("请输入：");
            String nums = sc.next();

            if (nums.matches(regex)) {
                num = Integer.parseInt(nums);
            } else {
                System.err.println("!输入有误！");
                continue;
            }
            break;
        } while (1);
        return num;
    }

    /**
     * @return String 获取键盘输入
     */
    public static String ScannerInfoString() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入：");
        return sc.next();
    }

    /**
     * 获取用户-更改完商品-下一步
     * 获取用户-删除完商品-下一步
     * 获取用户-添加完商品-下一步
     * @param 调用者
     */
    public static void changeInfoNext(String oper) {
        do {
            System.out.println("是否继续进行-当前操作:(Y/N)");
            String choice = ScannerChoice.ScannerInfoString();

            //在java中，Equals比较的是值，==比较的是地址
            if ("y".equals(choice) || "Y".equals(choice)) {
                //下面的嵌套if-else是让用户选择继续操作当前步骤所跳转到指定页面。（因为不同函数屌用，跳转的指定函数不同）
                if ("updateGoodsPage".equals(oper)) {
                    GoodsPage.updateGoodsPage();
                } else if ("deleteGoodsPage".equals(oper)) {
                    GoodsPage.deleteGoodsPage();
                } else if ("addGoodsPage".equals(oper)) {
                    GoodsPage.addGoodsPage();
                }
            } else if ("N".equals(choice) || "n".equals(choice)) {
                MainPage.MaintenancePage();
            }
            System.out.println("\n输入有误！请重新输入.");
        } while (1);
    }

 }

 /**
  * 获取用户-更改完售货员-下一步
  * 获取用户-添加完售货员-下一步
  * 获取用户-查询完售货员-下一步
  * 获取用户-删除完售货员-下一步
  * @param 调用者
  */
  public static void choiceSalesManNext(String oper){
      do{
          System.out.println("是否继续进行-当前操作:(Y/N)");
          String choice = ScannerChoice.ScannerInfoString();

          if("y".equals(choice)||"Y".equals(choice)){
              if("updateSalesMan".equals(oper)){
                  SalesManPage.updateSalesManPage();
              }else if("deleteSalesMan".equals(oper)){
                  SalesManPage.deleteSalesManPage();
              }else if("addSalesMan".equals(oper)){
                  SalesManPage.addSalesManPage();
              }else if("querySalesMan".equals(oper)){
                  SalesManPage.querySalesManPage();
              }
          }else if("N".equals(choice)||"n".equals(choice)){
              MainPage.salesManManagementPage();
          }
          System.out.println("\t输入有误!");
      }while(1);
  }