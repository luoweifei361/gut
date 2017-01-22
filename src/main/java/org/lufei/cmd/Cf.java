package org.lufei.cmd;


import org.lufei.tool.Tool;

import java.io.File;
import java.util.List;

/**
 * Created by lufei on 17/1/17.
 */
public class Cf extends AbstractCmd {
    private static final String name = "new";

    public String cmd() {
        return name;
    }

    @Override
    public void todo(String[] options) {
        super.todo(options);
    }

    private void cloneSubBranches(File workDir, String baseUrl) {
        List<String> branches = Tool.file2Line(workDir.getAbsolutePath() + File.separator + "branchList");

        for (String branch : branches) {
            //arg[0]=库名 arg[1]=分支名
            String[] args = branch.split(" ");
            String gitReps = args[0];
            String gitUrl = String.format("%s/%s", baseUrl, gitReps);
            try {
                //git clone -b master2 server_url .
                String branchName = args[1];
                Tool.call(workDir,workDir.getName(), "git", "checkout", "-b", branchName);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

        System.out.println("语法错误!");
        System.out.println(help());
    }
    public String help() {
        return String.format("%s========\n" +
                "功能:根据branchList仓库新建分支\n" +
                "语法1:gut %s -b branch\n",name,name);
    }

}
