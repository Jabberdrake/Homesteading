package jabberdrake.homesteading.common.recipe.crucible;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public enum CrucibleRecipeSerializer implements RecipeSerializer<CrucibleRecipe> {
    INSTANCE;

    @Override
    public CrucibleRecipe read(Identifier id, JsonObject json) {
        return CrucibleRecipe.CODEC.decode(JsonOps.INSTANCE, json).getOrThrow(false, System.err::println).getFirst();
    }

    @Override
    public CrucibleRecipe read(Identifier id, PacketByteBuf buf) {
        return buf.decodeAsJson(CrucibleRecipe.CODEC);
    }

    @Override
    public void write(PacketByteBuf buf, CrucibleRecipe recipe) {
        buf.encodeAsJson(CrucibleRecipe.CODEC, recipe);
    }
}
