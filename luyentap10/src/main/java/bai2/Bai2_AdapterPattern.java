package bai2;
public class Bai2_AdapterPattern {
    public static void main(String[] args) {
        MediaPlayer player = new MediaAdapter();

        player.play("mp3", "music.mp3");
        player.play("vlc", "video.vlc");
        player.play("mp4", "movie.mp4");
    }
}
