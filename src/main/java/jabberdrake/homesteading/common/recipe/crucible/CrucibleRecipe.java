package jabberdrake.homesteading.common.recipe.crucible;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import jabberdrake.homesteading.Homesteading;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;

public class CrucibleRecipe implements Recipe<Inventory> {
    public static final Identifier ID = new Identifier(Homesteading.MOD_ID, "crucible");

    private final List<Ingredient> input;
    private final ItemStack output;

    public CrucibleRecipe(List<Ingredient> input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public static final Codec<CrucibleRecipe> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Ingredient.DISALLOW_EMPTY_CODEC.listOf().fieldOf("input").forGetter(CrucibleRecipe::getInput),
            ItemStack.CODEC.fieldOf("output").forGetter(CrucibleRecipe::getOutput)
    ).apply(instance, CrucibleRecipe::new));

    public List<Ingredient> getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        int inputSize = getInput().size();
        int invSize = inventory.size();

        HashMap<Integer, ItemStack> inventoryMap = new HashMap<>();

        int matchAmount = 0;
        int stackSizeTested = 0;

        if (inputSize == 9 && invSize < 10) {
            for (int i = 0; i < invSize; i++) {
                ItemStack stack = inventory.getStack(i);

                inventoryMap.put(i, stack);
            }

            for (int j = 0; j < inventoryMap.size(); j++) {
                ItemStack stackToTest = inventoryMap.get(j);

                for (int k = 0; k < 9; k++) {
                    Ingredient ingToTest = getInput().get(k);

                    if (ingToTest.test(stackToTest)) {
                        matchAmount++;
                        stackSizeTested++;

                        if (stackSizeTested == stackToTest.getCount()) {
                            stackSizeTested = 0;
                            break;
                        }
                    }
                }
            }
        } else {
            Homesteading.LOGGER.info("Something went wrong with a Crucible Recipe!");
            Homesteading.LOGGER.info("Either the Input has more/less than 9 entries or the Crucible bugged and has more than 9 items inside.");

            return false;
        }

        return matchAmount == 9;
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
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
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
