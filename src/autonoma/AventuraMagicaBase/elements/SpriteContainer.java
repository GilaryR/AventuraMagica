/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.AventuraMagicaBase.elements;

import java.util.ArrayList;

/**
 *
 * @author jgiugtiñut
 */
public class SpriteContainer {
    private ArrayList<Sprite> sprites = new ArrayList<>();

    public void add(Sprite s) {
        sprites.add(s);
    }

    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}