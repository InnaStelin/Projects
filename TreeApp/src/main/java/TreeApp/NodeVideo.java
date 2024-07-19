package TreeApp;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import javax.swing.*;


// VLC media player (previously the VideoLAN Client and commonly known as simply VLC)
// is a free and open-source, portable, cross-platform media player software and streaming media server
// It is used in this class to play video clips specified by tree node data.
public class NodeVideo implements NodeVideoUtils {
    public static void showNodeVideo(JEditorPane htmlPane, String videoFilePath)  {

        JInternalFrame videoPanel = new JInternalFrame();
        videoPanel.setLocation(0, 0);
        videoPanel.setSize(660,400);
        videoPanel.setVisible(true);
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

        videoPanel.add(mediaPlayerComponent);
        htmlPane.add(videoPanel);

        // Replace forward slash with two backslashes in file path (each \ needs escape character \)
        // This is required by VLC media player component

        String videoFilePathFormatted = videoFilePath.replace("/", "\\\\");
        mediaPlayerComponent.mediaPlayer().media().start(videoFilePathFormatted);

        //To do: internet videos
        //String videoFilePathFormatted= "https://www.youtube.com/watch?v=fuk2Qlzk17U";
    }
}
