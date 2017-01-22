package org.lufei;

import org.lufei.cmd.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Git 帮助
 */
public class GitTools {
    //默认指令
    static final String DEFAULT_HELP_CMD = "help";
    static volatile GitTools instance;
    /**
     * 命令
     */
    Map<String, Cmd> cmdMap = new ConcurrentHashMap<String, Cmd>();

    private GitTools() {
    }

    private static void init() {
        //注册 gut help
        GitTools.getInstance().register(new Help());
        GitTools.getInstance().register(new Status());
        GitTools.getInstance().register(new Add());
        GitTools.getInstance().register(new Commit());
        GitTools.getInstance().register(new Clone());
        GitTools.getInstance().register(new Reset());
        GitTools.getInstance().register(new Pull());
        GitTools.getInstance().register(new Push());
        GitTools.getInstance().register(new Checkout());
        GitTools.getInstance().register(new Branch());
        GitTools.getInstance().register(new Merge());
    }


    public static void main(String[] args) {
        init();
        //执行
        GitTools.getInstance().cmd(args);
    }

    public static GitTools getInstance() {
        if (instance == null) {
            synchronized (GitTools.class) {
                if (instance == null) {
                    instance = new GitTools();
                }
            }
        }
        return instance;
    }

    /**
     * 解析命令
     */
    void cmd(String[] args) {
        Cmd cmd = null;
        if (args.length > 0) {
            String name = args[0];
            cmd = cmdMap.get(name);
        }

        //尝试获取 默认
        if (cmd == null) {
            cmd = cmdMap.get(DEFAULT_HELP_CMD);
        }
//        System.out.println("解析命令:" + cmd.cmd());
        if (cmd == null) {
            throw new RuntimeException("不存在指令帮助模块");
        }
        cmd.todo(args);
    }

    /**
     * 注册
     */
    public void register(Cmd cmd) {
        if (cmd.cmd() == null) {
            throw new RuntimeException(String.format("命令没有名字: %s", cmd.cmd()));
        }
        if (cmdMap.containsKey(cmd.cmd())) {
            throw new RuntimeException(String.format("重复注册: %s", cmd.cmd()));
        } else {
            cmdMap.put(cmd.cmd(), cmd);
        }
    }

    /**
     * 获取所有的CMD
     */
    public List<Cmd> touch() {
        List<Cmd> ret = new ArrayList<Cmd>();
        for (String key : cmdMap.keySet()) {
            ret.add(cmdMap.get(key));
        }
        return ret;
    }

    public Cmd get(String cmdKey){
        return cmdMap.get(cmdKey);
    }

}
