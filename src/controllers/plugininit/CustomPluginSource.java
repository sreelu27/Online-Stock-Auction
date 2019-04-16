package controllers.plugininit;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.servlet.http.HttpServlet;

import org.plugface.core.PluginSource;
import org.plugface.core.internal.PluginClassLoader;

public class CustomPluginSource
{

    /**
     * Load plugins from JAR files located at the given path
     *
     * @param pluginDirectoryPath the path to the directory where the JAR files are located
     * @return a list of loaded {@link Class} objects, never null
     */
    public static PluginSource jarSource(final String pluginDirectoryPath,final HttpServlet servlet) {
        return jarSource(URI.create(pluginDirectoryPath),servlet);
    }

    /**
     * Load plugins from JAR files located at the given {@link URI}
     *
     * @param pluginUri the {@link URI} to the directory where the JAR files are located
     * @return a list of loaded {@link Class} objects, never null
     */
    public static PluginSource jarSource(final URI pluginUri,final HttpServlet servlet) {
        return new PluginSource() {
            @Override
            public Collection<Class<?>> load() throws IOException, ClassNotFoundException {
                final ArrayList<Class<?>> plugins = new ArrayList<>();
                final Path path = Paths.get(pluginUri);
                if (!Files.exists(path)) {
                    throw new IllegalArgumentException("Path " + pluginUri + " does not exist");
                }

                if (!Files.isDirectory(path)) {
                    throw new IllegalArgumentException("Path " + pluginUri + " is not a directory");
                }
                final Map<Path, URL> jarUrls = new HashMap<>();
                for (Path filePath : Files.newDirectoryStream(path)) {
                    if (filePath.getFileName().toString().endsWith(".jar")) {
                        jarUrls.put(filePath, filePath.toUri().toURL());
                    }
                }
                final CustomPluginClassLoader cl = new CustomPluginClassLoader(jarUrls.values().toArray(new URL[]{}),servlet.getClass().getClassLoader());
                for (Path jarPaht: jarUrls.keySet()) {
                    final File file = jarPaht.toAbsolutePath().toFile();
                    final JarFile jar = new JarFile(file);
                    for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();) {
                        final JarEntry entry = entries.nextElement();
                        if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                            continue;
                        }
                        String className = entry.getName().substring(0, entry.getName().length() - 6);
                        className = className.replace('/', '.');
                        Class<?> clazz = Class.forName(className, true, cl);
                        plugins.add(clazz);
                    }
                }
                return plugins;
            }
        };

    }

    /**
     * Load plugins from the given list of {@link Class}. Mostly useful for testing and debugging
     *
     * @param classes a list of classes to load
     * @return the same list given as input, never null
     */
    public static PluginSource classList(final Class<?>... classes) {
        return new PluginSource() {
            @Override
            public Collection<Class<?>> load() throws Exception {
                return new ArrayList<Class<?>>() {{
                    addAll(Arrays.asList(classes));
                }};
            }

        };
    }
}