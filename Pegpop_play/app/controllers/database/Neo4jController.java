package controllers.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import models.jdbc.server.Neo4JDAO;
import models.users.UserModel;
import org.json.JSONArray;
import org.json.JSONException;
import play.libs.Json;
import play.mvc.Controller;
import play.Logger;
import play.mvc.Result;
import org.json.JSONObject;
import play.mvc.BodyParser;

/**
 * Created by joanna on 28/12/14.
 */
public class Neo4jController extends Controller {

    private static Neo4JDAO service = new Neo4JDAO();;

    public Neo4jController(Neo4JDAO service) {
        this.service = service;
    }

    //Check is facebook id is in database
    public static Result getIsExistingUser(String id) {
        Logger.debug("in getIsExistingUser, id is " + id);
        JSONObject jsonQuery = new JSONObject();
        try {
            jsonQuery = new JSONObject(new Gson().toJson(service.searchFacebookID(id)));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return ok(jsonQuery.toString());
    }

    //Creates Pegpop user
    @BodyParser.Of(BodyParser.Json.class)
    public static Result postCreateNewUser() {
        JsonNode json = request().body().asJson();
        JSONObject jsonQuery = new JSONObject();
        String uid = json.findPath("uid").textValue();
        String firstname = json.findPath("firstname").textValue();
        String lastname = json.findPath("lastname").textValue();
        String username = json.findPath("username").textValue();
        String profilePicture = json.findPath("profilePicture").textValue();
        int privacy = json.findPath("privacy").asInt();
        int toc = json.findPath("toc").asInt();
        if(uid == null || firstname == null || lastname == null || username == null /*|| (privacy == 0 || privacy == 1)*/ || toc == 0) {
            return badRequest("Missing params");
        }
        else {
            try {
                UserModel user = new UserModel(uid, firstname, lastname, username, profilePicture, privacy, toc);
                jsonQuery = new JSONObject((new Gson().toJson(service.createUser(user))));
            }
            catch (JSONException e) {
                e.getStackTrace();
            }
            return ok(jsonQuery.toString());
        }
    }
}
