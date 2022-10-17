package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;

public class GenLayerShore extends GenLayer
{
    private static final String __OBFID = "CL_00000568";

    public GenLayerShore(long p_i2130_1_, GenLayer p_i2130_3_)
    {
        super(p_i2130_1_);
        this.parent = p_i2130_3_;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] var5 = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] var6 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int var7 = 0; var7 < areaHeight; ++var7)
        {
            for (int var8 = 0; var8 < areaWidth; ++var8)
            {
                this.initChunkSeed((long)(var8 + areaX), (long)(var7 + areaY));
                int var9 = var5[var8 + 1 + (var7 + 1) * (areaWidth + 2)];
                BiomeGenBase var10 = BiomeGenBase.getBiome(var9);
                int var11;
                int var12;
                int var13;
                int var14;

                if (var9 == BiomeGenBase.mushroomIsland.biomeID)
                {
                    var11 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
                    var12 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
                    var13 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
                    var14 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];

                    if (var11 != BiomeGenBase.ocean.biomeID && var12 != BiomeGenBase.ocean.biomeID && var13 != BiomeGenBase.ocean.biomeID && var14 != BiomeGenBase.ocean.biomeID)
                    {
                        var6[var8 + var7 * areaWidth] = var9;
                    }
                    else
                    {
                        var6[var8 + var7 * areaWidth] = BiomeGenBase.mushroomIslandShore.biomeID;
                    }
                }
                else if (var10 != null && var10.getBiomeClass() == BiomeGenJungle.class)
                {
                    var11 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
                    var12 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
                    var13 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
                    var14 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];

                    if (this.func_151631_c(var11) && this.func_151631_c(var12) && this.func_151631_c(var13) && this.func_151631_c(var14))
                    {
                        if (!isBiomeOceanic(var11) && !isBiomeOceanic(var12) && !isBiomeOceanic(var13) && !isBiomeOceanic(var14))
                        {
                            var6[var8 + var7 * areaWidth] = var9;
                        }
                        else
                        {
                            var6[var8 + var7 * areaWidth] = BiomeGenBase.beach.biomeID;
                        }
                    }
                    else
                    {
                        var6[var8 + var7 * areaWidth] = BiomeGenBase.jungleEdge.biomeID;
                    }
                }
                else if (var9 != BiomeGenBase.extremeHills.biomeID && var9 != BiomeGenBase.extremeHillsPlus.biomeID && var9 != BiomeGenBase.extremeHillsEdge.biomeID)
                {
                    if (var10 != null && var10.isSnowyBiome())
                    {
                        this.func_151632_a(var5, var6, var8, var7, areaWidth, var9, BiomeGenBase.coldBeach.biomeID);
                    }
                    else if (var9 != BiomeGenBase.mesa.biomeID && var9 != BiomeGenBase.mesaPlateau_F.biomeID)
                    {
                        if (var9 != BiomeGenBase.ocean.biomeID && var9 != BiomeGenBase.deepOcean.biomeID && var9 != BiomeGenBase.river.biomeID && var9 != BiomeGenBase.swampland.biomeID)
                        {
                            var11 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
                            var12 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
                            var13 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
                            var14 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];

                            if (!isBiomeOceanic(var11) && !isBiomeOceanic(var12) && !isBiomeOceanic(var13) && !isBiomeOceanic(var14))
                            {
                                var6[var8 + var7 * areaWidth] = var9;
                            }
                            else
                            {
                                var6[var8 + var7 * areaWidth] = BiomeGenBase.beach.biomeID;
                            }
                        }
                        else
                        {
                            var6[var8 + var7 * areaWidth] = var9;
                        }
                    }
                    else
                    {
                        var11 = var5[var8 + 1 + (var7 + 1 - 1) * (areaWidth + 2)];
                        var12 = var5[var8 + 1 + 1 + (var7 + 1) * (areaWidth + 2)];
                        var13 = var5[var8 + 1 - 1 + (var7 + 1) * (areaWidth + 2)];
                        var14 = var5[var8 + 1 + (var7 + 1 + 1) * (areaWidth + 2)];

                        if (!isBiomeOceanic(var11) && !isBiomeOceanic(var12) && !isBiomeOceanic(var13) && !isBiomeOceanic(var14))
                        {
                            if (this.func_151633_d(var11) && this.func_151633_d(var12) && this.func_151633_d(var13) && this.func_151633_d(var14))
                            {
                                var6[var8 + var7 * areaWidth] = var9;
                            }
                            else
                            {
                                var6[var8 + var7 * areaWidth] = BiomeGenBase.desert.biomeID;
                            }
                        }
                        else
                        {
                            var6[var8 + var7 * areaWidth] = var9;
                        }
                    }
                }
                else
                {
                    this.func_151632_a(var5, var6, var8, var7, areaWidth, var9, BiomeGenBase.stoneBeach.biomeID);
                }
            }
        }

        return var6;
    }

    private void func_151632_a(int[] p_151632_1_, int[] p_151632_2_, int p_151632_3_, int p_151632_4_, int p_151632_5_, int p_151632_6_, int p_151632_7_)
    {
        if (isBiomeOceanic(p_151632_6_))
        {
            p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
        }
        else
        {
            int var8 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 - 1) * (p_151632_5_ + 2)];
            int var9 = p_151632_1_[p_151632_3_ + 1 + 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int var10 = p_151632_1_[p_151632_3_ + 1 - 1 + (p_151632_4_ + 1) * (p_151632_5_ + 2)];
            int var11 = p_151632_1_[p_151632_3_ + 1 + (p_151632_4_ + 1 + 1) * (p_151632_5_ + 2)];

            if (!isBiomeOceanic(var8) && !isBiomeOceanic(var9) && !isBiomeOceanic(var10) && !isBiomeOceanic(var11))
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_6_;
            }
            else
            {
                p_151632_2_[p_151632_3_ + p_151632_4_ * p_151632_5_] = p_151632_7_;
            }
        }
    }

    private boolean func_151631_c(int p_151631_1_)
    {
        return BiomeGenBase.getBiome(p_151631_1_) != null && BiomeGenBase.getBiome(p_151631_1_).getBiomeClass() == BiomeGenJungle.class ? true : p_151631_1_ == BiomeGenBase.jungleEdge.biomeID || p_151631_1_ == BiomeGenBase.jungle.biomeID || p_151631_1_ == BiomeGenBase.jungleHills.biomeID || p_151631_1_ == BiomeGenBase.forest.biomeID || p_151631_1_ == BiomeGenBase.taiga.biomeID || isBiomeOceanic(p_151631_1_);
    }

    private boolean func_151633_d(int p_151633_1_)
    {
        return BiomeGenBase.getBiome(p_151633_1_) instanceof BiomeGenMesa;
    }
}
