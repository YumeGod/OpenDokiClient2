package just.monika.所以CZF你M死了.client;

import java.io.InputStream;

public interface IShaderPack
{
    String getName();

    InputStream getResourceAsStream(String var1);

    boolean hasDirectory(String var1);

    void close();
}
