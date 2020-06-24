package com.rubenskj.security.loginlocation.security.auth;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.rubenskj.security.loginlocation.dtos.AuthDTO;
import com.rubenskj.security.loginlocation.exception.NotFoundException;
import com.rubenskj.security.loginlocation.model.Location;
import com.rubenskj.security.loginlocation.model.Session;
import com.rubenskj.security.loginlocation.model.SessionDetails;
import com.rubenskj.security.loginlocation.model.UserDetailsImpl;
import com.rubenskj.security.loginlocation.property.DbMaxMindProperty;
import com.rubenskj.security.loginlocation.repository.ISessionRepository;
import com.rubenskj.security.loginlocation.util.ParamsKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ua_parser.Client;
import ua_parser.Parser;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import static java.util.Objects.nonNull;

@Service
public class UuidProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(UuidProvider.class);

    private final ISessionRepository sessionRepository;
    private final DbMaxMindProperty dbMaxMindProperty;

    public UuidProvider(ISessionRepository sessionRepository, DbMaxMindProperty dbMaxMindProperty) {
        this.sessionRepository = sessionRepository;
        this.dbMaxMindProperty = dbMaxMindProperty;
    }

    public String getUuidFromHeader(HttpServletRequest request) {
        String auth = request.getHeader(ParamsKey.AUTHORIZATION);

        if (auth != null) {
            return auth.trim();
        }

        return null;
    }

    public Session getSessionByUuid(String uuid) {
        return this.sessionRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException("Session cannot be found with this uuid. UUID: " + uuid));
    }

    public AuthDTO generateSessionToUserAuth(Authentication authentication, HttpServletRequest request) {
        UserDetailsImpl userDetails = UserDetailsImpl.getInstanceByAuthentication(authentication);

        SessionDetails sessionDetails = this.getSessionDetailsByRequest(request);
        Location location = this.getLocationByRequest(request);

        Session session = new Session(userDetails.getUsername(), sessionDetails, location);

        session = this.sessionRepository.save(session);

        return new AuthDTO(session.getUuid(), session.getDateLoggedIn());
    }

    private SessionDetails getSessionDetailsByRequest(HttpServletRequest request) {
        String userAgent = this.getUserAgentHeader(request);

        try {
            Parser parser = new Parser();
            Client client = parser.parse(userAgent);

            return new SessionDetails(client.userAgent.family, this.returnOsFamilyWithVersion(client), this.returnDevice(client));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String returnOsFamilyWithVersion(Client client) {
        return client.os.family + " " + client.os.major;
    }

    private String returnDevice(Client client) {
        if (client.device.family == null) {
            return null;
        }

        if (client.device.family.equalsIgnoreCase("Other")) {
            return "Computer";
        }

        return client.device.family;
    }

    private String getUserAgentHeader(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }

    private Location getLocationByRequest(HttpServletRequest request) {
        String ipClient = this.extractIp(request);

        try {
            InetAddress ipAddress = InetAddress.getByName(ipClient);
            DatabaseReader databaseReader = this.getInstanceOfDatabaseReader();

            CityResponse cityResponse = databaseReader.city(ipAddress);

            return new Location(ipClient, cityResponse.getCountry().getName(), cityResponse.getCity().getName());
        } catch (Exception e) {
            LOGGER.error("Cannot find city from user by ip. Client IP: {}", ipClient);
            return null;
        }
    }

    private DatabaseReader getInstanceOfDatabaseReader() throws IOException {
        String dbLocation = this.dbMaxMindProperty.getDblocation();
        File database = new File(dbLocation);

        return new DatabaseReader.Builder(database).build();
    }

    public String extractIp(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request.getHeader("x-forwarded-for");

        if (nonNull(clientXForwardedForIp)) {
            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    private String parseXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }
}
