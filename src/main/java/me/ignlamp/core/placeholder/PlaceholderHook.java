package me.ignlamp.core.placeholder;

public enum PlaceholderHook {
    PLACEHOLDERAPI("me.clip.placeholderapi.expansion.PlaceholderExpansion");

    private final String hookClass;
    PlaceholderHook(String hookClass) {
        this.hookClass = hookClass;
    }

    public boolean isEnabled() {
        try {
            Class.forName(this.hookClass);
        } catch (ClassNotFoundException var2) {
            return false;
        }
        return true;
    }

    public static PlaceholderHook parse(String input) {
        if (input == null) {
            throw new NullPointerException("Input cannot be null");
        }

        for (PlaceholderHook hook : PlaceholderHook.values()) {
            if (hook.name().equalsIgnoreCase(input)) {
                return hook;
            }

        }
        throw new IllegalArgumentException("No hook found for: " + input);
    }
}

