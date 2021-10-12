package com.example.dictionary_graphic;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTS {
    private String name;
    private Voice voice;

    public TTS(String name) {
        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        this.name = name;
        this.voice = VoiceManager.getInstance().getVoice(this.name);
        this.voice.allocate();
    }

    public void say(String word) {
        this.voice.speak(word);
    }
}
