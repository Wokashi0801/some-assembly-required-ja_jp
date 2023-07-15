package someassemblyrequired.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import someassemblyrequired.SomeAssemblyRequired;
import someassemblyrequired.init.ModBlocks;
import someassemblyrequired.init.ModTags;

import java.util.concurrent.CompletableFuture;

public class BlockTags extends BlockTagsProvider {

    public BlockTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(packOutput, provider, SomeAssemblyRequired.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.SANDWICHING_STATIONS).add(ModBlocks.SANDWICHING_STATION.get());
        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.SANDWICHING_STATION.get());
    }
}
