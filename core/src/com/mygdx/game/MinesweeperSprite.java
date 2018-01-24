package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MinesweeperSprite extends ApplicationAdapter {
    // what to do
    // create a number set that will define if a square is a mine or not with a limit
    // with the mines declared then set the number of

    SpriteBatch batch;
    Texture imgMine, imgGO, imgExit;
    Sprite sprBad, sprGO, sprExit;
    boolean bExit;
    int nY = 0, nX = 0, nBackground = 0;
    int nHeight;
    int nWidth;
    ShapeRenderer shape;
    int arnBombnum[][];
    int arnGridstate[][];

    public MinesweeperSprite() {
        this.arnBombnum = new int[nHeight][nWidth];
        this.arnGridstate = new int[nHeight][nWidth];
    }

    @Override
    public void create() {
        bExit = false;
        batch = new SpriteBatch();
        imgMine = new Texture("Mine.png");
        imgGO = new Texture("Gameover.png");
        imgExit = new Texture("Exit.png");
        sprBad = new Sprite(imgMine);
        sprBad.setPosition(nX, nY);
        sprBad.setSize(100, 100);
        sprGO = new Sprite(imgGO);
        sprGO.setPosition(0, 400);
        sprExit = new Sprite(imgExit);
        sprExit.setSize(100, 100);
        sprExit.setPosition(600, 100);
        shape = new ShapeRenderer();
        nHeight = Gdx.graphics.getHeight();
        nWidth = Gdx.graphics.getWidth();
    }

    @Override
    public void render() {
        click();
        Gdx.gl.glClearColor(0.5f, 0.25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin(); //everything is drawn and used
        if (nBackground == 1) {
            sprBad.draw(batch);
            sprGO.draw(batch);
            sprExit.draw(batch);
        }
        if (nBackground == 0) {
            shape();
        }
        if (bExit == true) {
            Gdx.app.exit();
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        imgMine.dispose();
    }

    public void click() {
        if (Gdx.input.isTouched()) {
            //System.out.println(Gdx.input.getX() + " " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
            if (sprBad.getBoundingRectangle().contains(Gdx.input.getX(), nHeight - Gdx.input.getY())) {
                nBackground = 1;
                // System.out.println("Gimme yo fakking money");
            }
            if (sprExit.getBoundingRectangle().contains(Gdx.input.getX(), nHeight - Gdx.input.getY())) {
                bExit = true;
            }
        }
    }

    public void shape() {
        float r, g, b;
        r = 0;
        g = 0;
        b = 0;
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(r, g, b, 1);
        for (int y = 0; y < nHeight; y += 20) {
            for (int x = 0; x < nWidth; x += 20) {
                //arnGridstate [][] = 
                //System.out.println(x + " , " + y);
                //r = (float) Math.random();
                g = (float) Math.random();
                //b = (float) Math.random();
                shape.setColor(r, g, b, 1);

                shape.rect(5 * x, 5 * y, 100, 100);
//(5*the x(from the for loop),5*the y(from the other for loop,height of cube,width of cube)
            }
        }
        shape.end();
    }
}
