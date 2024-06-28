package jabberdrake.homesteading.common.block.blockentities;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.recipe.crucible.CrucibleRecipe;
import jabberdrake.homesteading.common.registry.HomeBlockEntities;
import jabberdrake.homesteading.common.registry.HomeObjectRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class CrucibleBlockEntity extends BlockEntity {

    private DefaultedList<ItemStack> inventory;

    public CrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(HomeBlockEntities.CRUCIBLE_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
    }

    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public boolean addItem(ItemStack stack, int i) {
        ItemStack verifyStack = this.inventory.get(i);

        if (stack.getItem() == verifyStack.getItem()) {
            ItemStack stackIncrement = verifyStack.copy();
            stackIncrement.increment(1);
            this.inventory.set(i, stackIncrement);
            stack.decrement(1);

            return true;
        } else if (verifyStack.isEmpty()) {
            ItemStack stackNew = new ItemStack(stack.getItem(), 1);
            this.inventory.set(i, stackNew);
            stack.decrement(1);

            return true;
        }

        this.markDirty();
        return false;
    }

    public boolean canRemoveItem(int i) {
        ItemStack verifyStack = this.inventory.get(i);

        return !verifyStack.isEmpty();
    }

    public ItemStack removeItem(int i) {
        ItemStack removedStack = this.inventory.get(i);
        int stackAmount = removedStack.getCount();

        if (stackAmount > 1) {
            ItemStack stackNew = new ItemStack(removedStack.getItem(), stackAmount - 1);
            this.inventory.set(i, stackNew);
        } else {
            this.inventory.set(i, ItemStack.EMPTY);
        }

        this.markDirty();
        return new ItemStack(removedStack.getItem(), 1);
    }

    public List<ItemStack> getItemStacks() {
        List<ItemStack> contents = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            ItemStack stackI = this.inventory.get(i);

            if (stackI.isEmpty()) {
                continue;
            } else {
                contents.add(stackI);
            }
        }

        return contents;
    }

    public void processRecipes(World world, PlayerEntity player, BlockPos pos) {
        List<ItemStack> inv = getItemStacks();

        if (!world.isClient) {
            SimpleInventory simpleInventory = new SimpleInventory(inv.size());

            for (int i = 0; i < simpleInventory.size(); i++) {
                simpleInventory.setStack(i, inv.get(i));
            }

            Optional<CrucibleRecipe> match = world.getRecipeManager().getFirstMatch(Homesteading.CRUCIBLE_RECIPE, simpleInventory, world);

            if (match.isPresent()) {
                player.getInventory().offerOrDrop(match.get().getResult().copy());
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }

    public void tellRecipe(World world, PlayerEntity player) {
        List<ItemStack> inv = getItemStacks();

        Set<ItemStack> invStacks = new HashSet<>();
        Set<ItemStack> inputStacks = new HashSet<>();

        if (!world.isClient) {
            SimpleInventory simpleInventory = new SimpleInventory(getItemStacks().size());

            for (int i = 0; i < simpleInventory.size(); i++) {
                simpleInventory.setStack(i, inv.get(i));
            }

            Optional<CrucibleRecipe> match = world.getRecipeManager().getFirstMatch(Homesteading.CRUCIBLE_RECIPE, simpleInventory, world);

            if (match.isPresent()) {
                player.sendMessage(Text.of("Recipe = True"), true);
            } else {
                player.sendMessage(Text.of("There is no recipe here."), true);
            }
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        if (nbt.contains("inventory")) {
            NbtList list = nbt.getList("inventory", 10);
            int size = list.size();

            for (int i = 0; i < size; i++) {
                NbtCompound nbtCompound = list.getCompound(i);
                this.inventory.set(i, ItemStack.fromNbt(nbtCompound));
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        NbtList list = new NbtList();

        for (int i = 0; i < 9; i++) {
            NbtCompound nbtCompound = new NbtCompound();

            ItemStack stack = this.inventory.get(i);

            if (!stack.isEmpty()) {
                stack.writeNbt(nbtCompound);
                list.add(nbtCompound);
            }
        }

        nbt.put("inventory", list);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        NbtList list = new NbtList();

        nbtCompound.put("inventory", list);

        return nbtCompound;
    }
}
