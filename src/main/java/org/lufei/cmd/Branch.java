package org.lufei.cmd;

/**
 * Created by lufei on 17/1/17.
 */
public class Branch extends AbstractCmd {
    public static final String name = "branch";


    public String cmd() {
        return name;
    }

    public String toString() {
        return name;
    }

    public String help() {
        return String.format("%s========\n" +
                "功能:显示当前目录下及一级子目录仓库下的仓库分支\n" +
                "语法1:gut %s 显示当前目录及一级子目录下\n",name,name);
    }
}
