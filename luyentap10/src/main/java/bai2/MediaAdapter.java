package bai2;
public class MediaAdapter implements MediaPlayer {
   private VlcPlayer vlcPlayer;
   public MediaAdapter() {
        vlcPlayer = new VlcPlayer();
    }
   @Override
   public void play(String audioType, String fileName) {
    if (audioType.equalsIgnoreCase("vlc")) {
            vlcPlayer.playVlc(fileName);
     } else if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
     } else {
            System.out.println("Format not supported");
      }
    }
}