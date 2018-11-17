package com.netty.protocol;

import lombok.Data;

/**
 * @author WangGuanqian
 * @Date 2018/11/17 17:31
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();
}
