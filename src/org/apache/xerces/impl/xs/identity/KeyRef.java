/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.xerces.impl.xs.identity;

import org.apache.xerces.xs.XSIDCDefinition;

/**
 * Schema key reference identity constraint.
 *
 * @xerces.internal 
 *
 * @author Andy Clark, IBM
 * @version $Id: KeyRef.java 730488 2008-12-31 21:58:19Z knoaman $
 */
public class KeyRef
    extends IdentityConstraint {

    //
    // Data
    //

    /** The key (or unique) being referred to. */
    protected final UniqueOrKey fKey;

    //
    // Constructors
    //

    /** Constructs a keyref with the specified name. */
    public KeyRef(String namespace, String identityConstraintName,
                  UniqueOrKey key) {
        super(namespace, identityConstraintName);
        fKey = key;
        type = IC_KEYREF;
    } // <init>(String,String,String)

    //
    // Public methods
    //

    /** Returns the key being referred to.  */
    public UniqueOrKey getKey() {
        return fKey;
    } // getKey(): int

    /**
     * {referenced key} Required if {identity-constraint category} is keyref,
     * forbidden otherwise. An identity-constraint definition with
     * {identity-constraint category} equal to key or unique.
     */
    public XSIDCDefinition getRefKey() {
        return fKey;
    }

} // class KeyRef