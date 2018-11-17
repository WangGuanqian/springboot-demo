package com.netty.protocol;

import com.netty.protocol.serialize.JSONSerializer;
import com.netty.protocol.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.netty.protocol.serialize.Command.LOGIN_REQUEST;

/**
 * @author WangGuanqian
 * @Date 2018/11/17 17:44
 */
public class PacketCodec {

    private static final int MAGIC_NUMBER = 0x12345678;

    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    public ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        byte[] bytes = Serializer.DEFAULT_SERIALIZER.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT_SERIALIZER.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeByte(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    public Packet decode(ByteBuf byteBuf) {

        // 跳过MAGIC_NUMBER
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 算法
        byte serializerAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);


        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte command) {
        return serializerMap.get(command);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}
