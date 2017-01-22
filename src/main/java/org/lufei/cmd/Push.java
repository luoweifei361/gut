package org.lufei.cmd;

/**
 * Created by lufei on 17/1/17.
 */
public class Push extends AbstractCmd {
    public static final String name = "push";


    public String cmd() {
        return name;
    }

    public String toString() {
        return name;
    }

    public String help() {
        return String.format("%s========\n" +
                "功能:推送当前目录及一级子目录仓库变动到远程\n" +
                "语法:gut %s;\n",name,name);
    }
}
