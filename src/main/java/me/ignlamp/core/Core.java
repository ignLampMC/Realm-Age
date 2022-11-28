package me.ignlamp.core;
import me.ignlamp.core.placeholder.PapiPlaceholderHook;
import me.ignlamp.core.placeholder.PapiSupplier.DefaultPlaceholderSupplier;
import me.ignlamp.core.placeholder.PapiSupplier.PlaceholderSupplier;
import me.ignlamp.core.placeholder.PlaceholderHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {
    private PlaceholderSupplier supplier;


    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        String defaultProvider = this.getConfig().getString("default-provider");

        PlaceholderHook defaultHook = PlaceholderHook.parse(defaultProvider);
        PlaceholderHook foundHook = null;

        for (PlaceholderHook hook : PlaceholderHook.values()) {
            if (!hook.isEnabled()) {
                continue;
            }
            if (foundHook != null && hook != defaultHook) {
                continue;
            }
            if ((foundHook = hook) == defaultHook) {
                break;
            }

        }

        if (foundHook == null) {
            this.getLogger().info("Cloud not find supported placeholder plugin, disabling plugin.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.supplier = new DefaultPlaceholderSupplier();

        switch (foundHook) {
            case PLACEHOLDERAPI:
                new PapiPlaceholderHook(this.supplier).register();
                break;
            default:
                throw new AssertionError();
        }
        this.getLogger().info("Hooked into: " + foundHook.name());
    }

    public void setSupplier(PlaceholderSupplier supplier) {
        if (supplier == null) {
            throw new NullPointerException("Supplier cannot be null");
        }
        this.supplier = supplier;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
