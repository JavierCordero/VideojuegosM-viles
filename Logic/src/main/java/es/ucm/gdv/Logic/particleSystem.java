package es.ucm.gdv.Logic;

import java.lang.reflect.ParameterizedType;
import java.util.Random;

import es.ucm.gdv.engine.Graphics;
import es.ucm.gdv.engine.Image;
import es.ucm.gdv.engine.Rect;
import es.ucm.gdv.engine.ResourceManager;
import es.ucm.gdv.engine.Sprite;

class particle{
    boolean active = false;
    Sprite _mySprite;
    float velX, velY;
    float timeAlive = 0;

    public particle(Sprite sprite){
        _mySprite = sprite;
    }

    public Sprite getSprite(){
        return _mySprite;
    }

    public void setVelocity(float X, float Y) {velX = X; velY = Y;}

    public void setTimeAlive(float delta) { timeAlive += delta; }

    public void restartTime() { timeAlive = 0; }
}

public class particleSystem {

    ResourceManager _rM;
    Graphics _G;
    int numParticles = 10;
    Rect original;
    int decremento = 100;

    particle[] whiteParticles = new particle[numParticles];
    particle[] blackParticles = new particle[numParticles];

    particleSystem(ResourceManager resourceManager, Graphics graphics){
        _rM = resourceManager;
        _G = graphics;

        Image balls = _rM.getImage("balls");
        for(int i = 0; i < numParticles; i++){

            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, 0, balls.getHeight() / 2),
                    "whiteParticle"+i, new Random().nextInt(70+120));

            _rM.createSpriteFromImage("balls",
                    new Rect(0, 1280 / 10, balls.getHeight() / 2, balls.getHeight()),
                    "blackParticle"+i, new Random().nextInt(70+120));

            whiteParticles[i] = new particle(_rM.getSprite("whiteParticle"+i));
            blackParticles[i] = new particle(_rM.getSprite("blackParticle"+i));
        }

        Sprite p =  whiteParticles[0].getSprite(); //Particula cualquiera
        original = new Rect((_G.getWidth()/2)-p.getSpriteWidth()/2,
                (_G.getWidth()/2)+p.getSpriteWidth()/2,
                1100-p.getSpriteHeight()/2,
                (1100+p.getSpriteHeight()/2));
    }


    void ActivaParticulas(playState.ball destruida) {
        int activas = 0;
        int i = 0;

        particle[] particles;

        if(destruida.get_myColor() == "blackPlayer")
            particles = blackParticles;
        else particles = whiteParticles;

        while(i < particles.length && activas < 5){
            if(!particles[i].active){
                //particles[i] = eligeColorParticula(destruida, particles[i]);
                particles[i].active = true;
                particles[i].restartTime();
                particles[i].setVelocity(new Random().nextInt(150+150)-150, new Random().nextInt(300+200)*-1);

                Sprite p =  particles[i].getSprite();

                int tam = new Random().nextInt(p.getSpriteWidth()/2);

                particles[i].getSprite().set_destRect(new Rect((_G.getWidth()/2)-p.getSpriteWidth()/2,
                        (_G.getWidth()/2)+p.getSpriteWidth()/2-tam,
                        1100-p.getSpriteHeight()/2,
                        (1100+p.getSpriteHeight()/2)-tam));

                activas++;
            }
            i++;
        }
    }

    void ActualizaPartuculas(float deltaTime){
        for(int i = 0; i < numParticles; i++) {
            if (whiteParticles[i].active) {
                Sprite particle = whiteParticles[i].getSprite();
                Rect pRect = particle.get_destRect();

                particle.set_destRect(new Rect(pRect.get_left() + (whiteParticles[i].velX * deltaTime),
                        pRect.get_right() + whiteParticles[i].velX * deltaTime,
                        pRect.get_top() + (whiteParticles[i].velY * deltaTime),
                        pRect.get_bottom() + whiteParticles[i].velY * deltaTime));

                whiteParticles[i].setVelocity((whiteParticles[i].velX), (whiteParticles[i].velY)+(400*deltaTime));

                whiteParticles[i].setTimeAlive(deltaTime);

                if(whiteParticles[i].timeAlive > 1){
                    whiteParticles[i].active = false;
                    whiteParticles[i].getSprite().set_destRect(original);
                }
            }

            if (blackParticles[i].active) {
                Sprite particle = blackParticles[i].getSprite();
                Rect pRect = particle.get_destRect();

                particle.set_destRect(new Rect(pRect.get_left() + (blackParticles[i].velX * deltaTime),
                        pRect.get_right() + (blackParticles[i].velX * deltaTime),
                        pRect.get_top() + (blackParticles[i].velY * deltaTime),
                        pRect.get_bottom() + (blackParticles[i].velY * deltaTime)));

                blackParticles[i].setVelocity((blackParticles[i].velX), (blackParticles[i].velY)+(400*deltaTime));

                blackParticles[i].setTimeAlive(deltaTime);

                if(blackParticles[i].timeAlive > 1){
                    blackParticles[i].active = false;
                    blackParticles[i].getSprite().set_destRect(original);
                }
            }
        }
    }

    void renderPartuculas(){
        for(int i = 0; i < numParticles; i++){
            if(whiteParticles[i].active) {
                whiteParticles[i].getSprite().draw(_G, whiteParticles[i].getSprite().get_destRect());
            }
            if(blackParticles[i].active) {
                blackParticles[i].getSprite().draw(_G, blackParticles[i].getSprite().get_destRect());
            }
        }
    }
}
