package com.netty.protocol.serialize;

/**
 * @author WangGuanqian
 * @Date 2018/11/17 17:36
 */
public interface Serializer {
    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

    Serializer DEFAULT_SERIALIZER = new JSONSerializer();
}
