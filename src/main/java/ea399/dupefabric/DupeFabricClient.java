package ea399.dupefabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class DupeFabricClient implements ClientModInitializer {

    private static KeyBinding dupeKey;

    @Override
    public void onInitializeClient() {
        dupeKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.dupefabric.duplicate",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_G,
                        KeyBinding.Category.MISC
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (dupeKey.wasPressed()) {
                duplicateSelectedItem(client);
            }
        });
    }

    private static void duplicateSelectedItem(MinecraftClient client) {
        if (client.player == null) {
            return;
        }

        int slot = client.player.getInventory().getSelectedSlot();
        ItemStack original = client.player.getInventory().getStack(slot);

        if (original.isEmpty()) {
            return;
        }

        ItemStack duplicate = original.copy();
        int count = Math.min(original.getCount() * 2, duplicate.getMaxCount());

        duplicate.setCount(count);
        client.player.getInventory().setStack(slot, duplicate);
    }
}
