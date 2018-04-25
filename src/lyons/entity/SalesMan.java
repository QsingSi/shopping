package lyons.entity;

/**
 * SalesMan 售货员实体类
 * @author lyons(weizq)
 */

public final class SalesMan {
    private int sId;
    private String sName;
    private String sPassWord;

    /**
     * 验证用户登录
     * @param sId,spassword
     */

    public SalesMan(int sId, String spassword) {
        this.sId = sId;
        this.sPassWord = spassword;
    }

    /**
     * 查询用户、更改用户密码
     * @param sId,sName,sPassWord
     */
    public SalesMan(int sId, String sName, String sPassWord) {
        this.sId = sId;
        this.sName = sName;
        this.sPassWord = sPassWord;
    }

    /**
     * 添加用户
     * @param NsName,sPassWord
     */

    public SalesMan(String NsName, String sPassWord) {
        this.sName = NsName;
        this.sPassWord = sPassWord;
    }

    //共有get=set方法
    public int getSId() {
        return sId;
    }

    public void setSId(int sId) {
        this.sId = sId;
    }

    public String getSName() {
        return sName;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public String getSPassWord() {
        return sPassWord;
    }

    public void setSPassWord(String sPassWord) {
        this.sPassWord = sPassWord;
    }
}