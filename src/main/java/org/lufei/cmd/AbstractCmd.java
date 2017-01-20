package org.lufei.cmd;

import org.lufei.tool.Tool;

/**
 * Created by lufei on 17/1/18.
 */
abstract public class AbstractCmd implements Cmd {

    @Override
    public void todo(String[] options) {
//        System.out.println(String.format("参数:%s", Arrays.toString(options)));
        Tool.call(options);
    }

}
