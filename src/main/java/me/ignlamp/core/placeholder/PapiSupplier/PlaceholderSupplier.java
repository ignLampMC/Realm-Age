package me.ignlamp.core.placeholder.PapiSupplier;
@FunctionalInterface
public interface PlaceholderSupplier {
    String IDENTIFIER = "realmage";

    String onPlaceholderRequest(String argument);
}
