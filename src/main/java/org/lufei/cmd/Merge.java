package org.lufei.cmd;

/**
 * Created by lufei on 17/1/17.
 */
public class Merge extends AbstractCmd {

    public static final String name = "merge";


    public String cmd() {
        return name;
    }

    public String toString() {
        return name;
    }

    public String help() {
        return String.format("%s========\n" +
                "功能:合并某分支到当前分支,执行当前目录下及一级子目录仓库下的仓库分支\n" +
                "语法:gut %s <被合并分支名>\n",name,name);
    }
}
