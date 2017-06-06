import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by yishuihan on 17-6-6.
 */
public class QAPairGenerate {
    public static void main(String[] args) throws  Exception{
        String src_file = "/home/badou/data/dialog/multidialog.data";
        BufferedReader br_src = new BufferedReader(new InputStreamReader(new FileInputStream(src_file),"utf-8"));
        String dest_file = "/home/badou/data/dialog/dialog_rm.bson";
        //PrintWriter pw_json = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dest_file),"utf-8"),true);
        //String dest_file = "/home/badou/data/dialog/dialog_rm.txt";
        PrintWriter pw_json = new PrintWriter(new OutputStreamWriter(new FileOutputStream(dest_file),"utf-8"),true);
        String br_line = "";
        ArrayList<String> tmp_dialog = new ArrayList<String>();
        QAPairBean qa_bean = null;
        int count = 0;
        int line_count = 0;
        while ((br_line = br_src.readLine()) != null) {

            if(br_line.equals("") && count > 0){

                for(int i = 0; i< count - 1; i++){
                    qa_bean = new QAPairBean();
                    qa_bean.setQ(tmp_dialog.get(i));
                    qa_bean.setA(tmp_dialog.get(i+1));
                    pw_json.println(JSON.toJSONString(qa_bean));

                }
                tmp_dialog.clear();
                count = 0;
                continue;
            }
            //System.out.println(br_line);
            br_line = br_line.replaceAll("\\[[\\u0391-\\uFFE5]+\\]","");
            //System.out.println(br_line);
            line_count++;
            tmp_dialog.add(br_line);
            count++;
            if(line_count % 1000 == 0)
                System.out.println("processed count =: " + line_count);
        }



    }
}
