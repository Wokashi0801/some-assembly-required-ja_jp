package someassemblyrequired.common.item.spreadtype;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;

public class SimpleSpreadType extends SpreadType {

    private final Item container;
    private final Component displayName;
    private final int color;

    public SimpleSpreadType(Item ingredient, Item container, Component displayName, int color) {
        super(ingredient);
        this.container = container;
        this.displayName = displayName;
        this.color = color;
    }

    public Item getContainer(ItemStack stack) {
        return container;
    }

    public int getColor(ItemStack stack) {
        return color;
    }

    protected static SimpleSpreadType deserialize(JsonObject object) {
        if (!object.has("item")) {
            throw new JsonParseException("Ingredient item must be set");
        }

        Item item;
        String itemName = object.get("item").getAsString();
        if (ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName))) {
            item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemName));
        } else {
            throw new JsonParseException(String.format("Expected to find item but got '%s'", itemName));
        }
        if (item == Items.AIR) {
            throw new JsonParseException("Ingredient item can not be air");
        }

        Item containerItem = Items.AIR;
        if (object.has("container")) {
            String containerName = object.get("container").getAsString();
            if (ForgeRegistries.ITEMS.containsKey(new ResourceLocation(containerName))) {
                containerItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(containerName));
            } else {
                throw new JsonParseException(String.format("Expected to find item but got '%s'", containerName));
            }
        }

        Component displayName = Component.Serializer.fromJson(object.get("displayname"));
        if (displayName == null) {
            throw new JsonParseException("Display name must be set");
        }

        if (!object.has("color")) {
            throw new JsonParseException("Color must be set");
        }
        int color = object.get("color").getAsInt();

        return new SimpleSpreadType(item, containerItem, displayName, color);
    }

    public static SimpleSpreadType read(FriendlyByteBuf buffer) {
        Item ingredient = ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
        Item container = buffer.readBoolean() ? ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation()) : Items.AIR;
        Component displayName = buffer.readComponent();
        int color = buffer.readInt();
        return new SimpleSpreadType(ingredient, container, displayName, color);
    }

    @Override
    public Component getDisplayName(ItemStack stack) {
        return displayName;
    }

    @SuppressWarnings("ConstantConditions")
    public void write(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(getIngredient().getRegistryName());
        if (container == Items.AIR) {
            buffer.writeBoolean(false);
        } else {
            buffer.writeBoolean(true);
            buffer.writeResourceLocation(container.getRegistryName());
        }
        buffer.writeComponent(displayName);
        buffer.writeInt(color);
    }
}
