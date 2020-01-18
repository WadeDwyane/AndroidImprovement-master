package demo.mvp.com.mvpdemo2.login;

import demo.mvp.com.mvpdemo2.base.BaseEntity;

public class UserInfo extends BaseEntity {

    String company;

    String name;

    public UserInfo(String company, String name) {
        this.company = company;
        this.name = name;
    }

    public UserInfo() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
