package com.example.crucialfunctiontest.model.entity;

/**
 * @Author xushupeng
 * @Date 2025-01-04 12:55
 */

import lombok.Data;

import javax.websocket.Session;

@Data
public class SocketDomain {
    private Session session;

    private String uri;
}