/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.kieckegard.samples.marker;


/**
 *
 * @author Pedro Arthur <pfernandesvasconcelos@gmail.com>
 */
public class MarkerProperties {
    
    private BoundingBox drawableRectangle;
    private Integer distanceToTheTop;
    private Integer distanceToTheLeft;
    
    public static final Integer AUTO_DISTANCE = -1;

    public static class Builder {

        private BoundingBox drawableRectangle;
        private Integer distanceToTheTop;
        private Integer distanceToTheLeft;

        private Builder() {
        }

        public Builder drawableRectangle(final BoundingBox value) {
            this.drawableRectangle = value;
            return this;
        }

        public Builder distanceToTheTop(final Integer value) {
            this.distanceToTheTop = value;
            return this;
        }

        public Builder distanceToTheLeft(final Integer value) {
            this.distanceToTheLeft = value;
            return this;
        }

        public MarkerProperties build() {
            return new io.github.kieckegard.samples.marker.MarkerProperties(drawableRectangle, distanceToTheTop, distanceToTheLeft);
        }
    }

    public static MarkerProperties.Builder builder() {
        return new MarkerProperties.Builder();
    }

    private MarkerProperties(final BoundingBox drawableRectangle, final Integer distanceToTheTop, final Integer distanceToTheLeft) {
        this.drawableRectangle = drawableRectangle;
        this.distanceToTheTop = distanceToTheTop;
        this.distanceToTheLeft = distanceToTheLeft;
    }

    /**
     * Retorna as dimensoes da parte "desenhavel"
     * na imagem do marcador.
     * @return 
     */
    public BoundingBox getDrawableRectangle() {
        return drawableRectangle;
    }

    /**
     * Retorna a distancia do fim da parte desenhavel
     * para a borda inferior da imagem do marcador.
     * @return 
     */
    public Integer getDistanceToTheTop() {
        return distanceToTheTop;
    }

    /**
     * Retorna a distancia do fim da parte desenhavel para a borda 
     * da esquerda da imagem do marcador.
     * @return 
     */
    public Integer getDistanceToTheLeft() {
        return distanceToTheLeft;
    }

    @Override
    public String toString() {
        return "Marker{" + "drawableRectangle=" + drawableRectangle + ", distanceToTheTop=" + distanceToTheTop + ", distanceToTheLeft=" + distanceToTheLeft + '}';
    }
}
