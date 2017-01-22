
## windows安装步骤

1. 下载`gut-1.0-SNAPSHOT.jar` 到新建的`C:\Users\Administrator\Documents\gut`中
2. 创建`gut.bat`
3. 设定环境变量,path上追加`C:\Users\Administrator\Documents\gut`
gut.bat文件基本格式为：

```
@echo off
java -cp C:\Users\Administrator\Documents\gut\gut-1.0-SNAPSHOT.jar org.lufei.GitTools %1 %2 %3 %4 %5 %6 %7 %8

```


## mac安装步骤

1. 下载`gut-1.0-SNAPSHOT.jar` 到新建的`~/gut/`中
2. 编辑用户配置文件`~/.bash_profile`,增加如下一行
3. `source ~/.bash_profile`,重启所有命令窗口

.bash_profile文件基本格式为：

```
alias gut=" java -cp /Users/lufei/work/git-tools/target/gut-1.0-SNAPSHOT.jar org.lufei.GitTools "
```

####命令
运行`gut`查看支持的命令

####使用帮助
gut
gut help clone

####clone wacai项目的所有分支包括branchList中的所有分支
`cd work`
`gut clone -p wacai http://110.249.162.18:8090/tfs/DefaultCollection/KLWK/_git/mvn-pom`

####查看mvn-pom及子目录仓库的所有状态
`cd mvn-pom`
`gut status`

####拉取更新mvn-pom及子目录仓库的所有状态
`cd mvn-pom`
`gut pull`

####推送mvn-pom及子目录仓库到远程
`cd mvn-pom`
`gut push`