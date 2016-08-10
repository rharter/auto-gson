package com.ryanharter.auto.value.gson;

import com.google.auto.value.processor.AutoValueProcessor;
import com.google.common.collect.ImmutableSet;
import com.google.testing.compile.JavaFileObjects;
import java.util.Arrays;
import javax.tools.JavaFileObject;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertAbout;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;

public class AutoValueGsonExtensionTest {

  private JavaFileObject nullable;

  @Before
  public void setup() {
    nullable = JavaFileObjects.forSourceString("com.ryanharter.auto.value.gson.Nullable", ""
        + "package com.ryanharter.auto.value.gson;\n"
        + "import java.lang.annotation.Retention;\n"
        + "import java.lang.annotation.Target;\n"
        + "import static java.lang.annotation.ElementType.METHOD;\n"
        + "import static java.lang.annotation.ElementType.PARAMETER;\n"
        + "import static java.lang.annotation.ElementType.FIELD;\n"
        + "import static java.lang.annotation.RetentionPolicy.SOURCE;\n"
        + "@Retention(SOURCE)\n"
        + "@Target({METHOD, PARAMETER, FIELD})\n"
        + "public @interface Nullable {\n"
        + "}");
  }

  @Test
  public void simple() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
        + "package test;\n"
        + "import com.google.gson.annotations.SerializedName;\n"
        + "import com.ryanharter.auto.value.gson.Nullable;\n"
        + "import com.google.auto.value.AutoValue;\n"
        + "import com.google.common.collect.ImmutableMap;\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "import java.util.List;\n"
        + "import java.util.Map;\n"
        + "import java.util.Set;\n"
        + "@AutoValue public abstract class Test {\n"
        + "  public static TypeAdapter<Test> typeAdapter(Gson gson) {\n"
        + "    return new AutoValue_Test.GsonTypeAdapter(gson);\n"
        + "  }\n"
        // Reference type
        + "public abstract String a();\n"
        // Array type
        + "public abstract int[] b();\n"
        // Primitive type
        + "public abstract int c();\n"
        // SerializedName
        + "@SerializedName(\"_D\") public abstract String d();\n"
        // Nullable type
        + "@Nullable abstract String e();\n"
        // Parametrized type, multiple parameters
        + "public abstract ImmutableMap<String, Number> f();\n"
        // Parametrized type, single parameter
        + "public abstract Set<String> g();\n"
        // Nested parameterized type
        + "public abstract Map<String, Set<String>> h();\n"
        // SerializedName with alternate
        + "@SerializedName(value = \"_I\", alternate = {\"_I_1\", \"_I_2\"}) public abstract String i();\n"
        // Nullable collection type
        + "@Nullable public abstract List<String> j();\n" +
        "  @AutoValue.Builder public static abstract class Builder {\n" +
        "    public abstract Builder a(String a);\n" +
        "    public abstract Builder b(int[] b);\n" +
        "    public abstract Builder c(int c);\n" +
        "    public abstract Builder d(String d);\n" +
        "    public abstract Builder e(String e);\n" +
        "    public abstract Builder f(ImmutableMap<String, Number> f);\n" +
        "    public abstract Builder g(Set<String> g);\n" +
        "    public abstract Builder h(Map<String, Set<String>> h);\n" +
        "    public abstract Builder i(String i);\n" +
        "    public abstract Builder j(List<String> j);\n" +
        "    public abstract Test build();\n" +
        "  }\n"
        + "}\n"
    );

    JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Test", ""
        + "package test;\n"
        + "\n"
        + "import com.google.common.collect.ImmutableMap;\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "import com.google.gson.reflect.TypeToken;\n"
        + "import com.google.gson.stream.JsonReader;\n"
        + "import com.google.gson.stream.JsonToken;\n"
        + "import com.google.gson.stream.JsonWriter;\n"
        + "import java.io.IOException;\n"
        + "import java.lang.Integer;\n"
        + "import java.lang.Number;\n"
        + "import java.lang.Override;\n"
        + "import java.lang.String;\n"
        + "import java.util.Collections;\n"
        + "import java.util.List;\n"
        + "import java.util.Map;\n"
        + "import java.util.Set;\n"
        + "\n"
        + "final class AutoValue_Test extends $AutoValue_Test {\n"
        + "  AutoValue_Test(String a, int[] b, int c, String d, String e, ImmutableMap<String, Number> f, Set<String> g, Map<String, Set<String>> h, String i, List<String> j) {\n"
        + "    super(a, b, c, d, e, f, g, h, i, j);\n"
        + "  }\n"
        + "\n"
        + "  public static final class GsonTypeAdapter extends TypeAdapter<Test> {\n"
        + "    private final TypeAdapter<String> aAdapter;\n"
        + "    private final TypeAdapter<int[]> bAdapter;\n"
        + "    private final TypeAdapter<Integer> cAdapter;\n"
        + "    private final TypeAdapter<String> dAdapter;\n"
        + "    private final TypeAdapter<String> eAdapter;\n"
        + "    private final TypeAdapter<ImmutableMap<String, Number>> fAdapter;\n"
        + "    private final TypeAdapter<Set<String>> gAdapter;\n"
        + "    private final TypeAdapter<Map<String, Set<String>>> hAdapter;\n"
        + "    private final TypeAdapter<String> iAdapter;\n"
        + "    private final TypeAdapter<List<String>> jAdapter;\n"
        + "    private String defaultA = null;\n"
        + "    private int[] defaultB = null;\n"
        + "    private int defaultC = 0;\n"
        + "    private String defaultD = null;\n"
        + "    private String defaultE = null;\n"
        + "    private ImmutableMap<String, Number> defaultF = ImmutableMap.of();\n"
        + "    private Set<String> defaultG = Collections.emptySet();\n"
        + "    private Map<String, Set<String>> defaultH = Collections.emptyMap();\n"
        + "    private String defaultI = null;\n"
        + "    private List<String> defaultJ = null;\n"
        + "    public GsonTypeAdapter(Gson gson) {\n"
        + "      this.aAdapter = gson.getAdapter(String.class);\n"
        + "      this.bAdapter = gson.getAdapter(int[].class);\n"
        + "      this.cAdapter = gson.getAdapter(Integer.class);\n"
        + "      this.dAdapter = gson.getAdapter(String.class);\n"
        + "      this.eAdapter = gson.getAdapter(String.class);\n"
        + "      this.fAdapter = gson.getAdapter(new TypeToken<ImmutableMap<String, Number>>(){});\n"
        + "      this.gAdapter = gson.getAdapter(new TypeToken<Set<String>>(){});\n"
        + "      this.hAdapter = gson.getAdapter(new TypeToken<Map<String, Set<String>>>(){});\n"
        + "      this.iAdapter = gson.getAdapter(String.class);\n"
        + "      this.jAdapter = gson.getAdapter(new TypeToken<List<String>>(){});\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultA(String defaultA) {\n"
        + "      this.defaultA = defaultA;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultB(int[] defaultB) {\n"
        + "      this.defaultB = defaultB;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultC(int defaultC) {\n"
        + "      this.defaultC = defaultC;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultD(String defaultD) {\n"
        + "      this.defaultD = defaultD;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultE(String defaultE) {\n"
        + "      this.defaultE = defaultE;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultF(ImmutableMap<String, Number> defaultF) {\n"
        + "      this.defaultF = defaultF;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultG(Set<String> defaultG) {\n"
        + "      this.defaultG = defaultG;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultH(Map<String, Set<String>> defaultH) {\n"
        + "      this.defaultH = defaultH;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultI(String defaultI) {\n"
        + "      this.defaultI = defaultI;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultJ(List<String> defaultJ) {\n"
        + "      this.defaultJ = defaultJ;\n"
        + "      return this;\n"
        + "    }\n"
        + "    @Override\n"
        + "    public void write(JsonWriter jsonWriter, Test object) throws IOException {\n"
        + "      if (object == null) {\n"
        + "        jsonWriter.nullValue();\n"
        + "        return;\n"
        + "      }\n"
        + "      jsonWriter.beginObject();\n"
        + "      jsonWriter.name(\"a\");\n"
        + "      aAdapter.write(jsonWriter, object.a());\n"
        + "      jsonWriter.name(\"b\");\n"
        + "      bAdapter.write(jsonWriter, object.b());\n"
        + "      jsonWriter.name(\"c\");\n"
        + "      cAdapter.write(jsonWriter, object.c());\n"
        + "      jsonWriter.name(\"_D\");\n"
        + "      dAdapter.write(jsonWriter, object.d());\n"
        + "      jsonWriter.name(\"e\");\n"
        + "      eAdapter.write(jsonWriter, object.e());\n"
        + "      jsonWriter.name(\"f\");\n"
        + "      fAdapter.write(jsonWriter, object.f());\n"
        + "      jsonWriter.name(\"g\");\n"
        + "      gAdapter.write(jsonWriter, object.g());\n"
        + "      jsonWriter.name(\"h\");\n"
        + "      hAdapter.write(jsonWriter, object.h());\n"
        + "      jsonWriter.name(\"_I\");\n"
        + "      iAdapter.write(jsonWriter, object.i());\n"
        + "      jsonWriter.name(\"j\");\n"
        + "      jAdapter.write(jsonWriter, object.j());\n"
        + "      jsonWriter.endObject();\n"
        + "    }\n"
        + "    @Override\n"
        + "    public Test read(JsonReader jsonReader) throws IOException {\n"
        + "      if (jsonReader.peek() == JsonToken.NULL) {\n"
        + "        jsonReader.nextNull();\n"
        + "        return null;\n"
        + "      }\n"
        + "      jsonReader.beginObject();\n"
        + "      String a = this.defaultA;\n"
        + "      int[] b = this.defaultB;\n"
        + "      int c = this.defaultC;\n"
        + "      String d = this.defaultD;\n"
        + "      String e = this.defaultE;\n"
        + "      ImmutableMap<String, Number> f = this.defaultF;\n"
        + "      Set<String> g = this.defaultG;\n"
        + "      Map<String, Set<String>> h = this.defaultH;\n"
        + "      String i = this.defaultI;\n"
        + "      List<String> j = this.defaultJ;\n"
        + "      while (jsonReader.hasNext()) {\n"
        + "        String _name = jsonReader.nextName();\n"
        + "        switch (_name) {\n"
        + "          case \"a\": {\n"
        + "            a = aAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"b\": {\n"
        + "            b = bAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"c\": {\n"
        + "            c = cAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"_D\": {\n"
        + "            d = dAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"e\": {\n"
        + "            e = eAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"f\": {\n"
        + "            f = fAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"g\": {\n"
        + "            g = gAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"h\": {\n"
        + "            h = hAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"_I_1\":\n"
        + "          case \"_I_2\":\n"
        + "          case \"_I\": {\n"
        + "            i = iAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"j\": {\n"
        + "            j = jAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          default: {\n"
        + "            jsonReader.skipValue();\n"
        + "          }\n"
        + "        }\n"
        + "      }\n"
        + "      jsonReader.endObject();\n"
        + "      return new AutoValue_Test(a, b, c, d, e, f, g, h, i, j);\n"
        + "    }\n"
        + "  }\n"
        + "}"
    );

    assertAbout(javaSources())
        .that(Arrays.asList(nullable, source))
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expected);
  }

  @Test public void propertyMethodReferencedWithPrefix() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
        + "package test;\n"
        + "import com.google.auto.value.AutoValue;\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "@AutoValue public abstract class Test {\n"
        + "  public static TypeAdapter<Test> typeAdapter(Gson gson) {\n"
        + "    return new AutoValue_Test.GsonTypeAdapter(gson);\n"
        + "  }\n"
        + "  public abstract String getName();\n"
        + "  public abstract boolean isAwesome();\n"
        + "}"
    );
    JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Test", ""
        + "package test;\n"
        + "\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "import com.google.gson.stream.JsonReader;\n"
        + "import com.google.gson.stream.JsonToken;\n"
        + "import com.google.gson.stream.JsonWriter;\n"
        + "import java.io.IOException;\n"
        + "import java.lang.Boolean;\n"
        + "import java.lang.Override;\n"
        + "import java.lang.String;\n"
        + "\n"
        + "final class AutoValue_Test extends $AutoValue_Test {\n"
        + "  AutoValue_Test(String name, boolean awesome) {\n"
        + "    super(name, awesome);\n"
        + "  }\n"
        + "\n"
        + "  public static final class GsonTypeAdapter extends TypeAdapter<Test> {\n"
        + "    private final TypeAdapter<String> nameAdapter;\n"
        + "    private final TypeAdapter<Boolean> awesomeAdapter;\n"
        + "    private String defaultName = null;\n"
        + "    private boolean defaultAwesome = false;\n"
        + "    public TestTypeAdapter(Gson gson) {\n"
        + "      this.nameAdapter = gson.getAdapter(String.class);\n"
        + "      this.awesomeAdapter = gson.getAdapter(Boolean.class);\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultName(String defaultName) {\n"
        + "      this.defaultName = defaultName;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultAwesome(boolean defaultAwesome) {\n"
        + "      this.defaultAwesome = defaultAwesome;\n"
        + "      return this;\n"
        + "    }\n"
        + "    @Override\n"
        + "    public void write(JsonWriter jsonWriter, Test object) throws IOException {\n"
        + "      if (object == null) {\n"
        + "        jsonWriter.nullValue();\n"
        + "        return;\n"
        + "      }\n"
        + "      jsonWriter.beginObject();\n"
        + "      jsonWriter.name(\"name\");\n"
        + "      nameAdapter.write(jsonWriter, object.getName());\n"
        + "      jsonWriter.name(\"awesome\");\n"
        + "      awesomeAdapter.write(jsonWriter, object.isAwesome());\n"
        + "      jsonWriter.endObject();\n"
        + "    }\n"
        + "    @Override\n"
        + "    public Test read(JsonReader jsonReader) throws IOException {\n"
        + "      if (jsonReader.peek() == JsonToken.NULL) {\n"
        + "        jsonReader.nextNull();\n"
        + "        return null;\n"
        + "      }\n"
        + "      jsonReader.beginObject();\n"
        + "      String name = this.defaultName;\n"
        + "      boolean awesome = this.defaultAwesome;\n"
        + "      while (jsonReader.hasNext()) {\n"
        + "        String _name = jsonReader.nextName();\n"
        + "        switch (_name) {\n"
        + "          case \"name\": {\n"
        + "            name = nameAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"awesome\": {\n"
        + "            awesome = awesomeAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          default: {\n"
        + "            jsonReader.skipValue();\n"
        + "          }\n"
        + "        }\n"
        + "      }\n"
        + "      jsonReader.endObject();\n"
        + "      return new AutoValue_Test(name, awesome);\n"
        + "    }\n"
        + "  }\n"
        + "}");

    assertAbout(javaSource())
        .that(source)
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expected);
  }

  @Test public void generatesNothingWithoutTypeAdapterMethod() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
        + "package test;\n"
        + "import com.google.auto.value.AutoValue;\n"
        + "@AutoValue public abstract class Test {\n"
        + "  public abstract String a();\n"
        + "  public abstract boolean b();\n"
        + "}"
    );
    JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Test", ""
        + "package test;\n"
        + "\n"
        + "import javax.annotation.Generated;\n"
        + "\n"
        + "@Generated(\"com.google.auto.value.processor.AutoValueProcessor\")\n"
        + " final class AutoValue_Test extends Test {\n"
        + "\n"
        + "  private final String a;\n"
        + "  private final boolean b;\n"
        + "\n"
        + "  AutoValue_Test(\n"
        + "      String a,\n"
        + "      boolean b) {\n"
        + "    if (a == null) {\n"
        + "      throw new NullPointerException(\"Null a\");\n"
        + "    }\n"
        + "    this.a = a;\n"
        + "    this.b = b;\n"
        + "  }\n"
        + "\n"
        + "  @Override\n"
        + "  public String a() {\n"
        + "    return a;\n"
        + "  }\n"
        + "\n"
        + "  @Override\n"
        + "  public boolean b() {\n"
        + "    return b;\n"
        + "  }\n"
        + "\n"
        + "  @Override\n"
        + "  public String toString() {\n"
        + "    return \"Test{\"\n"
        + "        + \"a=\" + a + \", \"\n"
        + "        + \"b=\" + b\n"
        + "        + \"}\";\n"
        + "  }\n"
        + "\n"
        + "  @Override\n"
        + "  public boolean equals(Object o) {\n"
        + "    if (o == this) {\n"
        + "      return true;\n"
        + "    }\n"
        + "    if (o instanceof Test) {\n"
        + "      Test that = (Test) o;\n"
        + "      return (this.a.equals(that.a()))\n"
        + "           && (this.b == that.b());\n"
        + "    }\n"
        + "    return false;\n"
        + "  }\n"
        + "\n"
        + "  @Override\n"
        + "  public int hashCode() {\n"
        + "    int h = 1;\n"
        + "    h *= 1000003;\n"
        + "    h ^= this.a.hashCode();\n"
        + "    h *= 1000003;\n"
        + "    h ^= this.b ? 1231 : 1237;\n"
        + "    return h;\n"
        + "  }\n"
        + "\n"
        + "}");

    assertAbout(javaSource())
        .that(source)
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .withWarningCount(2)
        .and()
        .generatesSources(expected);
  }

  @Test public void emitsWarningForWrongTypeAdapterTypeArgument() {
    JavaFileObject source1 = JavaFileObjects.forSourceString("test.Foo", ""
        + "package test;\n"
        + "import com.google.auto.value.AutoValue;\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "@AutoValue public abstract class Foo {\n"
        + "  public static TypeAdapter<Bar> typeAdapter(Gson gson) {\n"
        + "    return null;"
        + "  }\n"
        + "  public abstract String a();\n"
        + "  public abstract boolean b();\n"
        + "}"
    );

    JavaFileObject source2 = JavaFileObjects.forSourceString("test.Bar", ""
        + "package test;\n"
        + "public class Bar {\n"
        + "}");

    assertAbout(javaSources())
        .that(ImmutableSet.of(source1, source2))
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .withWarningContaining("Found public static method returning TypeAdapter<test.Bar> on "
            + "test.Foo class. Skipping GsonTypeAdapter generation.");
  }

  @Test public void emitsWarningForNoTypeAdapterTypeArgument() {
    JavaFileObject source1 = JavaFileObjects.forSourceString("test.Foo", ""
        + "package test;\n"
        + "import com.google.auto.value.AutoValue;\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "@AutoValue public abstract class Foo {\n"
        + "  public static TypeAdapter typeAdapter(Gson gson) {\n"
        + "    return null;"
        + "  }\n"
        + "  public abstract String a();\n"
        + "  public abstract boolean b();\n"
        + "}"
    );

    assertAbout(javaSource())
        .that(source1)
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .withWarningContaining("Found public static method returning TypeAdapter with no type "
            + "arguments, skipping GsonTypeAdapter generation.");
  }

  @Test public void compilesWithCapitalPackageName() {
    JavaFileObject source1 = JavaFileObjects.forSourceString("MyPackage.Foo", ""
        + "package MyPackage;\n"
        + "import com.google.auto.value.AutoValue;\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "@AutoValue public abstract class Foo {\n"
        + "  public static TypeAdapter<Foo> typeAdapter(Gson gson) {\n"
        + "    return new AutoValue_Foo.GsonTypeAdapter(gson);"
        + "  }\n"
        + "  public abstract String a();\n"
        + "  public abstract boolean b();\n"
        + "}"
    );

    assertAbout(javaSource())
        .that(source1)
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .withWarningCount(2);
  }

  @Test public void generatesCorrectDefaultCharPrimitiveValue() {
    JavaFileObject source = JavaFileObjects.forSourceString("test.Test", ""
        + "package test;\n"
        + "import com.google.auto.value.AutoValue;\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "@AutoValue public abstract class Test {\n"
        + "  public static TypeAdapter<Test> typeAdapter(Gson gson) {\n"
        + "    return new AutoValue_Test.GsonTypeAdapter(gson);\n"
        + "  }\n"
        + "public abstract char c();\n"
        + "}\n"
    );

    JavaFileObject expected = JavaFileObjects.forSourceString("test/AutoValue_Test", ""
        + "package test;\n"
        + "\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "import com.google.gson.stream.JsonReader;\n"
        + "import com.google.gson.stream.JsonToken;\n"
        + "import com.google.gson.stream.JsonWriter;\n"
        + "import java.io.IOException;\n"
        + "import java.lang.Character;\n"
        + "import java.lang.Override;\n"
        + "import java.lang.String;\n"
        + "\n"
        + "final class AutoValue_Test extends $AutoValue_Test {\n"
        + "  AutoValue_Test(char c) {\n"
        + "    super(c);\n"
        + "  }\n"
        + "\n"
        + "  public static final class GsonTypeAdapter extends TypeAdapter<Test> {\n"
        + "    private final TypeAdapter<Character> cAdapter;\n"
        + "    private char defaultC = '\0';\n"
        + "    public GsonTypeAdapter(Gson gson) {\n"
        + "      this.cAdapter = gson.getAdapter(Character.class);\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultC(char defaultC) {\n"
        + "      this.defaultC = defaultC;\n"
        + "      return this;\n"
        + "    }\n"
        + "    @Override\n"
        + "    public void write(JsonWriter jsonWriter, Test object) throws IOException {\n"
        + "      if (object == null) {\n"
        + "        jsonWriter.nullValue();\n"
        + "        return;\n"
        + "      }\n"
        + "      jsonWriter.beginObject();\n"
        + "      jsonWriter.name(\"c\");\n"
        + "      cAdapter.write(jsonWriter, object.c());\n"
        + "      jsonWriter.endObject();\n"
        + "    }\n"
        + "    @Override\n"
        + "    public Test read(JsonReader jsonReader) throws IOException {\n"
        + "      if (jsonReader.peek() == JsonToken.NULL) {\n"
        + "        jsonReader.nextNull();\n"
        + "        return null;\n"
        + "      }\n"
        + "      jsonReader.beginObject();\n"
        + "      char c = this.defaultC;\n"
        + "      while (jsonReader.hasNext()) {\n"
        + "        String _name = jsonReader.nextName();\n"
        + "        switch (_name) {\n"
        + "          case \"c\": {\n"
        + "            c = cAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          default: {\n"
        + "            jsonReader.skipValue();\n"
        + "          }\n"
        + "        }\n"
        + "      }\n"
        + "      jsonReader.endObject();\n"
        + "      return new AutoValue_Test(c);\n"
        + "    }\n"
        + "  }\n"
        + "}"
    );

    assertAbout(javaSources())
      .that(Arrays.asList(nullable, source))
      .processedWith(new AutoValueProcessor())
      .compilesWithoutError()
      .and()
      .generatesSources(expected);
  }

  @Test public void handlesGenericTypes() {
    JavaFileObject source1 = JavaFileObjects.forSourceString("test.Foo", ""
        + "package test;\n"
        + "import com.google.auto.value.AutoValue;\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "import com.google.gson.reflect.TypeToken;\n"
        + "import java.util.List;\n"
        + "import java.util.Map;\n"
        + "@AutoValue public abstract class Foo<A, B, C> {\n"
        + "  public static <A, B, C> TypeAdapter<Foo<A, B, C>> typeAdapter(Gson gson, TypeToken<? extends Foo<A, B, C>> typeToken) {\n"
        + "    return new AutoValue_Foo.GsonTypeAdapter(gson, typeToken);"
        + "  }\n"
        + "  public abstract C c();\n"
        + "  public abstract A a();\n"
        + "  public abstract B b();\n"
        + "  public abstract List<A> list();\n"
        + "  public abstract Map<String, List<C>> map();\n"
        + "  public abstract String d();\n"
        + "}"
    );

    JavaFileObject expected = JavaFileObjects.forSourceString("test.AutoValue_Test", ""
        + "package test;\n"
        + "\n"
        + "import com.google.gson.Gson;\n"
        + "import com.google.gson.TypeAdapter;\n"
        + "import com.google.gson.internal.$Gson$Types;\n"
        + "import com.google.gson.reflect.TypeToken;\n"
        + "import com.google.gson.stream.JsonReader;\n"
        + "import com.google.gson.stream.JsonToken;\n"
        + "import com.google.gson.stream.JsonWriter;\n"
        + "import java.io.IOException;\n"
        + "import java.lang.Override;\n"
        + "import java.lang.String;\n"
        + "import java.lang.reflect.ParameterizedType;\n"
        + "import java.lang.reflect.Type;\n"
        + "import java.util.List;\n"
        + "import java.util.Map;\n"
        + "\n"
        + "final class AutoValue_Foo<A, B, C> extends $AutoValue_Foo<A, B, C> {\n"
        + "  AutoValue_Foo(C c, A a, B b, List<A> list, Map<String, List<C>> map, String d) {\n"
        + "    super(c, a, b, list, map, d);\n"
        + "  }\n"
        + "\n"
        + "  public static final class GsonTypeAdapter<A, B, C> extends TypeAdapter<Foo<A, B, C>> {\n"
        + "    private final TypeAdapter<C> cAdapter;\n"
        + "    private final TypeAdapter<A> aAdapter;\n"
        + "    private final TypeAdapter<B> bAdapter;\n"
        + "    private final TypeAdapter<List<A>> listAdapter;\n"
        + "    private final TypeAdapter<Map<String, List<C>>> mapAdapter;\n"
        + "    private final TypeAdapter<String> dAdapter;\n"
        + "    private C defaultC = null;\n"
        + "    private A defaultA = null;\n"
        + "    private B defaultB = null;\n"
        + "    private String defaultD = null;\n"
        + "    public GsonTypeAdapter(Gson gson, TypeToken<? extends Foo<A, B, C>> typeToken) {\n"
        + "      ParameterizedType type = (ParameterizedType) typeToken.getType();\n"
        + "      Type[] typeArgs = type.getActualTypeArguments();\n"
        + "      this.cAdapter = (TypeAdapter<C>) gson.getAdapter(TypeToken.get(typeArgs[2]));\n"
        + "      this.aAdapter = (TypeAdapter<A>) gson.getAdapter(TypeToken.get(typeArgs[0]));\n"
        + "      this.bAdapter = (TypeAdapter<B>) gson.getAdapter(TypeToken.get(typeArgs[1]));\n"
        + "      this.listAdapter = (TypeAdapter<List<A>>) gson.getAdapter(TypeToken.get($Gson$Types.newParameterizedTypeWithOwner(null, List.class, typeArgs[0])));\n"
        + "      this.mapAdapter = (TypeAdapter<Map<String, List<C>>>) gson.getAdapter(TypeToken.get($Gson$Types.newParameterizedTypeWithOwner(null, Map.class, String.class, $Gson$Types.newParameterizedTypeWithOwner(null, List.class, typeArgs[2]))));\n"
        + "      this.dAdapter = gson.getAdapter(String.class);\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultC(C defaultC) {\n"
        + "      this.defaultC = defaultC;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultA(A defaultA) {\n"
        + "      this.defaultA = defaultA;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultB(B defaultB) {\n"
        + "      this.defaultB = defaultB;\n"
        + "      return this;\n"
        + "    }\n"
        + "    public GsonTypeAdapter setDefaultD(String defaultD) {\n"
        + "      this.defaultD = defaultD;\n"
        + "      return this;\n"
        + "    }\n"
        + "    @Override\n"
        + "    public void write(JsonWriter jsonWriter, Foo<A, B, C> object) throws IOException {\n"
        + "      if (object == null) {\n"
        + "        jsonWriter.nullValue();\n"
        + "        return;\n"
        + "      }\n"
        + "      jsonWriter.beginObject();\n"
        + "      jsonWriter.name(\"c\");\n"
        + "      cAdapter.write(jsonWriter, object.c());\n"
        + "      jsonWriter.name(\"a\");\n"
        + "      aAdapter.write(jsonWriter, object.a());\n"
        + "      jsonWriter.name(\"b\");\n"
        + "      bAdapter.write(jsonWriter, object.b());\n"
        + "      jsonWriter.name(\"list\");\n"
        + "      listAdapter.write(jsonWriter, object.list());\n"
        + "      jsonWriter.name(\"map\");\n"
        + "      mapAdapter.write(jsonWriter, object.map());\n"
        + "      jsonWriter.name(\"d\");\n"
        + "      dAdapter.write(jsonWriter, object.d());\n"
        + "      jsonWriter.endObject();\n"
        + "    }\n"
        + "    @Override\n"
        + "    public Foo<A, B, C> read(JsonReader jsonReader) throws IOException {\n"
        + "      if (jsonReader.peek() == JsonToken.NULL) {\n"
        + "        jsonReader.nextNull();\n"
        + "        return null;\n"
        + "      }\n"
        + "      jsonReader.beginObject();\n"
        + "      C c = this.defaultC;\n"
        + "      A a = this.defaultA;\n"
        + "      B b = this.defaultB;\n"
        + "      List<A> list = null;\n"
        + "      Map<String, List<C>> map = null;\n"
        + "      String d = this.defaultD;\n"
        + "      while (jsonReader.hasNext()) {\n"
        + "        String _name = jsonReader.nextName();\n"
        + "        switch (_name) {\n"
        + "          case \"c\": {\n"
        + "            c = cAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"a\": {\n"
        + "            a = aAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"b\": {\n"
        + "            b = bAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"list\": {\n"
        + "            list = listAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"map\": {\n"
        + "            map = mapAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          case \"d\": {\n"
        + "            d = dAdapter.read(jsonReader);\n"
        + "            break;\n"
        + "          }\n"
        + "          default: {\n"
        + "            jsonReader.skipValue();\n"
        + "          }\n"
        + "        }\n"
        + "      }\n"
        + "      jsonReader.endObject();\n"
        + "      return new AutoValue_Foo<>(c, a, b, list, map, d);\n"
        + "    }\n"
        + "  }\n"
        + "}");

    assertAbout(javaSource())
        .that(source1)
        .processedWith(new AutoValueProcessor())
        .compilesWithoutError()
        .and()
        .generatesSources(expected);
  }
}