package netty.decoder;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * CombinedChannelDuplexHandler<I,O>
 * 通过该解码器和编码器实现参数化 CombinedByteCharCodec
 */
public class CombinedByteCharCodec extends
        CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

}
