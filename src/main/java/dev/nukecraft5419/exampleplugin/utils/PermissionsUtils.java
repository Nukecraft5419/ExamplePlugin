package dev.nukecraft5419.exampleplugin.utils;

public final class PermissionsUtils {

    /**
     * Private constructor to prevent instantiation of this utility class.
     *
     * @throws UnsupportedOperationException if an attempt is made to instantiate this class.
     */
    private PermissionsUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /** Permission for /example hello */
    public static final String COMMAND_HELLO = "exampleplugin.commands.hello";

    /** Permission for /example help */
    public static final String COMMAND_HELP = "exampleplugin.commands.help";

    /** Permission for /example get */
    public static final String COMMAND_GET = "exampleplugin.commands.get";

    /** Permission for /example reload */
    public static final String COMMAND_RELOAD = "exampleplugin.commands.reload";
}
