/*
 * Copyright (c) 2002, 2008, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package ws.daley.genealogy.components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ActionMapUIResource;

/**
 * An ActionMap that populates its contents as necessary. The
 * contents are populated by invoking the <code>loadActionMap</code>
 * method on the passed in Object.
 *
 * @author Scott Violet
 */
@SuppressWarnings("serial")
public class MyLazyActionMap extends ActionMapUIResource {
    /**
     * Object to invoke <code>loadActionMap</code> on. This may be
     * a Class object.
     */
    private transient Object _loader;

    /**
     * Installs an ActionMap that will be populated by invoking the
     * <code>loadActionMap</code> method on the specified Class
     * when necessary.
     * <p>
     * This should be used if the ActionMap can be shared.
     *
     * @param c JComponent to install the ActionMap on.
     * @param loaderClass Class object that gets loadActionMap invoked
     *                    on.
     * @param defaultsKey Key to use to defaults table to check for
     *        existing map and what resulting Map will be registered on.
     */
    public static void installLazyActionMap(JComponent c, @SuppressWarnings("rawtypes") Class loaderClass,
                                     String defaultsKey) {
        ActionMap map = (ActionMap)UIManager.get(defaultsKey);
        if (map == null) {
            map = new MyLazyActionMap(loaderClass);
            UIManager.getLookAndFeelDefaults().put(defaultsKey, map);
        }
        SwingUtilities.replaceUIActionMap(c, map);
    }

    /**
     * Returns an ActionMap that will be populated by invoking the
     * <code>loadActionMap</code> method on the specified Class
     * when necessary.
     * <p>
     * This should be used if the ActionMap can be shared.
     *
     * @param c JComponent to install the ActionMap on.
     * @param loaderClass Class object that gets loadActionMap invoked
     *                    on.
     * @param defaultsKey Key to use to defaults table to check for
     *        existing map and what resulting Map will be registered on.
     */
    static ActionMap getActionMap(@SuppressWarnings("rawtypes") Class loaderClass,
                                  String defaultsKey) {
        ActionMap map = (ActionMap)UIManager.get(defaultsKey);
        if (map == null) {
            map = new MyLazyActionMap(loaderClass);
            UIManager.getLookAndFeelDefaults().put(defaultsKey, map);
        }
        return map;
    }


    private MyLazyActionMap(@SuppressWarnings("rawtypes") Class loader) {
        this._loader = loader;
    }

    public void put(Action action) {
        put(action.getValue(Action.NAME), action);
    }

    @Override
	public void put(Object key, Action action) {
        loadIfNecessary();
        super.put(key, action);
    }

    @Override
	public Action get(Object key) {
        loadIfNecessary();
        return super.get(key);
    }

    @Override
	public void remove(Object key) {
        loadIfNecessary();
        super.remove(key);
    }

    @Override
	public void clear() {
        loadIfNecessary();
        super.clear();
    }

    @Override
	public Object[] keys() {
        loadIfNecessary();
        return super.keys();
    }

    @Override
	public int size() {
        loadIfNecessary();
        return super.size();
    }

    @Override
	public Object[] allKeys() {
        loadIfNecessary();
        return super.allKeys();
    }

    @Override
	public void setParent(ActionMap map) {
        loadIfNecessary();
        super.setParent(map);
    }

    private void loadIfNecessary() {
        if (this._loader != null) {
            Object loader = this._loader;

            this._loader = null;
            Class<?> klass = (Class<?>)loader;
            try {
                Method method = klass.getDeclaredMethod("loadActionMap",
                                      new Class[] { MyLazyActionMap.class });
                method.invoke(klass, new Object[] { this });
            } catch (NoSuchMethodException nsme) {
                assert false : "MyLazyActionMap unable to load actions " +
                        klass;
            } catch (IllegalAccessException iae) {
                assert false : "MyLazyActionMap unable to load actions " +
                        iae;
            } catch (InvocationTargetException ite) {
                assert false : "MyLazyActionMap unable to load actions " +
                        ite;
            } catch (IllegalArgumentException iae) {
                assert false : "MyLazyActionMap unable to load actions " +
                        iae;
            }
        }
    }
}
