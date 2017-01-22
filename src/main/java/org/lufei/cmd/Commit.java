package org.lufei.cmd;

import org.lufei.tool.Tool;

import java.io.File;
import java.util.List;

/**
 * Created by lufei on 17/1/17.
 */
public class Commit extends AbstractCmd {
    private static final String name = "commit";

    public Commit() {
    }

    public String cmd() {
        return name;
    }


    public String help() {
        return String.format("%s========\n" +
                "功能:提交当前目录下及一级子目录仓库下的修改\n" +
                "语法:gut %s -m <\"说明注释\">\n",name,name);
    }
}
