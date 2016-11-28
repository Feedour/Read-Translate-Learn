package ru.meeg.read_translate_learn;

/**
 * Created by meeg on 28.11.16.
 */

public class Word {
    private String engVersion;

    private String ruVersion; //временное решение, необходимое до начала работы с API

    public Word(String engVer, String rusVer){
        this.engVersion = engVer;
        this.ruVersion = rusVer;
    }

    public String getEngVersion(){
        return engVersion;
    }

    public String getRuVersion() {
        return ruVersion;
    }

    public void setRuVersion(String value){
        ruVersion = value;
    }
}
