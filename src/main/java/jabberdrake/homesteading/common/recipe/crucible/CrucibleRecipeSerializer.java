package jabberdrake.homesteading.common.recipe.crucible;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public enum CrucibleRecipeSerializer implements RecipeSerializer<CrucibleRecipe> {
    INSTANCE;

    @Override
    public Codec<CrucibleRecipe> codec() {
        return CrucibleRecipe.CODEC;
    }

    @Override
    public CrucibleRecipe read(PacketByteBuf buf) {
        return buf.decodeAsJson(CrucibleRecipe.CODEC);
    }

    @Override
    public void write(PacketByteBuf buf, CrucibleRecipe recipe) {
        buf.encodeAsJson(CrucibleRecipe.CODEC, recipe);
    }
}
