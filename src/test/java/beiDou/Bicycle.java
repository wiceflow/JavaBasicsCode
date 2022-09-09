package beiDou;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author BF
 * @date 2022/9/9
 */
public class Bicycle {

    public static void main(String[] args) throws Exception {
        Bicycle autoGeneral = new Bicycle();
        List<String> column = autoGeneral.getSomething("E:\\22.TOCC\\公共交通监测一体化\\公共交通监测一体化\\共享单车数据\\202109-202110数据\\20211001.csv_");
        List<String> result = new ArrayList<>();
        for (String str : column) {
            result.add(str.replaceAll("\t",","));
        }
        System.out.println(column.get(1));
    }


    public List<String> getSomething(String filePath) throws Exception {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();
        String str = reader.readLine();
        while (str != null) {
            list.add(str);
            str = reader.readLine();
        }
        return list;
    }
}
/**
 * ???§ID	????ID	??·??±??	?????±??	????????	????????	????????	????????
 * daaa3b6e1147e1177ea167be9a3a86f4	9030093603	2021-09-30 23:45:25	2021-09-30 23:55:48	113.8224889	22.7362275	113.8216683712	22.7314597846
 * 12713055e39993028d333900eb510d06	9080133915	2021-09-30 23:14:50	2021-09-30 23:55:54	113.8656538	22.812305	113.8625660162	22.801223006
 */
