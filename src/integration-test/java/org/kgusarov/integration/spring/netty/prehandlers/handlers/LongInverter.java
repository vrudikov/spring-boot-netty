package org.kgusarov.integration.spring.netty.prehandlers.handlers;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.kgusarov.integration.spring.netty.annotations.PreHandler;
import org.kgusarov.integration.spring.netty.etc.HandlerCallStack;
import org.springframework.beans.factory.annotation.Autowired;

@ChannelHandler.Sharable
@PreHandler(serverName = "server1", priority = 2)
public class LongInverter extends ChannelInboundHandlerAdapter {
    @Autowired
    private HandlerCallStack handlerCallStack;

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        handlerCallStack.add(getClass());

        final Long l = (Long) msg;
        ctx.fireChannelRead(-l);
    }
}
