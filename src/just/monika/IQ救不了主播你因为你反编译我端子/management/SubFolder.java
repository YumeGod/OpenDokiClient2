/*
 * Decompiled with CFR 0_122.
 */
package just.monika.IQ救不了主播你因为你反编译我端子.management;

public enum SubFolder {
    ModuleJars("ModulePacks"),
    Module("Modules"),
    Alt("Alts"),
    Other("Other");
    
    private final String folderName;

    private SubFolder(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderName() {
        return this.folderName;
    }
}

