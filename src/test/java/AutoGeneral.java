import com.wiceflow.Proxy.extendOrPolymerization.A;
import com.wiceflow.util.StringUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BF
 * @date 2019/7/5 9:58
 */
public class AutoGeneral {


    public static void main(String[] args) throws Exception {
        AutoGeneral autoGeneral = new AutoGeneral();
        List<String> column = autoGeneral.getSomething("C:\\Users\\BF\\Desktop\\1.txt");
        List<String> filed = autoGeneral.getSomething("C:\\Users\\BF\\Desktop\\2.txt");
        List<String> commont = autoGeneral.getSomething("C:\\Users\\BF\\Desktop\\3.txt");
        List<String> name = autoGeneral.getSomething("C:\\Users\\BF\\Desktop\\4.txt");

        String a = "'";
        String b = " ";
        String c = ",";
        String d = "`";
        String isNull = "DEFAULT NULL";
        String notNull = "NOT NULL";
        String COMMENT = "COMMENT";
        String blank = "\r\n";
        StringBuilder sb = new StringBuilder("CREATE TABLE `driving_record_of_violation_taxi` (");

        int size = column.size();
        if (column.size() != filed.size() && column.size() != commont.size()){
            System.out.println("初始换变量失败");
            return;
        }
        for (int i = 0;i < size;i++){
            sb.append(blank).append(d).append(column.get(i)).append(d).append(b)
                    .append(filed.get(i)).append(b).append(isNull).append(b).append(COMMENT).append(b).append(a).append(name.get(i)).append(b).append(commont.get(i)).append(a).append(c);
        }
        sb.append(blank).append("PRIMARY KEY (`serial_number`)").append(blank).append(")").append("ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        System.out.println(sb.toString());
    }


    public List<String> getSomething(String filePath) throws Exception {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();
        String str = reader.readLine();
        while (str != null){
            list.add(str);
            str = reader.readLine();
        }
        return list;
    }
}
