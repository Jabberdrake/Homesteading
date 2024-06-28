package jabberdrake.homesteading.common.registry;

import jabberdrake.homesteading.Homesteading;
import jabberdrake.homesteading.common.block.blockentities.CrucibleBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class HomeBlockEntities {

    private static final Map<BlockEntityType<?>, Identifier> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    public static final BlockEntityType<CrucibleBlockEntity> CRUCIBLE_BLOCK_ENTITY = registerBlockEntityType("crucible", FabricBlockEntityTypeBuilder.create(CrucibleBlockEntity::new).build(null));

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntityType(String name, BlockEntityType<T> type) {
        BLOCK_ENTITY_TYPES.put(type, Homesteading.createIdentifier(name));
        return type;
    }

    public static void init() {
        BLOCK_ENTITY_TYPES.keySet().forEach(blockEntityType -> Registry.register(Registries.BLOCK_ENTITY_TYPE, BLOCK_ENTITY_TYPES.get(blockEntityType), blockEntityType));
    }
}
