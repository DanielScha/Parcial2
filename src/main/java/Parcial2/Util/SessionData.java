package Parcial2.Util;

import Parcial2.Entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

/**
 * Created by yo on 8/6/2017.
 */
@Service
public class SessionData {
    final static Logger logger = Logger.getLogger(SessionData.class);
    private HashMap<String, AuthenticationData> sessionData;

    @Value("${session.expiration}")
    private int expirationTime;

    @Autowired
    protected AuthenticationData aData;


    public SessionData() {
        sessionData = new HashMap<String, AuthenticationData>();
    }

    public String addSession(Usuario usuario) {
        String sessionId = UUID.randomUUID().toString();
        aData.setUsuario(usuario);
        aData.setLastAction(new DateTime());
        sessionData.put(sessionId, aData);
        return sessionId;
    }


    public void removeSession(String sessionId) {
        sessionData.remove(sessionId);
    }

    public AuthenticationData getSession(String sessionId) {
        AuthenticationData aData = sessionData.get(sessionId);
        if (aData != null) {
            return aData;
        } else {
            return null;
        }
    }

    @Scheduled(fixedRate = 5000)
    public void checkSessions() {
        System.out.println("Checking sessions");
        Set<String> sessionsId = sessionData.keySet();
        for (String sessionId : sessionsId) {
            AuthenticationData aData = sessionData.get(sessionId);
            if (aData.getLastAction().plusSeconds(expirationTime).isBefore(System.currentTimeMillis())) {
                System.out.println("Deleting sessionId = " + sessionId);
                sessionData.remove(sessionId);
            }
        }
    }

}
