package someassemblyrequired.common.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import someassemblyrequired.SomeAssemblyRequired;
import someassemblyrequired.common.block.redstonetoaster.RedstoneToasterBlockEntity;
import someassemblyrequired.common.block.sandwich.SandwichBlockEntity;
import someassemblyrequired.common.block.sandwichassemblytable.SandwichAssemblyTableBlockEntity;

@SuppressWarnings("ConstantConditions")
public class ModBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SomeAssemblyRequired.MODID);

    public static RegistryObject<BlockEntityType<SandwichAssemblyTableBlockEntity>> SANDWICH_ASSEMBLY_TABLE = REGISTRY.register(
            "sandwich_assembly_table",
            () -> BlockEntityType.Builder.of(SandwichAssemblyTableBlockEntity::new,
                    ModBlocks.getSandwichAssemblyTables()
            ).build(null)
    );

    public static RegistryObject<BlockEntityType<SandwichBlockEntity>> SANDWICH = REGISTRY.register(
            "sandwich",
            () -> BlockEntityType.Builder.of(SandwichBlockEntity::new,
                    ModBlocks.SANDWICH.get()
            ).build(null)
    );

    public static RegistryObject<BlockEntityType<RedstoneToasterBlockEntity>> REDSTONE_TOASTER = REGISTRY.register(
            "redstone_toaster",
            () -> BlockEntityType.Builder.of(RedstoneToasterBlockEntity::new,
                    ModBlocks.getToasters()
            ).build(null)
    );
}