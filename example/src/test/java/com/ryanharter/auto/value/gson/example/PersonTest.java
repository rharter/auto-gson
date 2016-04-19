package com.ryanharter.auto.value.gson.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ryanharter.auto.value.gson.annotations.AutoTypeAdapterFactory;
import org.junit.Assert;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class PersonTest {
    @Test
    public void testGson() throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new AutoTypeAdapterFactory())
                .create();
        Person person = Person.builder()
                .name("Piasy")
                .gender(1)
                .age(23)
                .build();
        String json = "{\"name\":\"Piasy\",\"age\":23,\"gender\":1}";

        String toJson = gson.toJson(person, Person.class);
        JSONAssert.assertEquals(json, toJson, false);

        Person fromJson = gson.fromJson(json, Person.class);
        Assert.assertEquals("Piasy", fromJson.name());
        Assert.assertEquals(23, fromJson.age());
        Assert.assertEquals(1, fromJson.gender());
    }
}
