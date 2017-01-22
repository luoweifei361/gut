package org.lufei.cmd;

import java.util.Iterator;
import java.util.List;

import org.lufei.GitTools;

/**
 * Created by lufei on 17/1/17.
 */
public class Help implements Cmd {
    final String name = "help";

    public Help() {
    }

    public String cmd() {
        return name;
    }

    public void todo(String[] option) {
        List<Cmd> cmdList = GitTools.getInstance().touch();

        if (option.length > 1) {
            Cmd cmd = GitTools.getInstance().get(option[1]);
            if (cmd == null) {
                System.out.println("没有此命令" + option[0]);
                return;
            }
            System.out.println(cmd.help());
        } else {
            for (Cmd cmd : cmdList) {
                System.out.println(cmd.help());
            }
        }


    }

    public String help() {
        return String.format("%s========\n" +
                "功能:帮助命令\n" +
                "语法:gut  :列出所有命令帮助\n" +
                "语法:gut %s <命令>:列表给定命令帮助\n ",name,name);
    }
}
