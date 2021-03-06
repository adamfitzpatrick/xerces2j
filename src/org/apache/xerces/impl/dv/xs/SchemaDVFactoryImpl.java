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

package org.apache.xerces.impl.dv.xs;

import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.util.SymbolHash;

/**
 * the factory to create/return built-in schema DVs and create user-defined DVs
 * 
 * @xerces.internal 
 *
 * @author Neeraj Bajaj, Sun Microsystems, inc.
 * @author Sandy Gao, IBM
 *
 * @version $Id: SchemaDVFactoryImpl.java 985524 2010-08-14 16:16:36Z knoaman $
 */
public class SchemaDVFactoryImpl extends BaseSchemaDVFactory {

    static final SymbolHash fBuiltInTypes = new SymbolHash();
    
    static {
        createBuiltInTypes();
    }

    // create all built-in types
    static void createBuiltInTypes() {
        final String ENTITIES = "ENTITIES";
        final String ENTITY   = "ENTITY";
        final String NMTOKENS = "NMTOKENS";
        final String NMTOKEN  = "NMTOKEN";
        final String IDREFS   = "IDREFS";
        final String IDREF    = "IDREF";
        
        createBuiltInTypes(fBuiltInTypes, XSSimpleTypeDecl.fAnySimpleType);
        
        XSFacets facets = new XSFacets();
        facets.minLength = 1;

        // add ENTITIES
        final XSSimpleTypeDecl entityDV = (XSSimpleTypeDecl)fBuiltInTypes.get(ENTITY);
        XSSimpleTypeDecl tempDV = new XSSimpleTypeDecl(null, URI_SCHEMAFORSCHEMA, (short)0, entityDV, true, null);
        final XSSimpleTypeDecl entitiesDV = new XSSimpleTypeDecl(tempDV, ENTITIES, URI_SCHEMAFORSCHEMA, (short)0, false, null);
        entitiesDV.applyFacets1(facets, XSSimpleType.FACET_MINLENGTH, (short)0);
        fBuiltInTypes.put(ENTITIES, entitiesDV);

        // add NMTOKENS
        final XSSimpleTypeDecl nmtokenDV = (XSSimpleTypeDecl)fBuiltInTypes.get(NMTOKEN);
        tempDV = new XSSimpleTypeDecl(null, URI_SCHEMAFORSCHEMA, (short)0, nmtokenDV, true, null);
        final XSSimpleTypeDecl nmtokensDV = new XSSimpleTypeDecl(tempDV, NMTOKENS, URI_SCHEMAFORSCHEMA, (short)0, false, null);
        nmtokensDV.applyFacets1(facets, XSSimpleType.FACET_MINLENGTH, (short)0);
        fBuiltInTypes.put(NMTOKENS, nmtokensDV);
        
        // add IDREFS
        final XSSimpleTypeDecl idrefDV = (XSSimpleTypeDecl)fBuiltInTypes.get(IDREF);
        tempDV = new XSSimpleTypeDecl(null, URI_SCHEMAFORSCHEMA, (short)0, idrefDV, true, null);
        final XSSimpleTypeDecl idrefsDV = new XSSimpleTypeDecl(tempDV, IDREFS, URI_SCHEMAFORSCHEMA, (short)0, false, null);
        idrefsDV.applyFacets1(facets, XSSimpleType.FACET_MINLENGTH, (short)0);
        fBuiltInTypes.put(IDREFS, idrefsDV);

        // TODO: move specific 1.0 DV implementation from base
    } //createBuiltInTypes()

    /**
     * Get a built-in simple type of the given name
     * REVISIT: its still not decided within the Schema WG how to define the
     *          ur-types and if all simple types should be derived from a
     *          complex type, so as of now we ignore the fact that anySimpleType
     *          is derived from anyType, and pass 'null' as the base of
     *          anySimpleType. It needs to be changed as per the decision taken.
     *
     * @param name  the name of the datatype
     * @return      the datatype validator of the given name
     */
    public XSSimpleType getBuiltInType(String name) {
        return (XSSimpleType)fBuiltInTypes.get(name);
    }

    /**
     * get all built-in simple types, which are stored in a hashtable keyed by
     * the name
     *
     * @return      a hashtable which contains all built-in simple types
     */
    public SymbolHash getBuiltInTypes() {
        return (SymbolHash)fBuiltInTypes.makeClone();
    }

}//SchemaDVFactoryImpl
