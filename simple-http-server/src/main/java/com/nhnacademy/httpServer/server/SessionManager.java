package com.nhnacademy.httpServer.server;

import com.nhnacademy.httpServer.domain.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private static final SessionManager sessionManager = new SessionManager();
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    private SessionManager() {}

    public static SessionManager getInstance() {
        return sessionManager;
    }

    public Session getSession(String sessionID) {
        return sessions.get(sessionID);
    }

    public void putSession(String sessionID, Session session) {
        sessions.put(sessionID, session);
    }

    public void removeSession(String sessionID) {
        sessions.remove(sessionID);
    }

    public boolean isValidSession(String sessionID) {
        return sessions.containsKey(sessionID);
    }
}
