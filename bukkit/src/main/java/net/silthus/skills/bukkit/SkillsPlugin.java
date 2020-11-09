package net.silthus.skills.bukkit;

import io.ebean.Database;
import kr.entree.spigradle.annotations.PluginMain;
import net.silthus.ebean.Config;
import net.silthus.ebean.Driver;
import net.silthus.ebean.EbeanWrapper;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

@PluginMain
public class SkillsPlugin extends JavaPlugin {

    private Database database;

    public SkillsPlugin() {}

    public SkillsPlugin(
            JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {

        this.database = connectToDatabase();
    }

    private Database connectToDatabase() {

        FileConfiguration config = getConfig();

        Config dbConfig = Config.builder()
                .entities(

                )
                .driverPath(new File("lib"))
                .autoDownloadDriver(true)
                .migrations(getClass())
                .url(config.getString("database.url", "jdbc:h2:~/skills.db"))
                .username(config.getString("database.username", "sa"))
                .password(config.getString("database.password", "sa"))
                .driver(config.getString("database.driver", Driver.H2))
                .build();
        return new EbeanWrapper(dbConfig).getDatabase();
    }
}