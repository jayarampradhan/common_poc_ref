package com.uimirror.common.object;

import java.io.Serializable;

/**
 * <p>
 * This utility class will be helpful to read and modify the static resource
 * contents
 * 
 * @author Jayaram
 * @version 1.0
 * @category utill for the classified
 * 
 */
public final class ClassUtil implements Serializable {

    private static final long serialVersionUID = -9053404238963959946L;

    public ClassUtil() {
	super();
    }

    /**
     * <p>
     * This will check the object null behavior
     * 
     * @param o
     *            any object
     * @return true if null else false
     */
    public static boolean isNullObject(Object o) {

	if (o == null) {
	    return Boolean.TRUE;
	}

	return Boolean.FALSE;
    }

}
