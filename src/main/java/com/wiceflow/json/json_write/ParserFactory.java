package com.wiceflow.json.json_write;

/**
 * 解析器工厂类
 * @author BF
 */
public class ParserFactory {
    
    /**
     * 获取一个JSON解析器
     * @return
     */
    public static BaseJsonParser getJsonParser() {
        return InnerJsonParserFactory.getParser();
    }
    
    // 通过内部静态类实现线程安全的懒汉式单例Json解析器
    private static final class InnerJsonParserFactory {
        private final static BaseJsonParser PARSER;
        static {
            PARSER = new DefaultJsonParser();
        }
        public static BaseJsonParser getParser() {
            return PARSER;
        }
    }
    
}
