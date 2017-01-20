package org.lufei.cmd;

import org.lufei.tool.Tool;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lufei on 17/1/17.
 */
public class Clone implements Cmd {
    String name = "clone";

    @Override
    public String cmd() {
        return name;
    }

    @Override
    public void todo(String[] options) {
        String debugPath = System.getProperty("debug.path");
        String workdir = debugPath != null ? debugPath : ".";
        File dir = new File(workdir);

        // gut clone(0) -p(1) wacai(2) http://110.249.162.18:8090/tfs/DefaultCollection/KLWK/_git/mvn-pom(3)
        if (Arrays.asList(options).contains("-p")) {
            if (options.length < 4) {
                System.out.println("参数太少,语法错误!");
                System.out.println(help());
                return;
            }
            for (int i = 0; i < options.length; i++) {

                if("-p".equals(options[i])){
                    options[i]="-b";
                }
            }

            try {
//                String[] args = new String[options.length - 1];
//                System.arraycopy(options, 2, args, 1, options.length - 2);
                //拉顶层分支
//                args[0]="clone";
                String log = call(options);
                if (log.contains("Cloning into")) {
                    //http://xxx/tfs/DefaultCollection/KLWK/_git/mvn-pom
                    String mvnPomGit = options[3];
                    String subGitDir = mvnPomGit.substring(mvnPomGit.lastIndexOf("/"));
                    File subDir = new File(dir.getAbsoluteFile() + File.separator + subGitDir);
                    String baseUrl = mvnPomGit.substring(0, mvnPomGit.lastIndexOf("/"));
                    cloneAndSwitch(subDir, baseUrl);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            return;
        }
        // gut clone(0) -b http://110.249.162.18:8090/tfs/DefaultCollection/KLWK/_git/(1) 在mvn-pom下执行
        if (Arrays.asList(options).contains("-b")) {
            //当前目录=mvn-pom
            cloneAndSwitch(dir, options[2]);
        }
        // gut clone(0) http://110.249.162.18:8090/tfs/DefaultCollection/KLWK/_git/(1) 在mvn-pom下执行
        clone(dir, options[1]);
    }

    private void cloneAndSwitch(File workDir, String baseUrl) {
        List<String> branches = Tool.file2Line(workDir.getAbsolutePath() + File.separator + "branchList");

        for (String branch : branches) {
            //arg[0]=库名 arg[1]=分支名
            String[] args = branch.split(" ");
            String gitReps = args[0];
            String gitUrl = String.format("%s/%s", baseUrl, gitReps);
            try {
                //git clone -b master2 server_url .
                String branchName = args[1];
                Tool.call(workDir, "git", "clone", "-b", branchName, gitUrl);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        System.out.println("语法错误!");
        System.out.println(help());
    }


    private void clone(File workDir, String baseUrl) {
        List<String> branches = Tool.file2Line(workDir.getAbsolutePath() + File.separator + "branchList");

        for (String branch : branches) {
            //arg[0]=库名 arg[1]=分支名
            String[] args = branch.split(" ");
            String gitReps = args[0];
            String gitUrl = String.format("%s/%s", baseUrl, gitReps);
            try {
                //git clone -b master2 server_url .
                String branchName = args[1];
                Tool.call(workDir, "git", "clone", gitUrl);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        System.out.println("语法错误!");
        System.out.println(help());
    }


    private static String call(String... option) {
        String debugPath = System.getProperty("debug.path");
        String path = debugPath != null ? debugPath : ".";
        String[] desc = new String[option.length + 1];
        desc[0] = "git";
        System.arraycopy(option, 0, desc, 1, option.length);
        try {
            return Tool.call(new File(path).getAbsoluteFile(), desc);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String help() {
        return "clone========\n" +
                "功能:clone仓库,参数在branchList文件提供中,格式为 [库名 分支名]如[web-run wacai]一行一个,\n 文件名中只提供简单仓库名,全库路径由 " +
                "'仓库基地址/文件名中的简单库名组成'\n" +
                "语法1:gut clone <仓库基地址>;\n" +
                "       在mvn-pom工程下执行,命令会读取branchList文件,并clone对应的子库,但不切换分支!\n" +
                "       例gut clone http://110.249.162.18:8090/tfs/DefaultCollection/KLWK/_git/\n" +
                "语法2:gut clone -b <仓库基地址>;\n" +
                "       与语法1的区别在于会切换分支,切换到branchList上配置的分支上!\n" +
                "       例gut clone -b wacai http://110.249.162.18:8090/tfs/DefaultCollection/KLWK/_git/\n" +
                "语法3:gut clone -p <顶层分支名> <顶层git仓库地址>; \n" +
                "       先clone顶层仓库,再解析顶层git仓库中的branchList信息并直接clone对应仓库并切到对应分支\n" +
                "       例 gut clone -p wacai http://110.249.162" +
                ".18:8090/tfs/DefaultCollection/KLWK/_git/mvn-pom\n";
    }
}
