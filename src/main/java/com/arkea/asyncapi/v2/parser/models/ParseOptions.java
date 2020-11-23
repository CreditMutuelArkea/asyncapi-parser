package com.arkea.asyncapi.v2.parser.models;

public class ParseOptions {

    private boolean resolve;

    private boolean resolveCombinators = true;

    private boolean resolveFully;

    private boolean flatten;

    private boolean flattenComposedSchemas;

    private boolean camelCaseFlattenNaming;

    private boolean skipMatches;

    public boolean isResolve() {
        return this.resolve;
    }

    public void setResolve(final boolean resolve) {
        this.resolve = resolve;
    }

    public boolean isResolveCombinators() {
        return this.resolveCombinators;
    }

    public void setResolveCombinators(final boolean resolveCombinators) {
        this.resolveCombinators = resolveCombinators;
    }

    public boolean isResolveFully() {
        return this.resolveFully;
    }

    public void setResolveFully(final boolean resolveFully) {
        this.resolveFully = resolveFully;
    }

    public boolean isFlatten() {
        return this.flatten;
    }

    public void setFlatten(final boolean flatten) {
        this.flatten = flatten;
    }

    public boolean isSkipMatches() {
        return this.skipMatches;
    }

    public void setSkipMatches(final boolean skipMatches) {
        this.skipMatches = skipMatches;
    }

    public boolean isFlattenComposedSchemas() {
        return this.flattenComposedSchemas;
    }

    public void setFlattenComposedSchemas(final boolean flattenComposedSchemas) {
        this.flattenComposedSchemas = flattenComposedSchemas;
    }

    public boolean isCamelCaseFlattenNaming() {
        return this.camelCaseFlattenNaming;
    }

    public void setCamelCaseFlattenNaming(final boolean camelCaseFlattenNaming) {
        this.camelCaseFlattenNaming = camelCaseFlattenNaming;
    }
}
