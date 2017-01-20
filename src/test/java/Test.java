import org.lufei.IoKit;
import org.lufei.tool.Tool;

import java.io.*;

/**
 * Created by lufei on 17/1/17.
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        String log = Tool.call(new File("/Users/lufei/work/mvn-pom"), "===", "git" ,"clone","-b","wacai",
//                "http://110.249.162.18:8090/tfs/DefaultCollection/KLWK/_git/user-web");
//        System.out.println(log);

        ProcessBuilder processBuilder = new ProcessBuilder( "git" ,"clone","-b","wacai", "http://110.249.162.18:8090/tfs/DefaultCollection/KLWK/_git/user-web");
        File workDir = new File("/Users/lufei/work/mvn-pom");
        processBuilder.directory(workDir);
        System.out.println(String.format("==============%s===================", new Object[]{workDir.getAbsoluteFile()}));
        Process process = processBuilder.start();
//        int retCode = process.waitFor();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        InputStream outIs = process.getInputStream();
        InputStream errIs = process.getErrorStream();
        BufferedReader reader =new BufferedReader(new InputStreamReader(outIs));
        String line=null;
        while ((line=reader.readLine())!=null){
            System.out.println(line);
        }
//        String log;
//        try {
//            IoKit.copyStream(outIs, bos, (IoKit.CopyListener) null);
//            IoKit.copyStream(errIs, bos, (IoKit.CopyListener) null);
//            log = new String(bos.toByteArray());
//        } finally {
//            IoKit.closeSilently(outIs);
//            IoKit.closeSilently(errIs);
//        }

//        System.out.print(log);

    }
}
