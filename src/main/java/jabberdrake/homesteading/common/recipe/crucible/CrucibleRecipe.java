package jabberdrake.homesteading.common.recipe.crucible;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import jabberdrake.homesteading.Homesteading;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrucibleRecipe implements Recipe<Inventory> {
    public static final Identifier ID = new Identifier(Homesteading.MOD_ID, "crucible");

    /*This List<ItemStack> needs to be changed to two lists, one for Ingredients, and another for Integers;
    Pay attention, cuz when we build the HashMap<Ingredient, Integer> it is going to process ArrayList<Ingredient>[0] with ArrayList<Integer>[0] and so on...
    We need to do this to make sure we can use tags in Crucible recipes, meaning we don't need to do 4 recipes per alloy - Spacer*/
    private final List<ItemStack> input;
    private final ItemStack output;

    public CrucibleRecipe(List<ItemStack> input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public static final Codec<CrucibleRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.listOf().fieldOf("input").forGetter(CrucibleRecipe::getInput),
            ItemStack.CODEC.fieldOf("output").forGetter(CrucibleRecipe::getResult)
    ).apply(instance, CrucibleRecipe::new));

    public List<ItemStack> getInput() {
        return input;
    }

    public ItemStack getResult() {
        return output;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        int inputSize = getInput().size();
        int invSize = inventory.size();

        HashMap<Item, Integer> inputMap = new HashMap<>();
        HashMap<Item, Integer> inventoryMap = new HashMap<>();

        int matchAmount = 0;

        if (invSize == inputSize) {
            for (int i = 0; i < inputSize; i++) {
                Item inputItem = getInput().get(i).getItem();
                int inputCount = getInput().get(i).getCount();

                inputMap.put(inputItem, inputCount);

                Item invItem = inventory.getStack(i).getItem();
                int invCount = inventory.getStack(i).getCount();

                inventoryMap.put(invItem, invCount);
            }

            for (Map.Entry<Item, Integer> entry : inventoryMap.entrySet()) {
                Item key = entry.getKey();
                int invMapCount = entry.getValue();

                if (inputMap.containsKey(key)) {
                    int inpMapCount = inputMap.get(key);

                    if (inpMapCount == invMapCount) {
                        matchAmount++;
                    }
                }
            }
        }

        return matchAmount == inputSize;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CrucibleRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Homesteading.CRUCIBLE_RECIPE;
    }
}
