package com.netty.protocol.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author WangGuanqian
 * @Date 2018/11/17 17:39
 */
public class JSONSerializer implements Serializer {


    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
