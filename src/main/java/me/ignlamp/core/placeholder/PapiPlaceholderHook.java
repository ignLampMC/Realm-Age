package me.ignlamp.core.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.ignlamp.core.placeholder.PapiSupplier.PlaceholderSupplier;
import org.bukkit.entity.Player;

public final class PapiPlaceholderHook extends PlaceholderExpansion {
    private final PlaceholderSupplier supplier;

    public PapiPlaceholderHook(PlaceholderSupplier supplier) {
        this.supplier = supplier;
    }
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier == null || this.supplier == null) {
            return null;
        }
        return this.supplier.onPlaceholderRequest(identifier);
    }

    public String getIdentifier() {
        return PlaceholderSupplier.IDENTIFIER;
    }

    public String getAuthor() {
        return "Josh";
    }

    public String getVersion() {
        return "1.0.0";
    }
}

