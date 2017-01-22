package org.lufei.cmd;

/**
 * Created by lufei on 17/1/17.
 */
public class Pull extends AbstractCmd {
    public static final String name = "pull";


    public String cmd() {
        return name;
    }

    public String toString() {
        return name;
    }

    public String help() {
        return String.format("%s========\n" +
                "功能:拉取当前目录及一级子目录仓库的远程变动\n" +
                "语法:gut %s;\n ",name,name) ;
    }
}
