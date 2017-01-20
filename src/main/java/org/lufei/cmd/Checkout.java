package org.lufei.cmd;


/**
 * Created by lufei on 17/1/17.
 */
public class Checkout extends AbstractCmd {
    private static final String name = "checkout";

    public String cmd() {
        return name;
    }

    public String help() {
        return String.format("%s========\n" +
                "功能:切换分支当前目录下及一级子目录仓库下分支到指定分支\n" +
                "语法1:gut %s -b branch\n",name,name);
    }

}
