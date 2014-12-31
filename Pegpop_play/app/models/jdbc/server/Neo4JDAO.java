package models.jdbc.server;

import models.jdbc.executor.CypherExecutor;
import models.jdbc.executor.JdbcNeo4JCypherExecutor;
import models.users.UserModel;
import org.neo4j.helpers.collection.IteratorUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import play.Logger;

import static org.neo4j.helpers.collection.MapUtil.map;

/**
 * Created by joanna on 07/11/14.
 */
public class Neo4JDAO {

    private final CypherExecutor cypher;
    private final String server = "http://localhost:7474/db/data";

    public Neo4JDAO() {
        cypher = createCypherExecutor(server);
    }

    private CypherExecutor createCypherExecutor(String uri) {
        try {
            String auth = new URL(uri).getUserInfo();
            if (auth != null) {
                String[] parts = auth.split(":");
                return new JdbcNeo4JCypherExecutor(uri,parts[0],parts[1]);
            }
            return new JdbcNeo4JCypherExecutor(uri);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Neo4j-ServerURL " + uri);
        }
    }

    //Search for facebook id
    public Map<String, Object> searchFacebookID(String id) {
        if(id == null) {
            return Collections.emptyMap();
        }
        return IteratorUtil.singleOrNull(cypher.query(
                "MATCH (user{uid:{1}})\n" +
                        " RETURN user.uid as uid",
                map("1", id)
        ));
    }

    //Create a pegpop user
    public Map<String, Object> createUser(UserModel user) {
        //Do a check if user exists
        return IteratorUtil.singleOrNull(cypher.query(
                "MERGE (user:USER {uid:{1},\n"+
                                    "firstname:{2},\n" +
                                    "lastname:{3},\n" +
                                    "username:{4},\n" +
                                    "profilePicture:{5},\n" +
                                    "privacy:{6},\n" +
                                    "toc:{7}})" +
                "RETURN user",
                user.getUserMap()
        ));
    }

}
