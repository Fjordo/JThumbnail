package io.github.makbn.thumbnailer;

import io.github.makbn.thumbnailer.listener.ThumbnailListener;
import io.github.makbn.thumbnailer.model.ThumbnailCandidate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by Mehdi Akbarian-Rastaghi on 9/30/18
 */

public class TestFolder {

    private static Logger mLog = LogManager.getLogger("TestFolder");

    public static void main(String[] args) {

        try {
            AppSettings.init(args);
            Thumbnailer.start();
            String folderPath = "/home/fjordo/input";
            File in = new File(folderPath);
            for (File file : in.listFiles()) {
                processSingleFile(file);
            }
        } catch (IOException e) {
            mLog.error(e);
        }

    }

    private static void processSingleFile(File fileToProcess) {
        if(fileToProcess.exists()) {
            ThumbnailCandidate candidate = new ThumbnailCandidate(in,"unique_code");

            Thumbnailer.createThumbnail(candidate, new ThumbnailListener() {
                @Override
                public void onThumbnailReady(String hash, File thumbnail) {
                    mLog.info("FILE created at : " + thumbnail.getAbsolutePath());
                }

                @Override
                public void onThumbnailFailed(String hash, String message, int code) {
                    mLog.warn("Jthumbnail failed!");
                }
            });
        }
    }
}
