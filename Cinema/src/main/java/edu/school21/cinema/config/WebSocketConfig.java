package edu.school21.cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
/*
*    EnableWebSocketMessageBroker - Add this annotation to an @Configuration
*    class to enable broker-backed messaging over WebSocket using
*    a higher-level messaging sub-protocol.
*/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    /*
    * MessageBrokerRegistry - A registry for configuring message broker options.
    * */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*
        *    Enable a simple message broker and cWebSocketMessageBrokerConfigureronfigure one or more prefixes
        *    to filter destinations targeting the broker (e.g. destinations prefixed with "/topic").
        *    Return SimpleBrokerRegistration
        **/
        config.enableSimpleBroker("/topic");

        /*
        *    Configure one or more prefixes to filter destinations targeting application
        *    annotated methods.
        *    For example destinations prefixed with "/app" may be processed by annotated methods
        *    while other destinations may target the message broker (e.g. "/topic", "/queue").
        *    When messages are processed, the matching prefix is removed from the
        *    destination in order to form the lookup path. This means annotations should not contain the destination prefix.
        *    Prefixes that do not have a trailing slash will have one automatically appended.
        *    Return MessageBrokerRegistry
        **/
        config.setApplicationDestinationPrefixes("/app");
    }

    /*
    * Register STOMP endpoints mapping each to a specific URL
    * and (optionally) enabling and configuring SockJS fallback options.
    * StompEndPointRegistry - A contract for registering STOMP over WebSocket endpoints.
    * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
        * Register a STOMP over WebSocket endpoint at the given mapping path.
        * */
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
                .nullDestMatcher().permitAll()
                .simpDestMatchers("/app/**").hasAnyRole("USER", "ADMIN")
                .simpSubscribeDestMatchers("/topic/" + "**").permitAll()
                .anyMessage().denyAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}
