package org.lufei.cmd;


/**
 * Created by lufei on 17/1/17.
 */
public class Reset extends AbstractCmd {

    public static final String name = "reset";

    public String cmd() {
        return name;
    }

    public String toString() {
        return name;
    }

    public String help() {
        return String.format("%s========\n" +
                "功能:还原当前目录及子目录下仓库改动,等价于使用git reset --hard命令\n" +
                "语法1:gut %s --hard;\n",name,name);
    }
}
