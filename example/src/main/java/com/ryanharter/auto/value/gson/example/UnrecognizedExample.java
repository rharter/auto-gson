package com.ryanharter.auto.value.gson.example;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Map;

@AutoValue
public abstract class UnrecognizedExample {

  public abstract int knownField();

  public static UnrecognizedExample.Builder builder() {
    return new AutoValue_UnrecognizedExample.Builder();
  }

  public static TypeAdapter<UnrecognizedExample> typeAdapter(Gson gson) {
    return new AutoValue_UnrecognizedExample.GsonTypeAdapter(gson);
  }

  abstract Map<String, String> unrecognized();

  @AutoValue.Builder
  public static abstract class Builder {
    public abstract Builder knownField(int value);
    abstract Builder unrecognized(Map<String, String> value);
    public abstract UnrecognizedExample build();
  }
}
