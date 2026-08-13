private static void duplicateSelectedItem(MinecraftClient client) {
    if (client.player == null) return;

    client.player.sendMessage(
            net.minecraft.text.Text.literal("DupeFabric: G gedrückt!"),
            true
    );

    int slot = client.player.getInventory().getSelectedSlot();
    ItemStack original = client.player.getInventory().getStack(slot);

    if (original.isEmpty()) return;

    ItemStack duplicate = original.copy();
    int count = Math.min(original.getCount() * 2, duplicate.getMaxCount());

    duplicate.setCount(count);
    client.player.getInventory().setStack(slot, duplicate);
}
