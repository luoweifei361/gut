package org.lufei.cmd;


/**
 * Created by lufei on 17/1/17.
 */
public class Stash extends AbstractCmd {
    private static final String name = "stash";

    public Stash() {
    }

    public String cmd() {
        return name;
    }

    public String help() {
        return String.format("%s========\n" +
                "功能:stash给定目录下及一级子目录仓库下的修改\n" +
                "语法1:gut %s -A\n",name,name);
    }

}
