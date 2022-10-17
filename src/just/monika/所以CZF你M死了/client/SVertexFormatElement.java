package just.monika.所以CZF你M死了.client;

import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class SVertexFormatElement extends VertexFormatElement
{
    int sUsage;

    public SVertexFormatElement(int sUsage, VertexFormatElement.EnumType type, int count)
    {
        super(0, type, VertexFormatElement.EnumUseage.PADDING, count);
        this.sUsage = sUsage;
    }
}
