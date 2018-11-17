package com.netty.protocol;

import com.netty.protocol.serialize.Command;
import lombok.Data;

/**
 * @author WangGuanqian
 * @Date 2018/11/17 17:34
 */
@Data
public class LoginRequestPacket extends Packet {

    private Integer userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
