package com.example.myapplication2.Diary;
// version 2020/10/25
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class Guidor {
    private final boolean debug = true;
    private DBHelper DBHelper;
    private JSONArray how_option = new JSONArray(), taste = new JSONArray(),
            visual = new JSONArray(), smell = new JSONArray(), tactile = new JSONArray(), hearing = new JSONArray();
    private ArrayList<String> howSeq = new ArrayList<>();
    private String mood_option, tag_option, what_option, who_option, when_option, why_option, where_option, diary, previous_patternNo;
    private boolean prologue, error=false;
    private ArrayList<String> propSeq = new ArrayList<>(), diaryContentNoSeq = new ArrayList<>();
    Guidor(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        init();
        this.DBHelper = new DBHelper(context, name, factory, version);
    }
    private void init(){
        this.prologue = true;
        this.mood_option = "";
        this.tag_option = "";
        this.what_option = "";
        this.who_option = "";
        this.when_option = "";
        this.why_option = "";
        this.where_option = "";
        try{
            this.taste.put(0, "味覺");
            this.visual.put(0, "視覺");
            this.smell.put(0, "嗅覺");
            this.tactile.put(0, "觸覺");
            this.hearing.put(0, "聽覺");
            this.how_option.put(0, this.taste);
            this.how_option.put(1, this.visual);
            this.how_option.put(2, this.smell);
            this.how_option.put(3, this.tactile);
            this.how_option.put(4, this.hearing);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.diary = "";
    }
    private boolean seqAuth(String prop){
        for (int i=0; i<propSeq.indexOf(prop); i++){
            if (propSeq.get(i).equals("")){return false;}
        }
        return true;
    }
    public Guidor setMood(String mood){
        this.mood_option = mood;
        return this;
    }
    public Guidor setTag(String tag){
        this.tag_option = tag;
        return this;
    }
    public Guidor setWhat(String what){
        if (propSeq.indexOf("what") == -1)
            propSeq.add("what");
        this.what_option = what;
        return this;
    }
    public Guidor setWho(String who){
        if (propSeq.indexOf("who") == -1)
            propSeq.add("who");
        this.who_option = who;
        return this;
    }
    public Guidor setWhen(String when){
        if (propSeq.indexOf("when") == -1)
            propSeq.add("when");
        this.when_option = when;
        return this;
    }
    public Guidor setWhy(String why){
        if (propSeq.indexOf("why") == -1)
            propSeq.add("why");
        this.why_option = why;
        return this;
    }
    public Guidor setWhere(String where){
        if (propSeq.indexOf("where") == -1)
            propSeq.add("where");
        this.where_option = where;
        return this;
    }
    public Guidor setHow(String sense, String how){
        if (propSeq.indexOf("how") == -1)
            propSeq.add("how");
        try{
            switch (sense){
                case "味覺":
                    if (howSeq.indexOf("味覺") == -1)
                        howSeq.add("味覺");
                    taste.put(taste.length(), how);
                    break;
                case "視覺":
                    if (howSeq.indexOf("視覺") == -1)
                        howSeq.add("視覺");
                    visual.put(visual.length(), how);
                    break;
                case "嗅覺":
                    if (howSeq.indexOf("嗅覺") == -1)
                        howSeq.add("嗅覺");
                    smell.put(smell.length(), how);
                    break;
                case "觸覺":
                    if (howSeq.indexOf("觸覺") == -1)
                        howSeq.add("觸覺");
                    tactile.put(tactile.length(), how);
                    break;
                case "聽覺":
                    if (howSeq.indexOf("聽覺") == -1)
                        howSeq.add("聽覺");
                    hearing.put(hearing.length(), how);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }
    public void setDiary(String diary){
        this.diary = diary;
    }
    public void setPrologue(boolean flag){
        this.prologue = flag;
    }
    public void preQuestion(){
        if (propSeq.isEmpty())
            return;
        propSeq.remove(propSeq.size()-1);
        while(propSeq.size() < diaryContentNoSeq.size()){
            diaryContentNoSeq.remove(diaryContentNoSeq.size()-1);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDiary(){
        if (error)
            return "the fucking error";
        if (this.diary.equals("") && prologue)
            addPrologue();
        if (propSeq.size() == diaryContentNoSeq.size())
            return this.diary;
        if (propSeq.size() > diaryContentNoSeq.size())
            createDiary();
        if (propSeq.size() < diaryContentNoSeq.size())
            recreateDiary();

        return this.diary;
    }
    public void clearDiary(){
        diaryContentNoSeq.clear();
        propSeq.clear();
        this.diary = "";
    }
    private void recreateDiary(){}
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void createDiary(){
        ArrayList<String> addDiaryContentNoSeq = new ArrayList<>();
        while(propSeq.size() > diaryContentNoSeq.size()){
            String index = getPatternIndex();
            if (error)
                return;
            previous_patternNo = index;
            diaryContentNoSeq.add(index);
            addDiaryContentNoSeq.add(index);
            if (debug)
                Log.d("prop diaryContentNoSeq", propSeq.size() + String.valueOf(diaryContentNoSeq.size()) + " -> " + index);
        }
        for (String s : diaryContentNoSeq){
            if (debug)
                Log.d("diaryContentNoSeq", s);
        }
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        Cursor cursor = null;
        sb.append(this.diary);
        for (int i=propSeq.size()-addDiaryContentNoSeq.size(); i<propSeq.size(); i++){
            String index = "", pattern = "", replace = "", punctuation = "";
            ArrayList<String> punctuations = new ArrayList<>();

            if (propSeq.get(i).equals("how")){
                String[] how_pattern = diaryContentNoSeq.get(i).split("_");

                for (int k=0; k<how_pattern.length; k++){
                    // append punctuation
                    if (i != 0){
                        String sentencePatternNo = "";
                        if (k!=0) {
                            if (debug)
                                Log.d("how_pattern[k - 1]", how_pattern[k - 1]);
                            sentencePatternNo = how_pattern[k - 1];
                        }
                        else
                            sentencePatternNo = diaryContentNoSeq.get(i-1);
                        if (debug)
                            Log.d("diaryContentNoSeq", diaryContentNoSeq.get(i-1));
                        if (debug)
                            Log.d("sentencePatternNo", sentencePatternNo);
                        if(tag_option.equals("旅遊")){
                            sb.append("，");
                        }
                        try{
                            do{
                                punctuations.add(cursor.getString(0));
                            }while(cursor.moveToNext());
                            punctuation = choosePunctuation(punctuations.get((int)(Math.random()*punctuations.size())));
                            sb.append(punctuation);
                        }catch(CursorIndexOutOfBoundsException e){
                            sb.append("，");
                        }
                    }
                    // get pattern
                    index = how_pattern[k];
                    cursor = db.rawQuery("SELECT pattern\n" +
                            "FROM sentence_pattern\n" +
                            "WHERE sentencePatternNo = '" + index + "'", null);
                    cursor.moveToFirst();
                    pattern = cursor.getString(0);
                    if (pattern.contains("_multiOption_")){
                        StringBuilder replace_how = new StringBuilder();
                        switch (howSeq.get(0)){
                            case "味覺":
                                for (int p=1; p<taste.length(); p++){
                                    try {
                                        replace_how.append(taste.get(p));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (p != taste.length()-1){
                                        replace_how.append("、");
                                    }
                                }
                                break;
                            case "視覺":
                                for (int p=1; p<visual.length(); p++){
                                    try {
                                        replace_how.append(visual.get(p));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (p != visual.length()-1){
                                        replace_how.append("、");
                                    }
                                }
                                break;
                            case "嗅覺":
                                for (int p=1; p<smell.length(); p++){
                                    try {
                                        replace_how.append(smell.get(p));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (p != smell.length()-1){
                                        replace_how.append("、");
                                    }
                                }
                                break;
                            case "觸覺":
                                for (int p=1; p<tactile.length(); p++){
                                    try {
                                        replace_how.append(tactile.get(p));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (p != tactile.length()-1){
                                        replace_how.append("、");
                                    }
                                }
                                break;
                            case "聽覺":
                                for (int p=1; p<hearing.length(); p++){
                                    try {
                                        replace_how.append(hearing.get(p));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (p != hearing.length()-1){
                                        replace_how.append("、");
                                    }
                                }
                                break;
                            default:
                                replace_how.append("錯誤");
                        }
                        howSeq.remove(0);
                        sb.append(pattern.replace("_multiOption_", replace_how.toString()));
                    }
                }
            }else{
                index = diaryContentNoSeq.get(i);
                // append punctuation
                if (i != 0){
                    cursor = db.rawQuery("SELECT punctuationMark\n" +
                            "FROM pattern_link\n" +
                            "WHERE sentencePatternNo = " + diaryContentNoSeq.get(i-1) + " AND nextPattern = " + index + "", null);
                    cursor.moveToFirst();
                    if (debug)
                        Log.d("NiCe", "SELECT punctuationMark\n" +
                            "FROM pattern_link\n" +
                            "WHERE sentencePatternNo = " + diaryContentNoSeq.get(i-1) + " AND nextPattern = " + index + "");
                    do{
                        punctuations.add(cursor.getString(0));
                    }while(cursor.moveToNext());
                    punctuation = choosePunctuation(punctuations.get((int)(Math.random()*punctuations.size())));
                    sb.append(punctuation);
                }
                // get pattern
                cursor = db.rawQuery("SELECT pattern\n" +
                        "FROM sentence_pattern\n" +
                        "WHERE sentencePatternNo = '" + index + "'", null);
                cursor.moveToFirst();
                if (debug)
                    Log.d("NiCe", "SELECT pattern\n" +
                        "FROM sentence_pattern\n" +
                        "WHERE sentencePatternNo = '" + index + "'");
                pattern = cursor.getString(0);

                switch (propSeq.get(i)){
                    case "what":
                        replace = what_option;
                        break;
                    case "who":
                        replace = who_option;
                        break;
                    case "when":
                        replace = when_option;
                        break;
                    case "why":
                        replace = why_option;
                        break;
                    case "where":
                        replace = where_option;
                        break;
                }
                sb.append(pattern.replace("_option_", replace));
            }
        }
        sb.append("。");
        this.diary = sb.toString();
        assert cursor != null;
        cursor.close();
    }
    private String getOption(String prop){
        switch (prop){
            case "what":
                return what_option;
            case "why":
                return why_option;
            case "when":
                return when_option;
            case "who":
                return who_option;
            case "where":
                return where_option;
        }
        return null;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getPatternIndex(){
        String ret = "", option = "", index = "-1";
        ArrayList<String> indexes = new ArrayList<>();
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        Cursor cursor = null;
        String prop = propSeq.get(diaryContentNoSeq.size());
        option = getOption(prop);
        boolean first=true;
        if (prop.equals("how")){
            // 5 sense
            for (int i=0; i<how_option.length(); i++) {
                StringBuilder like = new StringBuilder();
                String optionClass = "";
                if (debug)
                    Log.d("FOR迴圈", String.valueOf(i));
                try {
                    for (int k=1; k<how_option.getJSONArray(i).length(); k++){
                        if (k==1){
                            like.append("%");
                        }
                        like.append("_option_");
                        like.append(k);
                        like.append("%");
                    }
                    optionClass = how_option.getJSONArray(i).getString(0);
                    if (debug)
                        Log.d("how_option", optionClass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                switch (optionClass){
                    case "味覺":
                        if (taste.length() == 1)
                            continue;
                        break;
                    case "視覺":
                        if (visual.length() == 1)
                            continue;
                        break;
                    case "嗅覺":
                        if (smell.length() == 1)
                            continue;
                        break;
                    case "觸覺":
                        if (tactile.length() == 1)
                            continue;
                        break;
                    case "聽覺":
                        if (hearing.length() == 1)
                            continue;
                        break;
                }
                // get pattern index
                cursor = db.rawQuery("SELECT sentencePatternNo\n" +
                        "FROM sentence_pattern\n" +
                        "WHERE sentencePatternNo IN (\n" +
                        "\tSELECT sentencePatternNo\n" +
                        "\tFROM pattern_index\n" +
                        "\tWHERE optionNo = (\n" +
                        "\t\tSELECT optionNo\n" +
                        "\t\tFROM `option`\t\n" +
                        "\t\tWHERE questionClassNo = (\n" +
                        "\t\t\tSELECT questionClassNo\n" +
                        "\t\t\tFROM questionclass\n" +
                        "\t\t\tWHERE questionNo = (\n" +
                        "\t\t\t\tSELECT questionNo\n" +
                        "\t\t\t\tFROM question\n" +
                        "\t\t\t\tWHERE mood = '" + mood_option + "' AND tag = '" + tag_option + "'\n" +
                        "\t\t\t)\n" +
                        "\t\t\tAND questionClass = 'how'\n" +
                        "\t\t)\n" +
                        "\t\tAND optionClass = '" + optionClass + "'\n" +
                        "\t)\n" +
                        ")\n" +
                        "AND pattern LIKE '%_multiOption_%' OR pattern LIKE '" + like.toString() + "'", null);
                if (debug)
                    Log.d("NiCe", "SELECT sentencePatternNo\n" +
                        "FROM sentence_pattern\n" +
                        "WHERE sentencePatternNo IN (\n" +
                        "\tSELECT sentencePatternNo\n" +
                        "\tFROM pattern_index\n" +
                        "\tWHERE optionNo = (\n" +
                        "\t\tSELECT optionNo\n" +
                        "\t\tFROM `option`\t\n" +
                        "\t\tWHERE questionClassNo = (\n" +
                        "\t\t\tSELECT questionClassNo\n" +
                        "\t\t\tFROM questionclass\n" +
                        "\t\t\tWHERE questionNo = (\n" +
                        "\t\t\t\tSELECT questionNo\n" +
                        "\t\t\t\tFROM question\n" +
                        "\t\t\t\tWHERE mood = '" + mood_option + "' AND tag = '" + tag_option + "'\n" +
                        "\t\t\t)\n" +
                        "\t\t\tAND questionClass = 'how'\n" +
                        "\t\t)\n" +
                        "\t\tAND optionClass = '" + optionClass + "'\n" +
                        "\t)\n" +
                        ")\n" +
                        "AND pattern LIKE '%_multiOption_%' OR pattern LIKE '" + like.toString() + "'");
                cursor.moveToFirst();
                do {
                    indexes.add(cursor.getString(0));
                }while(cursor.moveToNext());

                if(diaryContentNoSeq.size()>=1 && first && !tag_option.equals("旅遊")){
                    first=false;
                    StringBuilder selectedIndexes = new StringBuilder();
                    for (int j=0; j<indexes.size(); j++){
                        selectedIndexes.append("'").append(indexes.get(j)).append("'").append(", ");
                    }
                    selectedIndexes.deleteCharAt(selectedIndexes.length()-2);
                    indexes.clear();
                    cursor = db.rawQuery("SELECT nextPattern\n" +
                            "    FROM pattern_link\n" +
                            "    WHERE nextPattern in (\n" +
                            "    \t" + selectedIndexes.toString() + " \n" +
                            "    )\n" +
                            "    AND sentencePatternNo IN (\n" +
                            "    \t" + previous_patternNo + "\n" +
                            "    )", null);
                    Log.d("NiCe", "SELECT nextPattern\n" +
                            "    FROM pattern_link\n" +
                            "    WHERE nextPattern in (\n" +
                            "    \t" + selectedIndexes.toString() + " \n" +
                            "    )\n" +
                            "    AND sentencePatternNo IN (\n" +
                            "    \t" + previous_patternNo + "\n" +
                            "    )");
                    if (cursor.getCount() == 0){
                        String callback_value = recursiveFixPatternSeq(diaryContentNoSeq.size()-1, selectedIndexes.toString(), selectedIndexes.toString(), null);
                        switch (callback_value){
                            case "-1":
                            case "-2":
                            case "-3":
                            case "-4":
                                error = true;
                                return callback_value;
                        }
                        ret += callback_value;
                        if (i != how_option.length()-1)
                            ret += "_";
                        indexes.clear();
                    }else{
                        cursor.moveToFirst();
                        do{
                            indexes.add(cursor.getString(0));
                            if (debug)
                                Log.d("indexes.add", cursor.getString(0));
                        }while(cursor.moveToNext());

                        int randomIndex = (int)(Math.random()*indexes.size());
                        index = indexes.get(randomIndex);
                        ret += index;
                        if (i != how_option.length()-1)
                            ret += "_";
                        indexes.clear();
                    }
                }else{
                    int randomIndex = (int)(Math.random()*indexes.size());
                    index = indexes.get(randomIndex);
                    ret += index;
                    if (i != how_option.length()-1)
                        ret += "_";
                    indexes.clear();
                }

            }

        }else {
            int randomIndex;
            boolean fixed = false;
            // get pattern index
            cursor = db.rawQuery("SELECT sentencePatternNo\n" +
                    "FROM `pattern_index`\n" +
                    "WHERE optionNo = (\n" +
                    "\tSELECT optionNo\n" +
                    "\tFROM `option`\n" +
                    "\tWHERE questionClassNo = (\n" +
                    "\t\tSELECT questionClassNo\n" +
                    "\t\tFROM questionclass\n" +
                    "\t\tWHERE questionNo = (\n" +
                    "\t\t\tSELECT questionNo\n" +
                    "\t\t\tFROM question\n" +
                    "\t\t\tWHERE mood = '" + mood_option + "' AND tag = '" + tag_option + "'\n" +
                    "\t\t)\n" +
                    "\t\tAND questionClass = '" + prop + "'\n" +
                    "\t)\n" +
                    "\tAND optionClass = '" + option + "'\n" +
                    ")", null);
            cursor.moveToFirst();
            if (debug)
                Log.d("NiCe", "SELECT sentencePatternNo\n" +
                    "FROM `pattern_index`\n" +
                    "WHERE optionNo = (\n" +
                    "\tSELECT optionNo\n" +
                    "\tFROM `option`\n" +
                    "\tWHERE questionClassNo = (\n" +
                    "\t\tSELECT questionClassNo\n" +
                    "\t\tFROM questionclass\n" +
                    "\t\tWHERE questionNo = (\n" +
                    "\t\t\tSELECT questionNo\n" +
                    "\t\t\tFROM question\n" +
                    "\t\t\tWHERE mood = '" + mood_option + "' AND tag = '" + tag_option + "'\n" +
                    "\t\t)\n" +
                    "\t\tAND questionClass = '" + prop + "'\n" +
                    "\t)\n" +
                    "\tAND optionClass = '" + option + "'\n" +
                    ")");
            do {
                indexes.add(cursor.getString(0));
                if (debug)
                    Log.d("indexes.add", cursor.getString(0));
            }while(cursor.moveToNext());
            if (diaryContentNoSeq.size() >= 1){
                if (!propSeq.get(diaryContentNoSeq.size()-1).equals("how")){
                    StringBuilder existProp = new StringBuilder(), selectedIndexes = new StringBuilder();
                    for (int i=0; i<diaryContentNoSeq.size(); i++){
                        existProp.append("'").append(propSeq.get(i)).append("'").append(", ");
                    }
                    for (int i=0; i<indexes.size(); i++){
                        selectedIndexes.append("'").append(indexes.get(i)).append("'").append(", ");
                    }
                    existProp.deleteCharAt(existProp.length()-2);
                    selectedIndexes.deleteCharAt(selectedIndexes.length()-2);
                    indexes.clear();
                    cursor = db.rawQuery("SELECT nextPattern\n" +
                            "    FROM pattern_link\n" +
                            "    WHERE nextPattern in (\n" +
                            "    \t" + selectedIndexes.toString() + " \n" +
                            "    )\n" +
                            "    AND sentencePatternNo IN (\n" +
                            "    \t" + previous_patternNo + "\n" +
                            "    )", null);
                    if (debug)
                        Log.d("NiCe", "SELECT nextPattern\n" +
                                "    FROM pattern_link\n" +
                                "    WHERE nextPattern in (\n" +
                                "    \t" + selectedIndexes.toString() + "\n" +
                                "    )\n" +
                                "    AND sentencePatternNo IN (\n" +
                                "    \t" + previous_patternNo + "\n" +
                                "    )");
                    if (cursor.getCount() == 0){
                        String callback_value = recursiveFixPatternSeq(diaryContentNoSeq.size()-1, selectedIndexes.toString(), selectedIndexes.toString(), null);
                        switch (callback_value){
                            case "-1":
                            case "-2":
                            case "-3":
                            case "-4":
                                error = true;
                        }
                        return callback_value;
                    }else{
                        cursor.moveToFirst();
                        do{
                            indexes.add(cursor.getString(0));
                            if (debug)
                                Log.d("indexes.add", cursor.getString(0));
                        }while(cursor.moveToNext());
                    }
                }
            }
            randomIndex = (int)(Math.random()*indexes.size());
            index = indexes.get(randomIndex);
            ret = index;
        }
        assert cursor != null;
        cursor.close();
        return ret;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String fixPatternSeq(int start_position, String target_patternNos) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        Cursor cursor;
        String callback_value, return_value;
        String index = null;
        callback_value = recursiveFixPatternSeq(start_position, target_patternNos, target_patternNos, null);
        switch (callback_value){
            case "-1":
            case "-2":
            case "-3":
            case "-4":
                return callback_value;
            default:
                return callback_value;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String recursiveFixPatternSeq(int position, String target_patternNos, String next_patternNos, Map<Integer, ArrayList<String>> nodeSet){
        if (nodeSet == null){
            nodeSet = new HashMap<>();
        }
        int callback_value;
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        ArrayList<String> thisSentencePatternNo_list = new ArrayList<>();
        StringBuilder sb_thisSentencePatternNo_list_tmp = new StringBuilder();
        StringBuilder sb_thisSentencePatternNo_list = new StringBuilder();
        Cursor cursor;
        String prop = propSeq.get(position);
        String option = getOption(propSeq.get(position));
        // select sentencePatternNos as nodes at this position
        cursor = db.rawQuery("SELECT sentencePatternNo\n" +
                "FROM `pattern_index`\n" +
                "WHERE optionNo = (\n" +
                "\tSELECT optionNo\n" +
                "\tFROM `option`\n" +
                "\tWHERE questionClassNo = (\n" +
                "\t\tSELECT questionClassNo\n" +
                "\t\tFROM questionclass\n" +
                "\t\tWHERE questionNo = (\n" +
                "\t\t\tSELECT questionNo\n" +
                "\t\t\tFROM question\n" +
                "\t\t\tWHERE mood = '" + mood_option + "' AND tag = '" + tag_option + "'\n" +
                "\t\t)\n" +
                "\t\tAND questionClass = '" + prop + "'\n" +
                "\t)\n" +
                "\tAND optionClass = '" + option + "'\n" +
                ")", null);
        if (cursor.getCount() == 0) return "-1";
        cursor.moveToFirst();
        do {
            thisSentencePatternNo_list.add(cursor.getString(0));
        }while(cursor.moveToNext());
        for (String s : thisSentencePatternNo_list) sb_thisSentencePatternNo_list_tmp.append("'").append(s).append("'").append(", ");
        sb_thisSentencePatternNo_list_tmp.deleteCharAt(sb_thisSentencePatternNo_list_tmp.length()-2);
        thisSentencePatternNo_list.clear();
        if (position >= 1){
            cursor = db.rawQuery("SELECT DISTINCT nextPattern\n" +
                    "    FROM pattern_link\n" +
                    "    WHERE nextPattern in (\n" +
                    "    \t" + sb_thisSentencePatternNo_list_tmp.toString() + " \n" +
                    "    )\n" +
                    "    AND sentencePatternNo IN (\n" +
                    "    \t" + diaryContentNoSeq.get(position-1) + "\n" +
                    "    )", null);
            if (cursor.getCount() == 0) return "-2";
        }
        cursor.moveToFirst();
        do {
            thisSentencePatternNo_list.add(cursor.getString(0));
            if (debug)
                Log.d("fix_indexes.this.add", cursor.getString(0));
        }while(cursor.moveToNext());
        for (String s : thisSentencePatternNo_list) sb_thisSentencePatternNo_list.append("'").append(s).append("'").append(", ");
        sb_thisSentencePatternNo_list.deleteCharAt(sb_thisSentencePatternNo_list.length()-2);
        cursor = db.rawQuery("SELECT DISTINCT sentencePatternNo\n" +
                "    FROM pattern_link\n" +
                "    WHERE nextPattern in (\n" +
                "    \t" + next_patternNos + " \n" +
                "    )\n" +
                "    AND sentencePatternNo IN (\n" +
                "    \t" + sb_thisSentencePatternNo_list.toString() + "\n" +
                "    )", null);
        if (cursor.getCount() > 0){
            ArrayList<String> tmp_list = new ArrayList<>();
            cursor.moveToFirst();
            do{
                tmp_list.add(cursor.getString(0));
            }while(cursor.moveToNext());
            nodeSet.put(position, tmp_list);
            ArrayList<Integer> sort_position = new ArrayList<>(nodeSet.keySet());
            Map<Integer, ArrayList<String>> linkable_nodeSet = new HashMap<>();
            Collections.sort(sort_position);
            boolean node_connected=true;
            String nextPatternNos = null, thisPatternNos;
            for (int i=0; i<=sort_position.size(); i++){
                ArrayList<String> patternNos_list = new ArrayList<>();
                ArrayList<String> nextPatternNos_list = new ArrayList<>();
                thisPatternNos = nextPatternNos;
                StringBuilder sb = new StringBuilder();
                if (i == sort_position.size()){
                    nextPatternNos = target_patternNos;
                }else{
                    for (String node : nodeSet.get(sort_position.get(i)))
                        sb.append("'").append(node).append("'").append(", ");
                    sb.deleteCharAt(sb.length()-2);
                    nextPatternNos = sb.toString();
                }
                if (i==0)
                    continue;
                cursor = db.rawQuery("SELECT DISTINCT sentencePatternNo, nextPattern\n" +
                        "    FROM pattern_link\n" +
                        "    WHERE nextPattern in (\n" +
                        "    \t" + nextPatternNos + " \n" +
                        "    )\n" +
                        "    AND sentencePatternNo IN (\n" +
                        "    \t" + thisPatternNos + "\n" +
                        "    )", null);
                Log.d("NiCe", "SELECT sentencePatternNo\n" +
                        "    FROM pattern_link\n" +
                        "    WHERE nextPattern in (\n" +
                        "    \t" + nextPatternNos + " \n" +
                        "    )\n" +
                        "    AND sentencePatternNo IN (\n" +
                        "    \t" + thisPatternNos + "\n" +
                        "    )");
                if (cursor.getCount() > 0){
                    cursor.moveToFirst();
                    sb = new StringBuilder();
                    do {
                        patternNos_list.add(cursor.getString(0));
                        nextPatternNos_list.add(cursor.getString(1));
                        if (debug)
                            Log.d("fix_indexes.i"+(i-1)+".add", cursor.getString(0));
                    }while(cursor.moveToNext());
                    for (String s : patternNos_list)
                        sb.append("'").append(s).append("'").append(",");
                    sb.deleteCharAt(sb.length()-1);
                    linkable_nodeSet.put(i-1, patternNos_list);
                    if (i == sort_position.size()){
                        linkable_nodeSet.put(i, nextPatternNos_list);
                    }
                    nextPatternNos = sb.toString();
                }else{
                    node_connected = false;
                    break;
                }
            }
            if (node_connected){
                for (Integer i : linkable_nodeSet.keySet()){
                    for (String s : linkable_nodeSet.get(i)){
                        Log.d("linkable_node_"+i, ""+s);
                    }
                }
                // pick one route to the end node and apply the route
                ArrayList<Integer> route = new ArrayList<>();
                String v=null;
                int randomIndex;
                for (int z=0; z<=sort_position.size(); z++){
                    Log.d("NiCe", ""+z);
                    randomIndex = (int)(Math.random()*linkable_nodeSet.get(z).size());
                    v = linkable_nodeSet.get(z).get(randomIndex);
                    if (z!=sort_position.size() && !v.equals(diaryContentNoSeq.get(sort_position.get(z)))){
                        Log.d("NiCe", "before: "+sort_position.get(z)+"-"+diaryContentNoSeq.get(sort_position.get(z)));
                        diaryContentNoSeq.remove(diaryContentNoSeq.get(sort_position.get(z)));
                        diaryContentNoSeq.add(sort_position.get(z), v);
                        Log.d("NiCe", "modified: "+sort_position.get(z)+"-"+diaryContentNoSeq.get(sort_position.get(z)));
                    }
                }
                Log.d("NiCe", "final pick: "+v);
                return v;
            }else{
                if (position == 0){
                    return "-4";
                }
                nodeSet.put(position, thisSentencePatternNo_list);
                return recursiveFixPatternSeq(position-1, target_patternNos, sb_thisSentencePatternNo_list.toString(), nodeSet);
            }
        }else{
            if (position == 0){
                return "-3";
            }
            nodeSet.put(position, thisSentencePatternNo_list);
            return recursiveFixPatternSeq(position-1, target_patternNos, sb_thisSentencePatternNo_list.toString(), nodeSet);
        }
    }
    private String choosePunctuation(String s){
        switch (s){
            case "1":
                return "，";
            case "2":
                return "。";
            case "3":
                return "；";
            case "4":
                return "...";
            default:
                return "";
        }
    }
    private void addPrologue(){
        String[] mood_pattern = {"我今天", "今天", "今天我"};
        String[] mood_pattern_1 = {};
        switch (mood_option){
            case "心情1":
                mood_pattern_1 = new String[]{"心情很棒", "心情很讚", "心情很好", "心情特別好", "心情十分愉悅", "心情十分雀躍", "心情很愉快"};
                break;
            case "心情2":
                mood_pattern_1 = new String[]{"心情不錯", "心情還不錯", "心情還蠻不錯的", "心情還挺讚的", "心情有點開心", "心情有點快樂"};
                break;
            case "心情3":
                mood_pattern_1 = new String[]{"心情普普通通", "心情很普通", "心情沒什麼特別的", "心情就跟平常一樣", "心情沒有特別的起伏"};
                break;
            case "心情4":
                mood_pattern_1 = new String[]{"心情不太好", "心情不太OK", "心情有點差", "心情有些鬱鬱寡歡", "心情有點糟", "心情有點差"};
                break;
            case "心情5":
                mood_pattern_1 = new String[]{"心情很差", "心情很爛", "心情很糟糕", "心情很不好", "心情十分鬱悶"};
                break;
        }
        this.diary = mood_pattern[(int)(Math.random()*mood_pattern.length)] + mood_pattern_1[(int)(Math.random()*mood_pattern_1.length)] + "，";
    }
}
