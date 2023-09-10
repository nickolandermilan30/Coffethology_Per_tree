package com.example.coffeetectapp;



import android.graphics.Bitmap;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavedResultsManager {
    private static List<SavedResult> historyList = new ArrayList<>();
    private static List<SavedResult> history2List = new ArrayList<>();
    private static List<SavedResult> history3List = new ArrayList<>();
    private static List<SavedResult> history4List = new ArrayList<>();
    private static List<SavedResult> history5List = new ArrayList<>();
    private static List<SavedResult> history6List = new ArrayList<>();




    private static List<SavedResult> savedResultsList = new ArrayList<>();
    private static Map<String, Integer> diseaseFrequencies = new HashMap<>();
    private static Map<String, Integer> diseaseSeverities = new HashMap<>();



    public static List<SavedResult> getSavedResultsList() {
        return savedResultsList;
    }

    public static void addSavedResult(SavedResult savedResult) {
        savedResultsList.add(0, savedResult);

        String diseaseName = savedResult.getDiseaseName();
        int severityLevel = SeverityConverter.convertToInteger(savedResult.getSeverityLevel());  // Convert to integer

        // Update disease frequencies
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            diseaseFrequencies.put(diseaseName, diseaseFrequencies.getOrDefault(diseaseName, 0) + 1);
        }

        // Update disease severities
        if (!diseaseSeverities.containsKey(diseaseName) || severityLevel > diseaseSeverities.get(diseaseName)) {
            diseaseSeverities.put(diseaseName, severityLevel);
        }

        // If savedResultsList size exceeds 10, remove the last item
        if (savedResultsList.size() > 10) {
            savedResultsList.remove(savedResultsList.size() - 1);
        }
    }

    public static String getMostFrequentAndSevereDisease() {
        String mostFrequentAndSevereDisease = null;
        int maxFrequency = 0;

        for (Map.Entry<String, Integer> entry : diseaseFrequencies.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentAndSevereDisease = entry.getKey();
            }
        }

        return mostFrequentAndSevereDisease;
    }

    public static int getSeverityForDisease(String diseaseName) {
        if (diseaseSeverities.containsKey(diseaseName)) {
            return diseaseSeverities.get(diseaseName);
        }
        return 0;  // Return a default severity if disease name is not found
    }


    public static void clearSavedResults() {
        savedResultsList.clear();
    }

    public static Bitmap getImageForMostFrequentDisease() {
        String mostFrequentDisease = SavedResultsManager.getMostFrequentAndSevereDisease();
        List<SavedResult> savedResults = SavedResultsManager.getSavedResultsList();

        for (SavedResult result : savedResults) {
            if (result.getDiseaseName().equals(mostFrequentDisease)) {
                return result.getImageBitmap();
            }
        }

        return null;

    }

    public static void addSavedResultToHistory2(SavedResult savedResult) {
        history2List.add(0, savedResult);

        // Check if `History2` exceeds the desired limit (e.g., 10)
        if (history2List.size() > 10) {
            history2List.remove(history2List.size() - 1);

            // Move the exceeding results to History3
            if (history3List.size() < 10) {
                SavedResult removedResult = history2List.remove(history2List.size() - 1);
                history3List.add(0, removedResult);
            }
        }
    }


    public static List<SavedResult> getHistoryList() {
        return historyList;
    }
    public static List<SavedResult> getHistory2List() {
        return history2List;
    }

    public static void addSavedResultToHistory(SavedResult savedResult) {
        if (historyList.size() < 10) {
            historyList.add(savedResult);
        } else if (history2List.size() < 10) {
            history2List.add(savedResult);
        } else {
            history3List.add(savedResult);
        }
    }

    public static int getSavedResultsCount() {
        return savedResultsList.size();
    }





    public static void clearHistory2Results() {
        history2List.clear();
    }


    public static int getSavedResultsCountHistory2() {
        return history2List.size();




    }

    public static void addSavedResultToHistory3(SavedResult result) {
        if (history3List.size() < 10) {
            history3List.add(result);
        }
    }

    public static void clearHistory3Results() {
        history3List.clear();
    }

    public static List<SavedResult> getHistory3List() {
        return history3List;
    }


    public static int getSavedResultsCountHistory3() {
        return history3List.size();
    }



    public static void addSavedResultToHistory4(SavedResult result) {
        if (history4List.size() < 10) {
            history4List.add(result);
        }
    }

    public static List<SavedResult> getHistory4List() {
        return history4List;
    }

    public static void clearHistory4Results() {
        history4List.clear();
    }

    public static int getSavedResultsCountHistory4() {
        return history4List.size();
    }




    public static void addSavedResultToHistory5(SavedResult result) {
        if (history5List.size() < 10) {
            history5List.add(result);
        }
    }

    public static void clearHistory5Results() {
        history5List.clear();
    }

    public static List<SavedResult> getHistory5List() {
        return history5List;
    }

    public static int getSavedResultsCountHistory5() {
        return history5List.size();
    }




    public static int getSavedResultsCountHistory6() {
        return history6List.size();
    }

    public static void addSavedResultToHistory6(SavedResult result) {
        if (history6List.size() < 10) {
            history6List.add(result);
        }
    }

    public static void clearHistory6Results() {
        history6List.clear();
    }

    public static List<SavedResult> getHistory6List() {
        return history6List;
    }
}
