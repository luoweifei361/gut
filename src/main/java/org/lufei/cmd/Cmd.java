package org.lufei.cmd;

/**
 * Created by lufei on 17/1/17.
 */
public interface Cmd {
    String cmd();

    void todo(String[] options);

    String help();
}
