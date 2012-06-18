/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.hub.emffrag.bm.benchmark;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>EMF Frag Candidates</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.hub.emffrag.bm.benchmark.BenchmarkPackage#getEMFFragCandidates()
 * @model
 * @generated
 */
public enum EMFFragCandidates implements Enumerator {
    /**
	 * The '<em><b>Xmi</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #XMI_VALUE
	 * @generated
	 * @ordered
	 */
    XMI(0, "xmi", "xmi"),

    /**
	 * The '<em><b>Cdo</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #CDO_VALUE
	 * @generated
	 * @ordered
	 */
    CDO(1, "cdo", "cdo"),

    /**
	 * The '<em><b>Morsa</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #MORSA_VALUE
	 * @generated
	 * @ordered
	 */
    MORSA(2, "morsa", "morsa"),

    /**
	 * The '<em><b>Emffrag</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #EMFFRAG_VALUE
	 * @generated
	 * @ordered
	 */
    EMFFRAG(3, "emffrag", "emffrag");

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright 2012 Markus Scheidgen\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.";

				/**
	 * The '<em><b>Xmi</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Xmi</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #XMI
	 * @model name="xmi"
	 * @generated
	 * @ordered
	 */
    public static final int XMI_VALUE = 0;

    /**
	 * The '<em><b>Cdo</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Cdo</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #CDO
	 * @model name="cdo"
	 * @generated
	 * @ordered
	 */
    public static final int CDO_VALUE = 1;

    /**
	 * The '<em><b>Morsa</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Morsa</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #MORSA
	 * @model name="morsa"
	 * @generated
	 * @ordered
	 */
    public static final int MORSA_VALUE = 2;

    /**
	 * The '<em><b>Emffrag</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Emffrag</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #EMFFRAG
	 * @model name="emffrag"
	 * @generated
	 * @ordered
	 */
    public static final int EMFFRAG_VALUE = 3;

    /**
	 * An array of all the '<em><b>EMF Frag Candidates</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final EMFFragCandidates[] VALUES_ARRAY =
        new EMFFragCandidates[] {
			XMI,
			CDO,
			MORSA,
			EMFFRAG,
		};

    /**
	 * A public read-only list of all the '<em><b>EMF Frag Candidates</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List<EMFFragCandidates> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>EMF Frag Candidates</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static EMFFragCandidates get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EMFFragCandidates result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>EMF Frag Candidates</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static EMFFragCandidates getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EMFFragCandidates result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>EMF Frag Candidates</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static EMFFragCandidates get(int value) {
		switch (value) {
			case XMI_VALUE: return XMI;
			case CDO_VALUE: return CDO;
			case MORSA_VALUE: return MORSA;
			case EMFFRAG_VALUE: return EMFFRAG;
		}
		return null;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private final int value;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private final String name;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private final String literal;

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EMFFragCandidates(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getValue() {
	  return value;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getName() {
	  return name;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getLiteral() {
	  return literal;
	}

    /**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String toString() {
		return literal;
	}
    
} //EMFFragCandidates
