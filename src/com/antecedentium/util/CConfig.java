package com.antecedentium.util;

import com.antecedentium.AnteCedentium;
import org.bukkit.configuration.file.YamlConfiguration;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;


public class CConfig {
    public final File file;
    public final String folder;
    public final String path;
    private String fullPath;
    private YamlConfiguration yamlConfiguration;

    /** @apiNote Using snakeyaml. */
    public CConfig(String path, String folder) {
        this.folder = folder;
        this.path = path;
        this.fullPath = AnteCedentium.INSTANCE.getDataFolder().getPath() + ((folder == "") ? "" : ("\\" + folder));
        new File(fullPath).mkdirs();
        this.fullPath += "\\" + path;
        this.file = new File(this.fullPath);
        if(!file.exists()) try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }

        yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(fullPath);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void set(String path, Object value) { yamlConfiguration.set(path, value); }
    public Object get(String path) { return yamlConfiguration.get(path); }
    public YamlConfiguration getYaml() { return yamlConfiguration; }
    public void save() { try { yamlConfiguration.save(file); } catch (IOException e) { e.printStackTrace(); }}
}