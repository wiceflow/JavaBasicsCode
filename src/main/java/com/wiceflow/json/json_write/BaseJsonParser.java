package com.wiceflow.json.json_write;
/**
 * json解析器接口
 * @author BF
 */
public interface BaseJsonParser {

    Entry parse(String json);
    
    /**
     * 键值对链表节点
     * 
     * @author sequoiadb
     *
     */
    public static final class Entry {
        private String key;
        private Object value;
        private Entry next;
        public String getKey() {
            return key;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public Object getValue() {
            return value;
        }
        public void setValue(Object value) {
            this.value = value;
        }
        public Entry getNext() {
            return next;
        }
        public void setNext(Entry next) {
            this.next = next;
        }
        
        /**
         * 获取String形式的value值
         * @param obj [Object]待转换对象
         * @param buf [StringBuilder]转换后字符串存储到该StringBuilder中
         * @return
         */
        private void getString(StringBuilder buf, Object obj) {
            Class<?> cls = obj.getClass();
            if (cls == Boolean.class) {         // Boolean
                buf.append((Boolean) obj ? "true" : "false");
            } else if (cls == Entry.class) {    // Entry
                buf.append(((Entry) obj).toString());
            } else if (cls == Object[].class) { // Object[]
                buf.append(appendList((Object[]) obj));
            } else if (cls == String.class) {   // String
                buf.append('\"')
                   .append(obj)
                   .append('\"');
            } else {                            // other
                buf.append(obj.toString());
            }
        }
        
        /**
         * 获取数组value对应的字符串格式
         * @param list [Object[]]数组value
         * @return
         */
        private String appendList(Object[] list) {
            StringBuilder buf = new StringBuilder("[");
            boolean isFirst = true;
            for(Object obj : list) {
                if(!isFirst) {
                    buf.append(",");
                } else {
                    isFirst = false;
                }
                this.getString(buf, obj);
            }
            buf.append("]");
            return buf.toString();
        }
        
        /**
         * 获取Entry节点的字符串格式
         */
        public String toString() {
            if (this.key == null) {
                return "{}";
            } else {
                StringBuilder buf = new StringBuilder("{");
                Entry entry = this;
                boolean isFirst = true;
                while (entry != null) {
                    if (!isFirst) {
                        buf.append(",");
                    } else {
                        isFirst = false;
                    }
                    buf.append('\"')
                       .append(entry.getKey())
                       .append("\":");
                    if (this.value == null) {
                        buf.append("null");
                    } else {
                        this.getString(buf, entry.getValue());
                    }
                    entry = entry.getNext();
                }
                return buf.append("}").toString();
            }
        }
        
    }
    
}
