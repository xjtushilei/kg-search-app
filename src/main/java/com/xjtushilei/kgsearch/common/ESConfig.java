package com.xjtushilei.kgsearch.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ESConfig {
    @Value("${es.host}")
    private String hostString;

    @Value("${es.port}")
    private int portInteger;


    public static String host;
    public static int port;

    @PostConstruct
    public void init() {
        host = this.hostString;
        port = this.portInteger;
    }


}
