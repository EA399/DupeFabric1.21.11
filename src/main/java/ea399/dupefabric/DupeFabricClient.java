package ea399.dupefabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
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

                if (client.player == null) {
                    return;
                }

                client.player.sendMessage(
                        Text.literal("§aDupeFabric: G erkannt!"),
                        false
                );
            }
        });
    }
}
