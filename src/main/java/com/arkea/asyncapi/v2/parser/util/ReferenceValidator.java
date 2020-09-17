package com.arkea.asyncapi.v2.parser.util;

import com.arkea.asyncapi.v2.models.Components;


public enum ReferenceValidator {

    schemas {
        @Override
        public boolean validateComponent(final Components components,final String reference) {
            return components.getSchemas().containsKey(reference);
        }
    },
    parameters {
        @Override
        public boolean validateComponent(final Components components,final String reference) {
            return components.getParameters().containsKey(reference);
        }
    },
//    examples {
//        @Override
//        public boolean validateComponent(Components components,String reference) {
//            return components.getExamples().containsKey(reference);
//        }
//    },
    securitySchemes {
        @Override
        public boolean validateComponent(final Components components,final String reference) {
            return components.getSecuritySchemes().containsKey(reference);
        }
    };


    public abstract boolean validateComponent(Components components,String reference);
}
