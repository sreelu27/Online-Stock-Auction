package pluginconfig.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

import javax.inject.Inject;

import org.plugface.core.PluginContext;
import org.plugface.core.PluginManager;
import org.plugface.core.PluginRef;
import org.plugface.core.PluginSource;
import org.plugface.core.annotations.Plugin;
import org.plugface.core.factory.PluginManagers;
import org.plugface.core.internal.AnnotationProcessor;
import org.plugface.core.internal.DependencyResolver;
import org.plugface.core.internal.di.MissingDependencyException;
import org.plugface.core.internal.di.Node;

public class CustomDefaultPluginManager implements PluginManager {

    protected final PluginContext context;
    protected final AnnotationProcessor annotationProcessor;
    protected final DependencyResolver dependencyResolver;

    /**
     * Ideally not for public usage. Use {@link PluginManagers#defaultPluginManager()} instead for a fully
     * configured <code>DefaultPluginManager</code>
     *
     * @param context             the {@link PluginContext} to use
     * @param annotationProcessor the {@link AnnotationProcessor} to use
     * @param dependencyResolver  the {@link DependencyResolver} to use
     */
    public CustomDefaultPluginManager(PluginContext context, AnnotationProcessor annotationProcessor, DependencyResolver dependencyResolver) {
        this.context = context;
        this.annotationProcessor = annotationProcessor;
        this.dependencyResolver = dependencyResolver;
    }

    @Override
    public <T> void register(T plugin) {
        context.addPlugin(plugin);
    }

    @Override
    public <T> void register(String name, T plugin) {
        context.addPlugin(name, plugin);
    }

    @Override
    public <T> T getPlugin(String name) {
        return context.getPlugin(name);
    }

    @Override
    public <T> T getPlugin(Class<T> pluginClass) {
        return context.getPlugin(pluginClass);
    }

    @Override
    public Collection<PluginRef> getAllPlugins() {
        return context.getAllPlugins();
    }

    @Override
    public <T> T removePlugin(String name) {
        return context.removePlugin(name);
    }

    @Override
    public <T> T removePlugin(T plugin) {
        return context.removePlugin(plugin);
    }

    @Override
    public Collection<Object> loadPlugins(PluginSource source) throws Exception {
        final Collection<Class<?>> pluginClasses = Objects.requireNonNull(source, "Plugin Source cannot be null").load();
        final Collection<Object> loaded = new ArrayList<>();

        if (pluginClasses.isEmpty()) {
            return loaded;
        }

        final Iterator<Class<?>> itr = pluginClasses.iterator();
        while (itr.hasNext()) {
            Class<?> pluginClass = itr.next();
            if (pluginClass.getAnnotation(Plugin.class) == null) {
                itr.remove();
                continue;
            }
            if (context.hasPlugin(pluginClass)) {
                itr.remove();
            }
        }

        Collection<Node<?>> nodes = dependencyResolver.resolve(pluginClasses);
        createPlugins(nodes, loaded);
        return loaded;
    }

    private void createPlugins(Collection<Node<?>> nodes, Collection<Object> loaded) {
        for (Node<?> node : nodes) {
            createPlugin(node, loaded);
        }
    }

    private void createPlugin(Node<?> node, Collection<Object> loaded) {
        final Class<?> refClass = node.getRefClass();
        if (refClass.getAnnotation(Plugin.class) == null) {
            return;
        }
        if (!context.hasPlugin(refClass)) {
            final Object plugin = Objects.requireNonNull(create(refClass), "Could not create plugin of type " + refClass.getName());
            context.addPlugin(plugin);
            loaded.add(plugin);
        }
    }

    protected Object create(Class<?> refClass) {
        final Constructor<?>[] constructors = refClass.getConstructors();
        for (Constructor<?> constructor : constructors) {

            try {
                final Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == 0) {
                    return refClass.newInstance();
                } else {
                    if (constructor.getAnnotation(Inject.class) != null) {
                        final Object[] deps = new Object[parameterTypes.length];
                        for (int i = 0; i < parameterTypes.length; i++) {
                            final Class<?> param = parameterTypes[i];
                            final Object dep = context.getPlugin(param);
                            if (dep != null) {
                                deps[i] = dep;
                            } else {
                                throw new MissingDependencyException("No plugin found for type %s while it is required by %s", param.getName(), refClass.getName());
                            }


                        }
                        return constructor.newInstance(deps);

                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | IllegalArgumentException ignored) {
            }
        }
        return null;
    }

}