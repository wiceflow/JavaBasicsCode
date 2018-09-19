package com.wiceflow.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author iceflow
 * @date 2018/8/15
 *      代码自动生成类
 */
public class AutomaticCodeGenerationUtil {

    public static void main(String[] args) {
        AutomaticCodeGenerationUtil a = new AutomaticCodeGenerationUtil();

        Map<String,List<String>> map = new HashMap<>(3);
        List<String> methodTypes = new ArrayList<String>(3);
        String[] strings1 = new String[]{"String"};
        methodTypes.addAll(Arrays.asList(strings1));
        List<String> fileds = new ArrayList<>(3);
        String[] strings2 = new String[]{"instructionsId"};
        fileds.addAll(Arrays.asList(strings2));
        map.put("methodTypes",methodTypes);
        map.put("fileds",fileds);
        a.CodeGeneration("cn.sibat.bus.emergency.common.model","instructionsOther",map,"E:\\");

    }



    public final void CodeGeneration(String packPath, String className, Map<String,List<String>> filedMethodTypes,String path){
        // 定义换行符 下面会频繁用到
        String rt = "\r\t";
        String rn = "\r\n";
        String t = "    ";
        String t2 = " ";
        StringBuilder methodStr = new StringBuilder();
        List<String> methodTypes = filedMethodTypes.get("methodTypes");
        List<String> fileds = filedMethodTypes.get("fileds");

        // 参数值
        for (int i = 0;i < methodTypes.size(); i++){
            String methodType = methodTypes.get(i);
            String filed = fileds.get(i);
            methodStr.append("private ").append(methodType).append(t2).append(filed).append(";").append(rt);
        }

        // 空多一行
        methodStr.append(rt);
        for (int i = 0;i < methodTypes.size();i++){
            String methodType = methodTypes.get(i);
            String filed = fileds.get(i);
//            // 设置id
//            if (i == 0){
//                // get方法
//                methodStr.append("@Id").append(rt);
//                methodStr.append("public ").append(methodType).append(t2).
//                        append("get").append(StringUtil.upperCase(filed)).append("()").append(t2).append("{").append(rt).
//                        append(t).append(t).append("return ").append(filed).append(";").append(rt).append("}").append(rt);
//            }else {
                // get方法
                methodStr.append("@Basic").append(rt);
                methodStr.append("public ").append(methodType).append(t2).
                        append("get").append(StringUtil.upperCase(filed)).append("()").append(t2).append("{").append(rt).
                        append(t).append(t).append("return ").append(filed).append(";").append(rt).append("}").append(rt);
//            }
            // set方法
            methodStr.append("public void ").append("set").append(StringUtil.upperCase(filed)).append("(").
                    append(methodType).append(t2).append(filed).append(")").append(t).append("{").append(rt).append(t + t).
                    append("this.").append(filed).append(t2).append("=").append(t2).append(filed).append(";")
                    .append(rt).append("}").append(rt);
        }


        String src = "package " + packPath + ";" + rt + rn +
                "@Entity" + rn + "@Table(name = \"" + StringUtil.humpTransformation(className) + "\")" + rn +
                "public class " + StringUtil.upperCase(className)+ "Entity" + " extends Pojo implements Serializable {" + rn + rt +
                    methodStr + rn +
                "}";
        path = path + StringUtil.upperCase(className) + "Entity.java";
        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(src);
            fw.flush();  // 强制刷新
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
