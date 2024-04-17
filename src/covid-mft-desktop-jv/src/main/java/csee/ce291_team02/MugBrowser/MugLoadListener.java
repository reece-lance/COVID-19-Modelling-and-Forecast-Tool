package csee.ce291_team02.MugBrowser;

import com.codebrig.journey.JourneyLoader;

import java.io.File;

public class MugLoadListener extends JourneyLoader.JourneyLoaderListener {

    private Executable loadedEvent;

    public MugLoadListener(Executable loadedEvent) {
        this.loadedEvent = loadedEvent;
    }

    public void journeyLoaderStarted(String s, String s1) {

    }

    @Override
    public void usingNativeDirectory(File file) {

    }

    @Override
    public void determiningOS() {

    }

    @Override
    public void determinedOS(String s, int i) {

    }

    @Override
    public void downloadingNativeCEFFiles() {

    }

    @Override
    public void downloadedNativeCEFFiles() {

    }

    @Override
    public void extractingNativeCEFFiles() {

    }

    @Override
    public void extractedNativeCEFFiles() {

    }

    @Override
    public void loadingNativeCEFFiles() {

    }

    @Override
    public void loadedNativeCEFFiles() {

    }

    @Override
    public void loadingJCEF() {

    }

    @Override
    public void loadedJCEF() {

    }

    @Override
    public void journeyLoaderComplete() {
        this.loadedEvent.fire();
    }
}
