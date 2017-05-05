package com.kalix.admin.state.itest;

import com.kalix.framework.core.rest.test.APIRequest;
import com.kalix.framework.core.rest.test.APIResponse;
import com.kalix.framework.core.rest.test.APITest;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.core.MediaType;

/**
 * Demo about how to use this framework
 * <p/>
 * Need store some some APIs configuration in a env.properties files
 *
 * @author Carl Ji
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClientBean extends APITest {
    //login cookie token

    private String base_url = getValue("base.uri");
    ;
    //insert entity id return by tag
    private static long id;
    //insert delete update status
    private boolean succeed;


    /**
     * test for add new Entity method
     */
    @Test
    public void test001_Add() {
        String payload = loadFile("client.add.json");
        String uri = base_url + getValue("client.post.uri");

        APIResponse response = setAuthHeader(APIRequest.POST(uri)).type(MediaType.APPLICATION_JSON_TYPE).body(payload)
                .invoke().assertStatus(200);
        String returnString = response.getBody(String.class);
        JSONObject jsonObject = new JSONObject(returnString);
        Assert.assertNotNull(jsonObject);
        id = jsonObject.getLong("tag");
        Assert.assertTrue(id > 0);
        succeed = jsonObject.getBoolean("success");
        Assert.assertTrue(succeed);
    }

    /**
     * test for getAll method
     */
    @Test
    public void test002_GETALL() {
        String uri = base_url + getValue("client.getall.uri");
        APIResponse response = setAuthHeader(APIRequest.GET(uri)).param("page", "1").param("limit", "10")
                .invoke().assertStatus(200);
        String returnString = response.getBody(String.class);
        JSONObject jsonObject = new JSONObject(returnString);
        Assert.assertNotNull(jsonObject);
    }

    /**
     * test for get a entity method
     */
    @Test
    public void test003_GET() {
        Assert.assertNotNull(id);
        String uri = String.format(base_url + getValue("client.get.uri"), id);
        APIResponse response = setAuthHeader(APIRequest.GET(uri))
                .invoke().assertStatus(200);
        String returnString = response.getBody(String.class);
        JSONObject jsonObject = new JSONObject(returnString);
        Assert.assertNotNull(jsonObject);
    }

    /**
     * test for update a entity method
     */
    @Test
    public void test004_PUT() {
        Assert.assertNotNull(id);
        String uri = String.format(base_url + getValue("client.put.uri"), id);
        //format data
        String payload = String.format(loadFile("client.update.json"), id);
        APIResponse response = setAuthHeader(APIRequest.PUT(uri)).type(MediaType.APPLICATION_JSON_TYPE).body(payload)
                .invoke().assertStatus(200);
        String returnString = response.getBody(String.class);
        JSONObject jsonObject = new JSONObject(returnString);
        Assert.assertNotNull(jsonObject);
        succeed = jsonObject.getBoolean("success");
        Assert.assertTrue(succeed);
    }

    /**
     * test for delete a entity method
     */
    @Test
    public void test005_DELETE() {
        String uri = String.format(base_url + getValue("client.delete.uri"), id);
        APIResponse response = setAuthHeader(APIRequest.DELETE(uri))
                .invoke().assertStatus(200);
        String returnString = response.getBody(String.class);
        JSONObject jsonObject = new JSONObject(returnString);
        Assert.assertNotNull(jsonObject);
        succeed = jsonObject.getBoolean("success");
        Assert.assertTrue(succeed);
    }

}
