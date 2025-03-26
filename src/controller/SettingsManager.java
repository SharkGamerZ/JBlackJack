//package controller;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.json.JSONObject;
////import org.json.JSONParser;
////import org.json.parser.ParseException;
//
//public class SettingsManager {
//    // Singleton instance
//    private static SettingsManager instance;
//
//    // Settings cache
//    private JSONObject settings;
//
//    // Default settings
//    private static final Map<String, Object> DEFAULT_SETTINGS = new HashMap<>();
//    static {
//        DEFAULT_SETTINGS.put("mutated", false);
//        DEFAULT_SETTINGS.put("volume", 50);
//        DEFAULT_SETTINGS.put("difficulty", "normal");
//        DEFAULT_SETTINGS.put("showTutorial", true);
//        // Add more default settings as needed
//    }
//
//    /**
//     * Private constructor for singleton pattern
//     */
//    private SettingsManager() {
//        loadSettings();
//    }
//
//    /**
//     * Get the singleton instance
//     */
//    public static SettingsManager getInstance() {
//        if (instance == null) {
//            instance = new SettingsManager();
//        }
//        return instance;
//    }
//
//    /**
//     * Load settings from file
//     */
//    private void loadSettings() {
//        // If file doesn't exist, create default settings
//        if (!file.exists()) {
//            settings = new JSONObject();
////            settings.putAll(DEFAULT_SETTINGS);
////            saveSettings();
//            return;
//        }
//
//        // Parse existing settings
////        JSONParser parser = new JSONParser();
////        try (FileReader reader = new FileReader(file)) {
////            settings = (JSONObject) parser.parse(reader);
////
////            // Check for missing settings and apply defaults
////            for (Map.Entry<String, Object> entry : DEFAULT_SETTINGS.entrySet()) {
////                if (!settings.containsKey(entry.getKey())) {
////                    settings.put(entry.getKey(), entry.getValue());
////                }
////            }
////
////        } catch (IOException | ParseException e) {
////            System.err.println("Error loading settings: " + e.getMessage());
////            settings = new JSONObject();
////            settings.putAll(DEFAULT_SETTINGS);
////        }
//    }
//
//    /**
//     * Save settings to file
//     */
////    public void saveSettings() {
////        try (FileWriter writer = new FileWriter(SETTINGS_FILE)) {
////            writer.write(settings.toJSONString());
////            writer.flush();
////        } catch (IOException e) {
////            System.err.println("Error saving settings: " + e.getMessage());
////        }
////    }
//
//    /**
//     * Get a boolean setting
//     */
//    public boolean getBoolean(String key) {
//        Object value = settings.get(key);
//        return value != null ? (Boolean) value : (Boolean) DEFAULT_SETTINGS.getOrDefault(key, false);
//    }
//
//    /**
//     * Get an integer setting
//     */
//    public int getInt(String key) {
//        Object value = settings.get(key);
//        if (value instanceof Long) {
//            return ((Long) value).intValue();
//        }
//        return value != null ? (Integer) value : (Integer) DEFAULT_SETTINGS.getOrDefault(key, 0);
//    }
//
//    /**
//     * Get a string setting
//     */
//    public String getString(String key) {
//        Object value = settings.get(key);
//        return value != null ? (String) value : (String) DEFAULT_SETTINGS.getOrDefault(key, "");
//    }
//
//    /**
//     * Set a setting value
//     */
//    public void setSetting(String key, Object value) {
//        settings.put(key, value);
////        saveSettings(); // Save immediately after any change
//    }
//}
