package app.modele.Eleve;

import app.modele.Attaquant;
import app.modele.BFS;
import app.modele.Environnement;
import app.modele.Utile;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Alexandre extends Attaquant {
    int musique = 0;
    MediaPlayer mediaPlayer;
    public Alexandre(Environnement env, int x, int y){
        super(env, 85,6,x,y,"Alexandre");
    }

    @Override
    public void agit() {
        BFS.BFS(this,env);
        if (env.getNbTours()%30 == 0) {
            env.getActeurs().add(new Telio(this.env, Utile.toPixel(Utile.toTexture(this.getX())) + 25 + (int) (Math.random() * 15), Utile.toPixel(Utile.toTexture(this.getY())) + 25 + (int) (Math.random() * 15),"Nyan"));
            if (musique == 0) {
                final File file = new File("src/resources/musique/NyanCat.mp3");
                final Media media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                musique++;
            }
        }
    }

    public void stopMusique(){
        mediaPlayer.stop();
    }
}
