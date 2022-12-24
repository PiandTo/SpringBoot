package edu.school21.cinema.models;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HostDetails {
        @Value("${server.port}")
        private String serverPort;
        @Value("${host}")
        private String host;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getServerPort() {
            return serverPort;
        }

        public void setServerPort(String serverPort) {
            this.serverPort = serverPort;
        }
}
